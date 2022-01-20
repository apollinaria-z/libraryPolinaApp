package by.iTechArt.dao;


import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Genre;

import java.util.List;
import java.util.Map;

public interface IGenreDAO {
    Genre getGenreById(int id) throws DAOException;
  Map<Integer,Genre> getAllIdsGenres() throws DAOException;
    int getIdByGenre(Genre genre)throws DAOException;
}
