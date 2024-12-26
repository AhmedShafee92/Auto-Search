package conncet.server.analyse.file;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ConnectGoogleAPIServer
{
	
	//Data Area 
	private static String fileTotext = "";
	private static String fileLocation = "personal_data/user_cv.docx";
		
	//Implementation Method 
	public static String analyseUserCVData() throws IOException 
	{
		
	   //FileLocation = StoreUserDataLocal.getFileCVPathLoaction();
		String resultOFAnlyseFile = "";
	    String promotToAI = "What this person can work and where, depending of the location of the user :";
		convetFileToText(fileLocation); 	    
		promotToAI += fileTotext;
		resultOFAnlyseFile = AnalyseText(promotToAI);
	    return resultOFAnlyseFile;
	
	}
	
	
    private static String AnalyseText(String message) 
    {
    	 
        String serverAddress = "127.0.0.1";  
        int port = 4000;  // Server port
        String response = "";
        try (Socket socket = new Socket(serverAddress, port)) 
        {
            // Create input and output streams
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            output.println(message);
            // Receive the server's response
            response = serverInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return " ";
        }
        
        return response;
    }
    
    
	public static String convetFileToText(String fileLocationLocal) throws IOException 
	{
		if(fileLocationLocal == null)
		{
			System.err.println("your path of file is null ");
			return null;
		}
		// Create a File object for the input file
		
		try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileLocationLocal)))) 
		 {
		
		    XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
		    String docText = xwpfWordExtractor.getText();
		    xwpfWordExtractor.close();
		    fileTotext += docText;
		    return docText;	            
		    
		 }
	
	 }
    
	
	// function that return from the google API server list of positions that the user can work 
	// we send to HTTP server localhost:5000/positions_list 
	
	public static List<String> positionsAnalyseUserCVData() throws IOException 
	{
		
	    String promotToAI = "give me list of positions the user can work (write excatly the list without any answer ):";
	    convetFileToText(fileLocation); 	    
	    promotToAI += fileTotext;	
	    String positionsList = sanitizeString(promotToAI);
	    StringBuilder sb = new StringBuilder(ConnectConvertStringToJson.positionsListForUser(positionsList));

	    System.out.println("before the spilte of the stringBuilder :");
	    System.out.println(sb);
	    
	    
	    // Replace the literal "\n" with actual newlines
        String content = sb.toString().replace("\\n", "\n");
	
        
        // TODO: should to find why the " and - dose't erase from the first and the last string in the list 
        /*
        content = content.replace("“", "\"").replace("”", "\"")
                .replace("‘", "'").replace("’", "'");
	    */
   
       // Split by newline (\n) to get a list of strings
        List<String> list = new ArrayList<>(Arrays.asList(content.toString().split("\n")));

        // Clean up the list by removing the leading "- " and trimming
        List<String> cleanedList = new ArrayList<>();
        for (String line : list) {
            cleanedList.add(line.replaceFirst("^-\\s*", "").trim());
        }

        // Clean the first string
        if (!cleanedList.isEmpty()) {
            String first = cleanedList.get(0);
            first = first.replaceFirst("^-\\s*", "").trim(); // Remove "- " prefix
            cleanedList.set(0, first); // Update the list with the cleaned first string
        }

        // Clean the last string
        if (cleanedList.size() > 1) {
            String last = cleanedList.get(cleanedList.size() - 1);
            last = last.replaceFirst("^-\\s*", "").trim(); // Remove "- " prefix
            cleanedList.set(cleanedList.size() - 1, last); // Update the list with the cleaned last string
        }
        
        
        // Print each item in the cleaned list
        System.out.println("After cleaning the list of strings:");
        for (String item : cleanedList) {
            System.out.println(item);
        }
	    
        return cleanedList;

	}
		

	// function that return from the google API server list of places that the user can work 
	// we send to http server localhost:5000/places_list 

	public static List<String> placesAnalyseUserCVData() throws IOException 
	{
		
	    String promotToAI = "give me list of places the user can work (write excatly the list without any answer ):";
	    convetFileToText(fileLocation); 	    
	    promotToAI += fileTotext;	
	    String positionsList = sanitizeString(promotToAI);
	    StringBuilder sb = new StringBuilder(ConnectConvertStringToJson.positionsListForUser(positionsList));

	    System.out.println("before the spilte of the stringBuilder :");
	    System.out.println(sb);
	    
	    
	    // Replace the literal "\n" with actual newlines
        String content = sb.toString().replace("\\n", "\n");
	
        
        // TODO: should to find why the " and - dose't erase from the first and the last string in the list 
        /*
        content = content.replace("“", "\"").replace("”", "\"")
                .replace("‘", "'").replace("’", "'");
	    */
   
       // Split by newline (\n) to get a list of strings
        List<String> list = new ArrayList<>(Arrays.asList(content.toString().split("\n")));

        // Clean up the list by removing the leading "- " and trimming
        List<String> cleanedList = new ArrayList<>();
        for (String line : list) {
            cleanedList.add(line.replaceFirst("^-\\s*", "").trim());
        }

        // Clean the first string
        if (!cleanedList.isEmpty()) {
            String first = cleanedList.get(0);
            first = first.replaceFirst("^-\\s*", "").trim(); // Remove "- " prefix
            cleanedList.set(0, first); // Update the list with the cleaned first string
        }

        // Clean the last string
        if (cleanedList.size() > 1) {
            String last = cleanedList.get(cleanedList.size() - 1);
            last = last.replaceFirst("^-\\s*", "").trim(); // Remove "- " prefix
            cleanedList.set(cleanedList.size() - 1, last); // Update the list with the cleaned last string
        }
        
        
        // Print each item in the cleaned list
        System.out.println("After cleaning the list of strings:");
        for (String item : cleanedList) {
            System.out.println(item);
        }
	    
        return cleanedList;	
	}



	
	  public static String sanitizeString(String inputString) {
	        if (inputString == null) {
	            return null;
	        }
	        // Remove invalid control characters
	        return inputString.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F]", "");
	    }
	
		
	  
	    public static List<String> convertToList(StringBuilder sb) 
	    {
	        if (sb == null || sb.length() == 0) {
	            return new ArrayList<>(); // Return an empty list if the StringBuilder is null or empty
	        }

	        // Split the StringBuilder content into an array of strings based on delimiters
	        String[] parts = sb.toString().split("[,\n\"]-");

	        // Create a list and add the non-empty trimmed elements
	        List<String> result = new ArrayList<>();
	        for (String part : parts) {
	            if (!part.trim().isEmpty()) { // Skip empty strings
	                result.add(part.trim());
	            }
	        }

	        return result;
	    }
	
      
    
}
