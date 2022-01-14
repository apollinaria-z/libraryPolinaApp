package by.iTechArt.service.impl;

import by.iTechArt.dao.impl.BookImageDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.service.IBookImageService;

import java.sql.SQLException;
import java.util.List;

public class BookImageService implements IBookImageService {
    @Override
    public void addBookImagePairs(Book book) throws DAOException, SQLException {
        BookImageDAO bookImageDAO = new BookImageDAO();
        try{
            bookImageDAO.setConnection();
            bookImageDAO.createBookImagePairs(book);
        } finally {
            bookImageDAO.closeConnection();
        }
    }

    @Override
    public void deleteBookImagePairs(int bookId) throws DAOException, SQLException {
        BookImageDAO bookImageDAO = new BookImageDAO();
        try{
            bookImageDAO.setConnection();
            bookImageDAO.deleteBookImagePairs(bookId);
        } finally {
            bookImageDAO.closeConnection();
        }
    }

    @Override
    public List<String> showImagesOfBook(int bookId) throws DAOException, SQLException {
        BookImageDAO bookImageDAO = new BookImageDAO();
        try{
            bookImageDAO.setConnection();
            return bookImageDAO.getImagesOfBook(bookId);
        } finally {
            bookImageDAO.closeConnection();
        }
    }
}
