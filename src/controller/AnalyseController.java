package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import javax.swing.JOptionPane;

public class AnalyseController 
{
    
	 public static void sendAnalyseRequest(String PathFile,int userID) 
	 {
	        String boundary = "----Boundary" + System.currentTimeMillis();
	        String filePath = PathFile; 
	        int numberToSend = userID;

	        try {
		            URL url = new URL("http://localhost:3000/upload");
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setDoOutput(true);
		            conn.setRequestMethod("POST");
		            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

	            try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) 
	            {
	                // Send number field
	                out.writeBytes("--" + boundary + "\r\n");
	                out.writeBytes("Content-Disposition: form-data; name=\"number\"\r\n\r\n");
	                out.writeBytes(String.valueOf(numberToSend));
	                out.writeBytes("\r\n");

	                // Send file field
	                File file = new File(filePath);
	                out.writeBytes("--" + boundary + "\r\n");
	                out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
	                out.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
	                try (FileInputStream fis = new FileInputStream(file)) {
	                    byte[] buffer = new byte[4096];
	                    int bytesRead;
	                    while ((bytesRead = fis.read(buffer)) != -1) {
	                        out.write(buffer, 0, bytesRead);
	                    }
	                }
	                out.writeBytes("\r\n");

	                // End of multipart
	                out.writeBytes("--" + boundary + "--\r\n");
	            }

	            // Read server response
	            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
	                String line;
	                while ((line = in.readLine()) != null) {
	                    System.out.println("Server says: " + line);
	                }
	            }

	            conn.disconnect();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
     
 
}