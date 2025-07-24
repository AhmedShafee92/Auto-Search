// File: src/view/AuthView.java
package view;

import javax.swing.*;
import java.awt.*;

public class AuthView extends JPanel {
    public JTextField emailField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();
    public JButton loginButton = new JButton("Sign In");
    public JButton registerButton = new JButton("Sign Up");

    public AuthView() {
        setLayout(null);
        setPreferredSize(new Dimension(400, 300));

        JLabel titleLabel = new JLabel("Login / signup", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30);
        add(titleLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 70, 80, 25);
        add(emailLabel);
        emailField.setBounds(150, 70, 180, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 80, 25);
        add(passwordLabel);
        passwordField.setBounds(150, 110, 180, 25);
        add(passwordField);

        loginButton.setBounds(80, 170, 100, 30);
        add(loginButton);
        registerButton.setBounds(200, 170, 100, 30);
        add(registerButton);
    }
}
