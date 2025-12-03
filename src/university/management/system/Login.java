package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, cancel;
    JTextField tfusername;   // Move tfusername as an instance variable
    JPasswordField tfpassword;  // Move tfpassword as an instance variable

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Username label and field
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        add(tfusername);

        // Password label and field
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 20);
        add(tfpassword);

        // Login button
        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("serif", Font.BOLD, 15));
        add(login);

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("serif", Font.BOLD, 15));
        add(cancel);

        // Image icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

        // Frame settings
        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = tfusername.getText(); // Get username from text field
            String password = new String(tfpassword.getPassword()); // Get password from password field
           String query = "select * from login where username='"+username+"' and password='"+password+"'";
           
           try {
               Conn c = new Conn();
              ResultSet rs = c.s.executeQuery(query);
               if(rs.next()) {
                   setVisible(false);
                   new Project();
               } else {
                   JOptionPane.showMessageDialog(null, "Invalid username or password");
                   setVisible(false);
               }
               c.s.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
            // Sample login logic (you can replace it with a real DB check)
            if (username.equals("admin") && password.equals("12345")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                // Proceed to the next screen or main menu
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);  // Close the login window
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
