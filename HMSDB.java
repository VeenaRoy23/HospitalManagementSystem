import java.io.*;
import java.sql.*;
import java.util.List;
import java.nio.file.*;

class Employee implements Serializable {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc";
    static final String USER = "root";
    static final String PASS = "root@123";


    String insert(String name, int age, String address, long contact, String duty) throws IOException {
        Connection conn = null;
        Statement stmt = null;
        String empId = "";

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database.");

            stmt = conn.createStatement();
            String sql = "INSERT INTO EmployeeTable (name, age, address, contact, duty) VALUES ('" +
                    name + "', " + age + ", '" + address + "', " + contact + ", '" + duty + "')";
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                empId = generatedKeys.getString(1);
            }

            System.out.println("Record inserted into the EmployeeTable.");

        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return empId;
    }

    // ... (similar modifications for other methods)

    void display() throws IOException {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database.");

            stmt = conn.createStatement();
            String sql = "SELECT * FROM EmployeeTable";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String empName = rs.getString("name");
                int empAge = rs.getInt("age");
                String empAddress = rs.getString("address");
                long empContact = rs.getLong("contact");
                String empDuty = rs.getString("duty");

                System.out.println("NAME: " + empName);
                System.out.println("AGE: " + empAge);
                System.out.println("ADDRESS: " + empAddress);
                System.out.println("CONTACT: " + empContact);
                System.out.println("SPECIALIZATION: " + empDuty);
                System.out.println("\n");
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

class Roombooking implements Serializable {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    static final String USER = "your_username";
    static final String PASS = "your_password";

    // Existing code ...

    void insert(int roomno, int inmates, String name, int age, String address, long contact) throws IOException {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database.");

            stmt = conn.createStatement();
            String sql = "INSERT INTO RoomTable (roomno, inmates, name, age, address, contact) VALUES (" +
                    roomno + ", " + inmates + ", '" + name + "', " + age + ", '" + address + "', " + contact + ")";
            stmt.executeUpdate(sql);

            System.out.println("Record inserted into the RoomTable.");

        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    // ... (similar modifications for other methods)
}

class HMSDB {
    public static void main(String args[]) throws IOException {
        // ... (existing code)
    }
}
