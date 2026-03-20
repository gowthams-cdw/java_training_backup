class Main {
  /** 
   * @param args
   */
  public static void main(String[] args) {
    byte b = 9;
    short s = b;
    int i = s;
    long l = i;
    float f = l;
    double d = f;

    System.out.println("Implicit Type Casting: ");
    System.out.println("byte -> short: " + s);
    System.out.println("short -> int: " + i);
    System.out.println("int -> long: " + l);
    System.out.println("long -> float: " + f);
    System.out.println("float -> double: " + d);

    System.out.println();

    double d2 = 99.99;
    float f2 = (float)d2;
    long l2 = (long)f2;
    int i2 = (int)l2;
    short s2 = (short)i2;
    byte b2 = (byte)s2;

    System.out.println("Explicit Type Casting: ");
    System.out.println("double -> float: " + f2);
    System.out.println("float -> long: " + l2);
    System.out.println("long -> int: " + l2);
    System.out.println("int -> short: " + s2);
    System.out.println("short -> byte: " + b2);
  }
}
