package service.side.connect;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import read.secure.data.Read_PDF_File;


public class SendFile {
		
   public static boolean sendFile(){
  
    try {
      // Set up the HTTP POST request
      @SuppressWarnings("deprecation")
	URL url = new URL("localhost:8080");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/octet-stream");
      conn.setDoOutput(true);

      // Open the file and read its contents
      
      
      File file = Read_PDF_File.getPDFile();
      FileInputStream fis = new FileInputStream(file);
      byte[] data = new byte[(int) file.length()];
      fis.read(data);
      fis.close();

      // Send the POST request
      DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
      dos.write(data);
      dos.flush();
      dos.close();

      // Check the response status code
      int responseCode = conn.getResponseCode();
      if (responseCode == 200) {
        System.out.println("File successfully uploaded");
      } else {
        System.out.println("Error uploading file");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
 }


	

}
