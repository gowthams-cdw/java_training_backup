class Employee {
  String id;
  String name;
  String role;
  int age;

  Employee(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  void displayData() {
    System.out.println("Details of Employee with ID: " + this.id);
    System.out.println("Name: " + this.name);
    System.out.println("Age: " + this.age);
  }
}

class AdminEmployee extends Employee {
  String[] access;

  AdminEmployee(String id, String name, int age) {
    super(id, name, age);
    this.access = new String[] {"r1"};
  }

  void displayData() {
    super.displayData();

    System.out.println("Available Access: ");
    for (int i=0; i<access.length; i++) {
      System.out.println("Access " + (i+1) + ": " + access[i]);
    }
  }
}

class Main {
  public static void main(String[] args) {
    Employee e = new Employee("x1", "y1", 21);
    e.displayData();

    System.out.println();

    AdminEmployee ae = new AdminEmployee("x2", "y2", 21);
    ae.displayData();
  }
}
