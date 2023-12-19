import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.nio.file.*;
import java.util.Scanner;

class Patient_details implements Serializable {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root@123";

    private Connection connection;

    Patient_details() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String addPatient() throws IOException, SQLException {
        Scanner s = new Scanner(System.in);
        System.out.print("NAME:");
        String name = s.nextLine();
        System.out.print("AGE:");
        int age = s.nextInt();
        System.out.print("GENDER:");
        char gender = s.next().charAt(0);
        System.out.print("ADDRESS:");
        s.nextLine();
        String address = s.nextLine();
        System.out.print("CONTACT:");
        String contact = s.nextLine();
        System.out.print("SPECIALIZATION:");
        String specialization = s.nextLine();
        System.out.println("IS ADMIT?");
        boolean isAdmit = s.nextBoolean();
        
        String insertSQL = "INSERT INTO patients (name, age, gender, specialization, address, contact, is_admit) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, String.valueOf(gender));
            preparedStatement.setString(4, specialization);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, contact);
            preparedStatement.setBoolean(7, isAdmit);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    String id = generatedKeys.getString(1);

                 return id;
                }
            }
        }

        return null;
    }
    void deletePatient(String id) throws IOException, SQLException {
        String deleteSQL = "DELETE FROM patients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Patient deleted: " + id);
            } else {
                System.out.println("Patient not found: " + id);
            }
        }
    }

    void updatePatient(int c, String id, String credentials, String prev) throws IOException, SQLException {
        String updateSQL = null;

        switch (c) {
            case 1:
                updateSQL = "UPDATE patients SET prescriptions = ? WHERE id = ?";
                break;
            case 2:
                updateSQL = "UPDATE patients SET room_bed_no = ? WHERE id = ?";
                break;
        }

        if (updateSQL != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                preparedStatement.setString(1, credentials);
                preparedStatement.setString(2, id);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Patient updated: " + id);
                } else {
                    System.out.println("Patient not found: " + id);
                }
            }
        }
    }

    void patientDetails(String name) throws IOException, SQLException {
        String selectSQL = "SELECT * FROM patients WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Patient details found:");
                    System.out.println("ID: " + resultSet.getString("id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Gender: " + resultSet.getString("gender"));
                    System.out.println("Specialization: " + resultSet.getString("specialization"));
                    System.out.println("Address: " + resultSet.getString("address"));
                    System.out.println("Contact: " + resultSet.getString("contact"));
                    System.out.println("Is Admit: " + resultSet.getBoolean("is_admit"));
                } else {
                    System.out.println("Patient details not found!");
                }
            }
        }
    }

    // Your existing code for retrieving patient details
    void recollectPatient(String id) throws IOException, SQLException {
        String selectSQL = "SELECT * FROM patients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Patient found:");
                    System.out.println("ID: " + resultSet.getString("id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Age: " + resultSet.getInt("age"));
                    System.out.println("Gender: " + resultSet.getString("gender"));
                    System.out.println("Specialization: " + resultSet.getString("specialization"));
                    System.out.println("Address: " + resultSet.getString("address"));
                    System.out.println("Contact: " + resultSet.getString("contact"));
                    System.out.println("Is Admit: " + resultSet.getBoolean("is_admit"));
                } else {
                    System.out.println("Patient details not found!");
                }
            }
        }
    }

    private void savePatientToFile(String id, String name, int age, char gender, String specialization,
                                   String address, String contact, boolean isAdmit) throws IOException {
        // Your existing code to save patient details to files
        // ...
    }

    void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

class PMSDBC {
    public static void main(String[] args) throws IOException,SQLException {
        int n;
        Scanner s = new Scanner(System.in);
        Patient_details pd = new Patient_details();

        do {
            System.out.println("MENU..\n1:Add/Admit patient\n2:Remove patient\n3:Update patient\n" +
                    "4:Get patient details\n5:Recollect old patients\n6:EXIT");
            n = s.nextInt();

            switch (n) {
                case 1:
                    String id = pd.addPatient();
                    if (id != null) {
                        System.out.println("PATIENT REGISTERED:" + id);
                    }
                    break;
                case 2:
                    System.out.print("Patient id:");
                    id = s.next();
                    pd.deletePatient(id);
                    break;
                case 3:
                    System.out.print("Patient id:");
                    id = s.next();
                    System.out.println("Your duty:");
                    System.out.println("1:Edit medicine\n2:Edit room");
                    int choice = s.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.print("Previous medicine list:");
                            String prevMedicine = s.next();
                            System.out.print("Updated medicine list:");
                            String medicine = s.next();
                            pd.updatePatient(1, id, medicine, prevMedicine);
                            break;
                        case 2:
                            System.out.print("Previous room:");
                            String prevRoom = s.next();
                            System.out.print("Updated room:");
                            String room = s.next();
                            pd.updatePatient(2, id, room, prevRoom);
                            break;
                    }
                    break;
                case 4:
                    System.out.print("Patient name:");
                    String patientName = s.next();
                    pd.patientDetails(patientName);
                    break;
                case 5:
                    System.out.print("Patient id:");
                    id = s.next();
                    pd.recollectPatient(id);
                    break;
                case 6:
                    System.out.println("EXITING..!");
            }
        } while (n != 6);

        // Close the database connection when the program exits
        pd.closeConnection();
    }
}

