import services.LibraryService;

/**
* entry point for running library application
*/
class LibraryTracker {
    /**
     * @param args
     */
    public static void main(String[] args) {
        LibraryService service = new LibraryService();

        // create books
        service.createBook("Clean Code", "Robert Marlin", "Programming");
        service.createBook("Effective Java", "Joshua Bloch", "Programming");
        service.createBook("Atomic Habits", "James Clear", "Self Help");
        service.createBook("The Hobbit", "Toikien", "Fantasy");

        // borrow books
        service.borrowBook(1, "Alice");
        service.borrowBook(2, "Alice");
        service.borrowBook(3, "Bob");

        // return book
        service.returnBook(1);

        System.out.println("Available Books: ");
        System.out.println(service.getAllAvailableBooks());

        System.out.println("Borrowed Books: ");
        System.out.println(service.getBorrowedBooks());

        System.out.println("Students with atleast 2 borrows");
        System.out.println(service.getStudentsWithMinimumBorrowAction());

        System.out.println("Top 3 Borrowed Books: ");
        System.out.println(service.getTop3BorrowedBooks());

        System.out.println("Books By Category Count");
        System.out.println(service.getCountBooksByCategory());

        System.out.println("Service State");
        System.out.println(service);
    }
}

/*
   - collection vs collections
   - comparitor vs comparable
*/
