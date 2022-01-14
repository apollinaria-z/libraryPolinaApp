package by.iTechArt.dao.impl;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Genre;
import by.iTechArt.dao.IGenreDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends BaseImpl implements IGenreDAO {

    static final Logger logger = LogManager.getLogger(GenreDAO.class);

    enum SQLGenre {
        GETBYID("SELECT name from genre where id = (?)"),
        GETBYGENRE("SELECT id from genre where name = (?)"),
        INSERT("INSERT INTO genre (id, name) VALUES (DEFAULT, (?)"),
        DELETE("DELETE FROM genre WHERE name = (?)");
        String QUERY;

        SQLGenre(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public Genre getGenreById(int id) {
        String genre = "";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLGenre.GETBYID.QUERY);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                genre = rs.getString("name");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return Genre.valueOf(genre);
    }

    public int getIdByGenre(Genre genre) {
        int id = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLGenre.GETBYGENRE.QUERY);
            statement.setString(1, genre.name());
            rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return id;
    }

    @Override
    public void createGenre(Genre genre) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLGenre.INSERT.QUERY);
            statement.setString(1, genre.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void deleteGenre(Genre genre) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLGenre.DELETE.QUERY);
            statement.setString(1, genre.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    public List<Genre> getAllGenres() {
        List<Genre> genreList = new ArrayList<>();
        String query = "SELECT * FROM genre";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                genreList.add(Genre.valueOf(rs.getString("name")));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return genreList;
    }

}
