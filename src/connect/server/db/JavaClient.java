package connect.server.db;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JavaClient {
    private static final String SERVER_URL = "http://localhost:3000";

    public static void main(String[] args) {
        try {
            // Test registration
            String registrationData = "{\"email\":\"test@example.com\",\"password\":\"password123\",\"phone\":\"1234567890\",\"fullName\":\"Test User\"}";
            sendPostRequest("/register", registrationData);

            // Test login
            String loginData = "{\"email\":\"test@example.com\",\"password\":\"password123\"}";
            sendPostRequest("/login", loginData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendPostRequest(String endpoint, String jsonData) throws Exception {
        URL url = new URL(SERVER_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            System.out.println("POST request worked");
        } else {
            System.out.println("POST request did not work");
        }
    }
}