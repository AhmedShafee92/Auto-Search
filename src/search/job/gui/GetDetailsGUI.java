package search.job.gui;

// The libraries
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import conncetServerAnalyseFile.SendRequestToServer6;
import first.option.forsendcv.SendMail;
import store.user.data.StoreUserDataLocal;
import three.option.forsendcv.SearchIntoLinkedIn;
import javax.swing.JPasswordField;

public class GetDetailsGUI 
{
	// Data Fields 
	private static JTextField LinkedInEmail = null;
	private static JPasswordField LinkedInPassword = null;
	private static JTextField EmailUser = null;
	private static JPasswordField PasswordUser = null; 			
	private static JButton startSearchingButton = null;
	private static JButton AnalysePesonalData = null;
	private static JButton attachButton = null;
	
	//Main Function for run the frame to start working. 
	public static void main(String[] args)	
	{		
		showScreen();
	}		

	//Implementation of Main Function 
	private static void showScreen() 
    {
		
		//Data Area 
		final JFrame frame = new JFrame();
		JLabel uploadCV = null;
		
		frame.setBounds(150, 150, 727, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Insert Your Detials");
		frame.getContentPane().setLayout(null);	
				
		try 
		{
			uploadCV = new JLabel("Upload Your CV");			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		uploadCV.setBounds(49, 284, 146, 37);
		frame.getContentPane().add(uploadCV);
	    attachButton = new JButton("Attach file");
		attachButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{  
				StoreUserDataLocal.storeDataLocal();
			}
		
		});
		attachButton.setBounds(172,284,94,37);
		frame.getContentPane().add(attachButton);
		
		//Fields Email and Email_password
		
		JLabel insertEmail = new JLabel("Insert your LinkedIn_Email and LinkedIn_Password");
		insertEmail.setBounds(49,210,199,37);
		frame.getContentPane().add(insertEmail);	
		LinkedInEmail = new JTextField();
		LinkedInEmail.setBounds(365, 101, 160, 30);
		
		frame.getContentPane().add(LinkedInEmail);
		LinkedInEmail.setColumns(10);
		LinkedInPassword = new JPasswordField();
		
		//Fields LinkedIn and  LinkedIn_password 

		LinkedInPassword.setBounds(365, 158, 160, 30);
		frame.getContentPane().add(LinkedInPassword);
		
		
		JLabel lblNewLabel = new JLabel(" Insert Your Email and Password ");
		lblNewLabel.setBounds(46, 92, 220, 37);
		frame.getContentPane().add(lblNewLabel);	
		EmailUser = new JTextField();
		EmailUser.setBounds(365, 219, 160, 28);
			
		frame.getContentPane().add(EmailUser);
		EmailUser.setColumns(10);
		PasswordUser = new JPasswordField();
		PasswordUser.setBounds(365, 264, 160, 30);
		
		frame.getContentPane().add(PasswordUser);
		
		//Submit button 
		
		startSearchingButton = new JButton("Start Searching");
		startSearchingButton.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent e) 
		    {		
		    	// store sensitive data about email and the password 			
		    	String emailString = null;
		    	String password = null;
		    	char[] passwordChars = null;
			    try 
			    {
			    	emailString = EmailUser.getText();	
					
				} 
			    catch (Exception e2) 
			    {
					System.err.println("Can't get the email address");
			    }
				try 
				{	
					passwordChars = PasswordUser.getPassword();
				} 
				catch (Exception e2) 
				{
					System.err.println("Can't get the password data  ");
				}
				password = new String(passwordChars);
				//Validity the user data, that the data is not empty .
				
				if(emailString.isEmpty() || password.isEmpty())
				{
				    JOptionPane.showMessageDialog(null, "You chose rock!");
				}
				else
					{	
						//	TODO: complete the first option of the search 
						//	Store sensitive data about linkedIn
					
						StoreUserDataLocal.storeEncrptyData(emailString, password);
						/*
						 * 
						 * 1-here will change the using , will use 
						 * vertex AI model that training with images. 
						 * 
						 * The model training with a lot of image of web page 
						 * and when we running this model will detect what king of this page 
						 * 
						 * 
						 * 
						 * 	{
						 * 		1.a - If we find a companies that match the skills of the person .
						 * 		1.b - Collect these companies inside excel sheet . 
						 *
						 * 		1.c - start apply to position in each company that we collected inside the excel sheet 
						 * 	}	
						 * 
						 * 
						 * 
						 */
						
						
						/* 1- Collect data where the person can work */
						/* 2- create a local data base for the companies that fit his skills and jobs  */
						/* 3- start send data to these companies */
						
						SendMail.setFrom(emailString);
						SendMail.setEmailPassword(password);
						
						String emailLinkedinString=LinkedInEmail.getText();
						char[] passwordCharLinkedin = LinkedInPassword.getPassword();
						String passwordLinkedin = new String(passwordCharLinkedin);
						
						
						StoreUserDataLocal.storeEncrptyData(emailLinkedinString, passwordLinkedin);
						SearchIntoLinkedIn.setLinkedInEmailString(emailLinkedinString);
						SearchIntoLinkedIn.setLinkedInPasswordString(passwordLinkedin);
						// Update the data inside the send email class 
						frame.setVisible(false);
						AutomationJobSearchGUI.main(null);
						
					}
		    	}
	});
	startSearchingButton.setBounds(437,348,146,58);	
	frame.getContentPane().add(startSearchingButton);	
	frame.setVisible(true);	
	
	AnalysePesonalData = new JButton("Analysing Data");
	AnalysePesonalData.addActionListener(new ActionListener() 
	{
		//TODO: put the implementation of the method in private method
	   public void actionPerformed(ActionEvent e) 
	   {		
		   String analyseFileForPostions = "";
		   Object[] options = {"Yes, Save The Data",
		   "No, Analyse the data again"};
		   try 
		   {
			   analyseFileForPostions = SendRequestToServer6.analyseData();
		   }
		   catch (IOException e1) 
		   {
			   e1.printStackTrace();
		   }
			int result = JOptionPane.showOptionDialog(frame,analyseFileForPostions,
			"A Silly Question",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[1]);
			
			if ( result == JOptionPane.OK_OPTION ) 
			{
				// here we need to save the data of the analyse AI machine inside the local machine of the user   
				StoreUserDataLocal.storeAnalyseData(analyseFileForPostions);   	
		    
			}
		    
		}
	  });
	AnalysePesonalData.setBounds(234,348,146,58);	
	frame.getContentPane().add(AnalysePesonalData);	
	frame.setVisible(true);
	
 }
    
	//Get and Set method of the Email and Password (LinkedIn and Normal_Email)
	
	public static JTextField getEmailInput() 
	{
		return LinkedInEmail;
	}
	@SuppressWarnings("unused")
	private static void setEmailInput(JTextField emailInput)
	{
		GetDetailsGUI.LinkedInEmail = emailInput;
	}
	public static JPasswordField getEmailPassword() 
	{
		return LinkedInPassword;
	}
	@SuppressWarnings("unused")
	private static void setEmailPassword(JPasswordField emailPassword) 
	{
		GetDetailsGUI.LinkedInPassword = emailPassword;
	}
	
	public static JTextField getEmailUser() 
	{
		return EmailUser;
	}
	
	@SuppressWarnings("unused")
	private static void setEmailUser(JTextField linkedInEmailField)
	{
		GetDetailsGUI.EmailUser = linkedInEmailField;
	}
	
	public static JPasswordField getPasswordUser() 
	{
		return PasswordUser;
	}
	
	@SuppressWarnings("unused")
	private static void setPasswordUser(JPasswordField linkedInPasswordField) 
	{
		GetDetailsGUI.PasswordUser = linkedInPasswordField;
	}	
	

}
