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
		/// search option button 1:
		
		// Here we create an two levels of email . 
		// The first level is the main email  which  will send and receive all the email's negative and positive . 
		// The second level is user email that will receive just positive (which will be sent from the main email ) .
		// The main email will delete all the negative email and update the result (negative or positive ) .
		// (our data base will be cleared  after 2.5 months) which  the result in the excel file will be natural.

		// to be good process should the user - update the positive result in the weekly excel file 
		// and the system will update he result . 
		
		// for the prototype will use normal ( gmail email ) as first level of process and the email of the user . 
		// for full version will use the emails of the domain (for every customer will a have an email )
		
		
		
		
		// The first search engine opt1 has three process :
		// 1- send the email from the main domain (include reading the data from the storage in the cloud )
		// 2- update the storage of the domain 
		//3- manage the main email (include sending back the positive email to the user domain )
		// and deleting all the result of the INBOX in the main email after updating .
		
		
	
        // Create the "Go" button
        JButton jobSearchButtonOption1 = new JButton("Go- Search Option 1-Emails");
        // Add an ActionListener to the button
        jobSearchButtonOption1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Check the current text on the button
	    	 	FirstSearchOpation searchOpation=new FirstSearchOpation();	
                if (jobSearchButtonOption1.getText().equals("Go- Search Option 1")) 
                {
                    // Change the text to "Stop"
                    jobSearchButtonOption1.setText("Go- Search Option 1");
                    // Perform action for "Go" button
                    System.out.println("Action: Start searching...");
                    //searchOpation.search();
                       
                } 
                else 
                {
                    // Change the text back to "Go"
                    jobSearchButtonOption1.setText("Stop- Search Option 1");
                    // Perform action for "Stop" button
                    System.out.println("Action: Stop searching...");
   		    	 	//searchOpation.stopSearch();
                
                }
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
