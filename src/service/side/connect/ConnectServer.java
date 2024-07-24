package service.side.connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectServer {
	
public static boolean connection()
{
	
	// this request to the server that should  hello world 
	
	try {
	      URL url = new URL("http://localhost:8080/");
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");

	      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	      }
	      reader.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return false;
	    }
 
	return true;
 }
	
	

}
