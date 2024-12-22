package conncet.server.analyse.file;
import java.io.*;
import java.net.Socket;

public class TestSocetConnection {
    public static void main(String[] args) 
    {
        String serverAddress = "127.0.0.1"; // Server IP address
        int port = 4000;                   // Server port

        try (Socket socket = new Socket(serverAddress, port)) 
        {
            // Prepare the request
            String request = "GET /text_analyse HTTP/1.1\r\n" +
                             "Host: " + serverAddress + "\r\n" +
                             "\r\n";

            // Send the request to the server
            OutputStream output = socket.getOutputStream();
            output.write(request.getBytes());
            output.flush();

            // Read the response from the server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Print the response line by line
            String responseLine;
            System.out.println("Response from server:");
            while ((responseLine = reader.readLine()) != null) 
            {
                System.out.println(responseLine);
            }
            
        } catch (IOException e) 
        {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

