import java.util.*;

class Main {
  /** 
   * @param args
   */
  public static void main(String[] args) {
    Set<Integer> l1 = new HashSet();
    Integer[] arr = {1, 2, 3, 4, 5};

    Collections.addAll(l1, arr);

    Integer[] arr2 = {1, 2, 3, 4, 6};
    l1.retainAll(Arrays.asList(arr2));

    System.out.println(l1);
  }
}

/*
 - what if try to sort an array that have null value in it
 - priority quese vs treeset
 - lru cache
 - can map have null as key or value
 - diff types of map imp
 - what r like streams and terminal op
 - :: symbol
 - sequential and parallel stream and all its pros and trade offs
*/
