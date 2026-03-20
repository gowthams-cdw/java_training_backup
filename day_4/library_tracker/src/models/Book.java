package models;

import java.time.LocalDate;

/**
* stores the properties of Book
*/
public class Book {
    private int id;
    String title;
    String author;
    String category;
    boolean isBorrowed;
    String borrowedBy;
    LocalDate borrowedOn;

    public Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return String
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return boolean
     */
    public boolean isBorrowed() {
        return isBorrowed;
    }

    /**
     * @param isBorrowed
     */
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    /**
     * @return String
     */
    public String getBorrowedBy() {
        return borrowedBy;
    }

    /**
     * @param borrowedBy
     */
    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    /**
     * @return LocalDate
     */
    public LocalDate getBorrowedOn() {
        return borrowedOn;
    }

    /**
     * @param borrowedOn
     */
    public void setBorrowedOn(LocalDate borrowedOn) {
        this.borrowedOn = borrowedOn;
    }

    /**
     * @return String
     */
    public String toString() {
        return """
                Book Details:
                    - Id: %s
                    - Title: %s
                    - Author: %s
                    - Category: %s
                    - Is Borrowed: %s
                    - Borrowed By: %s
                    - Borrowed On: %s
                """.formatted(id, title, author, category, isBorrowed, borrowedBy, borrowedOn);
    }
}
