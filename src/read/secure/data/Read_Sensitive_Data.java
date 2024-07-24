package read.secure.data;

import java.io.File;

import store.user.data.StoreUserDataLocal;

public class Read_Sensitive_Data {
	
private static String locationFile;

public static String getLocationFile() {
	return locationFile;
}

public static void setLocationFile(String locationFile) {
	Read_Sensitive_Data.locationFile = StoreUserDataLocal.getFileUserDataLocation();
}


 public static File readFileUserData()
 {
	 return readFile();
 }



  private static File readFile() {
  
  File file = new File(locationFile); if (file.exists()) { return file; } else
  { throw new RuntimeException("File not found: " + locationFile); }
  
  }
 
	
	
	
}
