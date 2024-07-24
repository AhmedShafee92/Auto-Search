package search.job.gui;

import javax.swing.JFrame;

public class PersonalDataGUI {
	

	public static void main(String[] args)
	{
		showScreen();
		
	}
	
     private static void showScreen()
    {
	
    	 JFrame frame = new JFrame();
 		frame.setBounds(150, 150, 727, 467);
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.setTitle("Personal Data, Abut your postions");
 		frame.getContentPane().setLayout(null);	
 		frame.setVisible(true);

    }
		
}
