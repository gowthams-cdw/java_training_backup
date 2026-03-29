abstract class Vehicle {
  String brand;
  String model;
  int year;

  Vehicle(String brand, String model, int year) {
    this.brand = brand;
    this.model = model;
    this.year = year;
  }

  /**
   * @return String
   */
  public String getBrand() {
    return brand;
  }

  /**
   * @param brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * @return String
   */
  public String getModel() {
    return model;
  }

  /**
   * @param model
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * @return int
   */
  public int getYear() {
    return year;
  }

  /**
   * @param year
   */
  public void setYear(int year) {
    this.year = year;
  }

  abstract void run();

  /**
   * @return String
   */
  public String toString() {
    return """
    Vehicle Details:
      - Brand: %s
      - Model: %s
      - Year: %s
    """
        .formatted(brand, model, year);
  }
}

abstract class Car extends Vehicle {
  String fuelType;

  Car(String brand, String model, int year, String fuelType) {
    super(brand, model, year);
    this.fuelType = fuelType;
  }

  /**
   * @return String
   */
  public String getFuelType() {
    return fuelType;
  }

  /**
   * @param fuelType
   */
  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  public String toString() {
    return super.toString()
        + """
          - Fuel Type: %s
        """
            .formatted(fuelType);
  }
}

class ElectricCar extends Car {
  int batteryCapacity;
  int timeToFullCharge;
  boolean hasFastCharging;

  ElectricCar(
      String brand,
      String model,
      int year,
      int batterCapacity,
      int timeToFullCharge,
      boolean hasFastCharging) {
    super(brand, model, year, "Electric");
    this.batteryCapacity = batterCapacity;
    this.timeToFullCharge = timeToFullCharge;
    this.hasFastCharging = hasFastCharging;
  }

  /**
   * @return boolean
   */
  public boolean isHasFastCharging() {
    return hasFastCharging;
  }

  /**
   * @param hasFastCharging
   */
  public void setHasFastCharging(boolean hasFastCharging) {
    this.hasFastCharging = hasFastCharging;
  }

  /**
   * @return int
   */
  public int getBatteryCapacity() {
    return batteryCapacity;
  }

  /**
   * @param batteryCapacity
   */
  public void setBatteryCapacity(int batteryCapacity) {
    this.batteryCapacity = batteryCapacity;
  }

  /**
   * @param time
   */
  public void setTimeToFullCharge(int time) {
    this.timeToFullCharge = time;
  }

  /**
   * @return int
   */
  public int getTimeToFullCharge() {
    return timeToFullCharge;
  }

  public String toString() {
    return super.toString()
        + """
          - Battery Capacity: %s
          - Time to full charge: %s
          - Supports fast charging: %s
        """
            .formatted(batteryCapacity, timeToFullCharge, hasFastCharging);
  }

  void run() {
    System.out.println("Electric car is running.");
  }
}

class EngineCar extends Car {
  int fuelCapacity;
  String engineType;

  /**
   * @return int
   */
  public int getFuelCapacity() {
    return fuelCapacity;
  }

  /**
   * @param fuelType
   */
  public void setFuelCapacity(int fuelCapacity) {
    this.fuelCapacity = fuelCapacity;
  }

  /**
   * @return String
   */
  public String getEngineType() {
    return engineType;
  }

  /**
   * @param engineType
   */
  public void setEngineType(String engineType) {
    this.engineType = engineType;
  }

  EngineCar(String brand, String model, int year, int fuelCapacity, String engineType) {
    super(brand, model, year, "Engine");
    this.fuelCapacity = fuelCapacity;
    this.engineType = engineType;
  }

  public String toString() {
    return super.toString()
        + """
          - Fuel Capacity: %s
          - Engine Type: %s
        """
            .formatted(fuelCapacity, engineType);
  }

  void run() {
    System.out.println("Engine car is running.");
  }
}

public class VehicleLauncher {
  public static void main(String[] args) {
    ElectricCar evc = new ElectricCar("b3", "m3", 2016, 10000, 3, true);
    System.out.println(evc);

    EngineCar enc = new EngineCar("b3", "m3", 2016, 100, "v8");
    System.out.println(enc);
  }
}

// change file names
