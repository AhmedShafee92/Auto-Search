// File: src/controller/AuthController.java
package controller;

import model.UserModel;
import view.AuthView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController {
    private final MainFrame mainFrame;
    private final AuthView view;

    public AuthController(MainFrame frame) {
        this.mainFrame = frame;
        this.view = frame.getAuthView();
        initListeners();
    }

    private void initListeners() {
        view.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = view.emailField.getText();
                String password = new String(view.passwordField.getPassword());

                if (UserModel.login(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    mainFrame.showScreen(MainFrame.MAIN);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            }
        });

        view.registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = view.emailField.getText();
                String password = new String(view.passwordField.getPassword());

                if (UserModel.register(email, password)) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "User already exists.");
                }
            }
        });
    }
}
