package by.iTechArt.service;


import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Genre;

import java.sql.SQLException;
import java.util.Map;

public interface IGenreService {
    Genre searchGenreById(int id) throws DAOException, SQLException;
    Map<Integer, Genre> showIdGenreMap() throws DAOException, SQLException;
    int searchIdOfGenre(Genre genre)throws DAOException, SQLException;
}
