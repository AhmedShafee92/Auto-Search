package ConncetServerAnalyseFile;

//Libraries 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class SendRequestToServer6 
{
	//Data Area 
	private static String fileTotext = "";
	private static String fileLocation = "C:/appdata/CV.docx";
	
	//Implementation Method 
	public static String analyseData() throws IOException 
	{
		
	   //FileLocation = StoreUserDataLocal.getFileCVPathLoaction();
		String resultOFAnlyseFile = "";
	    String promotToAI = "What this person can work :";
		convetFileToText(fileLocation); 	    
		promotToAI +=fileTotext;
	    resultOFAnlyseFile = analyseDataFile(promotToAI);
	    return resultOFAnlyseFile;
	    
	}
	
	//Function that get data ,return the analyse of the data 
	
	private static String analyseDataFile(String textFile) throws IOException 
	{
		assert(textFile != null);
	
		try 
		{
			String vertexResponce = "";
			vertexResponce = ConnectVertexAI.SendVertexAI(textFile);
			return vertexResponce;
		} catch (Exception e) 
		{
			// TODO: handle exception
			System.err.println(e);	
		}
        return null;
    }
	
	public static String convetFileToText(String fileLocationLocal) throws IOException 
	{
		if(fileLocationLocal == null)
		{
			System.err.println("your path of file is null ");
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
	

	// Gets and Sets Functions 
	
	public static String getFileTotext() 
	{
		return fileTotext;
	}
	public static void setFileTotext(String fileTotext) 
	{
		SendRequestToServer6.fileTotext = fileTotext;
	}
	public static String getFileLocation() 
	{
		return fileLocation;
	}
	public static void setFileLocation(String fileLocation) 
	{
		SendRequestToServer6.fileLocation = fileLocation;
	}	
	
}
