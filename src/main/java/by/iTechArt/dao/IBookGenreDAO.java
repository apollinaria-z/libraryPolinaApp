package by.iTechArt.dao;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;

import java.sql.SQLException;
import java.util.List;

public interface IBookGenreDAO {
    void createBookGenrePairs(Book book) throws DAOException, SQLException;
    void deleteBookGenrePairs(int bookId) throws DAOException;
    List<Genre> getGenresOfBook(int bookId) throws DAOException;

}
