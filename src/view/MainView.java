package view;
import controller.AuthController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel 
{
	
    public JButton attachCVButton = new JButton("Attach CV");
    public JLabel attachTipLabel = new JLabel("Please attach your CV before analyzing or searching.");
    public JButton analysePersonalDataButton = new JButton("Analyse Data (Start Here)");
    public JButton startSearchingButton = new JButton("Search Jobs");
    public JButton viewPersonalDataButton = new JButton("View Personal Data");

    
    public MainView() 
    {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 400));

        // Row 0 - Attach CV
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.gridx = 0;
        gbc0.gridy = 0;
        gbc0.insets = new Insets(10, 20, 10, 20);
        gbc0.fill = GridBagConstraints.HORIZONTAL;
        add(attachCVButton, gbc0);

        // Row 1 - Attach Tip Label
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        gbc1.insets = new Insets(0, 20, 20, 20);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        attachTipLabel.setForeground(Color.GRAY);
        attachTipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(attachTipLabel, gbc1);

        // Row 2 - Analyse Button
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.insets = new Insets(10, 20, 10, 20);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        analysePersonalDataButton.setBackground(Color.decode("#4CAF50")); // green
        analysePersonalDataButton.setForeground(Color.WHITE);
        add(analysePersonalDataButton, gbc2);

        // Row 3 - Search Button
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 3;
        gbc3.insets = new Insets(10, 20, 10, 20);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        startSearchingButton.setBackground(Color.decode("#2196F3")); // blue
        startSearchingButton.setForeground(Color.WHITE);
        add(startSearchingButton, gbc3);

        // Row 4 - View Personal Data
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 4;
        gbc4.insets = new Insets(10, 20, 10, 20);
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        viewPersonalDataButton.setBackground(Color.LIGHT_GRAY);
        add(viewPersonalDataButton, gbc4);
        
		/*
		 * // ✅ Add action listeners attachCVButton.addActionListener(e ->
		 * controller.handleAttachCV()); analysePersonalDataButton.addActionListener(e
		 * -> controller.handleAnalyze()); startSearchingButton.addActionListener(e ->
		 * controller.handleSearch()); viewPersonalDataButton.addActionListener(e ->
		 * controller.handleViewPersonalData());
		 */
    }
}
