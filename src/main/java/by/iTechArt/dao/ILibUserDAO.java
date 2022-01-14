package by.iTechArt.dao;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.LibUser;

import java.sql.SQLException;
import java.util.List;

public interface ILibUserDAO {
    List<LibUser> getAllLibUsers() throws DAOException, SQLException;
    LibUser getLibUserById(int id) throws DAOException;
    void createLibUser(LibUser libUser) throws DAOException;
    void updateLibUser(int id, LibUser libUser) throws DAOException;
    void deleteLibUser(int id) throws DAOException;
}
