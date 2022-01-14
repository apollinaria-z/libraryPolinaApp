package by.iTechArt.service.impl;

import by.iTechArt.dao.impl.GenreDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Genre;
import by.iTechArt.service.IGenreService;

import java.sql.SQLException;
import java.util.List;

public class GenreService implements IGenreService {
    @Override
    public Genre searchGenreById(int id) throws DAOException, SQLException {
        GenreDAO genreDAO = new GenreDAO();
        try{
            genreDAO.setConnection();
            return genreDAO.getGenreById(id);
        } finally {
            genreDAO.closeConnection();
        }
    }

    @Override
    public List<Genre> showGenreList() throws DAOException, SQLException {
        GenreDAO genreDAO = new GenreDAO();
        try{
            genreDAO.setConnection();
            return genreDAO.getAllGenres();
        } finally {
            genreDAO.closeConnection();
        }
    }

    @Override
    public int searchIdOfGenre(Genre genre) throws DAOException, SQLException {
        GenreDAO genreDAO = new GenreDAO();
        try{
            genreDAO.setConnection();
            return genreDAO.getIdByGenre(genre);
        } finally {
            genreDAO.closeConnection();
        }
    }

    @Override
    public void addGenre(Genre genre) throws DAOException, SQLException {
        GenreDAO genreDAO = new GenreDAO();
        try{
            genreDAO.setConnection();
            genreDAO.createGenre(genre);
        } finally {
            genreDAO.closeConnection();
        }
    }

    @Override
    public void deleteGenre(Genre genre) throws DAOException, SQLException {
        GenreDAO genreDAO = new GenreDAO();
        try{
            genreDAO.setConnection();
            genreDAO.deleteGenre(genre);
        } finally {
            genreDAO.closeConnection();
        }
    }
}
