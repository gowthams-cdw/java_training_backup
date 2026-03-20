class Test {
  int a;

  Test() {
    this.a = 9;
  }
}

class Main {
  static void changeInt(int a) {
    a = 99;
  }

  static void changeObj(Test t) {
    t.a = 99;
  }

  public static void main(String[] args) {
    int intA = 9;
    changeInt(intA);
    System.out.println("The value of intA is: " + intA);

    Test t = new Test();
    changeObj(t);
    System.out.println("The value of Test.a is: " + t.a);
  }
}
