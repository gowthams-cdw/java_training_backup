class Test {
  Test() {
    System.out.println("Inside Constructor");
  }

  static {
    System.out.println("Inside Static Block");
  }
}

class Main {
  public static void main(String[] args) {
    Test t = new Test();
  }
}
