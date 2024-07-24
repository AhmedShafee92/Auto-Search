package search.job.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AutomatedJobSearchSecoundScreenGUI {
	
	
	public static void main(String[] args)
	{
		
	    showScreen();	
		 showMessage();

	}
	
	
	private static void showMessage() 
	{
		// TODO Auto-generated method stub
		
		JOptionPane.showMessageDialog(null,"Congratulations!Now we are contuine the process \n,Be ready for interviwes ");
		
	}


	public static void showScreen() 
	{
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setBounds(150, 150, 727, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Manual Job Search");
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	}
	

}
