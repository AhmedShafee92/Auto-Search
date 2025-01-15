package store.user.data;

// Library Area 
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;  // Use this instead of DatatypeConverter
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.security.SecureRandom;

public class Client 
{
    public static void main(String[] args) 
    {
        try {
	            // User email and password
	            String userEmail = "user@example.com";
	            String userPassword = "userPassword123";
	
	            // Generate AES key (256 bits, 32 bytes) and IV (128 bits, 16 bytes)
	            SecretKey key = KeyGenerator.getInstance("AES").generateKey();  // Generates a 256-bit key
	            byte[] iv = new byte[16];
	            new SecureRandom().nextBytes(iv);  // Generate a random 128-bit IV
	            // Encrypt data before sending
	            String encryptedEmail = encrypt(userEmail, key, iv);
	            String encryptedPassword = encrypt(userPassword, key, iv);
	            // Encode key and IV in Base64
	            String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
	            String base64Iv = Base64.getEncoder().encodeToString(iv);
	            // Send the encrypted data to the server along with the key and IV
	            sendToServer(encryptedEmail, encryptedPassword, base64Key, base64Iv);
	            
        	} catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encrypt(String data, SecretKey key, byte[] iv) throws Exception 
    {
        // Create cipher and encrypt data using AES with CBC mode and PKCS5 padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        byte[] encryptedData = cipher.doFinal(data.getBytes());

        // Convert to Base64 string to send over HTTP
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    private static void sendToServer(String encryptedEmail, String encryptedPassword, String key, String iv) throws Exception {
        // Setup HTTP request to server
        URL url = new URL("http://localhost:8000/receive-data"); // Use your actual server URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Prepare JSON payload with encrypted data, key, and iv
        String jsonPayload = String.format(
            "{\"email\":\"%s\",\"password\":\"%s\",\"key\":\"%s\",\"iv\":\"%s\"}",
            encryptedEmail, encryptedPassword, key, iv
        );

        // Send data
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Get response from server
        connection.getResponseCode();
    }
}
