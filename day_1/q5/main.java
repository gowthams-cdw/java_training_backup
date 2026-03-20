class Main {
  /** 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Length of command line args: " + args.length);

    if (args.length > 0) {
      System.out.println();
    }

    for (int i = 0; i < args.length; i++) {
      System.out.println("Arg " + (i + 1) + args[i]);
    }
  }
}
