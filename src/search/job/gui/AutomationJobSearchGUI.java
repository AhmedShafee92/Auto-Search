package search.job.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AutomationJobSearchGUI 
{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutomationJobSearchGUI window = new AutomationJobSearchGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutomationJobSearchGUI() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(150, 150, 727, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Automaton Job Search");
		frame.getContentPane().setLayout(null);
		
		
		/// Automated search button action 
		JButton automatedSearchButton = new JButton("Automated Search Jobs");
		automatedSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// TODO : this process will be fully in the server side 
				// can't run the process in the client side the custumer should't keep their system of 
				// device run .
				frame.setVisible(false);
				showMessage();
				// AutomatedJobSearchSecoundScreen.showScreen();
				// Here we should continue to make the automation search .
				// Will search using the 3 option but without user action . 
				
			}
		});
		automatedSearchButton.setBounds(307, 103, 157, 35);
		frame.getContentPane().add(automatedSearchButton);
		
		
		/// Manual search  button action 
		JButton manualSearchButton = new JButton("Manual Search Jobs");
		manualSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				// Here we just take the user to the next screen .
				// where the user will choose which option to work with . 
				ManualJobSearchSecoundScreenGUI.showScreen();
		 
			}
		});
		manualSearchButton.setBounds(307, 214, 157, 35);
		frame.getContentPane().add(manualSearchButton);
	}
	

	private static void showMessage() 
	{
		// TODO Auto-generated method stub
		
		JOptionPane.showMessageDialog(null,"Congratulations!Now we are contuine the process \n,Be ready for interviwes ");
		
	}
	
	
	
	
	
}
