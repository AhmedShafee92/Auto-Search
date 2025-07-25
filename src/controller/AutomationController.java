package controller;

import model.UserModel;
import view.AuthView;
import view.MainView;

import javax.swing.*;

public class AutomationController {

    private AuthView authView;
    private JFrame mainFrame;

    public AutomationController(AuthView authView, JFrame mainFrame) {
        this.authView = authView;
        this.mainFrame = mainFrame;

        initController();
    }

    private void initController() {
        authView.loginButton.addActionListener(e -> handleLogin());
        authView.registerButton.addActionListener(e -> handleSignup());
    }

    private void handleLogin() {
        String email = authView.emailField.getText();
        String password = new String(authView.passwordField.getPassword());

        if (UserModel.login(email, password)) {
            JOptionPane.showMessageDialog(authView, "Login Successful!");
            showMainView();
        } else {
            JOptionPane.showMessageDialog(authView, "Login Failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSignup() 
    {
        String email = authView.emailField.getText();
        String password = new String(authView.passwordField.getPassword());

        if (UserModel.signup(email, password)) 
        {
            JOptionPane.showMessageDialog(authView, "Sign-Up Successful!");
            showMainView();
        } else 
        {
            JOptionPane.showMessageDialog(authView, "Sign-Up Failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMainView() {
        MainView mainView = new MainView();
        mainFrame.setContentPane(mainView);
        mainFrame.revalidate();
    }
}
