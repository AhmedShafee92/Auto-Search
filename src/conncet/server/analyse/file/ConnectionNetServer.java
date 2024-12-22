package conncet.server.analyse.file;

import java.io.*;
import java.net.Socket;

public class ConnectionNetServer {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int port = 7000; // The server's port number
        String messageToSend = "Hello from Java client!";

        try (Socket socket = new Socket(serverAddress, port)) {
            // Output stream to send data to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Input stream to receive data from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a message to the server
            out.println(messageToSend);
            System.out.println("Sent to server: " + messageToSend);

            // Read the response from the server
            String response = in.readLine();
            System.out.println("Received from server: " + response);

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
