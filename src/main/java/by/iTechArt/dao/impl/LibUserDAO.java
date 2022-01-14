package by.iTechArt.dao.impl;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.LibUser;
import by.iTechArt.dao.ILibUserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibUserDAO extends BaseImpl implements ILibUserDAO {
    static final Logger logger = LogManager.getLogger(LibUserDAO.class);

    enum SQLLibUser {
        GET("SELECT lu.id, lu.firstname, lu.surname, lu.middleName, " +
                "lu.passportId, lu.address, lu.dateOfBirth, lu.email" +
                " FROM libuser AS lu WHERE lu.id = (?)"),
        INSERT("INSERT INTO libuser (id, firstname, surname, middleName," +
                " passportId, address, dateOfBirth, email) VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?))"),
        UPDATE("UPDATE libuser SET firstname=(?), surname=(?), middleName=(?), " +
                "passportId=(?), address=(?), dateOfBirth=(?), email=(?) WHERE id=(?)"),
        DELETE("DELETE FROM libuser WHERE id = (?)"),
        GETLIST("SELECT * FROM libuser");
        String QUERY;
        SQLLibUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public List<LibUser> getAllLibUsers() throws DAOException, SQLException {
        List<LibUser> libUsers = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLLibUser.GETLIST.QUERY);
            rs = statement.executeQuery();
            while (rs.next()) {
                LibUser newLibUser = new LibUser(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("surname"),
                        rs.getString("middlename"),
                        rs.getString("passportId"),
                        rs.getString("address"),
                        rs.getObject("dateOfBirth", LocalDate.class),
                        rs.getString("email")
                );
                libUsers.add(newLibUser);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return libUsers;
    }

    @Override
    public void createLibUser(LibUser libUser) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLLibUser.INSERT.QUERY);
            statement.setString(1, libUser.getFirstname());
            statement.setString(2, libUser.getSurname());
            statement.setString(3, libUser.getMiddlename());
            statement.setString(4, libUser.getPassportID());
            statement.setString(5, libUser.getAddress());
            statement.setDate(6, Date.valueOf(libUser.getDateOfBirth()));
            statement.setString(7, libUser.getEmail());
            //setStatement(libUser, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public LibUser getLibUserById(int id) throws DAOException {
        LibUser libUser = new LibUser();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLLibUser.GET.QUERY);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                libUser.setId(rs.getInt("id"));
                libUser.setFirstname(rs.getString("firstname"));
                libUser.setSurname(rs.getString("surname"));
                libUser.setMiddleName(rs.getString("middleName"));
                libUser.setPassportID(rs.getString("passportId"));
                libUser.setAddress(rs.getString("address"));
                libUser.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
                libUser.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return libUser;
    }

    @Override
    public void updateLibUser(int id, LibUser libUser) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLLibUser.UPDATE.QUERY);
            setStatement(libUser, statement);
            statement.setInt(8, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void deleteLibUser(int id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLLibUser.DELETE.QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    private void setStatement(LibUser libUser, PreparedStatement statement) throws SQLException {
        statement.setString(1, libUser.getFirstname());
        statement.setString(2, libUser.getSurname());
        statement.setString(3, libUser.getMiddlename());
        statement.setString(4, libUser.getPassportID());
        statement.setString(5, libUser.getAddress());
        statement.setDate(6, Date.valueOf(libUser.getDateOfBirth()));
        statement.setString(7, libUser.getEmail());

    }

}
