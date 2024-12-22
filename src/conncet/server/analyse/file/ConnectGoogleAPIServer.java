package conncet.server.analyse.file;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	// we send to http server localhost:5000/positions_list 
	
	public static List<String> positionsAnalyseUserCVData() throws IOException 
	{
		
	   //FileLocation = StoreUserDataLocal.getFileCVPathLoaction();
		List<String> resultOFAnlyseFile ;
	    String promotToAI = "give list of positions the user can work :";
		convetFileToText(fileLocation); 	    
		promotToAI += fileTotext;
		resultOFAnlyseFile = positionsList(promotToAI);
		return resultOFAnlyseFile;
	}



	private static List<String> positionsList(String promotToAI) 
	{
		// TODO Auto-generated method stub
	       String serverAddress = "127.0.0.1";  
	        int port = 4000;  // Server port
	        List<String> response = null;
	        

	    
	        
	        
	        
    
	        return response;
			
	}
	
	// function that return from the google API server list of places that the user can work 
	// we send to http server localhost:5000/places_list 

	
	public static List<String> placesAnalyseUserCVData() throws IOException 
	{
		
	   //FileLocation = StoreUserDataLocal.getFileCVPathLoaction();
		List<String> resultOFAnlyseFile ;
	    String promotToAI = "according to the user data give me list of positions the user can work ";
		convetFileToText(fileLocation); 	    
		promotToAI += fileTotext;
		resultOFAnlyseFile = positionsList(promotToAI);
		return resultOFAnlyseFile;
	}



	private static List<String> placesList(String promotToAI) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	
	
    
      
    
}
