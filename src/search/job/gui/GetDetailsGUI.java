package search.job.gui;

// The libraries
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import conncet.server.analyse.file.ConnectConvertStringToJson;
import conncet.server.analyse.file.ConnectGoogleAPIServer;
import conncet.server.analyse.file.ExcelWriter;
import store.user.data.CreateFoldersCloud;
import store.user.data.StoreUserDataLocal;
import store.user.data.StoreUserDataServer;

import javax.swing.JPasswordField;
import java.io.File;

public class GetDetailsGUI 
{
	// TODO: Change the GUI to simple GUI which to hide the unused button and show the most used buttons. 
	// Like the search , and in the begging the analysing and upload the CV.
	// Maybe in the future will be good design for very simple APP. 
	// Should add login APP that will be the only frame in the local side .
	// All the other firms should be in the server side . 
	
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

		    	// This functions add the sensitive data of the user in the cloud 
		    	// before start the search  we should save the sensitive data and all other data that the system will 
		    	// use in the cloud which will give the system the option to work without the user local storage .
		    	StoreUserDataLocal.createSensitiveUserFiles();	 	
		    	
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
				    return ;
				}
				else
				{
					
					String emailLinkedinString = LinkedInEmail.getText();
					char[] passwordCharLinkedin = LinkedInPassword.getPassword();
					String passwordLinkedin = new String(passwordCharLinkedin);

					// Store sensitive data about User email +linkedIn						
					StoreUserDataLocal.storeEncrptyData(emailString, password);							
					StoreUserDataLocal.storeEncrptyData(emailLinkedinString, passwordLinkedin);
					
					// Store the sensitive data in the cloud 
			    	StoreUserDataServer.createSensitiveUserFiles(emailString,password);
			    	// TODO : should store the rest of the sensitive data in the cloud . 
			    	
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
	   public void actionPerformed(ActionEvent e) 
	   { 
		   	   
		   // Here we will create all the needed folders in the cloud :
		   // 1-user_analyse_data, 2- user_personal_data 3- user_privacy_data 
		   CreateFoldersCloud.createFoldersCloud();
		   // Check if the user not upload the CV yet .
		   if(!CheckCVFileExist())
		   {
			   return ;
		   }
		   
		   // create files for storing analysing data in the local and server (cloud) .
		   StoreUserDataLocal.createAnalyseUserFiles();
		   StoreUserDataServer.createAnalyseUserFiles();
		   
		  
		   // Showing the user analysing data of his CV file . 
		   String analyseFileForPostions = "";
		   @SuppressWarnings("unused")
		   String personalUserData = "";
		   Object[] options = {"Yes, Save The Data",
		   "No, Analyse the data again"};
		   try 
		   
		   {
			   // Here we got analyse about the user CV from google API . 
			   analyseFileForPostions = ConnectGoogleAPIServer.analyseUserCVData();   
		   }
		   catch (IOException e1) 
		   {
			   e1.printStackTrace();
		   }
		   // Ask the user if accepted the AI analysing of his CV file 
			int result = JOptionPane.showOptionDialog(frame,analyseFileForPostions,
			"A Silly Question",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[1]);
			
			if ( result == JOptionPane.OK_OPTION ) 
			{

  				// step 1+2 : 
				List<String> positionsList = null;
				List<String> placesList = null;
				try
				{
				
					positionsList = ConnectGoogleAPIServer.positionsAnalyseUserCVData();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try 
				{
					 placesList = ConnectGoogleAPIServer.placesAnalyseUserCVData();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				// step 3 :
				// file the data from the API server inside the local excel files . 				
				ExcelWriter.writeListToExcelLocal(positionsList, "analyse_user_data/user_positons_list.xlsx");
				ExcelWriter.writeListToExcelLocal(placesList, "analyse_user_data/user_places_list.xlsx");
				// Save the data of the analysing process inside the excel file . 				
				ExcelWriter.writeListToExcelSerevr(placesList,positionsList);
			
			}
			
			// Step 3- add JSON user_cv to personal_data folder 
	        String folderName = "personal_data"; // Folder inside the program folder
	        String fileName = "user_cv.docx"; // File to check
	        // Build the relative path
	        File file = new File(folderName, fileName);
	        // Check if the file exists
	        if (file.exists() && file.isFile() && result == JOptionPane.OK_OPTION ) 
	        {
				 try 
				 {
					ConnectConvertStringToJson.serverConvertWordToJson();
					// ConnectConvertStringToJson.UploadJsonFileCloud();
					
				 }catch (IOException e1) 
				  {
					e1.printStackTrace();
				  }
				 
				ConnectConvertStringToJson.uploadJsonFileCloud();
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
	btnNewButton.addActionListener(new ActionListener() 
	{
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
	
	
	
    public static List<String> convertToList(StringBuilder sb, String delimiter) 
    {
        if (sb == null || sb.length() == 0) {
            return new ArrayList<>(); // Return an empty list if StringBuilder is null or empty
        }
        // Convert StringBuilder to String and split by the specified delimiter
        String content = sb.toString();
        String[] parts = content.split(delimiter);
        
        // Convert the array to a List
        return Arrays.asList(parts);
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
