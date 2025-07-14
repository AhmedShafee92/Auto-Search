package controller;

import view.AutomationView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutomationController {
    private final AutomationView view;
    private final JFrame frame;

    public AutomationController() {
        view = new AutomationView();
        frame = new JFrame("Automation Job Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        initListeners();
    }

    private void initListeners() {
        view.automatedSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                showMessage();

                // TODO: Replace with automation logic or next screen controller
                // e.g., new AutomatedJobSearchController();
            }
        });

        view.manualSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                // TODO: Replace with manual search controller
                // e.g., new ManualJobSearchController();

                // Temporary call:
                showMessage("Manual search screen will open (not implemented yet)");
            }
        });
    }

    private void showMessage() {
        showMessage("Congratulations! Now we continue the process.\nBe ready for interviews.");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
