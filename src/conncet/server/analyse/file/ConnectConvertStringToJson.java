package conncet.server.analyse.file;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


// TODO : the name of the class should be change to name that the class is serving 
public class ConnectConvertStringToJson 
{
	private static String fileTotext = "";
	private static String fileLocation = "personal_data/user_cv.docx";
	
	public static void main(String[] args) throws IOException 
	{
		
	    String promotToAI = "give me list of positions the user can work (write excatly the list without any answer ):";
	    convetFileToText(fileLocation); 	    
	    promotToAI += fileTotext;	
	    String positionsList = sanitizeString(promotToAI);
	    StringBuilder sb = positionsListForUser(positionsList);

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
	    

	}
		

    public static StringBuilder  positionsListForUser(String inputString) 
    {
        String serverUrl = "http://localhost:4000/process";
        StringBuilder response = new StringBuilder();

        try {
	            // Step 1: Create a URL object and open a connection
	            URL url = new URL(serverUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	
	            // Step 2: Configure the connection for POST
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	
	            // Step 3: Create the JSON request body
	            String jsonInputString = "{ \"inputString\": \"" + inputString + "\" }";
	
	            // Step 4: Send the JSON data
	            try (OutputStream os = connection.getOutputStream()) 
	            {
	                byte[] input = jsonInputString.getBytes("utf-8");
	                os.write(input, 0, input.length);
	                System.out.println("success");
	            }
	
	            // Step 5: Read the JSON response
	            int status = connection.getResponseCode();
	            if (status == HttpURLConnection.HTTP_OK) 
	            {
	               
	            	try (BufferedReader br = new BufferedReader(
	                        new InputStreamReader(connection.getInputStream(), "utf-8"))) 
	            	{
	                    String responseLine;
	                    while ((responseLine = br.readLine()) != null) 
	                    {
	                        response.append(responseLine.trim());
	                    }
	
	                }	
	                       
	            } 
	            else 
            	{
            		System.out.println("Server responded with status code: " + status);
            	}

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return response; 
    }

    // Method to save JSON string to a local file
    @SuppressWarnings("unused")
	private static void saveJsonToFile(String jsonString, String fileName) throws IOException 
    {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonString);
        }
        
    }

	public static String convetFileToText(String fileLocationLocal) throws IOException 
	{
		if(fileLocationLocal == null)
		{
			System.err.println("your path of file is null ");
			return null;
		}
		// Create a File object for the input file . 
		try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileLocationLocal)))) 
		 {
		
		    XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
		    String docText = xwpfWordExtractor.getText();
		    xwpfWordExtractor.close();
		    fileTotext += docText;
		    return docText;	            
		    
		 }
	
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
	   
	    public static List<String> convertToList(StringBuilder sb, String delimiter) {
	        if (sb == null || sb.length() == 0) {
	            return new ArrayList<>(); // Return an empty list if StringBuilder is null or empty
	        }
	        // Convert StringBuilder to String and split by the specified delimiter
	        String content = sb.toString();
	        String[] parts = content.split(delimiter);
	        
	        // Convert the array to a List
	        return Arrays.asList(parts);
	    }

	    

}

