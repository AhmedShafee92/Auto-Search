package store.user.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalStorageManager 
{

    private static final String BASE_STORAGE_DIR = "AppStorage";

    public static Path getUserStorage(String userId) 
    {
        Path userPath = Paths.get(System.getProperty("user.dir"), BASE_STORAGE_DIR, userId);
        Path personalDataPath = userPath.resolve("personal_data");
        Path analyseDataPath = userPath.resolve("analyse_data");

        try {
            if (!Files.exists(userPath)) {
                Files.createDirectories(userPath);
            }
            if (!Files.exists(personalDataPath)) {
                Files.createDirectories(personalDataPath);
            }
            if (!Files.exists(analyseDataPath)) {
                Files.createDirectories(analyseDataPath);
            }
        } catch (IOException e) {
            System.err.println("Error creating directories: " + e.getMessage());
        }

        return userPath;
    }

    /**
     * Adds a file with specified content to a subdirectory.
     *
     * @param userId       The user ID.
     * @param subFolder    The subfolder name (e.g., "personal_data" or "analyse_data").
     * @param fileName     The name of the file to be created.
     * @param fileContent  The content to write to the file.
     */
    public static void addFile(String userId, String subFolder, String fileName, String fileContent) {
        Path userPath = getUserStorage(userId);
        Path subFolderPath = userPath.resolve(subFolder);

        // Ensure the subfolder exists
        if (!Files.exists(subFolderPath)) {
            System.err.println("Subfolder does not exist: " + subFolderPath.toAbsolutePath());
            return;
        }

        // File path
        Path filePath = subFolderPath.resolve(fileName);

        // Write content to the file
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write(fileContent);
            System.out.println("File created at: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    public static void main(String[] args) 
    {
        // Example usage
        String userId = "user123";

        // Ensure user storage and subfolders exist
        getUserStorage(userId);

        // Add a file to the "personal_data" folder
        addFile(userId, "personal_data", "resume.txt", "This is the user's resume.");

        // Add a file to the "analyse_data" folder
        addFile(userId, "analyse_data", "report.txt", "This is the analysis report.");
    }
}
