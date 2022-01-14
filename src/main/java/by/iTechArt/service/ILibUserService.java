package by.iTechArt.service;

import by.iTechArt.exception.DAOException;
import by.iTechArt.models.LibUser;

import java.sql.SQLException;
import java.util.List;

public interface ILibUserService {
    List<LibUser> showLibUserList() throws DAOException, SQLException;
    LibUser searchById(int id) throws DAOException, SQLException;
    void addLibUser(LibUser libUser) throws DAOException, SQLException;
    void editLibUser(int id, LibUser libUser) throws DAOException, SQLException;
    void deleteLibUser(int id) throws DAOException, SQLException;
}
