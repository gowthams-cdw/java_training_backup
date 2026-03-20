package models;

public class Student {
    public static void main(String[] args) {
        try {
            // For PostgreSQL, the driver class name is "org.postgresql.Driver"
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ JDBC Driver detected and loaded!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ JDBC Driver NOT found in classpath!");
            // This prints exactly what Java sees as the classpath
            System.out.println("Current Classpath: " + System.getProperty("java.class.path"));
        }
    }
}
