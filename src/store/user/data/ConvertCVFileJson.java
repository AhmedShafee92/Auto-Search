package store.user.data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertCVFileJson {
    public static void main(String[] args) {
        String serverUrl = "http://localhost:5000/upload";
        String userHome = System.getProperty("user.home");

        String filePath = "personal_data\\user_cv.docx";

        // Create HTTP client
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create POST request
            HttpPost uploadFile = new HttpPost(serverUrl);

            // Attach file to the request
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", new File(filePath));
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            // Execute request and get response
            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                // Parse response to string
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("Server Response: " + responseString);

                // Parse JSON
                JSONObject jsonResponse = new JSONObject(responseString);

                // Save JSON to a local file
                try (FileWriter fileWriter = new FileWriter("personal_data/user_analyse_data.json")) {
                    fileWriter.write(jsonResponse.toString(4)); // Pretty-print JSON
                    System.out.println("Response JSON saved to: response.json");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
