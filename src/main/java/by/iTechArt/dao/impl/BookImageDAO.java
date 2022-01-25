package by.iTechArt.dao.impl;

import by.iTechArt.dao.IBookImageDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.service.IBookService;
import by.iTechArt.service.impl.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookImageDAO extends BaseImpl implements IBookImageDAO {
  static final Logger logger = LogManager.getLogger(BookAuthorDAO.class);

  enum SQLBookImage {
    INSERT("INSERT INTO book_image (book_id, imagePath) VALUES ((?), (?))"),
    DELETE_BY_BOOK_ID("DELETE FROM book_image WHERE book_id=(?)"),
    GET_BY_BOOK_ID("SELECT imagePath FROM book_image where book_id = (?)");
    String QUERY;

    SQLBookImage(String QUERY) {
      this.QUERY = QUERY;
    }
  }

  @Override
  public void createBookImagePairs(Book book) throws DAOException, SQLException {
    IBookService bookService = new BookService();
    int bookIdFromDAO = bookService.searchByName(book.getNameRu()).getId();
    List<String> imageList = book.getImagePathList();
    PreparedStatement statement = null;
    for (String imagePath : imageList) {
      try {
        statement = connection.prepareStatement(SQLBookImage.INSERT.QUERY);
        statement.setInt(1, bookIdFromDAO);
        statement.setString(2, imagePath);
        statement.executeUpdate();
      } catch (SQLException e) {
        logger.info(e.getMessage());
      } finally {
        closeStatement(statement);
      }
    }
  }

  @Override
  public void deleteBookImagePairs(int bookId) throws DAOException {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(SQLBookImage.DELETE_BY_BOOK_ID.QUERY);
      statement.setInt(1, bookId);
      statement.executeUpdate();
    } catch (SQLException e) {
      logger.info(e.getMessage());
    } finally {
      closeStatement(statement);
    }
  }

  @Override
  public List<String> getImagesOfBook(int bookId) throws DAOException {
    List<String> imageList = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      statement = connection.prepareStatement(SQLBookImage.GET_BY_BOOK_ID.QUERY);
      statement.setInt(1, bookId);
      rs = statement.executeQuery();
      while (rs.next()) {
        imageList.add(rs.getString("imagePath"));
      }
    } catch (SQLException e) {
      logger.info(e.getMessage());
    } finally {
      closeResultSet(rs);
      closeStatement(statement);
    }
    return imageList;
  }
}
