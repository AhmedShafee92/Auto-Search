package view;

import javax.swing.*;
import java.awt.*;

public class AutomationView extends JPanel {
    public JButton automatedSearchButton = new JButton("Automated Search Jobs");
    public JButton manualSearchButton = new JButton("Manual Search Jobs");

    public AutomationView() {
        setLayout(null);
        setPreferredSize(new Dimension(727, 467));

        automatedSearchButton.setBounds(307, 103, 157, 35);
        add(automatedSearchButton);

        manualSearchButton.setBounds(307, 214, 157, 35);
        add(manualSearchButton);
    }
}
