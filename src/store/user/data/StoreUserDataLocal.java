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

import conncetServerAnalyseFile.SendRequestToServer6;
import first.option.forsendcv.SendMail;

public class StoreUserDataLocal 
{
	//Data Area 
	private static String fileCVPathLoaction;
	private static String fileUserDataLocation;
			
	public static void storeDataLocal()
	{
		createFolder();
		storeData();
	}	
	private static void createFolder()
	{
		// Create a File object for the new folder
		File newFolder = new File("c:/appdata");
	    // Create the new folder
	    boolean success = newFolder.mkdir();    
	    if (success) 
	    {
	      // The folder was created successfully
	      System.out.println("Folder created successfully");
	    } 
	    else 
	    {
	      // The folder was not created
	      System.out.println("Floder exist");
	    }
	       
	}
	
	// Here we save the data in encrypted way 
	
	 public static void storeEncrptyData(String email,String password) 
	 {
		
	     // Encrypt the data using base64
	     String encryptedEmail = Base64.getEncoder().encodeToString(email.getBytes());
	     String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
	
	     // Store the encrypted data in a file
	     String filePath = "C:/appdata/encrypted_data.txt";
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
	     String filePath = "C:/appdata/Personalpostions.txt";
	     setFileUserDataLocation(filePath);
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
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
		setFileCVPathLoaction(filePath);
		SendRequestToServer6.setFileLocation(filePath);
		// Create a File object for the selected file
		copyFile(filePath,"C:/appdata/CV.docx");
	    SendMail.setUrl(filePath);
		 // Write the contents of the file to the "c:/appdata" directory with the original file extension
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
