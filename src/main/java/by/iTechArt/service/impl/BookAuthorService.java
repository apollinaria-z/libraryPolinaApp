package by.iTechArt.service.impl;

import by.iTechArt.dao.impl.BookAuthorDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;
import by.iTechArt.service.IBookAuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BookAuthorService implements IBookAuthorService {
    static final Logger logger = LogManager.getLogger(LibUserService.class);
    @Override
    public void addBookAuthorPairs(Book book) throws DAOException, SQLException {
        BookAuthorDAO bookAuthorDAO = new BookAuthorDAO();
        try{
            bookAuthorDAO.setConnection();
            bookAuthorDAO.createBookAuthorPairs(book);
        } finally {
            bookAuthorDAO.closeConnection();
        }
    }

    @Override
    public void deleteBookAuthorPairs(int bookId) throws DAOException, SQLException {
        BookAuthorDAO bookAuthorDAO = new BookAuthorDAO();
        try{
            bookAuthorDAO.setConnection();
            bookAuthorDAO.deleteBookAuthorPairs(bookId);
        } finally {
            bookAuthorDAO.closeConnection();
        }
    }

    @Override
    public List<Author> showAuthorsOfBook(int bookId) throws DAOException, SQLException {
        BookAuthorDAO bookAuthorDAO = new BookAuthorDAO();
        try{
            bookAuthorDAO.setConnection();
            return bookAuthorDAO.getAuthorsOfBook(bookId);
        } finally {
            bookAuthorDAO.closeConnection();
        }
    }
}
