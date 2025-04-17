package search.job.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*; // For JavaMail
import java.util.Properties; // For JavaMail

public class AmazingLoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private Map<String, User> users = new HashMap<>(); // Simple in-memory user storage

    // User class to store user details
    private static class User {
        String username, email, password;

        User(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }

    public AmazingLoginPage() {
        // Initialize with a default user for testing
        users.put("player", new User("player", "player@example.com", "star123"));

        // Set up the frame
        setTitle("Star Defender Login");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(null);

        // Custom background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 51, 102), 0, getHeight(), new Color(0, 153, 204));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                // Add subtle stars
                g2d.setColor(Color.WHITE);
                for (int i = 0; i < 20; i++) {
                    int x = (int) (Math.random() * getWidth());
                    int y = (int) (Math.random() * getHeight());
                    g2d.fillOval(x, y, 2, 2);
                }
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 400, 550);
        add(backgroundPanel);

        // Title Label
        JLabel titleLabel = new JLabel("Star Defender Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(100, 50, 200, 30);
        backgroundPanel.add(titleLabel);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(80, 120, 100, 20);
        backgroundPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBounds(80, 150, 240, 30);
        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        backgroundPanel.add(usernameField);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(80, 200, 100, 20);
        backgroundPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(80, 230, 240, 30);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        backgroundPanel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 204, 102)); // Greenish button
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(80, 300, 240, 40);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (validateLogin(username, password)) {
                    messageLabel.setText("Login successful! Welcome, " + username + "!");
                    messageLabel.setForeground(Color.GREEN);
                    new GameWindow(); // Placeholder for game window
                    dispose(); // Close login window
                } else {
                    messageLabel.setText("Invalid username or password!");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });
        backgroundPanel.add(loginButton);

        // Forgot Password Link
        JLabel forgotPasswordLabel = new JLabel("<html><u>Forgot Password?</u></html>");
        forgotPasswordLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        forgotPasswordLabel.setForeground(Color.WHITE);
        forgotPasswordLabel.setBounds(80, 350, 240, 20);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showForgotPasswordDialog();
            }
        });
        backgroundPanel.add(forgotPasswordLabel);

        // Sign Up Link
        JLabel signUpLabel = new JLabel("<html><u>Don't have an account? Sign Up here</u></html>");
        signUpLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setBounds(80, 380, 240, 20);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showSignUpDialog();
            }
        });
        backgroundPanel.add(signUpLabel);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBounds(80, 410, 240, 20);
        backgroundPanel.add(messageLabel);
    }

    private boolean validateLogin(String username, String password) {
        User user = users.get(username);
        return user != null && user.password.equals(password);
    }

    private void showForgotPasswordDialog() {
        JDialog dialog = new JDialog(this, "Forgot Password", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.setBackground(new Color(0, 51, 102));

        // Email Label and Field
        JLabel emailLabel = new JLabel("Enter Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 20, 100, 20);
        dialog.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBounds(50, 50, 200, 30);
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(Color.BLACK);
        dialog.add(emailField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(0, 204, 102));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(50, 100, 200, 40);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                if (isValidEmail(email)) {
                    User user = findUserByEmail(email);
                    if (user != null) {
                        String newPassword = generateRandomPassword();
                        user.password = newPassword; // Update password in memory
                        sendPasswordResetEmail(user.email, newPassword); // Send email (mocked here)
                        JOptionPane.showMessageDialog(dialog, "A new password has been sent to " + email + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "No account found with this email!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Invalid email address!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.add(submitButton);

        dialog.setVisible(true);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private User findUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.email.equals(email)) {
                return user;
            }
        }
        return null;
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private void sendPasswordResetEmail(String toEmail, String newPassword) {
        // Mock email sending (replace with real JavaMail implementation)
        System.out.println("Sending email to " + toEmail + " with new password: " + newPassword);
        // Real implementation would require JavaMail setup (see below for notes)
    }

    private void showSignUpDialog() {
        JDialog dialog = new JDialog(this, "Sign Up", true);
        dialog.setSize(350, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);
        dialog.setBackground(new Color(0, 51, 102));

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 20, 100, 20);
        dialog.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBounds(50, 50, 250, 30);
        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        dialog.add(usernameField);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(50, 90, 100, 20);
        dialog.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBounds(50, 120, 250, 30);
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(Color.BLACK);
        dialog.add(emailField);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 160, 100, 20);
        dialog.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(50, 190, 250, 30);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        dialog.add(passwordField);

        // Confirm Password Label and Field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setBounds(50, 230, 150, 20);
        dialog.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordField.setBounds(50, 260, 250, 30);
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setForeground(Color.BLACK);
        dialog.add(confirmPasswordField);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setBackground(new Color(0, 204, 102));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBounds(50, 310, 250, 40);
        signUpButton.setFocusPainted(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(dialog, "Invalid email address!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (users.containsKey(username)) {
                    JOptionPane.showMessageDialog(dialog, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(dialog, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Register the user
                users.put(username, new User(username, email, password));
                JOptionPane.showMessageDialog(dialog, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        });
        dialog.add(signUpButton);

        dialog.setVisible(true);
    }

    // Placeholder for game window
    class GameWindow extends JFrame {
        public GameWindow() {
            setTitle("Star Defender Game");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            JLabel label = new JLabel("Game content would go here (e.g., Star Defender game loop).");
            label.setHorizontalAlignment(JLabel.CENTER);
            add(label);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AmazingLoginPage login = new AmazingLoginPage();
            login.setVisible(true);
        });
    }
}