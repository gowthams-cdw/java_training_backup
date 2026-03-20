import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

class CompleteableDemo {
  private static final String URL = "jdbc:postgresql://localhost:5432/student";
  private static final String USERNAME = "gowthams";
  private static final String PASSWORD = "SGM02468";

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
      System.out.print("Enter sudent id to generate report: ");
      int id = sc.nextInt();

      CompletableFuture<Void> marksFuture =
          CompletableFuture.runAsync(
              () -> {
                String query =
                    "select max(marks) as high, avg(marks) as avg from marks where id = ?";
                try (PreparedStatement pStatement = conn.prepareStatement(query)) {
                  pStatement.setInt(1, id);

                  try (ResultSet rs = pStatement.executeQuery()) {
                    if (rs.next()) {
                      int maxMark = rs.getInt("high");
                      double avgMark = rs.getDouble("avg");

                      System.out.println("i. max mark: " + maxMark);
                      System.out.println("ii. avg mark: " + avgMark);
                    }
                  }
                } catch (SQLException e) {
                  System.out.println("Error fetching student marks.");
                }
              });

      CompletableFuture<Void> attendenceFuture =
          CompletableFuture.runAsync(
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

                      System.out.println("iii. Total: " + total);
                      System.out.println("iv. Present days: " + present);
                      System.out.println("v. Attendence percentage: " + total / present);
                    }
                  }
                } catch (SQLException e) {
                  System.out.println("Error fetching student marks.");
                }
              });

      System.out.println("Student Report: ");
      // CompletableFuture.allOf(marksFuture, attendenceFuture).join();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sc.close();
  }
}
