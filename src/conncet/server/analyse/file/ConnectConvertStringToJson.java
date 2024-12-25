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

public class ConnectConvertStringToJson 
{
	private static String fileTotext = "";
	private static String fileLocation = "personal_data/user_cv.docx";
	
	public static void main(String[] args) throws IOException 
	{
	    String promotToAI = "give me list of positions the user can work (write excatly the list without any answer ):";
	    convetFileToText(fileLocation); 	    
	    promotToAI += fileTotext;	
	    String testresult = sanitizeString(promotToAI);
	    StringBuilder res = serverConvertStringJsonh(testresult);
	    
		List<String> positionsList = convertToList(res);
	    
	   for(String str : positionsList) 
	   {
            System.out.println(str);
       }
		
	}
		

    public static StringBuilder  serverConvertStringJsonh(String inputString) 
    {
        String serverUrl = "http://localhost:4000/process";
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
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    /* 
                    // Step 6: Save the JSON response to a local file
                    saveJsonToFile(response.toString(), "personal_data/response.json");
                    System.out.println("Response saved to response.json");
                	*/
                    return response;
                }
            } else {
                System.out.println("Server responded with status code: " + status);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
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
	        String[] parts = sb.toString().split("[,\n\"]");

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

