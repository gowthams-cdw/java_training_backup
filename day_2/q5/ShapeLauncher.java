import java.util.*;

abstract class Shape {
  static Float maxArea = Float.MIN_VALUE;
  static String maxAreaShape;
  static Float maxPerimeter = Float.MIN_VALUE;
  static String maxPerimeterShape;

  abstract float calcArea();

  abstract float calcPerimeter();

  /** Method to find max area and perimeter of the available shapes */
  static void printMax() {
    System.out.println("Max Area: " + maxArea);
    System.out.println("Max Perimeter: " + maxPerimeter);

    System.out.println("Max Area Shape: " + maxAreaShape);
    System.out.println("Max Perimeter Shape: " + maxPerimeterShape);
  }
}

class Circle extends Shape {
  float radius, area, perimeter;

  Circle(float radius) {
    this.radius = radius;
  }

  /**
   * @return float
   */
  public float getRadius() {
    return radius;
  }

  /**
   * @param radius
   */
  public void setRadius(float radius) {
    this.radius = radius;
  }

  /**
   * @return float
   */
  public float getArea() {
    return area;
  }

  /**
   * @param area
   */
  public void setArea(float area) {
    this.area = area;
  }

  /**
   * @return float
   */
  public float getPerimeter() {
    return perimeter;
  }

  /**
   * @param perimeter
   */
  public void setPerimeter(float perimeter) {
    this.perimeter = perimeter;
  }

  /*
   * calculate area and store it areas map
   * @return area
   */
  float calcArea() {
    area = 3.14f * radius * radius;
    if (Shape.maxArea < area) {
      Shape.maxArea = area;
      Shape.maxAreaShape = "Circle";
    }
    return area;
  }

  /*
   * calculate perimeter and store it areas map
   * @return perimeter
   */
  float calcPerimeter() {
    perimeter = 2 * 3.14f * radius;
    if (Shape.maxPerimeter < perimeter) {
      Shape.maxPerimeter = perimeter;
      Shape.maxPerimeterShape = "Circle";
    }
    return perimeter;
  }

  public String toString() {
    return """
    circle:
      - Area: %s
      - Perimeter: %s
    """
        .formatted(area, perimeter);
  }
}

class Rectangle extends Shape {
  float length, width, area, perimeter;

  Rectangle(float length, float width) {
    this.length = length;
    this.width = width;
  }

  /**
   * @return float
   */
  public float getLength() {
    return length;
  }

  /**
   * @param length
   */
  public void setLength(float length) {
    this.length = length;
  }

  /**
   * @return float
   */
  public float getWidth() {
    return width;
  }

  /**
   * @param width
   */
  public void setWidth(float width) {
    this.width = width;
  }

  /**
   * @return float
   */
  public float getArea() {
    return area;
  }

  /**
   * @param area
   */
  public void setArea(float area) {
    this.area = area;
  }

  /**
   * @return float
   */
  public float getPerimeter() {
    return perimeter;
  }

  /**
   * @param perimeter
   */
  public void setPerimeter(float perimeter) {
    this.perimeter = perimeter;
  }

  /*
   * calculate area and store it areas map
   * @return area
   */
  float calcArea() {
    area = length * width;
    if (Shape.maxArea < area) {
      Shape.maxArea = area;
      Shape.maxAreaShape = "Circle";
    }
    return area;
  }

  /*
   * calculate perimeter and store it areas map
   * @return perimeter
   */
  float calcPerimeter() {
    perimeter = 2 * (length + width);
    if (Shape.maxPerimeter < perimeter) {
      Shape.maxPerimeter = perimeter;
      Shape.maxPerimeterShape = "Circle";
    }
    return perimeter;
  }

  public String toString() {
    return """
    Rectangle:
      - Area: %s
      - Perimeter: %s
    """
        .formatted(area, perimeter);
  }
}

class Triangle extends Shape {
  float side1, side2, side3, base, height, area, perimeter;

  Triangle(float side1, float side2, float side3, float base, float height) {
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
    this.base = base;
    this.height = height;
  }

  /**
   * @return float
   */
  public float getSide1() {
    return side1;
  }

  /**
   * @param side1
   */
  public void setSide1(float side1) {
    this.side1 = side1;
  }

  /**
   * @return float
   */
  public float getSide2() {
    return side2;
  }

  /**
   * @param side2
   */
  public void setSide2(float side2) {
    this.side2 = side2;
  }

