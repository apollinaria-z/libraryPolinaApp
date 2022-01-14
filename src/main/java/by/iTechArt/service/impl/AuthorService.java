package by.iTechArt.service.impl;

import by.iTechArt.dao.IAuthorDAO;
import by.iTechArt.dao.impl.AuthorDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.service.IAuthorService;

import java.sql.SQLException;
import java.util.List;

public class AuthorService implements IAuthorService {

    @Override
    public List<Author> showAuthorList() throws DAOException, SQLException {
        AuthorDAO authorDAO = new AuthorDAO();
        try{
            authorDAO.setConnection();
            return authorDAO.getAllAuthors();
        }finally {
            authorDAO.closeConnection();
        }
    }

    @Override
    public Author searchByID(int id) throws DAOException, SQLException {
        AuthorDAO authorDAO = new AuthorDAO();
        try{
            authorDAO.setConnection();
            return authorDAO.getAuthorByID(id);
        }finally {
            authorDAO.closeConnection();
        }
    }

    @Override
    public boolean addAuthor(Author author) throws DAOException, SQLException {
        AuthorDAO authorDAO = new AuthorDAO();
        try{
            authorDAO.setConnection();
            return authorDAO.createAuthor(author);
        }finally {
            authorDAO.closeConnection();
        }
    }

    @Override
    public void editAuthor(int id, Author author) throws DAOException, SQLException {
        AuthorDAO authorDAO = new AuthorDAO();
        try{
            authorDAO.setConnection();
            authorDAO.updateAuthor(id,author);
        }finally {
            authorDAO.closeConnection();
        }
    }

    @Override
    public void deleteAuthor(int id) throws DAOException, SQLException {
        AuthorDAO authorDAO = new AuthorDAO();
        try{
            authorDAO.setConnection();
            authorDAO.deleteAuthor(id);
        }finally {
            authorDAO.closeConnection();
        }
    }

    @Override
    public Author searchBySurname(String surname) throws DAOException, SQLException {
        AuthorDAO authorDAO = new AuthorDAO();
        try{
            authorDAO.setConnection();
            return authorDAO.getAuthorBySurname(surname);
        }finally {
            authorDAO.closeConnection();
        }
    }
}
