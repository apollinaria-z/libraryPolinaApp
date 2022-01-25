package by.iTechArt.models;

import java.util.List;
import java.util.Objects;

public class Book {
    private int id;
    private String nameRu;
    private String nameOrigin;
    private List<Genre> genreList;
    private int cost;
    private List<Author> authorList;
    private List<String> imagePathList;
    private int dayPrice;
    private int publicationYear;
    private int pages;

    public Book() {
    }

    public Book(String nameRu, String nameOrigin, List<Genre> genreList, int cost, List<Author> authorList, List<String> imagePathList, int dayPrice, int publicationYear, int pages) {
        this.nameRu = nameRu;
        this.nameOrigin = nameOrigin;
        this.genreList = genreList;
        this.cost = cost;
        this.authorList = authorList;
        this.imagePathList = imagePathList;
        this.dayPrice = dayPrice;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }

    public Book(int id, String nameRu, String nameOrigin, List<Genre> genreList, int cost, List<Author> authorList, List<String> imagePathList, int dayPrice, int publicationYear, int pages) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameOrigin = nameOrigin;
        this.genreList = genreList;
        this.cost = cost;
        this.authorList = authorList;
        this.imagePathList = imagePathList;
        this.dayPrice = dayPrice;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }

    public Book(int id, String nameRu, String nameOrigin, int cost, int dayPrice, int publicationYear, int pages) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameOrigin = nameOrigin;
        this.cost = cost;
        this.dayPrice = dayPrice;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }
  public Book(String nameRu, String nameOrigin, int cost, int dayPrice, int publicationYear, int pages) {
    this.nameRu = nameRu;
    this.nameOrigin = nameOrigin;
    this.cost = cost;
    this.dayPrice = dayPrice;
    this.publicationYear = publicationYear;
    this.pages = pages;
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameOrigin() {
        return nameOrigin;
    }

    public void setNameOrigin(String nameOrigin) {
        this.nameOrigin = nameOrigin;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<String> getImagePathList() {
        return imagePathList;
    }

    public void setImagePathList(List<String> imagePathList) {
        this.imagePathList = imagePathList;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(int dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                cost == book.cost &&
                dayPrice == book.dayPrice &&
                publicationYear == book.publicationYear &&
                pages == book.pages &&
                Objects.equals(nameRu, book.nameRu) &&
                Objects.equals(nameOrigin, book.nameOrigin) &&
                Objects.equals(genreList, book.genreList) &&
                Objects.equals(authorList, book.authorList) &&
                Objects.equals(imagePathList, book.imagePathList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRu, nameOrigin, genreList, cost, authorList, imagePathList, dayPrice, publicationYear, pages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nameRu='" + nameRu + '\'' +
                ", nameOrigin='" + nameOrigin + '\'' +
                ", genreList=" + genreList +
                ", cost=" + cost +
                ", authorList=" + authorList +
                ", imagePathList=" + imagePathList +
                ", dayPrice=" + dayPrice +
                ", publicationYear=" + publicationYear +
                ", pages=" + pages +
                '}';
    }
}
