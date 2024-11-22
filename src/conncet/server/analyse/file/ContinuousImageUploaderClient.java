package conncet.server.analyse.file;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ContinuousImageUploaderClient {
    public static void main(String[] args) {
        String serverUrl = "http://127.0.0.1:4000/upload";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the path to the image file (or type 'exit' to quit):");
            String input = scanner.nextLine();

            // Exit condition
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            File file = new File(input);
            if (!file.exists() || !file.isFile()) {
                System.out.println("Invalid file path. Please provide a valid image file.");
                continue;
            }

            String boundary = "BoundaryString";

            try {
                // Establish HTTP connection
                URL url = new URL(serverUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                // Write the file to the server
                try (OutputStream outputStream = connection.getOutputStream();
                     FileInputStream fileInputStream = new FileInputStream(file)) {

                    // Write form data header
                    String fileHeader = "--" + boundary + "\r\n"
                            + "Content-Disposition: form-data; name=\"image\"; filename=\"" + file.getName() + "\"\r\n"
                            + "Content-Type: application/octet-stream\r\n\r\n";
                    outputStream.write(fileHeader.getBytes());

                    // Write file content
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    // Write form data footer
                    outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
                    outputStream.flush();
                }

                // Read the server's response
                int responseCode = connection.getResponseCode();
                System.out.println("Server responded with status code: " + responseCode);

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String responseLine;
                    while ((responseLine = in.readLine()) != null) {
                        System.out.println("Server response: " + responseLine);
                    }
                }

            } catch (IOException e) {
                System.err.println("Error while sending file: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
