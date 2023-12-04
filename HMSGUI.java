import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HMSGUI extends JFrame {

    private final Employee employee;
    private final Roombooking roombooking;

    public HMSGUI() {
        employee = new Employee();
        roombooking = new Roombooking();

        JFrame frame = new JFrame("Hospital Management System");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Employee", createEmployeePanel());
        tabbedPane.addTab("Inmates", createInmatesPanel());

        frame.add(tabbedPane);

        
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createEmployeePanel() {
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new GridLayout(3, 2));

        
        JButton registerButton = new JButton("Register Employee");
        JButton displayButton = new JButton("Display Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");

 
        employeePanel.add(registerButton);
        employeePanel.add(displayButton);
        employeePanel.add(updateButton);
        employeePanel.add(deleteButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String empId = employee.insert();
                    JOptionPane.showMessageDialog(null, "Welcome! Your ID is " + empId);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter the name:");
                try {
                    employee.display(name);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter the name:");
                String specialization = JOptionPane.showInputDialog("Enter the new specialization:");
                try {
                    employee.update(specialization, name);
                    JOptionPane.showMessageDialog(null, "Employee details updated successfully!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter the name:");
                try {
                    employee.delete(name);
                    JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return employeePanel;
    }

    private JPanel createInmatesPanel() {
        JPanel inmatesPanel = new JPanel();
        inmatesPanel.setLayout(new GridLayout(2, 2));

  
        JButton registerInmatesButton = new JButton("Register Inmates");
        JButton deleteInmatesButton = new JButton("Delete Inmates");
        JButton displayRoomButton = new JButton("Display Room Details");


        inmatesPanel.add(registerInmatesButton);
        inmatesPanel.add(deleteInmatesButton);
        inmatesPanel.add(displayRoomButton);

   
        registerInmatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int inmates = Integer.parseInt(JOptionPane.showInputDialog("Enter number of inmates:"));
                int roomNo = roombooking.roomAllocation(inmates);
                try {
                    roombooking.insert(roomNo, inmates);
                    JOptionPane.showMessageDialog(null, "Room allocated: " + roomNo + "\nInmates registered successfully!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        deleteInmatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNo = Integer.parseInt(JOptionPane.showInputDialog("Enter the Room No.:"));
                try {
                    roombooking.delete(roomNo);
                    JOptionPane.showMessageDialog(null, "Inmates deleted successfully!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        displayRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Room No.:"));
                try {
                    roombooking.display(roomNo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return inmatesPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HMSGUI();
            }
        });
    }
}
