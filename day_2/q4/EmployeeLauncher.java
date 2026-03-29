import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
  static Map<Integer, Employee> idToEmployeeMap = new HashMap<>();

  private static int nextEmployeeId = 1;

  private int id;
  String name;
  int serviceYears;
  float wage;

  Employee() {
    this.id = nextEmployeeId;
    idToEmployeeMap.put(nextEmployeeId, this);
    nextEmployeeId++;
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
  String getName() {
    return name;
  }

  /**
   * checks name not to be empty
   *
   * @param name
   */
  void setName(String name) {
    if (name.isEmpty()) {
      throw new Error("Invalid name.");
    }

    this.name = name;
  }

  /**
   * @return int
   */
  int getServiceYears() {
    return serviceYears;
  }

  /**
   * check service years for non negative
   *
   * @param serviceYears
   */
  void setServiceYears(int serviceYears) {
    if (serviceYears < 0) {
      throw new Error("Invalid service years.");
    }

    this.serviceYears = serviceYears;
  }

  /**
   * @return float
   */
  float getWage() {
    return wage;
  }

  /**
   * checks for minimum wage
   *
   * @param wage
   */
  void setWage(float wage) {
    if (wage < 20000) {
      throw new Error("Invalid wage.");
    }

    this.wage = wage;
  }

  /**
   * @return String
   */
  public String toString() {
    float totalIncome = wage * (serviceYears / 2);

    return """
    Employee Details:
      - Name: %s
      - Service Years: %s
      - Wage: %s
      - Total Income: %s
    """
        .formatted(name, serviceYears, wage, totalIncome);
  }
}

public class EmployeeLauncher {
  private static void printMenu() {
    System.out.println(
        """
        --- Bank Operations ---
        1. Create Employee
        2. View Details
        3. Exit
        """);
    System.out.print("Choice: ");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    printMenu();
    int choice = sc.nextInt();

    do {
      switch (choice) {
        case 1 -> {
          Employee employee = new Employee();

          sc.nextLine();
          System.out.print("Enter name: ");
          employee.setName(sc.nextLine());

          System.out.print("Enter service years: ");
          employee.setServiceYears(sc.nextInt());

          System.out.print("Enter wage: ");
          employee.setWage(sc.nextFloat());
        }
        case 2 -> {
          System.out.print("Enter employee id: ");
          int id = sc.nextInt();

          if (!Employee.idToEmployeeMap.containsKey(id)) {
            throw new Error("Invalid emp id");
          }

          Employee employee = Employee.idToEmployeeMap.get(id);
          System.out.println(employee);
        }
        default -> System.out.println("Invalid choice");
      }

      printMenu();
      choice = sc.nextInt();
    } while (choice != 3);

    sc.close();
  }
}

// .equals
// var name
// case syntax
