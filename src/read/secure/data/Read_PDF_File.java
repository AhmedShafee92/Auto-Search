package read.secure.data;

import java.io.File;

import store.user.data.StoreUserDataLocal;

public class Read_PDF_File {
	
	private static  String locationFile;
	
	
	public static File getPDFile()
	{
			
		return readFile();
	}


	public static String getLocationFile() {
		return locationFile;
	}


	public static void setLocationFile(String locationFile) {

	}
	
	
	
	  private static File readFile() {
	  
	  File file = new File(locationFile); 
	  if (file.exists()) 
	  { return file; } 
	  else
	  { throw new RuntimeException("File not found: " + locationFile); }
	  
	  }
	  
		

}
