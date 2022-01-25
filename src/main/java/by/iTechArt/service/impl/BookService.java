package by.iTechArt.service.impl;

import by.iTechArt.dao.impl.BookDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.exception.ServiceException;
import by.iTechArt.models.Book;
import by.iTechArt.service.IBookService;
import by.iTechArt.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BookService implements IBookService {
  static final Logger logger = LogManager.getLogger(BookService.class);

  @Override
  public List<Book> showBookList() throws DAOException, SQLException {
    BookDAO bookDAO = new BookDAO();
    try {
      bookDAO.setConnection();
      return bookDAO.getAllBooks();
    } finally {
      bookDAO.closeConnection();
    }
  }

  @Override
  public Book searchByName(String name) throws DAOException, SQLException {
    BookDAO bookDAO = new BookDAO();
    try {
      bookDAO.setConnection();
      return bookDAO.getBookByName(name);
    } finally {
      bookDAO.closeConnection();
    }
  }

  @Override
  public Book searchById(int id) throws DAOException, SQLException {
    BookDAO bookDAO = new BookDAO();
    try {
      bookDAO.setConnection();
      return bookDAO.getBookById(id);
    } finally {
      bookDAO.closeConnection();
    }
  }

  @Override
  public void addBook(Book book) throws DAOException, SQLException {
    BookDAO bookDAO = new BookDAO();
    ServiceValidator serviceValidator = new ServiceValidator();
    try {
      bookDAO.setConnection();
      serviceValidator.validationBook(book);
      bookDAO.createBook(book);
    } catch (ServiceException e) {
      logger.info("validation problem");
    } finally {
      bookDAO.closeConnection();
    }
  }

  @Override
  public void editBook(int id, Book book) throws DAOException, SQLException {
    BookDAO bookDAO = new BookDAO();
    ServiceValidator serviceValidator = new ServiceValidator();
    try {
      bookDAO.setConnection();
      serviceValidator.validationBook(book);
      bookDAO.updateBook(id, book);
    } catch (ServiceException e) {
      logger.info("validation problem");
    } finally {
      bookDAO.closeConnection();
    }
  }

  @Override
  public void deleteBook(int id) throws DAOException, SQLException {
    BookDAO bookDAO = new BookDAO();
    try {
      bookDAO.setConnection();
      bookDAO.deleteBook(id);
    } finally {
      bookDAO.closeConnection();
    }
  }
}
