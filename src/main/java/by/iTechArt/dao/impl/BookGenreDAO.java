package by.iTechArt.dao.impl;

import by.iTechArt.dao.IBookDAO;
import by.iTechArt.dao.IBookGenreDAO;
import by.iTechArt.dao.IGenreDAO;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;
import by.iTechArt.service.IBookService;
import by.iTechArt.service.IGenreService;
import by.iTechArt.service.impl.BookService;
import by.iTechArt.service.impl.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookGenreDAO extends BaseImpl implements IBookGenreDAO {
    static final Logger logger = LogManager.getLogger(BookGenreDAO.class);
    enum SQLBookGenre {
        INSERT("INSERT INTO book_genre (genre_id, book_id) VALUES ((?), (?))"),
        DELETE_BY_BOOK_ID("DELETE FROM book_genre WHERE book_id=(?)"),
        GET_BY_BOOK_ID("SELECT genre_id FROM book_genre where book_id = (?)");
        String QUERY;
        SQLBookGenre(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    @Override
    public void createBookGenrePairs(Book book) throws DAOException, SQLException {
        IBookService bookService = new BookService();
        IGenreService genreService = new GenreService();
        int bookIdFromDAO = bookService.searchByName(book.getNameRu()).getId();
        List<Genre> genreList = book.getGenreList();
        PreparedStatement statement = null;
        for (Genre genre : genreList) {
            int genreIdFromDAO = genreService.searchIdOfGenre(genre);
            try {
                statement = connection.prepareStatement(SQLBookGenre.INSERT.QUERY);
                statement.setInt(1, genreIdFromDAO);
                statement.setInt(2, bookIdFromDAO);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            } finally {
                closeStatement(statement);
            }
        }
    }

    @Override
    public void deleteBookGenrePairs(int bookId) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQLBookGenre.DELETE_BY_BOOK_ID.QUERY);
            statement.setInt(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public List<Genre> getGenresOfBook(int bookId) throws DAOException {
        GenreService genreService = new GenreService();
        List<Genre> genreList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SQLBookGenre.GET_BY_BOOK_ID.QUERY);
            statement.setInt(1, bookId);
            rs = statement.executeQuery();
            while (rs.next()) {
                int genreId = rs.getInt("genre_id");
                genreList.add(genreService.searchGenreById(genreId));
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
