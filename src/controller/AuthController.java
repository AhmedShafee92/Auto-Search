package controller;

import model.UserModel;
import view.AuthView;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController {
    private final JFrame frame;
    private final AuthView view;

    public AuthController() {
        view = new AuthView();
        frame = new JFrame("Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        initListeners();
    }

    private void initListeners() {
        view.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = view.emailField.getText();
                String password = new String(view.passwordField.getPassword());

                if (UserModel.login(email, password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    goToMainFrame();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials!");
                }
            }
        });

        view.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = view.emailField.getText();
                String password = new String(view.passwordField.getPassword());

                if (UserModel.register(email, password)) {
                    JOptionPane.showMessageDialog(frame, "Registration Successful! Please log in.");
                } else {
                    JOptionPane.showMessageDialog(frame, "User already exists.");
                }
            }
        });
    }

    private void goToMainFrame() {
        frame.setVisible(false); // Hide login
        new MainView();          // Show main app
    }
}
