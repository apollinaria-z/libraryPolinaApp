package by.iTechArt.service;


import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Genre;

import java.sql.SQLException;
import java.util.List;

public interface IGenreService {
    Genre searchGenreById(int id) throws DAOException, SQLException;
    List<Genre> showGenreList() throws DAOException, SQLException;
    int searchIdOfGenre(Genre genre)throws DAOException, SQLException;
    void addGenre(Genre genre) throws DAOException, SQLException;
    void deleteGenre(Genre genre) throws DAOException, SQLException;

}
