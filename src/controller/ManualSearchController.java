package controller;

import view.ManualSearchView;
import first.option.forsendcv.FirstSearchOpation;
import three.option.forsendcv.ThreeSearchOpation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManualSearchController {
    private final JFrame frame;
    private final ManualSearchView view;

    public ManualSearchController() {
        view = new ManualSearchView();
        frame = new JFrame("Manual Job Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        initListeners();
    }

    private void initListeners() {
        view.option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstSearchOpation option1 = new FirstSearchOpation();
                // TODO: Replace with actual method call
                System.out.println("Option 1: Search Emails started...");
                // option1.search();
                // Toggle text if needed
            }
        });

        view.option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Replace with LinkedIn search call
                System.out.println("Option 2: LinkedIn search (to be implemented)");
            }
        });

        view.option3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreeSearchOpation option3 = new ThreeSearchOpation();
                option3.search(); // Your existing logic
            }
        });
    }
}
