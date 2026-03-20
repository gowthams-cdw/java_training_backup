import java.util.ArrayList;
import java.util.List;

class IceCream {
  String flavour;
  List<String> toppings = new ArrayList<>();

  private IceCream(Builder builder) {
    this.flavour = builder.flavour;
    this.toppings = builder.toppings;
  }

  static class Builder {
    String flavour;
    List<String> toppings = new ArrayList<>();

    Builder addFlavour(String flavour) {
      this.flavour = flavour;
      return this;
    }

    Builder addToppings(String topping) {
      this.toppings.add(topping);
      return this;
    }

    IceCream build() {
      return new IceCream(this);
    }
  }
}

class Main {
  public static void main(String[] args) {
    IceCream icecream = new IceCream.Builder().addFlavour("Chocolate").addToppings("Extra dark chocolates").build();
    System.out.println(icecream.flavour);
    System.out.println(icecream.toppings);
  }
}
