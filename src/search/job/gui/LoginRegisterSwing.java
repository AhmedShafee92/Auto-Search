package search.job.gui;
import javax.swing.*;
import java.awt.*;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class LoginRegisterSwing 
{
    public static void main(String[] args) 
    {
        System.out.println("Application started.");
        SwingUtilities.invokeLater(LoginRegisterSwing::new);
    }

    /**
     * @wbp.parser.entryPoint
     */
    
    // Add controller for every button - which move to the MVC code style 
    public LoginRegisterSwing() 
    {
        System.out.println("Initializing GUI components.");
        JFrame frame = new JFrame("User Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new CardLayout());

        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginPanel.setBackground(new Color(240, 248, 255));

        JLabel loginTitle = new JLabel("Login Form", JLabel.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 18));
        loginTitle.setForeground(new Color(0, 51, 102));

        JTextField loginEmailField = new JTextField();
        loginEmailField.setBorder(BorderFactory.createTitledBorder("Email Address"));

        JPasswordField loginPasswordField = new JPasswordField();
        loginPasswordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);

        loginPanel.add(loginTitle);
        loginPanel.add(loginEmailField);
        loginPanel.add(loginPasswordField);
        loginPanel.add(loginButton);

        // Register Panel
        JPanel registerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        registerPanel.setBackground(new Color(240, 248, 255));

        JLabel registerTitle = new JLabel("Register Form", JLabel.CENTER);
        registerTitle.setFont(new Font("Arial", Font.BOLD, 18));
        registerTitle.setForeground(new Color(0, 51, 102));

        JTextField registerEmailField = new JTextField();
        registerEmailField.setBorder(BorderFactory.createTitledBorder("Email Address"));

        JPasswordField registerPasswordField = new JPasswordField();
        registerPasswordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JTextField phoneField = new JTextField();
        phoneField.setBorder(BorderFactory.createTitledBorder("Phone"));

        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Full Name"));

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(0, 102, 204));
        registerButton.setForeground(Color.WHITE);

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
            String email = loginEmailField.getText();
            String password = new String(loginPasswordField.getPassword());

            if (authenticateUser(email, password)) {
                System.out.println("Login successful! Navigating to the next page.");
            
                frame.dispose(); // Close the login frame
                openNextPage(); // Open the next page
           
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            System.out.println("Register button clicked.");
            registerUser(registerEmailField.getText(), new String(registerPasswordField.getPassword()), phoneField.getText(), nameField.getText());
        });
    }

    private boolean authenticateUser(String email, String password) {
        System.out.println("Attempting to log in user: " + email);
        try {
            // Server URL for login
            String urlStr = "http://localhost:3000/login";
            System.out.println("Connecting to: " + urlStr);
            
            // Prepare the request
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create JSON payload
            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);
            System.out.println("Request payload: " + json.toString());

            // Send request payload
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(json.toString());
            writer.flush();
            writer.close();

            // Read server response
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == 200) {
                System.out.println("Login successful!");
                JOptionPane.showMessageDialog(null, "Login successful!");
                
                return true;
            } else {
                System.out.println("Invalid credentials!");
                JOptionPane.showMessageDialog(null, "Invalid credentials!");
            }
        } catch (Exception ex) {
            System.err.println("Error during login: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
		return false;
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

    private void openNextPage() {
        JFrame nextFrame = new JFrame("Profile Page");
        nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextFrame.setSize(800, 600);
        nextFrame.getContentPane().setLayout(new BorderLayout());

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(47, 79, 79)); // Dark gray-green
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setPreferredSize(new Dimension(200, 0));

        JLabel profilePic = new JLabel();
        profilePic.setIcon(new ImageIcon("path/to/profile-pic.jpg")); // Add your profile image path
        profilePic.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel profileName = new JLabel("Burk Macklin", JLabel.CENTER);
        profileName.setForeground(Color.WHITE);
        profileName.setFont(new Font("Arial", Font.BOLD, 16));

        JButton homeButton = createSidebarButton("Home");
        JButton workButton = createSidebarButton("Work");
        JButton supportButton = createSidebarButton("Support");
        JButton settingsButton = createSidebarButton("Settings");
        JButton logoutButton = createSidebarButton("Signout");

        sidebar.add(profilePic);
        sidebar.add(profileName);
        sidebar.add(homeButton);
        sidebar.add(workButton);
        sidebar.add(supportButton);
        sidebar.add(settingsButton);
        sidebar.add(logoutButton);

        // Main Content Panel
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setBackground(Color.WHITE);

        // Header Panel
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 128, 128)); // Teal color
        header.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton homeNavButton = new JButton("Home");
        JButton workNavButton = new JButton("Work");
        JButton supportNavButton = new JButton("Support");
        JButton logoutNavButton = new JButton("Logout");

        header.add(homeNavButton);
        header.add(workNavButton);
        header.add(supportNavButton);
        header.add(logoutNavButton);

        // Profile Info Panel
        JPanel profileInfo = new JPanel();
        profileInfo.setLayout(new GridLayout(6, 2, 10, 10));
        profileInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        profileInfo.add(new JLabel("Full Name:"));
        profileInfo.add(new JLabel("Burk Macklin"));

        profileInfo.add(new JLabel("Email:"));
        profileInfo.add(new JLabel("abc@gmail.com"));

        profileInfo.add(new JLabel("Phone:"));
        profileInfo.add(new JLabel("00923406874656"));

        profileInfo.add(new JLabel("Address:"));
        profileInfo.add(new JLabel("Street no. 4, XYZ"));

        // Recent Projects Section
        JPanel recentProjects = new JPanel();
        recentProjects.setLayout(new GridLayout(2, 2, 10, 10));
        recentProjects.setBorder(BorderFactory.createTitledBorder("Recent Projects"));

        recentProjects.add(new JLabel("Project Name"));
        recentProjects.add(new JLabel("Project Description"));
        recentProjects.add(new JLabel("My First Project"));
        recentProjects.add(new JLabel("A short description of the project."));

        mainContent.add(header, BorderLayout.NORTH);
        mainContent.add(profileInfo, BorderLayout.CENTER);
        mainContent.add(recentProjects, BorderLayout.SOUTH);

        nextFrame.getContentPane().add(sidebar, BorderLayout.WEST);
        nextFrame.getContentPane().add(mainContent, BorderLayout.CENTER);
        nextFrame.setVisible(true);
    }

    // Helper method to create sidebar buttons
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(47, 79, 79));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

}
