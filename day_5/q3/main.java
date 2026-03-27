import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

class CompleteableDemo {
  // private static final String URL = "jdbc:postgresql://localhost:5432/student";
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
      System.out.print("Enter sudent id to generate report: ");
      int id = sc.nextInt();

      // CompletableFuture<Void> marksFuture =
      // CompletableFuture.runAsync(
      CompletableFuture<String> marksFuture =
          CompletableFuture.supplyAsync(
              () -> {
                String query =
                    "select max(marks) as high, avg(marks) as avg from marks where id = ?";
                try (PreparedStatement pStatement = conn.prepareStatement(query)) {
                  pStatement.setInt(1, id);

                  try (ResultSet rs = pStatement.executeQuery()) {
                    if (rs.next()) {
                      int maxMark = rs.getInt("high");
                      double avgMark = rs.getDouble("avg");

                      return "i. max mark: " + maxMark + "\n" + "ii. avg mark: " + avgMark;
                    }
                  }
                } catch (SQLException e) {
                  return "Error fetching student marks.";
                }
                return "";
              });

      // CompletableFuture<Void> attendenceFuture =
      //     CompletableFuture.runAsync(
      CompletableFuture<String> attendanceFuture =
          CompletableFuture.supplyAsync(
              () -> {
                String query =
                    "select count(*) as total, "
                        + "count(*) filter (where status = 'present') as present "
                        + "from attendence where id = ?";
                try (PreparedStatement pStatement = conn.prepareStatement(query)) {
                  pStatement.setInt(1, id);

                  try (ResultSet rs = pStatement.executeQuery()) {
                    if (rs.next()) {
                      int total = rs.getInt("total");
                      int present = rs.getInt("present");

                      return "iii. Total: "
                          + total
                          + "\n"
                          + "iv. Present days: "
                          + present
                          + "\n"
                          + "v. Attendance %: "
                          + total / present;
                    }
                  }
                } catch (SQLException e) {
                  return "Error fetching student marks.";
                }
                return "";
              });

      System.out.println("Student Report: ");

      CompletableFuture.allOf(marksFuture, attendanceFuture).join();
      System.out.println(marksFuture.join());
      System.out.println(attendanceFuture.join());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sc.close();
  }
}
