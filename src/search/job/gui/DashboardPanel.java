//DashboardPanel.java
package search.job.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel 
{
	 public DashboardPanel(String username) 
	 {
	     setLayout(new BorderLayout());
	     JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
	     welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
	     add(welcomeLabel, BorderLayout.CENTER);
	 }
}

