import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

class JDBCSample {
  // private static final String URL = "jdbc:postgresql://localhost:5432/java_training";
  // private static final String USERNAME = "gowthams";
  // private static final String PASSWORD = "SGM02468";

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Invalid usage: <program_name> <url> <username> <password>");
      return;
    }

    String URL = args[0];
    String USERNAME = args[1];
    String PASSWORD = args[2];

    Scanner sc = new Scanner(System.in);

    try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
      System.out.println("1. Create User\n2. Transact\n3. User detail\n3. Exit\n choice: ");
      int choice = sc.nextInt();

      while (choice != 4) {
        switch (choice) {
          case 1 -> createUser(conn, sc);
          case 2 -> getTransactionInfo(conn, sc);
          case 3 -> showUsers(conn, sc);
          case 4 -> System.out.println("GoodBye");
          default -> System.out.println("Invalid choice.");
        }

        System.out.println("1. Create User\n2. Transact\n3. User detail\n3. Exit\n choice: ");
        choice = sc.nextInt();
      }
    } catch (SQLException e) {
      System.out.println("An error has occured: ");
      e.printStackTrace();
    }

    sc.close();
  }

  public static void createUser(Connection conn, Scanner sc) {
    System.out.print("Enter user name: ");
    String username = sc.next();

    System.out.print("Enter initial amount: ");
    int amount = sc.nextInt();

    if (amount < 0) {
      System.out.println("Invalid amount.");
      return;
    }

    String query = "insert into users (name, amount) values (?, ?)";
    try (PreparedStatement pStatement = conn.prepareStatement(query)) {
      pStatement.setString(1, username);
      pStatement.setInt(2, amount);

      int rows = pStatement.executeUpdate();

      if (rows == 1) {
        System.out.println("User created successfully.");
      } else {
        System.out.println("User creation failed.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static boolean userExists(Connection conn, int userId) {
    String query = "select 1 from users where id = ?";

    try (PreparedStatement ps = conn.prepareStatement(query)) {
      ps.setInt(1, userId);

      try (var rs = ps.executeQuery()) {
        if (!rs.next()) {
          return false;
        }
        return true;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static void getTransactionInfo(Connection conn, Scanner sc) {
    System.out.print("Enter source account no.: ");
    int src = sc.nextInt();

    System.out.print("Enter destination account no.: ");
    int dest = sc.nextInt();

    System.out.print("Enter amount to transer: ");
    int amount = sc.nextInt();

    if (amount <= 0) {
      System.out.println("Invalid amount.");
      return;
    }

    if (src == dest) {
      System.out.println("Cannot transfer to same account.");
      return;
    }

    if (!userExists(conn, src)) {
      System.out.println("Source user does not exist.");
      return;
    }

    if (!userExists(conn, dest)) {
      System.out.println("Destination user does not exist.");
      return;
    }

    String query = "select amount from users where id = ?";
    try (PreparedStatement pStatement = conn.prepareStatement(query)) {
      pStatement.setInt(1, src);

      try (var rs = pStatement.executeQuery()) {
        if (rs.next()) {
          int currentBalance = rs.getInt("amount");

          if (currentBalance >= amount) {
            System.out.println("Sufficient balance. Processing payments...");
            transferMoney(conn, src, dest, amount);
          } else {
            System.out.println("Insufficient balance");
            return;
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void transferMoney(Connection conn, int src, int dest, int amount) {
    try {
      conn.setAutoCommit(false);

      String query = "update users set amount = amount - ? where id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(query)) {
        pStatement.setInt(1, amount);
        pStatement.setInt(2, src);

        int rows = pStatement.executeUpdate();
        if (rows != 1) {
          throw new SQLException("Debit failed for source account.");
        }
      }

      query = "update users set amount = amount + ? where id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(query)) {
        pStatement.setInt(1, amount);
        pStatement.setInt(2, dest);

        int rows = pStatement.executeUpdate();
        if (rows != 1) {
          throw new SQLException("Debit failed for source account.");
        }
      }

      System.out.println("Transaction completed successfully.");
      conn.commit();
    } catch (SQLException e) {
      try {
        conn.rollback();
        System.out.println("Transaction failed. Rollback successful.");
      } catch (SQLException rollbackEx) {
        System.out.println("Rollback failed!");
        rollbackEx.printStackTrace();
      }
      e.printStackTrace();
    } finally {
      try {
        conn.setAutoCommit(true);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void showUsers(Connection conn, Scanner sc) {
    try {
      System.out.print("Enter user account no.: ");
      int userId = sc.nextInt();

      String query = "select * from users where id = ?";
      try (PreparedStatement pStatement = conn.prepareStatement(query)) {
        pStatement.setInt(1, userId);

        try (var rs = pStatement.executeQuery()) {
          if (rs.next()) {
            // Extract data using column names from your DB schema
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int amount = rs.getInt("amount");

            System.out.println("--- User Found ---");
            System.out.println("ID     : " + id);
            System.out.println("Name   : " + name);
            System.out.println("Balance: " + amount);
          } else {
            System.out.println("No user found with ID: " + userId);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
