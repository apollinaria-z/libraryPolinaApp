package by.iTechArt.dao;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDAO {
    List<Book> getAllBooks() throws DAOException, SQLException;
    Book getBookByName(String name) throws DAOException;
    Book getBookById(int id) throws DAOException;
    void createBook(Book book) throws DAOException;
    void updateBook(int id, Book book) throws DAOException;
    void deleteBook(int id) throws DAOException;
}
