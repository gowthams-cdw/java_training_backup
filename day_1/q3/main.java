import java.util.Scanner;

class Resizer {
  /** 
   * @param arr
   * @param newSize
   * @return int[]
   */
  int[] resize(int[] arr, int newSize) {
    int[] newArr = new int[newSize];

    for (int i=0; i<arr.length; i++) {
      newArr[i] = arr[i];
    }

    return newArr;
  }
}

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter arr size: ");
    int n = sc.nextInt();

    int[] arr = new int[n];
    System.out.println("Arr length: " + arr.length);

    System.out.print("Enter new arr size: ");
    n = sc.nextInt();

    Resizer r = new Resizer();
    arr = r.resize(arr, n);

    System.out.println("Modified arr length: " + arr.length);
  }
}
