package conncet.server.analyse.file;

import java.io.*;
import java.net.*;

public class ConnectAnalyseImage {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change to server IP if needed
        int port = 4000;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the server!");

            // Create a new thread for reading server responses
            Thread readerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed by server.");
                }
            });
            readerThread.start();

            // Main thread handles user input
            String message;
            while (true) {
                System.out.print("You: ");
                message = inputReader.readLine();
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }
                out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
