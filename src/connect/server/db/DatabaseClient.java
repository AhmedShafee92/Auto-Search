package connect.server.db;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class DatabaseClient {

    private static final String SERVER_URL = "http://localhost:3000";

    // Function to send HTTP POST requests
    private static String sendPostRequest(String endpoint, JSONObject data) throws IOException {
        URL url = new URL(SERVER_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Write JSON data
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = data.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response
        int responseCode = conn.getResponseCode();
        InputStream is = (responseCode >= 200 && responseCode < 300) ? conn.getInputStream() : conn.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    // Register a new user
    public static void registerUser(String email, String password, String phone, String fullName) throws IOException {
        JSONObject requestData = new JSONObject();
        requestData.put("email", email);
        requestData.put("password", password);
        requestData.put("phone", phone);
        requestData.put("fullName", fullName);

        String response = sendPostRequest("/register", requestData);
        System.out.println("Register Response: " + response);
    }

    // Login a user
    public static void loginUser(String email, String password) throws IOException {
        JSONObject requestData = new JSONObject();
        requestData.put("email", email);
        requestData.put("password", password);

        String response = sendPostRequest("/login", requestData);
        System.out.println("Login Response: " + response);
    }

    public static void main(String[] args) {
        try {
            // Test Registration
            registerUser("test1@example.com", "securepassword1", "1234567800", "Doe Deeb");

            // Test Login
            loginUser("test1@example.com", "securepassword1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
