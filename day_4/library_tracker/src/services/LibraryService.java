package services;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import models.Book;
import models.BorrowRecord;
import utils.Validators;

/**
* services to perform CRUD operations on Book and BorrowRecord classes
*/
public class LibraryService {
  private int nextBookId = 1;

  private Map<Integer, Book> bookIdToBookMap;
  private Map<String, Set<Integer>> categoryToBookIdMap;

  private Set<Integer> currentlyBorrowedBooks;
  private List<BorrowRecord> borrowHistory;

  private Deque<Integer> recentlyViewedBooks;

  public LibraryService() {
    bookIdToBookMap = new HashMap<>();
    categoryToBookIdMap = new HashMap<>();

    currentlyBorrowedBooks =
        new TreeSet<>(
            (bid1, bid2) -> {
              Book book1 = bookIdToBookMap.get(bid1);
              Book book2 = bookIdToBookMap.get(bid2);

              return book1.getBorrowedOn().compareTo(book2.getBorrowedOn());
            });
    borrowHistory = new ArrayList<>();

    recentlyViewedBooks = new ArrayDeque<>();
  }

  /**
   * used to create book and add to id map and category map
   *
   * @param title
   * @param author
   * @param category
   * @return Book
   */
  public Book createBook(String title, String author, String category) {
    if (!Validators.StringValidator(title)
        || !Validators.StringValidator(author)
        || !Validators.StringValidator(category)) {
      throw new Error("Invalid book details.");
    }

    Book book = new Book(nextBookId, title, author, category);
    bookIdToBookMap.put(nextBookId, book);

    if (!categoryToBookIdMap.containsKey(category)) {
      categoryToBookIdMap.put(category, new LinkedHashSet<>());
    }
    categoryToBookIdMap.get(category).add(nextBookId);

    nextBookId++;
    System.out.println("Created book successfully.");
    return book;
  }

  /**
   * validate the existance of book based on id if not present throws error if present return book
   *
   * @param id
   * @return Book
   */
  public Book getBookById(int id) {
    if (!bookIdToBookMap.containsKey(id)) {
      throw new Error("Invalid book id, Book doesn't exists.");
    }

    if (recentlyViewedBooks.contains(id)) {
      recentlyViewedBooks.remove(id);
    }
    recentlyViewedBooks.addFirst(id);
    if (recentlyViewedBooks.size() > 5) {
      recentlyViewedBooks.removeLast();
    }

    return bookIdToBookMap.get(id);
  }

  /**
   * function that returns all books
   *
   * @return List<Book>
   */
  public List<Book> getAllBooks() {
    return new ArrayList<>(bookIdToBookMap.values());
  }

  /**
   * check for category to present if not present throws error if present returns all books for the
   * specified category
   *
   * @param categoryId
   * @return List<Book>
   */
  public List<Book> getAllBooksByCategory(String categoryId) {
    if (!categoryToBookIdMap.containsKey(categoryId)) {
      throw new Error("Invalid category.");
    }
    List<Book> bookEntries = new LinkedList<>();
    for (int bookId : categoryToBookIdMap.get(categoryId)) {
      bookEntries.add(bookIdToBookMap.get(bookId));
    }

    return bookEntries;
  }

  /**
   * validate the book from bookid if not present throws error if update the book data if category
   * changed remove from original bookid from the previous category, then add to new category map
   *
   * @param bookId
   * @param title
   * @param author
   * @param category
   * @return Book
   */
  public Book updateBook(int bookId, String title, String author, String category) {
    if (!bookIdToBookMap.containsKey(bookId)) {
      throw new Error("Invalid book id, Book doesn't exists.");
    }

    Book originalBook = bookIdToBookMap.get(bookId);
    String originalCategory = originalBook.getCategory();

    if (originalBook.isBorrowed()) {
      throw new Error("Cannot update borrowed book.");
    }

    originalBook.setTitle(title);
    originalBook.setAuthor(author);
    originalBook.setCategory(category);

    if (originalCategory != category) {
      categoryToBookIdMap.get(originalCategory).remove(bookId);
      categoryToBookIdMap.get(category).add(bookId);
    }

    System.out.println("Book with bookid " + bookId + " has been updated successfully.");
    return originalBook;
  }

  /**
   * checks for valid bookid if not present throws error if present delete the book and remove from
   * bookid map and category id map
   *
   * @param bookId
   */
  public void deleteBook(int bookId) {
    if (!bookIdToBookMap.containsKey(bookId)) {
      throw new Error("Invalid book id, Book doesn't exists.");
    }

    Book book = bookIdToBookMap.get(bookId);
    if (book.isBorrowed()) {
      throw new Error("Cannot delete borrowed book.");
    }

    bookIdToBookMap.remove(bookId);

    String category = book.getCategory();
    categoryToBookIdMap.get(category).remove(bookId);
    System.out.println("Book with book id " + bookId + " is deleted successfully.");
  }

