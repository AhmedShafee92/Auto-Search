// File: src/controller/MainFrame.java
package controller;

import view.AuthView;
import view.MainView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame 
{
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AuthView authView;
    private MainView mainView;

    public static final String AUTH = "auth";
    public static final String MAIN = "main";

    public MainFrame() {
        setTitle("Job Search App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Init views
        authView = new AuthView();
        mainView = new MainView();

        cardPanel.add(authView, AUTH);
        cardPanel.add(mainView, MAIN);

        add(cardPanel);
        setVisible(true);
    }

    public AuthView getAuthView() {
        return authView;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void showScreen(String name) {
        cardLayout.show(cardPanel, name);
    }
}
