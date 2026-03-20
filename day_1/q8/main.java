protected class Base {
  /** 
   * @return String
   */
  String method() {
    return "Wake up, Neo";
  }
}

class Derived extends Base {
  public void useD() {
    Base b = new Base();

    System.out.println("base says, " + s.method());
  }
}
