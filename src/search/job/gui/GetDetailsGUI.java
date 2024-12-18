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
import conncet.server.analyse.file.ConnectGoogleAPIServer;
import first.option.forsendcv.SendMail;
import store.user.data.ConvertCVFileJson;
import store.user.data.StoreUserDataLocal;
import three.option.forsendcv.SearchIntoLinkedIn;
import javax.swing.JPasswordField;
import java.io.File;



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
	
	//Implementation of Main Function .
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
		
		uploadCV.setBounds(49, 301, 100, 37);
		frame.getContentPane().add(uploadCV);
	    attachButton = new JButton("Attach file");
		attachButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{  
				
				StoreUserDataLocal.storeCVUserLocal();
			}
		
		});
		attachButton.setBounds(172,301,94,37);
		frame.getContentPane().add(attachButton);
		
		//Fields Email and Email_password
		
		JLabel insertEmail = new JLabel(" LinkedIn_Email");
		insertEmail.setBounds(203,217,94,30);
		frame.getContentPane().add(insertEmail);	
		LinkedInEmail = new JTextField();
		LinkedInEmail.setBounds(365, 101, 160, 30);
		
		frame.getContentPane().add(LinkedInEmail);
		LinkedInEmail.setColumns(10);
		LinkedInPassword = new JPasswordField();
		
		//Fields LinkedIn and  LinkedIn_password 

		LinkedInPassword.setBounds(365, 158, 160, 30);
		frame.getContentPane().add(LinkedInPassword);
		
		
		JLabel lblNewLabel = new JLabel(" Password");
		lblNewLabel.setBounds(205, 154, 114, 37);
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
		    	
		    	// TODO: should to change the design of the code to put the storage methods inside storage classes   
		    		    	
		    	// 1- save the privacy data of the user : (Email + password-Email, Email + password: LinkedIn (if exist) ). 
		    	// 1.1- create privacy folder + create privacy file 
		    	// insert the encrypted data to the file . 
		    	
		        // Specify folder and file using relative paths
		        String folderName = "PrivacyData"; // Folder inside the program folder
		        String fileName = "encrypted_personal_data.txt"; // File to check

		        // Create a File object for the folder
		        File folder = new File(folderName);

		        // Check if the folder exists, if not, create it
		        if (!folder.exists()) {
		            if (folder.mkdir()) {
		            } else {
		                System.out.println("Failed to create the folder: " + folderName);
		                return;
		            }
		        } else {
		            System.out.println("Folder already exists: " + folderName);
		        }

		        // Create a File object for the file inside the folder
		        File file = new File(folder, fileName);

		        try {
		            // Check if the file exists, if not, create it
		            if (file.createNewFile()) {
		            } else {
		                System.out.println("File already exists: " + file.getPath());
		            }
		        } catch (IOException e1) {
		            System.out.println("An error occurred while creating the file.");
		            e1.printStackTrace();
		        }
		        
		        		    	 	
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
						
					/* 1- Collect data where the person can work */
					/* 2- create a local data base for the companies that fit his skills and jobs  */
					/* 3- start send data to these companies */
					
					String emailLinkedinString=LinkedInEmail.getText();
					char[] passwordCharLinkedin = LinkedInPassword.getPassword();
					String passwordLinkedin = new String(passwordCharLinkedin);

					// Store sensitive data about User email.						
					StoreUserDataLocal.storeEncrptyData(emailString, password);							
					// Store sensitive data about User linkedIn.						
					StoreUserDataLocal.storeEncrptyData(emailLinkedinString, passwordLinkedin);
				
					SendMail.setFrom(emailString);
					SendMail.setEmailPassword(password);
					
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
		
		   StoreUserDataLocal.analyseUserFile();
		   String analyseFileForPostions = "";
		   @SuppressWarnings("unused")
		   String personalUserData = "";
		   Object[] options = {"Yes, Save The Data",
		   "No, Analyse the data again"};
		   try 
		   {
			   // Check if the user upload the CV . 
			   if(CheckCVFileExist())
			   {
				   // Here we got analyse about the user CV from google API . 
				   analyseFileForPostions = ConnectGoogleAPIServer.analyseUserCVData();   
			   }
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
				
				// TODO: 1- create function that get from the Google API : where the user can work get list .
				// 2- function that create list of positions of what the user can work .
				// 3- Store the results inside the excel files that we created in analyse_user_data .
				// TODO : delete the old version of storing the data . 
				StoreUserDataLocal.storeUserAnalyseData(analyseFileForPostions);   	
			}
			
			// Step 3- add JSON user_cv to personal_data folder 
			String file_path = "personal_data//user_cv.docx";			
	        String folderName = "personal_data"; // Folder inside the program folder
	        String fileName = "user_cv.docx"; // File to check

	        // Build the relative path
	        File file = new File(folderName, fileName);

	        // Check if the file exists
	        if (file.exists() && file.isFile()) 
	        {
	        	//Convert the user CV word file to JSON file format .
	        	//TODO : here we should fix the process still not working . 
				ConvertCVFileJson.serverConvertWordToJson(file_path);
	        }
	        else 
	        {
	            System.out.println("The file '" + fileName + "' does not exist in the folder: " + folderName);
	        }
						
		    
		}
	  });
	AnalysePesonalData.setBounds(234,348,146,58);	
	frame.getContentPane().add(AnalysePesonalData);	
	
	JButton btnNewButton = new JButton("Personal Data");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			// Here will show the user the summary of the analysing that we created 
			// User have the option to see the CV 
		    JOptionPane.showMessageDialog(null, "Soon Available!");
		      
		}

	});
	btnNewButton.setBounds(48, 353, 114, 49);
	frame.getContentPane().add(btnNewButton);
	
	JLabel lblLinkedinpassword = new JLabel(" LinkedIn_Password");
	lblLinkedinpassword.setBounds(203, 257, 94, 34);
	frame.getContentPane().add(lblLinkedinpassword);
	
	JLabel lblNewLabel_1 = new JLabel(" Email ");
	lblNewLabel_1.setBounds(219, 97, 100, 30);
	frame.getContentPane().add(lblNewLabel_1);
	frame.setVisible(true);
	
 }
	
	private static boolean CheckCVFileExist()
	{
	      // Specify the file path to check
        String filePath = "personal_data/user_cv.docx";
        // Create a File object
        File file = new File(filePath);
        // Check if the file exists
        if (file.exists()) {
            return true;
        } else {
            return false ;
        }
		
	}
	
	//Get and Set method of the Email and Password (LinkedIn and Normal_Email) . 
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
