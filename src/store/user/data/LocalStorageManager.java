package store.user.data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalStorageManager {

    // Base directory name for the storage within the project directory
    private static final String BASE_STORAGE_DIR = "AppStorage";

    /**
     * Initializes the base storage directory in the project directory.
     */
    public static void initializeStorage() {
        // Get the project's base directory
        Path projectDir = Paths.get(System.getProperty("user.dir"), BASE_STORAGE_DIR);

        try {
            // Create the storage directory if it doesn't exist
            if (!Files.exists(projectDir)) {
                Files.createDirectories(projectDir);
                System.out.println("Storage directory created at: " + projectDir.toAbsolutePath());
            } else {
                System.out.println("Storage directory already exists at: " + projectDir.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Error creating storage directory: " + e.getMessage());
        }
    }

    /**
     * Gets or creates the storage folder for a specific user.
     *
     * @param userId The unique identifier for the user.
     * @return The path to the user's storage folder.
     */
    public static Path getUserStorage(String userId) {
        // Resolve the user's specific folder path within the storage directory
        Path userPath = Paths.get(System.getProperty("user.dir"), BASE_STORAGE_DIR, userId);

        try {
            // Create the user's storage directory if it doesn't exist
            if (!Files.exists(userPath)) {
                Files.createDirectories(userPath);
                System.out.println("User storage directory created at: " + userPath.toAbsolutePath());
            } else {
                System.out.println("User storage directory already exists at: " + userPath.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Error creating user storage: " + e.getMessage());
        }

        return userPath;
    }

    /**
     * Main method for testing the storage manager.
     */
    public static void main(String[] args) {
        // Step 1: Initialize the base storage directory
        initializeStorage();

        // Step 2: Create or access a storage directory for a specific user
        String userId = "user123";
        Path userStorage = getUserStorage(userId);

        // Print the user storage path
        if (userStorage != null) {
            System.out.println("User storage path: " + userStorage.toAbsolutePath());
        } else {
            System.err.println("Failed to get user storage path.");
        }
    }
}
