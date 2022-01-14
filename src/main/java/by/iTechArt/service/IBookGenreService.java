package by.iTechArt.service;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;

import java.sql.SQLException;
import java.util.List;

public interface IBookGenreService {
    void addBookGenrePairs(Book book) throws DAOException, SQLException;
    void deleteBookGenrePairs(int bookId) throws DAOException, SQLException;
    List<Genre> showGenresOfBook(int bookId) throws DAOException, SQLException;

}