  /**
   * @return float
   */
  public float getSide3() {
    return side3;
  }

  /**
   * @param side3
   */
  public void setSide3(float side3) {
    this.side3 = side3;
  }

  /**
   * @return float
   */
  public float getBase() {
    return base;
  }

  /**
   * @param base
   */
  public void setBase(float base) {
    this.base = base;
  }

  /**
   * @return float
   */
  public float getHeight() {
    return height;
  }

  /**
   * @param height
   */
  public void setHeight(float height) {
    this.height = height;
  }

  /**
   * @return float
   */
  public float getArea() {
    return area;
  }

  /**
   * @param area
   */
  public void setArea(float area) {
    this.area = area;
  }

  /**
   * @return float
   */
  public float getPerimeter() {
    return perimeter;
  }

  /**
   * @param perimeter
   */
  public void setPerimeter(float perimeter) {
    this.perimeter = perimeter;
  }

  /*
   * calculate area and store it areas map
   * @return area
   */
  float calcArea() {
    area = 0.5f * base * height;
    if (Shape.maxArea < area) {
      Shape.maxArea = area;
      Shape.maxAreaShape = "Circle";
    }
    return area;
  }

  /*
   * calculate perimeter and store it areas map
   * @return perimeter
   */
  float calcPerimeter() {
    perimeter = side1 + side2 + side3;
    if (Shape.maxPerimeter < perimeter) {
      Shape.maxPerimeter = perimeter;
      Shape.maxPerimeterShape = "Circle";
    }
    return perimeter;
  }

  public String toString() {
    return """
    Triangle:
      - Area: %s
      - Perimeter: %s
    """
        .formatted(area, perimeter);
  }
}

public class ShapeLauncher {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter circle radius: ");
    Float radius = sc.nextFloat();
    Circle c = new Circle(radius);
    c.calcArea();
    c.calcPerimeter();

    System.out.println();

    System.out.print("Enter rectangle length: ");
    Float length = sc.nextFloat();

    System.out.print("Enter rectangle width: ");
    Float width = sc.nextFloat();
    Rectangle r = new Rectangle(length, width);
    r.calcArea();
    r.calcPerimeter();

    System.out.println();

    System.out.print("Enter triangle side1: ");
    Float side1 = sc.nextFloat();

    System.out.print("Enter triangle side2: ");
    Float side2 = sc.nextFloat();

    System.out.print("Enter triangle side3: ");
    Float side3 = sc.nextFloat();

    System.out.print("Enter triangle base: ");
    Float base = sc.nextFloat();

    System.out.print("Enter triangle height: ");
    Float height = sc.nextFloat();

    Triangle t = new Triangle(side1, side2, side3, base, height);
    t.calcArea();
    t.calcPerimeter();

    System.out.println(c);
    System.out.println(r);
    System.out.println(t);

    System.out.println(
        """
        Enter Shape:
        1. Circle
        2. Rectangle
        3. Triangle\
        """);
    Shape s;
    int shapeChoice = sc.nextInt();
    if (shapeChoice == 1) {
      System.out.print("Enter radius: ");
      Float circleRadius = Float.parseFloat(sc.next());
      s = new Circle(circleRadius);
    } else if (shapeChoice == 2) {
      System.out.print("Enter length: ");
      Float rectangleLength = Float.parseFloat(sc.next());

      System.out.print("Enter width: ");
      Float rectangleWidth = Float.parseFloat(sc.next());

      s = new Rectangle(rectangleLength, rectangleWidth);
    } else if (shapeChoice == 3) {
      System.out.print("Enter side1: ");
      Float triangleSide1 = Float.parseFloat(sc.next());

      System.out.print("Enter side2: ");
      Float triangleSide2 = Float.parseFloat(sc.next());

      System.out.print("Enter side3: ");
      Float triangleSide3 = Float.parseFloat(sc.next());

      System.out.print("Enter base: ");
      Float triangleBase = Float.parseFloat(sc.next());

      System.out.print("Enter height: ");
      Float triangleHeight = Float.parseFloat(sc.next());

      s = new Triangle(triangleSide1, triangleSide2, triangleSide3, triangleBase, triangleHeight);
    } else {
      System.out.println("Invalid shape");
      sc.close();
      return;
    }

    s.calcArea();
    s.calcPerimeter();
    System.out.println(s);
    Shape.printMax();

    sc.close();
  }
}

// remove keys like areamap and perimeter map
