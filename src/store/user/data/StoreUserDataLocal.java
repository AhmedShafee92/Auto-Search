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


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class StoreUserDataLocal 
{
	
    // Base directory name for the storage within the project directory
    private static final String BASE_STORAGE_DIR = "AppStorage";
    // Parameter Area 
   static Path projectDir = Paths.get(System.getProperty("user.dir"), BASE_STORAGE_DIR);
   static Path personalDataPath = projectDir.resolve("personal_data");
   static Path analyseDataPath = projectDir.resolve("analyse_data");

    /**
     * Initializes the base storage directory in the project directory.
     */
    public static void initializeStorage() 
    {
        try {
            // Create the storage directory if it doesn't exist
            if (!Files.exists(projectDir)) {
                Files.createDirectories(projectDir);
            } else {
            	// nothing to do (already the folder exist)
            }
        } catch (Exception e) {
            System.err.println("Error creating storage directory: " + e.getMessage());
            return ;
        }
        
	    try {
	        if (!Files.exists(projectDir)) {
	            Files.createDirectories(projectDir);
	        }
	        if (!Files.exists(personalDataPath)) {
	            Files.createDirectories(personalDataPath);
	        }
	        if (!Files.exists(analyseDataPath)) {
	            Files.createDirectories(analyseDataPath);
	        }
	    } catch (IOException e) {
	        System.err.println("Error creating directories: " + e.getMessage());
	        return ;
	    }
	    
        createWordFile(personalDataPath);
	    createJsonFile(analyseDataPath);
	    
    }
	

	//Data Area 
	private StoreUserDataLocal() 
	{
		// TODO Auto-generated constructor stub
		storeCVUserLocal();			
	}
		
	// 1- create copy CV File and personal_data folder . 
	// 2 - add CV File to personal_data folder. 
	public static void storeCVUserLocal()
	{
		// create word file and folder and put the word file inside the folder . 
		initializeStorage();
		// createFolder();
		// This function show the user option to upload file and stored this file inside local machine . 
		storeWordFileData(personalDataPath);
	}	
	private static void createFolder()
	{	    
	    
        // Define the folder path and Word file name
        String folderPath = "personal_user_data";
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
        // Check if the word file is exist 
        File file = new File(filePath);
    	if (file.exists() && file.isFile())
    	{
    		 return ;
    	}
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
	private static void storeWordFileData(Path desPath) 
	{
		// Create a JFrame for the file dialog
		JFrame frame = new JFrame();
		// Use the FileDialog class to prompt the user to select a file
		FileDialog fileDialog = new FileDialog(frame, "Select a file", FileDialog.LOAD);
		fileDialog.setVisible(true);
		// Get the selected file path
		String filePath = fileDialog.getDirectory() + fileDialog.getFile();
        Path pathSource = Path.of(filePath);
 
		// Here we save the word file that the user insert inside the personal_data/cv_user.docs
		copyFile(pathSource,desPath);
	}
    
    
	// Here we save the data in encrypted way 
	 public static void storeEncrptyData(String email,String password) 
	 {
		
	     // Encrypt the data using base64
	     String encryptedEmail = Base64.getEncoder().encodeToString(email.getBytes());
	     String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
	
	     // Store the encrypted data ץ
	     String filePath = "PrivacyData/encrypted_personal_data.txt";
	     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	         writer.write(encryptedEmail);
	         writer.newLine();
	         writer.write(encryptedPassword);
	         writer.newLine();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
			 
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


	 private static void copyFile(Path sourcePath, Path destinationPath) {
	        File sourceFile = sourcePath.toFile();
	        File destinationFile = destinationPath.toFile();

	        try (InputStream in = new FileInputStream(sourceFile);
	             OutputStream out = new FileOutputStream(destinationFile)) {

	            byte[] buffer = new byte[1024];
	            int length;

	            while ((length = in.read(buffer)) > 0) {
	                out.write(buffer, 0, length);
	            }

	            System.out.println("File copied from " + sourceFile + " to " + destinationFile);

	        } catch (IOException e) {
	            System.out.println("Error copying file: " + e.getMessage());
	        }
	    }

	
	// Building file analyse user data . 
		private static int createAnalyseUserFolder()
		{
			
			// Inside the folder : 1- excel file -list places  2- excel file -list positions.
			String folderName = "analyse_user_data"; 
	        File folder = new File(folderName);
	        // Check if the folder exists, if not, create it
	        if (!folder.exists()) 
	        {
	            if (folder.mkdir()) {
	            } else {
	                System.out.println("Failed to create the folder: " + folderName);
	                return 1;
	            }
	        }
			  
			return 0; 	
		}
		
		// Building file analyse user data . 
		public static int createSensitiveUserFiles()
		{
			
	        String folderName = "PrivacyData"; // Folder inside the program folder
	        String fileName = "encrypted_personal_data.txt"; // File to check

	        // Create a File object for the folder
	        File folder = new File(folderName);

	        // Check if the folder exists, if not, create it
	        if (!folder.exists()) 
	        {
	            if (folder.mkdir()) {
	            	// Do nothing 
	            } else {
	                System.out.println("Failed to create the folder: " + folderName);
	                return -1;
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
			
			
			return 0;
		}
	
		
	  private static void createJsonFile(Path directoryPath) 
	  {
	        try {
	            // Ensure the directory exists
	            if (!Files.exists(directoryPath)) {
	                Files.createDirectories(directoryPath); // Create the directory if it doesn't exist
	            }

	            // Define the JSON file name and path
	            Path jsonFilePath = directoryPath.resolve("data.json");

	            // Check if the file already exists
	            if (Files.exists(jsonFilePath)) {
	                System.out.println("The file 'data.json' already exists at: " + jsonFilePath);
	                return;
	            }

	            // Create the JSON content
	            String jsonContent = "{\n    \"key\": \"value\",\n    \"example\": 123\n}";

	            // Write the content to the file
	            try (FileWriter fileWriter = new FileWriter(jsonFilePath.toFile())) {
	                fileWriter.write(jsonContent);
	                System.out.println("JSON file created at: " + jsonFilePath);
	            }

	        } catch (IOException e) {
	            System.err.println("Error creating JSON file: " + e.getMessage());
	        }
	    }
	
	   private static void createWordFile(Path directoryPath) {
	        try {
	            // Ensure the directory exists
	            if (!Files.exists(directoryPath)) {
	                Files.createDirectories(directoryPath); // Create the directory if it doesn't exist
	            }

	            // Define the Word file name and path
	            Path wordFilePath = directoryPath.resolve("document.docx");

	            // Check if the file already exists
	            if (Files.exists(wordFilePath)) {
	                System.out.println("The file 'document.docx' already exists at: " + wordFilePath);
	                return;
	            }

	            // Create a new Word document
	            try (XWPFDocument document = new XWPFDocument()) {
	                // Add a paragraph
	                XWPFParagraph paragraph = document.createParagraph();
	                XWPFRun run = paragraph.createRun();
	                run.setText("This is a sample Word document created using Apache POI.");
	                run.setFontSize(12);

	                // Write the document to the file
	                try (FileOutputStream out = new FileOutputStream(wordFilePath.toFile())) {
	                    document.write(out);
	                    System.out.println("Word file created at: " + wordFilePath);
	                }
	            }

	        } catch (IOException e) {
	            System.err.println("Error creating Word file: " + e.getMessage());
	        }
	    }

	  			


}
