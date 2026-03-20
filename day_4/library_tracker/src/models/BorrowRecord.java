package models;

import java.time.LocalDate;

/** stores the properties of BorrowRecord */
public class BorrowRecord {
  private int bookId;
  String student;
  LocalDate date;
  String action;

  public BorrowRecord(int bookId, String student, LocalDate date, String action) {
    this.bookId = bookId;
    this.student = student;
    this.date = date;
    this.action = action;
  }

  /**
   * @return int
   */
  public int getBookId() {
    return bookId;
  }

  /**
   * @return String
   */
  public String getStudent() {
    return student;
  }

  /**
   * @param student
   */
  public void setStudent(String student) {
    this.student = student;
  }

  /**
   * @return LocalDate
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * @param date
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * @return String
   */
  public String getAction() {
    return action;
  }

  /**
   * @param action
   */
  public void setAction(String action) {
    this.action = action;
  }

  /**
   * @return String
   */
  public String toString() {
    return """
    Record Details:
        - Book Id: %s
        - Student: %s
        - Data: %s
        - Action: %s
    """
        .formatted(bookId, student, date, action);
  }
}
