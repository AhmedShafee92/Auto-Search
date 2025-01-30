package store.user.data;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserManager 
{

    // Method to insert a new key-value pair into the JSONObject without hashing the key
    public static JSONObject insertToJson(JSONObject jsonObject, String key, String value) 
    {
        // Insert the key-value pair directly into the JSON object
        jsonObject.put(key, value);
        return jsonObject;
    }

    // Method to update a value for a given key in the JSONObject
    public static JSONObject updateJson(JSONObject jsonObject, String key, String newValue) 
    {
        // Check if the key exists before updating
        if (jsonObject.has(key)) {
            jsonObject.put(key, newValue);
        } else {
            System.out.println("Key not found: " + key);
        }
        return jsonObject;
    }

    // Method to delete a key-value pair from the JSONObject
    public static JSONObject deleteFromJson(JSONObject jsonObject, String key) {
        // Check if the key exists before deleting
        if (jsonObject.has(key)) {
            jsonObject.remove(key);
        } else {
            System.out.println("Key not found: " + key);
        }
        return jsonObject;
    }

    // Method to save the JSONObject to a file
    public static void saveToJsonFile(JSONObject jsonObject, String directoryPath, String fileName) {
        // Create the directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();  // Creates the directory
        }

        // Create the file
        File file = new File(directory, fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            // Write JSON data to the file
            fileWriter.write(jsonObject.toString(4)); // Pretty-print JSON with 4 spaces indentation
            System.out.println("Data successfully saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving data to file.");
        }
    }

    // Method to load the existing JSON data from a file
    public static JSONObject loadFromJsonFile(String directoryPath, String fileName) {
        File file = new File(directoryPath, fileName);
        if (file.exists()) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
                return new JSONObject(content);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error reading data from file.");
            }
        }
        return new JSONObject(); // Return an empty JSONObject if file does not exist
    }

    public static void storeEncrptyData(String credentails_type ,String username,String password) {
        // Define the directory and file name for the JSON file
        String directoryPath = "AppStorage/privacy_data";  // Specify your desired directory
        String fileName = "credential_user.json";          // Specify your desired file name

        // Load existing data if the file exists
        JSONObject jsonObject = loadFromJsonFile(directoryPath, fileName);

        // Insert email credentials (username and password)
        JSONObject Credentials = new JSONObject();
        Credentials.put("username", username);
        Credentials.put("password", password);

        // Insert these into the main JSON object under appropriate keys
        jsonObject.put(credentails_type, Credentials);

        // Save the updated JSON object to the file
        saveToJsonFile(jsonObject, directoryPath, fileName);
    }
}
