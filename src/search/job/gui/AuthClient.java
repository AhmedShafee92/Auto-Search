// AuthClient.java
package search.job.gui;

import javax.swing.*;

import controller.MainController;
import view.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;
import java.io.IOException;

public class AuthClient extends JFrame 
{

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JTextArea messageArea;

    private static final String SERVER_URL = "http://localhost:3000";

    public AuthClient() {
        setTitle("Swing Auth Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton signUpButton = new JButton("Sign Up");
        JButton signInButton = new JButton("Sign In");
        buttonPanel.add(signUpButton);
        buttonPanel.add(signInButton);

        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Messages"));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(scrollPane, BorderLayout.SOUTH);

        signUpButton.addActionListener(this::onSignUp);
        signInButton.addActionListener(this::onSignIn);
    }

    private void onSignUp(ActionEvent e) 
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        sendMessageToServer("/signup", username, password);
    }

    private void onSignIn(ActionEvent e) 
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        sendMessageToServer("/signin", username, password);
    }

    private void sendMessageToServer(String endpoint, String username, String password) 
    {
        messageArea.setText("");

        if (username.isEmpty() || password.isEmpty()) {
            messageArea.append("Error: Username and password cannot be empty.\n");
            return;
        }

        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(SERVER_URL + endpoint);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                String jsonInputString = String.format(
                        "{\"username\": \"%s\", \"password\": \"%s\"}",
                        escapeJsonString(username),
                        escapeJsonString(password)
                );

                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                conn.setRequestProperty("Content-Length", String.valueOf(input.length));
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(input);
                    os.flush();
                }

                int responseCode = conn.getResponseCode();

                InputStream stream;
                try {
                    stream = conn.getInputStream(); // For 2xx
                } catch (IOException ex) {
                    stream = conn.getErrorStream(); // For 4xx/5xx
                }

                StringBuilder response = new StringBuilder();
                if (stream != null) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            response.append(line.trim());
                        }
                    }
                }

                String jsonResponse = response.toString();
                String displayMessage = "Unknown server response.";

                int messageStartIndex = jsonResponse.indexOf("\"message\":\"");
                if (messageStartIndex != -1) {
                    messageStartIndex += "\"message\":\"".length();
                    int messageEndIndex = jsonResponse.indexOf("\"", messageStartIndex);
                    if (messageEndIndex != -1) {
                        displayMessage = jsonResponse.substring(messageStartIndex, messageEndIndex);
                    }
                }

                String finalDisplayMessage = displayMessage;
                SwingUtilities.invokeLater(() -> {
                    messageArea.append("Server Response Code: " + responseCode + "\n");
                    messageArea.append("Server Message: " + finalDisplayMessage + "\n");

                    // ✅ Switch to dashboard if login was successful
                    if (endpoint.equals("/signin") && finalDisplayMessage.equals("Sign-in successful!")) {
                        showDashboard(username);
                       // dispose();
                        
                    }
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    messageArea.append("Error connecting to server: " + ex.getMessage() + "\n");
                });
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }).start();
    }

    private void showDashboard(String username) {
        MainView mainView = new MainView();
        new MainController(mainView); // ✅ hook up controller to enable button logic
        setContentPane(mainView);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ✅ maximize window
        revalidate();
        repaint();
    }

    
    
    
    private String escapeJsonString(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < ' ' || (c >= '\u007f' && c <= '\u009f')) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new AuthClient().setVisible(true));
    }
    
}
