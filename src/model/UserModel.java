package model;

import java.util.HashMap;

public class UserModel {
    // Simulate a database with a HashMap
    private static HashMap<String, String> userDB = new HashMap<>();

    // Sign up
    public static boolean register(String email, String password) {
        if (userDB.containsKey(email)) return false; // already registered
        userDB.put(email, password);
        return true;
    }

    // Sign in
    public static boolean login(String email, String password) {
        return userDB.containsKey(email) && userDB.get(email).equals(password);
    }
}
