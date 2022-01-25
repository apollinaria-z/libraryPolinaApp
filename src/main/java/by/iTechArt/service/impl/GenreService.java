package by.iTechArt.service.impl;

import by.iTechArt.dao.impl.GenreDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Genre;
import by.iTechArt.service.IGenreService;

import java.sql.SQLException;
import java.util.Map;

public class GenreService implements IGenreService {
  @Override
  public Genre searchGenreById(int id) throws DAOException, SQLException {
    GenreDAO genreDAO = new GenreDAO();
    try {
      genreDAO.setConnection();
      return genreDAO.getGenreById(id);
    } finally {
      genreDAO.closeConnection();
    }
  }

  @Override
  public Map<Integer, Genre> showIdGenreMap() throws DAOException, SQLException {
    GenreDAO genreDAO = new GenreDAO();
    try {
      genreDAO.setConnection();
      return genreDAO.getAllIdsGenres();
    } finally {
      genreDAO.closeConnection();
    }
  }

  @Override
  public int searchIdOfGenre(Genre genre) throws DAOException, SQLException {
    GenreDAO genreDAO = new GenreDAO();
    try {
      genreDAO.setConnection();
      return genreDAO.getIdByGenre(genre);
    } finally {
      genreDAO.closeConnection();
    }
  }

}
