package by.iTechArt.models;

import java.time.LocalDate;
import java.util.Objects;

public class LibUser {

  private int id;
  private String firstname;
  private String surname;
  private String middlename;
  private String passportID;
  private String address;
  private LocalDate dateOfBirth;
  private String email;

  public LibUser(int id, String firstname, String surname,
                 String middlename, String passportID,
                 String address, LocalDate dateOfBirth, String email) {
    this.id = id;
    this.firstname = firstname;
    this.surname = surname;
    this.middlename = middlename;
    this.passportID = passportID;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
  }

  public LibUser(String firstname, String surname, String middlename,
                 String passportID, String address,
                 LocalDate dateOfBirth, String email) {
    this.firstname = firstname;
    this.surname = surname;
    this.middlename = middlename;
    this.passportID = passportID;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
  }

  public LibUser() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getMiddlename() {
    return middlename;
  }

  public void setMiddleName(String middleName) {
    this.middlename = middleName;
  }

  public String getPassportID() {
    return passportID;
  }

  public void setPassportID(String passportID) {
    this.passportID = passportID;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Libreader{" +
      "id=" + id +
      ", name='" + firstname + '\'' +
      ", surname='" + surname + '\'' +
      ", middleName='" + middlename + '\'' +
      ", passportID='" + passportID + '\'' +
      ", address='" + address + '\'' +
      ", dateOfBirth=" + dateOfBirth +
      ", email='" + email + '\'' +
      '}';
  }
}
