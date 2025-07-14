package view;

import javax.swing.*;
import java.awt.*;

public class ManualSearchView extends JPanel {
    public JButton option1Button = new JButton("Go - Search Option 1 - Emails");
    public JButton option2Button = new JButton("Search Option 2 - LinkedIn");
    public JButton option3Button = new JButton("Search Option 3 - Secrets");

    public ManualSearchView() {
        setLayout(null);
        setPreferredSize(new Dimension(727, 467));

        option1Button.setBounds(326, 75, 250, 52);
        add(option1Button);

        option2Button.setBounds(326, 186, 250, 46);
        add(option2Button);

        option3Button.setBounds(326, 294, 250, 46);
        add(option3Button);
    }
}
