package by.iTechArt.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BookItem {
  private int id;
  private Book book;
  private LocalDate registrationDate;
  private List<BookState> bookStateList;

  public BookItem() {
  }

  public BookItem(int id, Book book, LocalDate registrationDate, List<BookState> bookStateList) {
    this.id = id;
    this.book = book;
    this.registrationDate = registrationDate;
    this.bookStateList = bookStateList;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  public List<BookState> getBookStateList() {
    return bookStateList;
  }

  public void setBookStateList(List<BookState> bookStateList) {
    this.bookStateList = bookStateList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookItem bookItem = (BookItem) o;
    return id == bookItem.id &&
      Objects.equals(book, bookItem.book) &&
      Objects.equals(registrationDate, bookItem.registrationDate) &&
      Objects.equals(bookStateList, bookItem.bookStateList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, book, registrationDate, bookStateList);
  }
}
