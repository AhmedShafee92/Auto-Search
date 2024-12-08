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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import conncet.server.analyse.file.SendRequestToServer6;
import first.option.forsendcv.SendMail;

public class StoreUserDataLocal 
{
	//Data Area 
	private static String fileCVPathLoaction;
	private static String fileUserDataLocation;
			
	// 1- create copy CV File and personal_data folder . 
	// 2 - add CV File to personal_data folder. 
	public static void storeCVUserLocal()
	{
		// create word file and folder and put the word file inside the folder . 
		createFolder();
		// This function show the user option to upload file and stored this file inside local machine . 
		storeWordFileData();
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
        // Create an empty Word document
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream fileOut = new FileOutputStream(filePath)) {
            // Write the empty document to the file
            document.write(fileOut);
        } catch (IOException e) {
            System.out.println("Error creating Word file: " + filePath);
            e.printStackTrace();
        }
                      
	}
    
    
	// Here we save the CV file of the user.
	private static void storeWordFileData() 
	{
		// Create a JFrame for the file dialog
		JFrame frame = new JFrame();
		// Use the FileDialog class to prompt the user to select a file
		FileDialog fileDialog = new FileDialog(frame, "Select a file", FileDialog.LOAD);
		fileDialog.setVisible(true);
		// Get the selected file path
		String filePath = fileDialog.getDirectory() + fileDialog.getFile();

		// here we save user file location which uploaded . 
		setFileCVPathLoaction(filePath);
		// Here we save the word file that the user insert inside the personal_data/cv_user.docs
		copyFile(filePath,"personal_data/user_cv.docx");
		// TODO : we can delete this option , there an class that serve the storage of all data about the user . 
	    SendMail.setUrl(filePath);

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
	    
	 public static void storeUserAnalyseData(String analyseData) 
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



	
	// TODO: should choose better name . 
	// Building file analyse user data . 
		public static int analyseUserFile()
		{
			// Inside the file : 1- excel file -list places  2- excel file -list positions.
		
			String folderName = "analyse_user_data"; 
	        File folder = new File(folderName);
	        // Check if the folder exists, if not, create it
	        if (!folder.exists()) {
	            if (folder.mkdir()) {
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
	
	

}
