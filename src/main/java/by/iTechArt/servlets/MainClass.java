package by.iTechArt.servlets;

import by.iTechArt.dao.connection.ConnectionPool;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;
import by.iTechArt.models.LibUser;
import by.iTechArt.service.impl.AuthorService;
import by.iTechArt.service.impl.BookService;
import by.iTechArt.service.impl.GenreService;
import by.iTechArt.service.impl.LibUserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MainClass {
    public static void main(String[] args) throws SQLException, DAOException {
        ConnectionPool.getInstance().init(1000, 0, 10);

        AuthorService authorService = new AuthorService();
        BookService bookService = new BookService();
        GenreService genreService = new GenreService();
        LibUserService libUserService = new LibUserService();
//        List<Genre> genres = genreService.showGenreList();
//        System.out.println(genres.toString());
        ConnectionPool.getInstance().init(1000, 0, 10);
       // List<Author> authors = authorService.showAuthorList();
      //  System.out.println(authors.toString());
//        List<LibUser> users = libUserService.showLibUserList();
//        System.out.println(users.toString());
        LibUser lu = new LibUser("yuyu","ff", "na","MP6554567", "address", LocalDate.parse("1890-03-05"),"po3@le.com");
        libUserService.addLibUser(lu);
        List<LibUser> users = libUserService.showLibUserList();
        System.out.println(users.toString());
    }
}
