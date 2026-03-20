import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@FunctionalInterface
interface StringCombiner {
  String combine(String s1, String s2);
}

class FunctionalLauncher {
  /**
   * @param args
   */
  public static void main(String[] args) {
    List<String> products = Arrays.asList("Smartphone", "Socks", "TV", "Desk", "Headphones");

    // predicate to have a condition to filter strings starts with letter S or have length of
    // character 5
    Predicate<String> startsWithSOrLength5 = p -> p.startsWith("S") || p.length() >= 5;
    List<String> filteredProducts =
        products.stream().filter(startsWithSOrLength5).collect(Collectors.toList());
    System.out.println("Filtered Products: " + filteredProducts);

    System.out.println();

    List<Double> pricesUSD = Arrays.asList(9.99, 25.50, 100.0, 4.99);

    // function that takes usd(double format) and converts it into euro(double format)
    Function<Double, Double> pricesEuroFunction = p -> p * 0.93;
    List<Double> pricesEuro = pricesUSD.stream().map(pricesEuroFunction).toList();
    System.out.println("Converted Prices: " + pricesEuro);

    System.out.println();

    List<String> employees = Arrays.asList("Alice", "Bob", "Charlie", "David");

    // takes a string and add "EMPLOYEE" as prefix and and append with the uppercase of input string
    Consumer<String> employeeFormat = e -> System.out.println("EMPLOYEE: " + e.toUpperCase());
    employees.forEach(employeeFormat);

    System.out.println();

    // generates a random percentage from 1 to 15
    Supplier<String> generateRandomPercentage = () -> (int) Math.ceil(Math.random() * 15) + "%";
    System.out.println(generateRandomPercentage.get());

    System.out.println();

    // interfaces that implement StringCombiner and combines string with proper seperation
    StringCombiner joinSpace = (a, b) -> a + " " + b;
    StringCombiner joinWithUpper = (a, b) -> a + " " + b.toUpperCase();

    System.out.println(joinSpace.combine("hello", "world"));
    System.out.println(joinWithUpper.combine("hello", "world"));
  }
}

/*
  - filter cannot be used without stram, since it uses lazy execution of stream implementation, only does things when used with a terminary operation
  - toList(), gives ArrayList
*/
