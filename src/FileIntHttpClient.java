import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;


import store.user.data.StoreUserDataLocal;

public class FileIntHttpClient 
{
	
	public static void main(String[] args) {
		
		
    	String PathFile =  StoreUserDataLocal.getPersonaldatapahtcv();
		int userID = 1; 
		sendAnalyseRequest(PathFile,userID);
	}
	
	
		private static final String LINE_FEED = "\r\n";
	    private static final String boundary = "----" + System.currentTimeMillis();



    public static void sendAnalyseRequest(String PathFile, int userID) {
        try {
        	       	
            URL url = new URL("http://localhost:3000/api/analyse");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (OutputStream outputStream = conn.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true)) 
            {
                // 1. Send the `userID` field with a `name` of "number"
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"number\"").append(LINE_FEED);
                writer.append(LINE_FEED);
                writer.append(String.valueOf(userID)).append(LINE_FEED);
                writer.flush();

                // 2. Send the file field with a `name` of "file"
                File file = new File(PathFile);
                writer.append("--" + boundary).append(LINE_FEED);
                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(LINE_FEED);
                writer.append("Content-Type: " + Files.probeContentType(file.toPath())).append(LINE_FEED);
                writer.append(LINE_FEED);
                writer.flush();

                // 3. Write the file's binary content
                Files.copy(file.toPath(), outputStream);
                outputStream.flush();
                writer.append(LINE_FEED);
                writer.flush();

                // 4. End the multipart/form-data request
                writer.append("--" + boundary + "--").append(LINE_FEED);
                writer.flush();
            }

            // Read server response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Server says: " + line);
                }
            } catch (IOException e) {
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        System.err.println("Server error: " + line);
                    }
                }
            } finally {
                conn.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
}
