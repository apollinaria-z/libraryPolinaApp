package by.iTechArt.service;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookService {
    List<Book> showBookList() throws DAOException, SQLException;
    Book searchByName(String name) throws DAOException, SQLException;
    Book searchById(int id) throws DAOException, SQLException;
    void addBook(Book book) throws DAOException, SQLException;
    void editBook(int id, Book book) throws DAOException, SQLException;
    void deleteBook(int id) throws DAOException, SQLException;
}
