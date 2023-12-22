import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class EmployeeGUI extends JFrame {
    private Employee employee;

    public EmployeeGUI() {
        employee = new Employee();

        setTitle("Employee Operations");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton registerButton = new JButton("Register Employee");
        JButton searchButton = new JButton("Search Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton displayButton = new JButton("Display Employees");

        JPanel panel = new JPanel();
        panel.add(registerButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(displayButton);

        add(panel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegisterDialog();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSearchDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteDialog();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    employee.display();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred while displaying employees.");
                }
            }
        });
    }

    private void showRegisterDialog() {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField specializationField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "Age:", ageField,
                "Address:", addressField,
                "Contact:", contactField,
                "Specialization:", specializationField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Register Employee", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String address = addressField.getText();
                long contact = Long.parseLong(contactField.getText());
                String specialization = specializationField.getText();

                String empId = employee.insert(name, age, address, contact, specialization);
                JOptionPane.showMessageDialog(null, "Employee Registered Successfully!\nEmployee ID: " + empId);
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred while registering employee.");
            }
        }
    }

    private void showSearchDialog() {
        String empId = JOptionPane.showInputDialog("Enter Employee ID:");
        try {
            employee.search(empId);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while searching for employee.");
        }
    }

    private void showUpdateDialog() {
        JTextField nameField = new JTextField();
        JTextField specializationField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "New Specialization:", specializationField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Update Employee", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String specialization = specializationField.getText();

                employee.update(specialization, name);
                JOptionPane.showMessageDialog(null, name + " updated to " + specialization);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred while updating employee.");
            }
        }
    }

    private void showDeleteDialog() {
        String name = JOptionPane.showInputDialog("Enter Employee Name:");
        try {
            employee.delete(name);
            JOptionPane.showMessageDialog(null, name + " deleted successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while deleting employee.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeGUI().setVisible(true);
            }
        });
    }
}
