import java.util.ArrayList;
import java.util.List;

interface Device {
  void turnon();

  void turnoff();

  String getName();

  boolean getStatus();

  public String toString();
}

class SmartLight implements Device {
  String name;
  boolean status;

  SmartLight(String name) {
    this.name = name;
    status = false;
  }

  /** method to turn off the smart light */
  public void turnon() {
    status = true;
    System.out.println("Smart Light turned on");
  }

  /** method to turn on the smart light */
  public void turnoff() {
    status = false;
    System.out.println("Smart Light turned off");
  }

  /**
   * @return String
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return boolean
   */
  public boolean getStatus() {
    return this.status;
  }

  /**
   * @return String
   */
  public String toString() {
    return """
    Device Details:
        - Name: %s
        - Status: %s
    """
        .formatted(name, status);
  }
}

class SmartThermostat implements Device {
  String name;
  boolean status;

  SmartThermostat(String name) {
    this.name = name;
    status = false;
  }

  /** method to turn on the smart thermostat */
  public void turnon() {
    status = true;
    System.out.println("Smart Thermostat turned on");
  }

  /** method to turn on the smart thermostat */
  public void turnoff() {
    status = false;
    System.out.println("Smart Thermostat turned off");
  }

  /**
   * @return String
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return boolean
   */
  public boolean getStatus() {
    return this.status;
  }

  public String toString() {
    return """
    Device Details:
        - Name: %s
        - Status: %s
    """
        .formatted(name, status);
  }
}

class DeviceFactory {
  static Device factory(String device, String deviceName) {
    if (device == "smart-light") {
      return new SmartLight(deviceName);
    } else if (device == "smart-thermostat") {
      return new SmartThermostat(deviceName);
    } else {
      throw new IllegalArgumentException();
    }
  }
}

class SmartHomeHub {
  List<Device> devices;
  SecuritySystem system;

  private static SmartHomeHub hub;

  private SmartHomeHub() {}
  ;

  static SmartHomeHub getInstance() {
    if (hub == null) {
      hub = new SmartHomeHub();
      hub.devices = new ArrayList<>();
    }
    return hub;
  }

  /**
   * @return List<Device>
   */
  List<Device> getDevices() {
    return this.devices;
  }

  /**
   * adds device to the hub
   *
   * @param device
   */
  void addDevice(Device device) {
    this.devices.add(device);
    System.out.println(device.getName() + " is added successfully.");
  }

  /**
   * add security system to the hub
   *
   * @param system
   */
  void addSecuritySystem(SecuritySystem system) {
    this.system = system;
    System.out.println("Security System added successfully.");
  }

  /** used to turnn on all the devices in the room */
  void enterHome() {
    devices.forEach(d -> d.turnon());
    System.out.println("System activated successfully.");
  }

  /** used to turn off all the devices in the room */
  void leaveHome() {
    devices.forEach(d -> d.turnoff());
    System.out.println("System shutdown sucessfully.");
  }

  public String toString() {
    return """
    Smart Hub Details:
        - Connected Devices: %s
        %s
    """
        .formatted(devices, system);
  }
}

class SecuritySystem {
  private SecuritySystem() {}
  ;

  private boolean hasAlarm;
  private int noOfCameras;
  private int noOfSensors;

  private SecuritySystem(Builder builder) {
    this.hasAlarm = builder.hasAlarm;
    this.noOfCameras = builder.noOfCameras;
    this.noOfSensors = builder.noOfSensors;
  }

  public boolean isHasAlarm() {
    return hasAlarm;
  }

  public void setHasAlarm(boolean hasAlarm) {
    this.hasAlarm = hasAlarm;
  }

  public int getNoOfCameras() {
    return noOfCameras;
  }

  public void setNoOfCameras(int noOfCameras) {
    this.noOfCameras = noOfCameras;
  }

  public int getNoOfSensors() {
    return noOfSensors;
  }

  public void setNoOfSensors(int noOfSensors) {
    this.noOfSensors = noOfSensors;
  }

  static class Builder {
    boolean hasAlarm;
    int noOfCameras;
    int noOfSensors;

    Builder setAlarm(boolean status) {
      this.hasAlarm = status;
      return this;
    }

    Builder setNoOfCameras(int count) {
      this.noOfCameras = count;
      return this;
    }

    Builder setNoOfSensors(int count) {
      this.noOfSensors = count;
      return this;
    }

    SecuritySystem build() {
      return new SecuritySystem(this);
    }
  }

  public String toString() {
    return """
    Security System Details:
        - Has Alarm: %s
        - No of cameras: %s
        - No of sensors: %s
    """
        .formatted(hasAlarm, noOfCameras, noOfSensors);
  }
}

class SmartHomeAutomationService {
  public static void main(String[] args) {
    SmartHomeHub hub = SmartHomeHub.getInstance();

    Device smartLight1 = new SmartLight("l1");
    Device smartLight2 = new SmartLight("l2");

    Device smartThermostat1 = new SmartThermostat("t1");
    Device smartThermostat2 = new SmartThermostat("t2");

    hub.addDevice(smartLight1);
    hub.addDevice(smartLight2);
    hub.addDevice(smartThermostat1);
    hub.addDevice(smartThermostat2);

    SecuritySystem system =
        new SecuritySystem.Builder().setAlarm(true).setNoOfCameras(9).setNoOfSensors(9).build();

    hub.addSecuritySystem(system);

    System.out.println(hub);

    hub.enterHome();
    System.out.println(hub);

    hub.leaveHome();
    System.out.println(hub);
  }
}

/*
   - added additional things like enter home to turn on all dev and leave home to turn off all dev
   - remove central hub class
*/
