package by.iTechArt.servlets;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;
import by.iTechArt.service.IBookService;
import by.iTechArt.service.IGenreService;
import by.iTechArt.service.impl.BookService;
import by.iTechArt.service.impl.GenreService;
import by.iTechArt.validator.BirthValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BookServlet extends HttpServlet {

  private final String UPLOAD_PATH = "/Users/polina/libraryImages/";
  static final Logger logger = LogManager.getLogger(BookServlet.class);
  private IBookService bookService;
  private IGenreService genreService;

  @Override
  public void init() throws ServletException {
    bookService = new BookService();
    genreService = new GenreService();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    checkAction(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    checkAction(request, response);
  }

  private void checkAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    switch (action) {
      case "new":
        showNewBookForm(request, response);
        break;
      case "insert":
        insertBook(request, response);
        break;
      case "delete":
        deleteBook(request, response);
        break;
      case "edit":
        showEditBookForm(request, response);
        break;
      case "update":
        updateBook(request, response);
        break;
      default:
        getBookList(request, response);
        break;
    }
  }

  private void insertBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String nameRu = request.getParameter("nameRu");
    String nameOrigin = request.getParameter("nameOrigin");
    Integer cost = Integer.parseInt(request.getParameter("cost"));
    int dayPrice = Integer.parseInt(request.getParameter("dayPrice"));
    int publicationYear = Integer.parseInt(request.getParameter("publicationYear"));
    int pages = Integer.parseInt(request.getParameter("pages"));
    String genresStringMassive[] = request.getParameterValues("selectedGenre");
    List<Genre> genreList = Arrays.stream(genresStringMassive).map(Genre::valueOf).collect(Collectors.toList());
    List<String> imageNames = imageUploadAndGetNames(request);
    List<Author> authorList = new ArrayList<>();
////// /////-getting authors///////////////////
    Book book = new Book(nameRu, nameOrigin, genreList, cost,
      authorList, imageNames, dayPrice, publicationYear, pages);
    try {
      bookService.addBook(book);
    } catch (DAOException | SQLException e) {
      logger.info(e.getMessage());
    }
    response.sendRedirect("/boolServlet/book?action=list");
  }

  private List<String> imageUploadAndGetNames(HttpServletRequest request){
  List<String> imageNames = new ArrayList<>();
    if(ServletFileUpload.isMultipartContent(request)){
      try{
        List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for(FileItem item : multiparts){
          if(!item.isFormField()){
            String name = new File(item.getName()).getName();
            item.write(new File(UPLOAD_PATH + name));
            imageNames.add(name);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
  }
    return imageNames;
  }

  private void showEditBookForm(HttpServletRequest request, HttpServletResponse response) {
  }

  private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
  }

  private void getBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getAttribute("booksFromServer") == null) {
      List<Book> allBooks = null;
      try {
        allBooks = bookService.showBookList();
      } catch (DAOException | SQLException e) {
        logger.info(e.getMessage());
      }
      request.setAttribute("booksFromServer", allBooks);
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/bookList.jsp");
    dispatcher.forward(request, response);
  }

  private void showNewBookForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    if (request.getAttribute("genreIdMap") == null) {
      Map<Integer, Genre> allIdsAndGenresMap = null;
      try {
        allIdsAndGenresMap = genreService.showIdGenreMap();
      } catch (DAOException | SQLException e) {
        logger.info(e.getMessage());
      }
      request.setAttribute("genreIdMap", allIdsAndGenresMap);
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/bookForm.jsp");
    dispatcher.forward(request, response);
  }
  private void updateBook(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    //////
  }
}
