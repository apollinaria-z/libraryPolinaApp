package by.iTechArt.dao;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;

import java.sql.SQLException;
import java.util.List;

public interface IAuthorDAO {
    List<Author> getAllAuthors() throws DAOException, SQLException;
    Author getAuthorByID(int id) throws DAOException;
    boolean createAuthor(Author author) throws DAOException;
    void updateAuthor(int id, Author author) throws DAOException;
    void deleteAuthor(int id) throws DAOException;
    Author getAuthorBySurname(String surname) throws DAOException;
}
