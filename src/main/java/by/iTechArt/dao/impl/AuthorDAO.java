package by.iTechArt.dao.impl;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.dao.IAuthorDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends BaseImpl implements IAuthorDAO {
    static final Logger logger = LogManager.getLogger(AuthorDAO.class);
    enum SQLAuthor {
        GET("SELECT au.id, au.firstname, au.surname, au.imagePath" +
                " FROM author AS au WHERE au.id = (?)"),
        GETBYSURNAME("SELECT au.id, au.firstname, au.surname, au.imagePath" +
                " FROM author AS au WHERE au.surname = (?)"),
        INSERT("INSERT INTO author (id, firstname, surname, imagePath)" +
                " VALUES (DEFAULT, (?), (?), (?))"),
        UPDATE("UPDATE author SET firstname=(?), surname=(?), imagePath=(?)" +
                " WHERE id=(?)"),
        DELETE("DELETE FROM author WHERE id = (?)"),
        GETLIST("SELECT * FROM author");
        String QUERY;
        SQLAuthor(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public List<Author> getAllAuthors() throws DAOException {
        List<Author> authors = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            statement = connection.prepareStatement(SQLAuthor.GETLIST.QUERY);
            rs = statement.executeQuery();
            while (rs.next()) {
                Author newAuthor = new Author(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("surname"),
                        rs.getString("imagePath"));
                authors.add(newAuthor);
            }
        } catch (SQLException e){
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return authors;
    }

    @Override
    public Author getAuthorByID(int id) throws DAOException {
        Author author = new Author();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            statement = connection.prepareStatement(SQLAuthor.GET.QUERY);
            statement.setInt(1,id);
            rs = statement.executeQuery();
            if (rs.next()) {
               // authorSet(author, rs);
                author.setId(rs.getInt("id"));
                author.setFirstname(rs.getString("firstname"));
                author.setSurname(rs.getString("surname"));
                author.setImagePath(rs.getString("imagePath"));
            }
        } catch (SQLException e){
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return author;
    }

    @Override
    public boolean createAuthor(Author author) throws DAOException {
        boolean result = false;
        PreparedStatement statement = null;
        if (!isAuthorExist(author)) {
            try{
                statement = connection.prepareStatement(SQLAuthor.INSERT.QUERY);
                statementSet(author, statement);
                statement.executeUpdate();
                result = true;
            } catch (SQLException e) {
                logger.info(e.getMessage());
            } finally {
                closeStatement(statement);
            }
        }
        return result;
        }

        @Override
    public void updateAuthor(int id, Author author) throws DAOException {
            PreparedStatement statement = null;
            try{
                statement = connection.prepareStatement(SQLAuthor.UPDATE.QUERY);
                statementSet(author, statement);
                statement.executeUpdate();
            } catch (SQLException e){
                logger.info(e.getMessage());
            } finally {
                closeStatement(statement);
            }
        }

    @Override
    public void deleteAuthor(int id) throws DAOException {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(SQLAuthor.DELETE.QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public Author getAuthorBySurname(String surname) throws DAOException {
        Author author = new Author();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            statement = connection.prepareStatement(SQLAuthor.GETBYSURNAME.QUERY);
            statement.setString(1,surname);
            rs = statement.executeQuery();
            if (rs.next()) {
                authorSet(author, rs);
            }
        } catch (SQLException e){
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return author;
    }
    private boolean isAuthorExist(Author author) throws DAOException {
        boolean result = false;
        String surname = author.getSurname();
        if(surname==getAuthorBySurname(surname).getSurname()){
            result = true;
        }
        return result;
    }
    private void authorSet(Author author, ResultSet rs) throws SQLException {
        author.setId(rs.getInt("id"));
        author.setFirstname(rs.getString("firstname"));
        author.setSurname(rs.getString("surname"));
        author.setImagePath(rs.getString("imagePath"));
    }
    private void statementSet(Author author, PreparedStatement statement) throws SQLException {
        statement.setString(1, author.getFirstname());
        statement.setString(2, author.getSurname());
        statement.setString(3, author.getImagePath());
    }
}
