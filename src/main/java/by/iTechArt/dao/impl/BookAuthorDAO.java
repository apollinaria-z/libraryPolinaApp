package by.iTechArt.dao.impl;

import by.iTechArt.dao.IAuthorDAO;
import by.iTechArt.dao.IBookAuthorDAO;
import by.iTechArt.dao.IBookDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;
import by.iTechArt.service.IBookService;
import by.iTechArt.service.impl.AuthorService;
import by.iTechArt.service.impl.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAO extends BaseImpl implements IBookAuthorDAO {
    static final Logger logger = LogManager.getLogger(BookAuthorDAO.class);
    enum SQLBookAuthor {
        INSERT("INSERT INTO book_author (book_id, author_id) VALUES ((?), (?))"),
        DELETE_BY_BOOK_ID("DELETE FROM book_author WHERE book_id=(?)"),
        GET_BY_BOOK_ID("SELECT author_id FROM book_author where book_id = (?)");
        String QUERY;

        SQLBookAuthor(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public void createBookAuthorPairs(Book book) throws DAOException, SQLException {
        AuthorService authorService = new AuthorService();
        IBookService bookService = new BookService();
        int bookIdFromDAO = bookService.searchByName(book.getNameRu()).getId();
        List<Author> authorList = book.getAuthorList();
        PreparedStatement statement = null;
        for (Author author : authorList){
            int authorIdFromDAO = authorService.searchBySurname(author.getSurname()).getId();
            try {
                statement = connection.prepareStatement(SQLBookAuthor.INSERT.QUERY);
                statement.setInt(1, bookIdFromDAO);
                statement.setInt(2, authorIdFromDAO);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            } finally {
                closeStatement(statement);
            }

        }
    }

    @Override
    public void deleteBookAuthorPairs(int bookId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLBookAuthor.DELETE_BY_BOOK_ID.QUERY);
            statement.setInt(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public List<Author> getAuthorsOfBook(int bookId) throws DAOException {
        AuthorService authorService = new AuthorService();
        List<Author> authorList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLBookAuthor.GET_BY_BOOK_ID.QUERY);
            statement.setInt(1, bookId);
            rs = statement.executeQuery();
            while (rs.next()) {
                int authorId = rs.getInt("author_id");
                authorList.add(authorService.searchByID(authorId));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            }
        return authorList;
    }
}
