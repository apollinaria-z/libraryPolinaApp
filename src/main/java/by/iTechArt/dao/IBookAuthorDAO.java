package by.iTechArt.dao;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookAuthorDAO {

    void createBookAuthorPairs(Book book) throws DAOException, SQLException;
    void deleteBookAuthorPairs(int bookId) throws DAOException;
    List<Author> getAuthorsOfBook(int bookId) throws DAOException;
}