  /**
   * validates bookid change the book data for borrowed status
   *
   * @param bookId
   * @param borrowedBy
   */
  public void borrowBook(int bookId, String borrowedBy) {
    if (!bookIdToBookMap.containsKey(bookId)) {
      throw new Error("Invalid book id, Book doesn't exists.");
    }

    Book book = bookIdToBookMap.get(bookId);
    if (book.isBorrowed()) {
      throw new Error("Cannot borrow already borrowed book.");
    }

    LocalDate borrowDate = LocalDate.now();

    book.setBorrowed(true);
    book.setBorrowedBy(borrowedBy);
    book.setBorrowedOn(borrowDate);

    BorrowRecord borrowRecord = new BorrowRecord(bookId, borrowedBy, borrowDate, "BORROW");
    borrowHistory.add(borrowRecord);
    currentlyBorrowedBooks.add(bookId);
  }

  /**
   * validates book id check if the book is borrowed if borrowed change the status to return and
   * remove from borrow map
   *
   * @param bookId
   */
  public void returnBook(int bookId) {
    if (!bookIdToBookMap.containsKey(bookId)) {
      throw new Error("Invalid book id, Book doesn't exists.");
    }

    Book book = bookIdToBookMap.get(bookId);
    if (!book.isBorrowed()) {
      throw new Error("Cannot return unborrowed book.");
    }

    LocalDate returnDate = LocalDate.now();
    BorrowRecord returnRecord =
        new BorrowRecord(bookId, book.getBorrowedBy(), returnDate, "RETURN");

    currentlyBorrowedBooks.remove(bookId);

    book.setBorrowed(false);
    book.setBorrowedBy(null);
    book.setBorrowedOn(null);

    borrowHistory.add(returnRecord);
  }

  /**
   * returns all non borrowed books
   *
   * @return List<Book>
   */
  public List<Book> getAllAvailableBooks() {
    List<Book> books =
        bookIdToBookMap.values().stream()
            .filter(b -> !b.isBorrowed())
            .sorted(Comparator.comparing(Book::getTitle))
            .toList();
    return books;
  }

  /**
   * retuns books count based on category
   *
   * @return Map<String, Integer>
   */
  public Map<String, Integer> getCountBooksByCategory() {
    Map<String, Integer> countMap =
        categoryToBookIdMap.entrySet().stream()
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().size()));
    return countMap;
  }

  /**
   * returns all borrowed books
   *
   * @return List<Book>
   */
  public List<Book> getBorrowedBooks() {
    List<Book> books =
        bookIdToBookMap.values().stream()
            .filter(b -> b.isBorrowed())
            .sorted(Comparator.comparing(Book::getBorrowedOn).reversed())
            .toList();
    return books;
  }

  /**
   * returns top 3 borrowed books
   *
   * @return List<Book>
   */
  public List<Book> getTop3BorrowedBooks() {
    List<Book> books =
        borrowHistory.stream()
            .filter(h -> h.getAction().equals("BORROW"))
            .collect(Collectors.groupingBy(BorrowRecord::getBookId, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
            .limit(3)
            .map(Map.Entry::getKey)
            .map(bookIdToBookMap::get)
            .toList();
    return books;
  }

  /**
   * returns student that borrows atleast 2 times
   *
   * @return List<String>
   */
  public List<String> getStudentsWithMinimumBorrowAction() {
    List<String> students =
        borrowHistory.stream()
            .filter(h -> h.getAction().equals("BORROW"))
            .collect(Collectors.groupingBy(BorrowRecord::getStudent, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(e -> e.getValue() >= 2)
            .map(Map.Entry::getKey)
            .toList();
    return students;
  }

  /**
   * returns books that are borrowed on specific date range
   *
   * @param after
   * @param before
   * @return List<Book>
   */
  public List<Book> getBooksInDateRange(LocalDate after, LocalDate before) {
    List<Book> books =
        borrowHistory.stream()
            .filter(h -> h.getAction().equals("BORROW"))
            .filter(h -> h.getDate().isBefore(before) && h.getDate().isAfter(after))
            .map(b -> getBookById(b.getBookId()))
            .toList();
    return books;
  }

  /**
   * @return String
   */
  public String toString() {
    return """
    Library Service Details:
        - Book ID Map: %s
        - Book Category Map: %s
        - Currently Borrowed Books: %s
        - Borrow History: %s
        - Recently Viewed Books: %s
    """
        .formatted(
            bookIdToBookMap,
            categoryToBookIdMap,
            currentlyBorrowedBooks,
            borrowHistory,
            recentlyViewedBooks);
  }
}
