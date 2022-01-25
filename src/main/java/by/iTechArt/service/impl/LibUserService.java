package by.iTechArt.service.impl;

import by.iTechArt.dao.impl.LibUserDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.exception.ServiceException;
import by.iTechArt.models.LibUser;
import by.iTechArt.service.ILibUserService;
import by.iTechArt.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class LibUserService implements ILibUserService {
  static final Logger logger = LogManager.getLogger(LibUserService.class);

  @Override
  public List<LibUser> showLibUserList() throws DAOException, SQLException {
    LibUserDAO libUserDAO = new LibUserDAO();
    try {
      libUserDAO.setConnection();
      return libUserDAO.getAllLibUsers();
    } finally {
      libUserDAO.closeConnection();
    }
  }

  @Override
  public LibUser searchById(int id) throws DAOException, SQLException {
    LibUserDAO libUserDAO = new LibUserDAO();
    try {
      libUserDAO.setConnection();
      return libUserDAO.getLibUserById(id);
    } finally {
      libUserDAO.closeConnection();
    }
  }

  @Override
  public void addLibUser(LibUser libUser) throws DAOException, SQLException {
    ServiceValidator serviceValidator = new ServiceValidator();
    LibUserDAO libUserDAO = new LibUserDAO();
    try {
      libUserDAO.setConnection();
      serviceValidator.validationLibUserFields(libUser);
      serviceValidator.validationUnique(libUser, libUserDAO.getAllLibUsers());
      libUserDAO.createLibUser(libUser);
    } catch (ServiceException e) {
      logger.info("validation problem");
    } finally {
      libUserDAO.closeConnection();
    }
  }

  @Override
  public void editLibUser(int id, LibUser libUser) throws DAOException, SQLException {
    ServiceValidator serviceValidator = new ServiceValidator();
    LibUserDAO libUserDAO = new LibUserDAO();
    try {
      libUserDAO.setConnection();
      serviceValidator.validationLibUserFields(libUser);
      libUserDAO.updateLibUser(id, libUser);
    } catch (ServiceException e) {
      logger.info("validation problem");
    } finally {
      libUserDAO.closeConnection();
    }
  }

  @Override
  public void deleteLibUser(int id) throws DAOException, SQLException {
    LibUserDAO libUserDAO = new LibUserDAO();
    try {
      libUserDAO.setConnection();
      libUserDAO.deleteLibUser(id);
    } finally {
      libUserDAO.closeConnection();
    }
  }
}
