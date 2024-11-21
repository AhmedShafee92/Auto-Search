package conncet.server.analyse.file;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

public class ConnectAnalyseImage {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server address
        int port = 3000;                   // Server port

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the image file:");
        String filePath = scanner.nextLine();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Invalid file path. Please provide a valid image file.");
            return;
        }

        try (Socket socket = new Socket(serverAddress, port);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            System.out.println("Connected to the server!");

            // Send the file name
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(file.getName());
            writer.newLine();
            writer.flush();

            // Send the file data
            Files.copy(file.toPath(), out);
            out.flush();
            System.out.println("Image sent to the server.");

            // Receive the response from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String response = reader.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
