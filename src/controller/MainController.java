package controller;

import model.MainModel;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private final MainModel model;
    private final MainView view;
    private final JFrame frame;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;

        frame = new JFrame("Insert Your Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        initListeners();
    }

    private void initListeners() {
        view.attachCVButton.addActionListener(e -> model.attachCV());

        view.startSearchingButton.addActionListener(e -> {
            String email = view.emailUser.getText();
            String password = String.valueOf(view.passwordUser.getPassword());
            String linkedInEmail = view.linkedInEmail.getText();
            String linkedInPassword = String.valueOf(view.linkedInPassword.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter all fields");
                return;
            }

            model.storeCredentials(email, password, linkedInEmail, linkedInPassword);

            frame.setVisible(false);
            // Replace this with your new GUI frame
            JOptionPane.showMessageDialog(null, "Proceeding to next screen...");
        });

        view.analysePersonalDataButton.addActionListener(e -> {
            if (!model.isCVValid()) {
                JOptionPane.showMessageDialog(view, "CV file missing or empty.");
                return;
            }

            model.analyseData(frame);
        });

        view.viewPersonalDataButton.addActionListener(e ->
            JOptionPane.showMessageDialog(frame, "Soon Available!")
        );
    }
}
