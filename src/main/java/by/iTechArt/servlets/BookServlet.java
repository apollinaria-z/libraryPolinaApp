package by.iTechArt.servlets;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;
import by.iTechArt.service.*;
import by.iTechArt.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BookServlet extends HttpServlet {

  private final String UPLOAD_PATH_Book = "/Users/polina/libraryImages/1/";
  private final String UPLOAD_PATH_Author = "/Users/polina/libraryImages/2/";
  static final Logger logger = LogManager.getLogger(BookServlet.class);
  private IBookService bookService;
  private IGenreService genreService;
  private IAuthorService authorService;
  private IBookGenreService bookGenreService;
  private IBookAuthorService bookAuthorService;
  private IBookImageService bookImageService;

  @Override
  public void init() throws ServletException {
    bookService = new BookService();
    genreService = new GenreService();
    authorService = new AuthorService();
    bookGenreService = new BookGenreService();
    bookAuthorService = new BookAuthorService();
    bookImageService = new BookImageService();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      checkAction(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      checkAction(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void checkAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String action = request.getParameter("action");
    switch (action) {
      case "ask":
        askNumberOfAuthors(request, response);
        break;
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

  private void askNumberOfAuthors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/prepareForBookInsert.jsp");
    dispatcher.forward(request, response);

  }

  private void insertBook(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String nameRu = request.getParameter("nameRu");
    String nameOrigin = request.getParameter("nameOrigin");
    Integer cost = Integer.parseInt(request.getParameter("cost"));
    int dayPrice = Integer.parseInt(request.getParameter("dayPrice"));
    int publicationYear = Integer.parseInt(request.getParameter("publicationYear"));
    int pages = Integer.parseInt(request.getParameter("pages"));
    Book book = new Book(nameRu, nameOrigin, cost, dayPrice, publicationYear, pages);
    try {
      bookService.addBook(book);
    } catch (DAOException | SQLException e) {
      logger.info(e.getMessage());
    }
    String genresStringMassive[] = request.getParameterValues("selectedGenre");
    List<Genre> genreList = Arrays.stream(genresStringMassive).map(Genre::valueOf).collect(Collectors.toList());
    System.out.println(genreList.toString());
    book.setGenreList(genreList);
    try {
      bookGenreService.addBookGenrePairs(book);
    } catch (DAOException | SQLException e) {
      logger.info(e.getMessage());
    }
    List<String> imagePathsOfBook = new ArrayList<>();
    if (ServletFileUpload.isMultipartContent(request)) {
      try {
        List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        for (FileItem item : multiparts) {
          if (!item.isFormField()) {

            String name = new File(item.getName()).getName();
            String fileDirectoryInDB = UPLOAD_PATH_Book.concat(name);
            item.write(new File(fileDirectoryInDB));
            imagePathsOfBook.add(fileDirectoryInDB);
          }
        }
      } catch (DAOException | SQLException e) {
        logger.info(e.getMessage());
      }
    }
    book.setImagePathList(imagePathsOfBook);
    bookImageService.addBookImagePairs(book);
    List<Author> authorList = new ArrayList<>();
    int authorsNumber = Integer.parseInt((String) request.getParameter("authorNumber"));
    for (int i = 1; i <= authorsNumber; i++) {
      String firstname = request.getParameter("firstname" + i);
      String surname = request.getParameter("surname" + i);
      String imagePathAuthor = null;
      if (ServletFileUpload.isMultipartContent(request)) {
        try {
          List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
          for (FileItem item : multiparts) {
            if (!item.isFormField()) {
              String name = new File(item.getName()).getName();
              imagePathAuthor = UPLOAD_PATH_Author.concat(name);
              item.write(new File(imagePathAuthor));
            }
          }
        } catch (Exception e) {
          logger.info(e);
        }
      }
      Author author = new Author(firstname, surname, imagePathAuthor);
      authorList.add(author);
    }
    book.setAuthorList(authorList);
    bookAuthorService.addBookAuthorPairs(book);
    response.sendRedirect("/bookServlet/book?action=list");
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
    int authorsNumber = Integer.parseInt((String) request.getParameter("authorNumber"));
    if (request.getAttribute("genreIdMap") == null) {
      Map<Integer, Genre> allIdsAndGenresMap = null;
      try {
        allIdsAndGenresMap = genreService.showIdGenreMap();
      } catch (DAOException | SQLException e) {
        logger.info(e.getMessage());
      }
      request.setAttribute("genreIdMap", allIdsAndGenresMap);
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/bookFormNew.jsp");
    request.setAttribute("authorsNumber", authorsNumber);
    dispatcher.forward(request, response);
  }

  private void updateBook(HttpServletRequest request, HttpServletResponse response) {
    int id = Integer.parseInt(request.getParameter("id"));
    //////
  }
}
