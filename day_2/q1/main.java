import java.util.Scanner;

class Student {
  private static int studentsCount;

  private int id;
  String name;
  int age;
  private float[] marks;
  private char grade;

  Student() {
    studentsCount++;
    this.id = studentsCount;
  }

  Student(String name, int age) {
    studentsCount++;
    this.id = studentsCount;

    this.name = name;
    this.age = age;
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
   * @return int
   */
  public int getAge() {
    return age;
  }

  /**
   * @param age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @return float[]
   */
  public float[] getMarks() {
    return marks;
  }

  /**
   * @param marks
   */
  public void setMarks(float[] marks) {
    this.marks = marks;
  }

  /**
   * @return
   */
  public char getGrade() {
    return grade;
  }

  /** Method to calculate grade and set it to grade */
  public void calcGrade() {
    float totalMarks = 0;

    for (float mark : marks) {
      totalMarks += mark;
    }

    float avgMarks = totalMarks / marks.length;

    if (avgMarks >= 90) {
      grade = 'O';
    } else if (avgMarks >= 80) {
      grade = 'A';
    } else if (avgMarks >= 70) {
      grade = 'B';
    } else if (avgMarks >= 60) {
      grade = 'C';
    } else {
      grade = 'F';
    }
  }

  /**
   * @return String
   */
  public String toString() {
    return """
    Studnet Details:
      - ID: %s
      - Name: %s
      - Age: %s
      - Grade: %s
    """
        .formatted(id, name, age, grade);
  }
}

class StudentLauncher {
  public static void main(String[] args) {
    Student s1 = new Student();
    s1.setName("s1");
    s1.setAge(20);
    s1.setMarks(new float[] {90, 80, 70, 90, 90});
    s1.calcGrade();
    System.out.println(s1);

    Student s2 = new Student("s2", 20);
    s2.setMarks(new float[] {70, 60, 60, 90, 50});
    s2.calcGrade();
    System.out.println(s2);

    Scanner sc = new Scanner(System.in);
    Student s3 = new Student();

    System.out.print("Enter student name: ");
    s3.setName(sc.nextLine());

    System.out.print("Enter student age: ");
    s3.setAge(sc.nextInt());

    System.out.print("Enter no. of scores: ");
    int n = sc.nextInt();
    float[] scores = new float[n];
    for (int i = 0; i < n; i++) {
      System.out.print("Enter score " + (i + 1) + ": ");
      scores[i] = sc.nextFloat();
    }
    s3.setMarks(scores);
    s3.calcGrade();
    sc.close();

    System.out.println(s3);
  }
}

// javadoc
// float
// set marks individually
// user input
