package by.iTechArt.dao;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;

import java.sql.SQLException;
import java.util.List;

public interface IBookImageDAO {

    void createBookImagePairs(Book book) throws DAOException, SQLException;
    void deleteBookImagePairs(int bookId) throws DAOException;
    List<String> getImagesOfBook(int bookId) throws DAOException;
}
