package by.iTechArt.service;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookAuthorService {
    void addBookAuthorPairs(Book book) throws DAOException, SQLException;
    void deleteBookAuthorPairs(int bookId) throws DAOException, SQLException;
    List<Author> showAuthorsOfBook(int bookId) throws DAOException, SQLException;

}
