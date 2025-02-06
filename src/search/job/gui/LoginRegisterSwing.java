package search.job.gui;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class LoginRegisterSwing {
    public static void main(String[] args) {
        System.out.println("Application started.");
        SwingUtilities.invokeLater(LoginRegisterSwing::new);
    }

    /**
     * @wbp.parser.entryPoint
     */
    public LoginRegisterSwing() {
        System.out.println("Initializing GUI components.");
        JFrame frame = new JFrame("User Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new CardLayout());

        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel loginTitle = new JLabel("Login Form", JLabel.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField loginEmailField = new JTextField();
        loginEmailField.setBorder(BorderFactory.createTitledBorder("Email Address"));

        JPasswordField loginPasswordField = new JPasswordField();
        loginPasswordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginButton = new JButton("Login");

        loginPanel.add(loginTitle);
        loginPanel.add(loginEmailField);
        loginPanel.add(loginPasswordField);
        loginPanel.add(loginButton);

        // Register Panel
        JPanel registerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel registerTitle = new JLabel("Register Form", JLabel.CENTER);
        registerTitle.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField registerEmailField = new JTextField();
        registerEmailField.setBorder(BorderFactory.createTitledBorder("Email Address"));

        JPasswordField registerPasswordField = new JPasswordField();
        registerPasswordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JTextField phoneField = new JTextField();
        phoneField.setBorder(BorderFactory.createTitledBorder("Phone"));

        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Full Name"));

        JButton registerButton = new JButton("Register");

        registerPanel.add(registerTitle);
        registerPanel.add(registerEmailField);
        registerPanel.add(registerPasswordField);
        registerPanel.add(phoneField);
        registerPanel.add(nameField);
        registerPanel.add(registerButton);

        // Tabs for Login and Register
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Register", registerPanel);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);

        // Button Actions
        loginButton.addActionListener(e -> {
            System.out.println("Login button clicked.");
            authenticateUser(loginEmailField.getText(), new String(loginPasswordField.getPassword()));
        });

        registerButton.addActionListener(e -> {
            System.out.println("Register button clicked.");
            registerUser(registerEmailField.getText(), new String(registerPasswordField.getPassword()), phoneField.getText(), nameField.getText());
        });
    }

    private void authenticateUser(String email, String password) {
        System.out.println("Attempting to log in user: " + email);
        sendRequest("http://localhost:3000/login", email, password, null, null, "Login successful!", "Invalid credentials!");
    }

    private void registerUser(String email, String password, String phone, String fullName) {
        System.out.println("Attempting to register user: " + email);
        sendRequest("http://localhost:3000/register", email, password, phone, fullName, "Registration successful!", "Registration failed!");
    }

    private void sendRequest(String urlStr, String email, String password, String phone, String fullName, String successMessage, String failureMessage) {
        try {
            System.out.println("Sending request to: " + urlStr);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);
            if (phone != null) json.put("phone", phone);
            if (fullName != null) json.put("fullName", fullName);

            System.out.println("Request payload: " + json.toString());

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(json.toString());
            writer.flush();
            writer.close();

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode == 200 || responseCode == 201) {
                JOptionPane.showMessageDialog(null, successMessage);
            } else {
                JOptionPane.showMessageDialog(null, failureMessage);
            }
        } catch (Exception ex) {
            System.err.println("Request failed: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
