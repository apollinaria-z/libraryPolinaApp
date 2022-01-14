package by.iTechArt.servlets;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;
import by.iTechArt.service.IBookService;
import by.iTechArt.service.impl.BookService;
import by.iTechArt.service.impl.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger(BookServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewBookForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                showEditBookForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            default:
                getBookList(request, response);
                break;
        }
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response) {
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) {
    }

    private void showEditBookForm(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IBookService bookService = new BookService();
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
        GenreService genreService = new GenreService();
        if (request.getAttribute("genreList") == null) {
            List<Genre> allGenres = null;
            try {
                allGenres = genreService.showGenreList();
            } catch (DAOException | SQLException e) {
                logger.info(e.getMessage());
            }
            request.setAttribute("genreList", allGenres);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bookForm.jsp");
        dispatcher.forward(request, response);
    }
}
