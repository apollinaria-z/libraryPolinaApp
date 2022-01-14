package by.iTechArt.models;

import java.util.Objects;

public class Author {
    private int id;
    private String firstname;
    private String surname;
    private String imagePath;

    public Author() {
    }

    public Author(int id, String firstname, String surname, String imagePath) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.imagePath = imagePath;
    }

    public Author(String firstname, String surname, String imagePath) {
        this.firstname = firstname;
        this.surname = surname;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(firstname, author.firstname) &&
                Objects.equals(surname, author.surname) &&
                Objects.equals(imagePath, author.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, surname, imagePath);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", name='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
