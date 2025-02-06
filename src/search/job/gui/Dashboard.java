package search.job.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JLabel lblWelcome, lblEmail, lblRegistrationDate;
    private JButton btnLogout;

    public Dashboard(String userEmail) {
        setTitle("User Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create panel and layout
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Get user data (replace with your actual data retrieval)
        User user = getUserData(userEmail);
        
        // Welcome label
        lblWelcome = new JLabel("Welcome, " + user.getUsername() + "!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Email label
        lblEmail = new JLabel("Email: " + user.getEmail());
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Registration date label
        lblRegistrationDate = new JLabel("Registered since: " + user.getRegistrationDate());
        lblRegistrationDate.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Logout button
        btnLogout = new JButton("Log Out");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        
        // Add components to panel
        panel.add(lblWelcome);
        panel.add(lblEmail);
        panel.add(lblRegistrationDate);
        panel.add(btnLogout);
        
        add(panel);
        setVisible(true);
    }

    private User getUserData(String email) {
        // Replace with your actual user data retrieval logic
        // This is just a mock implementation
        return new User(
            "john_doe", 
            email, 
            "2024-02-20"
        );
    }

    private void logout() {
        // Perform logout operations (clear sessions, etc.)
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard("user@example.com"); // Test with sample email
        });
    }
}

class User {
    private String username;
    private String email;
    private String registrationDate;

    public User(String username, String email, String registrationDate) {
        this.username = username;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRegistrationDate() { return registrationDate; }
}