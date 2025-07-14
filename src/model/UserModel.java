// File: src/model/UserModel.java
package model;

import java.util.HashMap;

public class UserModel {
    private static final HashMap<String, String> users = new HashMap<>();

    public static boolean login(String email, String password) {
        return users.containsKey(email) && users.get(email).equals(password);
    }

    public static boolean register(String email, String password) {
        if (users.containsKey(email)) return false;
        users.put(email, password);
        return true;
    }
}
