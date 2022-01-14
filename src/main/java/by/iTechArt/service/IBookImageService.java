package by.iTechArt.service;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookImageService {
    void addBookImagePairs(Book book) throws DAOException, SQLException;
    void deleteBookImagePairs(int bookId) throws DAOException, SQLException;
    List<String> showImagesOfBook(int bookId) throws DAOException, SQLException;

}
