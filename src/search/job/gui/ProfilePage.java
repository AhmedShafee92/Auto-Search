package search.job.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePage extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextArea bioArea;
    private JPasswordField passwordField;
    private JLabel profilePictureLabel;

    public ProfilePage(UserProfile user) {
        initializeUI(user);
    }

    private void initializeUI(UserProfile user) {
        setTitle("Profile Page");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Profile picture panel (left)
        JPanel picturePanel = new JPanel();
        picturePanel.setPreferredSize(new Dimension(150, 150));
        profilePictureLabel = new JLabel();
        profilePictureLabel.setIcon(new ImageIcon("default_profile.png")); // Default image
        picturePanel.add(profilePictureLabel);
        mainPanel.add(picturePanel, BorderLayout.WEST);

        // User info panel (center)
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        infoPanel.add(new JLabel("Name:"));
        nameField = new JTextField(user.getName(), 20);
        infoPanel.add(nameField);

        infoPanel.add(new JLabel("Email:"));
        emailField = new JTextField(user.getEmail(), 20);
        infoPanel.add(emailField);

        infoPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField(user.getPhoneNumber(), 20);
        infoPanel.add(phoneField);

        infoPanel.add(new JLabel("Bio:"));
        bioArea = new JTextArea(user.getBio(), 3, 20);
        bioArea.setLineWrap(true);
        JScrollPane bioScrollPane = new JScrollPane(bioArea);
        infoPanel.add(bioScrollPane);

        infoPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField("", 20);
        infoPanel.add(passwordField);

        infoPanel.add(new JLabel()); // Spacer
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(e -> changePassword());
        infoPanel.add(changePasswordButton);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Save button panel (bottom)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> saveChanges(user));
        buttonPanel.add(saveButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        JButton uploadButton = new JButton("Upload Picture");
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getPath());
                profilePictureLabel.setIcon(icon);
            }
        });
        picturePanel.add(uploadButton);
        

        add(mainPanel);
        setVisible(true);
    }

    private void changePassword() {
        String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
        if (newPassword != null && !newPassword.isEmpty()) {
            passwordField.setText(newPassword);
        }
    }

    private void saveChanges(UserProfile user) {
        String newName = nameField.getText();
        String newEmail = emailField.getText();
        String newPhone = phoneField.getText();
        String newBio = bioArea.getText();
        String newPassword = new String(passwordField.getPassword());

        // Update user object
        user.setName(newName);
        user.setEmail(newEmail);
        user.setPhoneNumber(newPhone);
        user.setBio(newBio);
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword); // Hash this in real implementation
        }

        // Save changes (e.g., to database)
        JOptionPane.showMessageDialog(this, "Changes saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Example user for testing
        UserProfile user = new UserProfile(
            "John Doe", 
            "john.doe@example.com", 
            "+1234567890", 
            "I love coding and building cool stuff!", 
            "password123"
        );
        SwingUtilities.invokeLater(() -> new ProfilePage(user));
    }
}


class UserProfile {
    private String name;
    private String email;
    private String phoneNumber;
    private String bio;
    private String password;

    public UserProfile(String name, String email, String phoneNumber, String bio, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.password = password;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}