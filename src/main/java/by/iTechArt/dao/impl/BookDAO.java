package by.iTechArt.dao.impl;

import by.iTechArt.dao.*;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.service.IBookAuthorService;
import by.iTechArt.service.IBookGenreService;
import by.iTechArt.service.IBookImageService;
import by.iTechArt.service.impl.BookAuthorService;
import by.iTechArt.service.impl.BookGenreService;
import by.iTechArt.service.impl.BookImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseImpl implements IBookDAO {
    static final Logger logger = LogManager.getLogger(BookDAO.class);

    enum SQLBook {
        GETBYID("SELECT b.id, b.nameRu, b.nameOrigin, " +
                "b.cost, b.dayPrice, b.publicationYear, b.pages" +
                " FROM book AS b WHERE b.id = (?)"),
        GETBYNAME("SELECT b.id, b.nameRu, b.nameOrigin," +
                "b.cost, b.dayPrice, b.publicationYear, b.pages" +
                " FROM book AS b WHERE (b.nameRu = (?) OR b.nameOrigin = (?))"),
        INSERT("INSERT INTO book (id, nameRu, nameOrigin, cost," +
                " dayPrice, publicationYear, pages)" +
                " VALUES (DEFAULT, (?), (?), (?), (?), (?), (?))"),
        UPDATE("UPDATE book SET nameRu=(?), nameOrigin=(?), cost=(?), " +
                "dayPrice=(?), publicationYear=(?), pages=(?) WHERE id=(?)"),
        DELETE("DELETE FROM book WHERE id = (?)"),
        GETLIST("SELECT * FROM book");
        String QUERY;

        SQLBook(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public List<Book> getAllBooks() throws DAOException, SQLException {
        List<Book> bookList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLBook.GETLIST.QUERY);
            rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                int bookId = rs.getInt("id");
                setBookFields(bookId, book, rs);
                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return bookList;
    }


    @Override
    public Book getBookByName(String name) throws DAOException {
        Book book = new Book();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLBook.GETBYNAME.QUERY);
            statement.setString(1, name);
            statement.setString(2, name);
            rs = statement.executeQuery();
            if (rs.next()) {
                int bookId = rs.getInt("id");
                setBookFields(bookId, book, rs);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return book;
    }

    @Override
    public Book getBookById(int id) throws DAOException {
        Book book = new Book();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLBook.GETBYID.QUERY);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                int bookId = rs.getInt("id");
                setBookFields(bookId, book, rs);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
        return book;
    }

    @Override
    public void createBook(Book book) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLBook.INSERT.QUERY);
            setStatement(book, statement);
            statement.executeUpdate();
            insertForeignKeysInTables(book);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void updateBook(int id, Book book) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLBook.UPDATE.QUERY);
            setStatement(book, statement);
            statement.setInt(7, id);
            statement.executeUpdate();
            deleteForeignKeysFromTables(id);
            insertForeignKeysInTables(book);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void deleteBook(int id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLBook.DELETE.QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    private void deleteForeignKeysFromTables(int bookId) throws DAOException, SQLException {
        IBookGenreService bookGenreService = new BookGenreService();
        IBookAuthorService bookAuthorService = new BookAuthorService();
        IBookImageService bookImageService = new BookImageService();
        bookAuthorService.deleteBookAuthorPairs(bookId);
        bookGenreService.deleteBookGenrePairs(bookId);
        bookImageService.deleteBookImagePairs(bookId);
    }

    private void insertForeignKeysInTables(Book book) throws SQLException, DAOException {
        IBookGenreService bookGenreService = new BookGenreService();
        IBookAuthorService bookAuthorService = new BookAuthorService();
        IBookImageService bookImageService = new BookImageService();
        bookGenreService.addBookGenrePairs(book);
        bookAuthorService.addBookAuthorPairs(book);
        bookImageService.addBookImagePairs(book);
    }

    private void setBookFields(int bookId, Book book, ResultSet rs) throws SQLException, DAOException {
        IBookGenreService bookGenreService = new BookGenreService();
        IBookAuthorService bookAuthorService = new BookAuthorService();
        IBookImageService bookImageService = new BookImageService();
        book.setId(bookId);
        book.setNameRu(rs.getString("nameRu"));
        book.setNameOrigin(rs.getString("nameOrigin"));
        book.setCost(rs.getInt("cost"));
        book.setDayPrice(rs.getInt("dayPrice"));
        book.setPublicationYear(rs.getInt("publicationYear"));
        book.setPages(rs.getInt("pages"));
        book.setGenreList(bookGenreService.showGenresOfBook(bookId));
        book.setAuthorList(bookAuthorService.showAuthorsOfBook(bookId));
        book.setImagePathList(bookImageService.showImagesOfBook(bookId));

    }

    private void setStatement(Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getNameRu());
        statement.setString(2, book.getNameOrigin());
        statement.setInt(3, book.getCost());
        statement.setInt(4, book.getDayPrice());
        statement.setInt(5, book.getPublicationYear());
        statement.setInt(6, book.getPages());

    }
}
