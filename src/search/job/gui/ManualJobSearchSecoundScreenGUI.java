package search.job.gui;
import javax.swing.JFrame;
import first.option.forsendcv.FirstSearchOpation;
import first.option.forsendcv.SendMail;
import three.option.forsendcv.SearchIntoLinkedIn;
import three.option.forsendcv.ThreeSearchOpation;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ManualJobSearchSecoundScreenGUI 
{	
	public static void main(String[] args)
	{
		showScreen();
	}
		  
	public static void showScreen() 
    {
		
		final JFrame frame = new JFrame();
		frame.setBounds(150, 150, 727, 467); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Manual Job Search");
		frame.getContentPane().setLayout(null);
		
		// This process should run in the server side, which will run after the user pressing the button 
		// and will work in the near future. 
		// will change the code to JS(javascript) or keep in java. 
		/// search option button 1:
		
		// Here we create an two levels of email . 
		// The first level that is main email  which  will send and receive all the email's negative and positive 
		// The second level is user email that will receive just positive (which be sent from the main email ) .
		// The main email will delete all the negative email and update the negative result in the excel file .
		// The same positive email will keep in the main email and will send to the second email and will update in the excel file that .  
		// Result is positive (we update the line of the domain that the result was negative or positive  ) . 
		// (our data base will be cleared  after 2.5 months) which  the result in the excel file will be natural.
		
		JButton jobSearchButtonOption1 = new JButton("Search option1, Send emails");
		jobSearchButtonOption1.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {	
		    	FirstSearchOpation searchOpation=new FirstSearchOpation();	
			     searchOpation.search();
			     frame.setVisible(false);
			     
			}   
			
		});
		jobSearchButtonOption1.setBounds(326, 75, 163, 52);
		frame.getContentPane().add(jobSearchButtonOption1);
		
		/// search option button 2
		JButton jobSearchButtonOption2 = new JButton("Search option2, LinkedIn Engine");
		jobSearchButtonOption2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			// This process should be in the server side also the data (The storage) that this process will use  should be in t
			//search type2
			}
		});
		jobSearchButtonOption2.setBounds(326, 186, 163, 46);
		frame.getContentPane().add(jobSearchButtonOption2);
		
		/// search option button 3	
		JButton jobSearchButtonOption3 = new JButton("Search option3, Secrets");
		jobSearchButtonOption3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{		
				ThreeSearchOpation threeSearch=new ThreeSearchOpation();
				threeSearch.search();		
			}
		});
		jobSearchButtonOption3.setBounds(326, 294, 163, 46);
		frame.getContentPane().add(jobSearchButtonOption3);
		frame.setVisible(true);
	
    }

}
