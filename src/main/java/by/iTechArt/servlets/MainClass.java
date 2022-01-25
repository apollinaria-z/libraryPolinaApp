package by.iTechArt.servlets;

import by.iTechArt.dao.connection.ConnectionPool;
import by.iTechArt.exception.DAOException;
import by.iTechArt.models.Author;
import by.iTechArt.models.Book;
import by.iTechArt.models.Genre;
import by.iTechArt.models.LibUser;
import by.iTechArt.service.impl.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClass {
  public static void main(String[] args) throws SQLException, DAOException {
//    String genresStringMassive[] = {"1","4","5"};
//    List<Genre> genreList = Arrays.stream(genresStringMassive).map(Genre::valueOf).collect(Collectors.toList());
//    System.out.println(genreList.toString());
//    ConnectionPool.getInstance().init(1000, 0, 10);
//
//    AuthorService authorService = new AuthorService();
//    BookService bookService = new BookService();
//    GenreService genreService = new GenreService();
//    LibUserService libUserService = new LibUserService();
//    BookGenreService bookGenreService = new BookGenreService();
//    BookImageService bookImageService = new BookImageService();
//    BookAuthorService bookAuthorService = new BookAuthorService();
//    //--------authorService checking-------------
////    System.out.println(authorService.searchBySurname("Rowling").getFirstname());
////    System.out.println(authorService.searchByID(5).getFirstname());
////    authorService.addAuthor(new Author("Steven", "King", "some/path/yes"));
////    System.out.println(authorService.showAuthorList().get(10).getFirstname());
////    authorService.deleteAuthor(8);
////    authorService.editAuthor(5, new Author("new3AU", "thor", "image"));
////    System.out.println(authorService.searchByID(5).getFirstname());
////--------genreService checking-------------
//    Map<Integer,Genre> genres = genreService.showIdGenreMap();
//    System.out.println(genres.toString());
//    System.out.println(genreService.searchGenreById(6));
//    System.out.println(genreService.searchIdOfGenre(Genre.valueOf("SCIFI")));
//--------bookGenreService cheking----------
//    System.out.println(bookGenreService.showGenresOfBook(2));
//--------bookImageService cheking-----------
//    System.out.println(bookImageService.showImagesOfBook(5));
//--------bookService cheking-----------
//    bookService.deleteBook(3);
//    List<Author> authors = Arrays.asList(authorService.searchBySurname("King"));
//    List<Genre> genres = Arrays.asList(Genre.DETECTIVE, Genre.HORROR);
//    List<String> images = Arrays.asList("path1King", "path2/king","path3King");
//    Book book = new Book("bookNameKing","",genres,300,authors,images,
//      4,1990,234);
//    bookService.addBook(book);
//    System.out.println(bookService.searchById(6).getImagePathList().toString());
//-------libUserService check----------------------
//    LibUser lu = new LibUser("franz", "ferdinand", "maria", "MP0909090", "LA", LocalDate.parse("1980-06-05"), "franz@gogo.com");
//    libUserService.addLibUser(lu);
//    List<LibUser> users = libUserService.showLibUserList();
//    System.out.println(users.toString());
//    String fileName = "boss.txt";
//    File file = new File("/Users/polina/libraryImages/" + fileName);
   // File file2 = new File("/Users/polina/libraryImages/boss.txt");
//    try {
//      file.createNewFile();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }
}
