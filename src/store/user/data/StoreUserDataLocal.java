package store.user.data;

//Libraries 
import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import javax.swing.JFrame;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import conncet.server.analyse.file.SendRequestToServer6;
import first.option.forsendcv.SendMail;

public class StoreUserDataLocal 
{
	//Data Area 
	private static String fileCVPathLoaction;
	private static String fileUserDataLocation;
			
	public static void storeCVUserLocal()
	{
		// 1- create copy CV File and personal_data folder . 
		// 2 - add CV File to personal_data folder. 
		createFolder();
		storeData();
	}	
	private static void createFolder()
	{	    
	    
        // Define the folder path and Word file name
        String folderPath = "personal_data";
        String wordFileName = "user_cv.docx";

        // Create the folder if it doesn't exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (folder.mkdir()) {
            } else {
                System.out.println("Failed to create folder: " + folderPath);
                return;
            }
        }

        // Define the full path for the Word file
        String wordFilePath = folderPath + File.separator + wordFileName;

        // Create the Word file
        createWordFile(wordFilePath);
    }

    private static void createWordFile(String filePath) 
    {
        // Create a new Word document
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream fileOut = new FileOutputStream(filePath)) {

            // Add a paragraph to the document
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.createRun().setText("This is a sample Word file created with Apache POI.");

            // Write the document to the file
            document.write(fileOut);
            System.out.println("Word file created: " + filePath);

        } catch (IOException e) {
            System.out.println("Error creating Word file: " + filePath);
            e.printStackTrace();
        }
	           
	}
	
	// Here we save the data in encrypted way 
	
	 public static void storeEncrptyData(String email,String password) 
	 {
		
	     // Encrypt the data using base64
	     String encryptedEmail = Base64.getEncoder().encodeToString(email.getBytes());
	     String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
	
	     // Store the encrypted data ץ
	     String filePath = "PrivacyData/encrypted_personal_data.txt";
	     setFileUserDataLocation(filePath);
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	         writer.write(encryptedEmail);
	         writer.newLine();
	         writer.write(encryptedPassword);
	         writer.newLine();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
			 
	}
	    
	 public static void storeAnalyseData(String analyseData) 
	 {
		 
		 // Store the encrypted data in a file
	     String filePath = "personal_data/Personalpostions.txt";
	     setFileUserDataLocation(filePath);
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) 
	     {
	         writer.write(analyseData);
	         writer.newLine();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
		 			 
	 }
	      
	 
	 // Here we save the CV file of the user.
	private static void storeData() 
	{
		// Create a JFrame for the file dialog
		JFrame frame = new JFrame();
		// Use the FileDialog class to prompt the user to select a file
		FileDialog fileDialog = new FileDialog(frame, "Select a file", FileDialog.LOAD);
		fileDialog.setVisible(true);
		// Get the selected file path
		String filePath = fileDialog.getDirectory() + fileDialog.getFile();
		
		
		
		

		//DO something ??
		
		
		
		
		
		
		
		// This code maybe should update, the system should have an storage internal (for automation using in the background )
		setFileCVPathLoaction(filePath);
		SendRequestToServer6.setFileLocation(filePath);
		
		// Here we save the word file that the user insert inside the personal_data/cv_user.docs
		copyFile(filePath,"personal_data/user_cv.docx");
	    SendMail.setUrl(filePath);

	}
	
	
	public static String getFileCVPathLoaction() 
	{
		return fileCVPathLoaction;
	}
	
	
	private static void setFileCVPathLoaction(String fileCVPathLoaction) 
	{
		StoreUserDataLocal.fileCVPathLoaction = fileCVPathLoaction;
	}
	
	
	public static String getFileUserDataLocation() {
		return fileUserDataLocation;
	}
	
	
	private static void setFileUserDataLocation(String fileUserDataLocation) 
	{
		StoreUserDataLocal.fileUserDataLocation = fileUserDataLocation;
	}
	
	private static void copyFile(String source,String desc) 
	{
		
		  File sourceFile = new File(source);
	      File destinationFile = new File(desc);
	      try {
		          InputStream in = new FileInputStream(sourceFile);
		          OutputStream out = new FileOutputStream(destinationFile);
		          byte[] buffer = new byte[1024];
		          int length;
		          while ((length = in.read(buffer)) > 0) {
		              out.write(buffer, 0, length);
		          }
		          in.close();
		          out.close();
		          System.out.println("File copied from " + sourceFile + " to " + destinationFile);
	          } 
	      catch (IOException e) 
	      {
	          System.out.println("Error copying file: " + e.getMessage());
	      }
		
	}




}
