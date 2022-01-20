package by.iTechArt.servlets;

import by.iTechArt.dao.connection.ConnectionPool;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.service.IBookService;
import by.iTechArt.service.impl.BookService;
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

public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger("name");
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.getInstance().init(DB_POOL_MAX_SIZE,
              DB_POOL_CHECK_CONNECTION_TIMEOUT, DB_POOL_START_SIZE);
        } catch (DAOException e) {
            logger.info(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        RequestDispatcher dispatcher = request
          .getRequestDispatcher("welcomePage.jsp");
        dispatcher.forward(request, response);

    }

}
