package search.job.gui;

// The libraries
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import conncet.server.analyse.file.SendRequestToServer6;
import first.option.forsendcv.SendMail;
import store.user.data.ConvertCVFileJson;
import store.user.data.StoreUserDataLocal;
import three.option.forsendcv.SearchIntoLinkedIn;
import javax.swing.JPasswordField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

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

	// Building file analyse user data. 
	private static int analyseUserFile()
	{
		// Inside the file : 1- excel file -list places  2- excel file -list positions.
	
		String folderName = "analyse_user_data"; 
        File folder = new File(folderName);
        // Check if the folder exists, if not, create it
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created successfully: " + folderName);
            } else {
                System.out.println("Failed to create the folder: " + folderName);
                return 1;
            }
        } else {
            System.out.println("Folder already exists: " + folderName);
        }
		
        
        String positons_file = folderName + File.separator + "user_positons_list.xlsx";
        String places_work_file = folderName + File.separator + "user_places_list.xlsx";
        
        { 
	        // Create a workbook (HSSFWorkbook for .xls or XSSFWorkbook for .xlsx)
	        Workbook workbook = new XSSFWorkbook();
	        // Create a sheet in the workbook
	        Sheet sheet = workbook.createSheet("Sheet1");
	        // Create a header row
	        Row headerRow = sheet.createRow(0);
	        Cell headerCell1 = headerRow.createCell(0);
	        headerCell1.setCellValue("Positions");
	 
	        // Write the workbook to a file
	        try (FileOutputStream outputStream = new FileOutputStream(positons_file)) {
	            workbook.write(outputStream);
	        } catch (IOException e) {
	            System.out.println("Error writing Excel file.");
	            e.printStackTrace();
	        } finally {
	            // Close the workbook
	            try {
	                workbook.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
        }
               
        { 
	        // Create a workbook (HSSFWorkbook for .xls or XSSFWorkbook for .xlsx)
	        Workbook workbook = new XSSFWorkbook();
	        // Create a sheet in the workbook
	        Sheet sheet = workbook.createSheet("Sheet1");
	        // Create a header row
	        Row headerRow = sheet.createRow(0);
	        Cell headerCell1 = headerRow.createCell(0);
	        headerCell1.setCellValue("Places");
	 
	        // Write the workbook to a file
	        try (FileOutputStream outputStream = new FileOutputStream(places_work_file)) {
	            workbook.write(outputStream);
	        } catch (IOException e) {
	            System.out.println("Error writing Excel file.");
	            e.printStackTrace();
	        } finally {
	            // Close the workbook
	            try {
	                workbook.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
        }
        
        
		return 0; 	
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
		
		uploadCV.setBounds(49, 284, 146, 37);
		frame.getContentPane().add(uploadCV);
	    attachButton = new JButton("Attach file");
		attachButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{  
				
				StoreUserDataLocal.storeCVUserLocal();
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
		    		    	
		    	// The steps that will happen when the user press the search button : 
		    	// 1-  save the privacy data of the user : (Email + password-Email, Email + password: LinkedIn (if exist) ). 
		    	// 1.1 - create privacy folder + create privacy file 
		    	// insert the encrypted data to the file . 
		    	
		        // Specify folder and file using relative paths
		        String folderName = "PrivacyData"; // Folder inside the program folder
		        String fileName = "encrypted_personal_data.txt"; // File to check

		        // Create a File object for the folder
		        File folder = new File(folderName);

		        // Check if the folder exists, if not, create it
		        if (!folder.exists()) {
		            if (folder.mkdir()) {
		                System.out.println("Folder created successfully: " + folderName);
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
		                System.out.println("File created successfully: " + file.getPath());
		            } else {
		                System.out.println("File already exists: " + file.getPath());
		            }
		        } catch (IOException e1) {
		            System.out.println("An error occurred while creating the file.");
		            e1.printStackTrace();
		        }
		        
		        
		    	// 2- show the next page of the search (Automation search , Manual search )- completed . 
		    	 	
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
						
						// Store sensitive data about linkedIn.
						// should create the privacy data folder , and then create file for encrypted file and then.
						// save the data inside. 
						
						StoreUserDataLocal.storeEncrptyData(emailString, password);
						
						
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
		   analyseUserFile();	   
	
		   
		   String analyseFileForPostions = "";
		   String personalUserData = "";
		   Object[] options = {"Yes, Save The Data",
		   "No, Analyse the data again"};
		   try 
		   {
			   // TODO : change  server SendRequestToServer6 with ConnectAnalyseText.connectAnalyseTextServer
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
			
			
			// Step 3- add JSON user_cv to personal_data folder 
			String file_path = "personal_data//user_cv.docx";			
	        String folderName = "personal_data"; // Folder inside the program folder
	        String fileName = "user_cv.docx"; // File to check

	        // Build the relative path
	        File file = new File(folderName, fileName);

	        // Check if the file exists
	        if (file.exists() && file.isFile()) 
	        {
	        	// Here 1- create the user_cv JSON file format 
				// 2- and save the file inside the personal_data folder 
				ConvertCVFileJson.serverConvertWordToJson(file_path);
	        } else {
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
		}

	});
	btnNewButton.setBounds(48, 353, 114, 49);
	frame.getContentPane().add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Test Server ");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpPost httpPost = new HttpPost("http://localhost:3000/your_api_endpoint");

	        // Prepare request data (replace with your actual data)
	        String requestBody = "{\"data\": \"your_data_to_send\"}";
	        try {
				httpPost.setEntity(new StringEntity(requestBody));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        httpPost.setHeader("Content-type", "application/json");

	        CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(httpPost);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        try {
	            int statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode == 200) {
	                String responseBody = EntityUtils.toString(response.getEntity());
	                System.out.println("Response: " + responseBody);
	            } else {
	                System.err.println("Request failed with status code: " + statusCode);
	            }
	        } catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
	            try {
					response.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
					httpClient.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
			
			
		}
	});
	btnNewButton_1.setBounds(592, 218, 85, 21);
	frame.getContentPane().add(btnNewButton_1);
	frame.setVisible(true);
	
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
