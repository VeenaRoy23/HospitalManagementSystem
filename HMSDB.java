import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Iterator;
import java.nio.file.*;
import java.util.Scanner;

class Employee implements Serializable {
    static Scanner s = new Scanner(System.in);
    String name;
    int age;
    String address;
    String empId;
    long contact;
    String duty;
    int count = 10;

    String insert() throws IOException {
         FileWriter fout=new FileWriter("Data.txt",true);
        System.out.print("NAME:");
        name=s.nextLine();
        fout.write("NAME: ");
        fout.write(name);
        fout.write("\n");
        System.out.print("AGE:");
        age=s.nextInt();
        fout.write("AGE: ");
        fout.write(age+"");
        fout.write("\n");
        address=s.nextLine();
        System.out.print("ADDRESS:");
        address=s.nextLine();
        fout.write("ADDRESS: ");
        fout.write(address);
        fout.write("\n");
        System.out.print("CONTACT:");
        contact=s.nextLong();
        fout.write("CONTACT: ");
        fout.write(contact+" ");
        fout.write("\n");
        duty=s.nextLine();
        System.out.print("SPECIALIZATION:");
        duty=s.nextLine();
        fout.write("SPECIALIZATION: ");
        fout.write(duty);
        fout.write("\n");
        fout.write("EMPLOYEE ID: ");
        empId=name.substring(0,4)+count;
        fout.write(empId);
        fout.write("\n"); 
        count++;
        fout.write("\n");
        fout.write("\n"); 

        fout.close();

       

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HMS", "root", "root@123");
             Statement statement = connection.createStatement()) {

            // Create the employee table if not exists
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS employee (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), age INT, address VARCHAR(255), contact BIGINT, duty VARCHAR(255), empId VARCHAR(255))");

            // Insert employee data into the database
            String insertQuery = String.format("INSERT INTO employee (name, age, address, contact, duty, empId) VALUES ('%s', %d, '%s', %d, '%s', '%s')",
                    name, age, address, contact, duty, empId);
            statement.executeUpdate(insertQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empId;
    }

    void delete(String name) throws IOException {
      String str = "NAME: " + name;
        Path filePath = Paths.get("Data.txt");
        List<String> lines = Files.readAllLines(filePath);
        Iterator<String> iterator = lines.iterator();
        FileOutputStream fout = new FileOutputStream("Data.txt");
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains(str)) {
                System.out.println("Deleted " + name);
                for (int i = 0; i < 6; i++) {
                    iterator.remove();
                    iterator.next();
                }
            } 
            else {
                fout.write((line + System.lineSeparator()).getBytes());
            }
        }


        fout.close();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HMS", "root", "root@123");
             Statement statement = connection.createStatement()) {

            // Delete employee data from the database
            String deleteQuery = String.format("DELETE FROM employee WHERE name = '%s'", name);
            statement.executeUpdate(deleteQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void update(String duty, String name) throws IOException {
        String str = "NAME: " + name;
        Path filePath = Paths.get("Data.txt");
        List<String> lines = Files.readAllLines(filePath);
        Iterator<String> iterator = lines.iterator();
        FileOutputStream fout = new FileOutputStream("Data.txt");
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains(str))
            {
                fout.write((line + System.lineSeparator()).getBytes());
                for(int i=0;i<5;i++)
                {
                    String str1=iterator.next();
                    fout.write((str1 + System.lineSeparator()).getBytes());
                }
                iterator.next();
                fout.write((("SPECIALIZATION: "+duty)+System.lineSeparator()).getBytes());
            }
            else{
                 fout.write((line + System.lineSeparator()).getBytes());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HMS", "root", "root@123");
             Statement statement = connection.createStatement()) {

            // Update employee data in the database
            String updateQuery = String.format("UPDATE employee SET duty = '%s' WHERE name = '%s'", duty, name);
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n" + name + " updated to " + duty);
    }
   }

    void display() throws IOException {
       /*  String str = "NAME: " + name;
    Path filePath = Paths.get("Data.txt");
    List<String> lines = Files.readAllLines(filePath);
    Iterator<String> iterator = lines.iterator();
    FileOutputStream fout = new FileOutputStream("Data.txt");

    while (iterator.hasNext()) {
        String line = iterator.next();
        if (line.contains(str)) {
            System.out.println("\n");
            System.out.println(line);
            fout.write((line + System.lineSeparator()).getBytes());
            for (int i = 0; i < 6; i++) {
                String str1=iterator.next();
                System.out.println(str1);
                fout.write((str1 + System.lineSeparator()).getBytes());
            }
        }
        else{
            fout.write((line + System.lineSeparator()).getBytes());
        }
        
    }
            
       fout.close();
   
*/
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HMS", "root", "root@123");
             Statement statement = connection.createStatement()) {

            // Fetch and display employee data from the database
            String selectQuery = String.format("SELECT * FROM employee");
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("empId"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("Contact: " + resultSet.getLong("contact"));
                System.out.println("Duty: " + resultSet.getString("duty"));
                System.out.println("EmpId: " + resultSet.getString("empId"));
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


class HMS {
    public static void main(String args[]) throws IOException {
        int n,a,choice,roomno,inmates;
        String empId,name,specialization;
        Scanner s=new Scanner(System.in);
        Employee e=new Employee();
        do{
            System.out.println("\n1:Insert\n2:Display\n3:update\n4:delete\n5:exit\n");
            choice=s.nextInt();
            switch(choice){
       		case 1:
                                empId = e.insert();
                                System.out.println("\nWelcome!...Your ID is " + empId + "\n");
                                break;

                            case 2:
                              //  name = s.nextLine();
                               // System.out.println("Enter the name:");
                               // name = s.nextLine();
                                e.display();
                                break;

                            case 3:
                                name = s.nextLine();
                                System.out.println("Enter the name:");
                                name = s.nextLine();
                                System.out.println("Enter the new specialization:");
                                specialization = s.nextLine();
                                e.update(specialization, name);
                                break;

                            case 4:
                                name = s.nextLine();
                                System.out.print("Enter the name:");
                                name = s.nextLine();
                                e.delete(name);
                                break;

                            case 5:
                                System.out.println("EXITING..");
                                break;
                        }
                    } while (choice != 5);
                   

   }             
     
}
