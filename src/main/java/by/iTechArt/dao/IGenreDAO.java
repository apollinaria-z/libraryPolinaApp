package by.iTechArt.dao;


import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Genre;

import java.util.List;

public interface IGenreDAO {
    Genre getGenreById(int id) throws DAOException;
    List<Genre> getAllGenres() throws DAOException;
    int getIdByGenre(Genre genre)throws DAOException;
    void createGenre(Genre genre) throws DAOException;
    void deleteGenre(Genre genre) throws DAOException;
}
