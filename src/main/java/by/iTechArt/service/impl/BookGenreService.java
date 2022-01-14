package by.iTechArt.service.impl;

import by.iTechArt.dao.IBookGenreDAO;
import by.iTechArt.dao.impl.BookGenreDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;
import by.iTechArt.service.IBookGenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BookGenreService implements IBookGenreService {
    static final Logger logger = LogManager.getLogger(LibUserService.class);
    @Override
    public void addBookGenrePairs(Book book) throws DAOException, SQLException {
        BookGenreDAO bookGenreDAO = new BookGenreDAO();
        try{
            bookGenreDAO.setConnection();
            bookGenreDAO.createBookGenrePairs(book);
        } finally {
            bookGenreDAO.closeConnection();
        }
    }

    @Override
    public void deleteBookGenrePairs(int bookId) throws DAOException, SQLException {
        BookGenreDAO bookGenreDAO = new BookGenreDAO();
        try{
            bookGenreDAO.setConnection();
            bookGenreDAO.deleteBookGenrePairs(bookId);
        } finally {
            bookGenreDAO.closeConnection();
        }
    }

    @Override
    public List<Genre> showGenresOfBook(int bookId) throws DAOException, SQLException {
        BookGenreDAO bookGenreDAO = new BookGenreDAO();
        try{
            bookGenreDAO.setConnection();
            return bookGenreDAO.getGenresOfBook(bookId);
        } finally {
            bookGenreDAO.closeConnection();
        }
    }
}
