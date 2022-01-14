package by.iTechArt.service;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;

import java.sql.SQLException;
import java.util.List;

public interface IAuthorService {
    List<Author> showAuthorList() throws DAOException, SQLException;
    Author searchByID(int id) throws DAOException, SQLException;
    boolean addAuthor(Author author) throws DAOException, SQLException;
    void editAuthor(int id, Author author) throws DAOException, SQLException;
    void deleteAuthor(int id) throws DAOException, SQLException;
    Author searchBySurname(String surname) throws DAOException, SQLException;
}
