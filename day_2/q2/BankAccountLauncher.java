import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

abstract class BankAccount {
  int id;
  String name;
  String branch;
  float balance;

  abstract void withdraw(int amount);

  /**
   * @return String
   */
  public String toString() {
    return """
    Account Details:
      - ID: %s
      - Name: %s
      - Branch: %s
      - Balance: %s
    """
        .formatted(id, name, branch, balance);
  }
}

class HDFCBankAccount extends BankAccount {
  static Map<Integer, HDFCBankAccount> idToAccountMap = new HashMap<>();
  private static Map<Integer, String> idToPassword = new HashMap<>();

  private static int accountsCount;
  private int id;

  HDFCBankAccount(String name, String password) {
    accountsCount++;
    id = accountsCount;

    this.name = name;

    idToPassword.put(id, password);
    idToAccountMap.put(id, this);
  }

  HDFCBankAccount(String name, String password, String branch, float balance) {
    accountsCount++;
    id = accountsCount;

    this.name = name;
    this.branch = branch;
    this.balance = balance;

    idToPassword.put(id, password);
    idToAccountMap.put(id, this);
  }

  /**
   * @return String
   */
  public int getId() {
    return id;
  }

  /**
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return String
   */
  public String getBranch() {
    return branch;
  }

  /**
   * @param branch
   */
  public void setBranch(String branch) {
    this.branch = branch;
  }

  /**
   * @return float
   */
  public float getBalance() {
    return balance;
  }

  /**
   * @param balance
   */
  public void setBalance(float balance) {
    this.balance = balance;
  }

  /**
   * check for login password check
   *
   * @return boolean
   */
  static boolean checkPassword(int id, String password) {
    return idToPassword.get(id).equals(password);
  }

  /**
   * used to withdraw amount from the account
   *
   * @param amount
   */
  void withdraw(int amount) {
    if (balance < amount || amount < 0) {
      System.out.println("Invalid amount");
      return;
    }

    this.balance -= amount;
    // withdraw logic
    System.out.println("Amount withdraw successfully.");
  }

  /**
   * used to deposit amount to a account
   *
   * @param dest
   * @param amount
   */
  static void deposit(BankAccount dest, int amount) {
    if (amount < 0) {
      System.out.println("Invalid amount");
      return;
    }

    dest.balance += amount;
    System.out.println("Amount depositted successfully.");
  }

  /**
   * transfer a certain amount from one account to another account
   *
   * @param source
   * @param dest
   * @param amount
   */
  static void transferAmount(BankAccount source, BankAccount dest, int amount) {
    if (amount < 0) {
      System.out.println("Invalid amount");
      return;
    }

    if (source.balance < amount) {
      System.out.println("Insufficient balance");
      return;
    }

    source.balance -= amount;
    dest.balance += amount;
    System.out.println("Transaction completed successfully!");
  }
}

public class BankAccountLauncher {
  private static void printMenu() {
    System.out.println(
        """
        --- Bank Operations ---
        1. Create Account
        2. Deposit
        3. Withdraw
        4. Transfer
        5. View Details
        6. Exit
        """);
    System.out.print("Choice: ");
  }

  public static void main(String[] args) {
    BankAccount account1 = new HDFCBankAccount("x1", "p1");
    System.out.println(HDFCBankAccount.idToAccountMap);

    System.out.println();
    BankAccount account2 = new HDFCBankAccount("x2", "p2", "y2", 1000);
    System.out.println(HDFCBankAccount.idToAccountMap);

    HDFCBankAccount.transferAmount(account1, account2, 1000);
    HDFCBankAccount.deposit(account1, 1000);

    System.out.println();
    HDFCBankAccount.transferAmount(account1, account2, 500);

    System.out.println(account1);
    System.out.println(account2);

    Scanner sc = new Scanner(System.in);

    // user input
    printMenu();
    int choice = sc.nextInt();

    sc.nextLine();
    do {
      switch (choice) {
        case 1 -> {
          System.out.print("Enter name: ");
          String name = sc.nextLine();

          System.out.print("Enter branch: ");
          String branch = sc.nextLine();

          System.out.print("Enter initial balance: ");
          float amount = sc.nextFloat();

          sc.nextLine();
          System.out.print("Enter password: ");
          String password = sc.nextLine();

          System.out.print("Enter cPassword: ");
          String cPassword = sc.nextLine();

          if (!password.equals(cPassword)) {
            throw new Error("Password Mismatch");
          }

          HDFCBankAccount newAccount = new HDFCBankAccount(name, password, branch, amount);
          HDFCBankAccount.idToAccountMap.put(newAccount.getId(), newAccount);
        }
        case 2 -> {
          System.out.print("Enter account no. to deposit: ");
          int accoutNo = sc.nextInt();

          if (!HDFCBankAccount.idToAccountMap.containsKey(accoutNo)) {
            throw new Error("Invalid Account number.");
          }

          System.out.print("Enter amount to deposit: ");
          int amount = sc.nextInt();

          HDFCBankAccount.deposit(HDFCBankAccount.idToAccountMap.get(accoutNo), amount);
        }
        case 3 -> {
          System.out.print("Enter account no. to withdraw from: ");
          int accoutNo = sc.nextInt();

          if (!HDFCBankAccount.idToAccountMap.containsKey(accoutNo)) {
            throw new Error("Invalid Account number.");
          }

          BankAccount account = HDFCBankAccount.idToAccountMap.get(accoutNo);

          sc.nextLine();
          System.out.print("Enter password: ");
          String password = sc.nextLine();

          if (!HDFCBankAccount.checkPassword(accoutNo, password)) {
            throw new Error("Invalid password.");
          }

          System.out.print("Enter amount to withdraw: ");
          int amount = sc.nextInt();

          account.withdraw(amount);
        }
        case 4 -> {
          System.out.print("Enter account no. to withdraw from: ");
          int srcAccoutNo = sc.nextInt();

          if (!HDFCBankAccount.idToAccountMap.containsKey(srcAccoutNo)) {
            throw new Error("Invalid Account number.");
          }

          sc.nextLine();
          System.out.print("Enter password: ");
          String password = sc.nextLine();

          if (!HDFCBankAccount.checkPassword(srcAccoutNo, password)) {
            throw new Error("Invalid password.");
          }

          BankAccount srcAccount = HDFCBankAccount.idToAccountMap.get(srcAccoutNo);

          System.out.print("Enter account no. to deposit to: ");
          int destAccoutNo = sc.nextInt();

          if (!HDFCBankAccount.idToAccountMap.containsKey(destAccoutNo)) {
            throw new Error("Invalid Account number.");
          }

          BankAccount destAccount = HDFCBankAccount.idToAccountMap.get(destAccoutNo);

          System.out.print("Enter amount to transfer: ");
          int amount = sc.nextInt();

          HDFCBankAccount.transferAmount(srcAccount, destAccount, amount);
        }
        case 5 -> {
          System.out.print("Enter account no.: ");
          int accoutNo = sc.nextInt();

          if (!HDFCBankAccount.idToAccountMap.containsKey(accoutNo)) {
            throw new Error("Invalid Account number.");
          }

          BankAccount account = HDFCBankAccount.idToAccountMap.get(accoutNo);
          System.out.println(account);
        }
        default -> System.out.println("Invalid choice!");
      }

      printMenu();
      choice = sc.nextInt();
    } while (choice != 6);

    sc.close();
  }
}

// make BankAccount abstract class
