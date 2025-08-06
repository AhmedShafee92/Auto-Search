package model;

public class Session {
    private static int userId = -1;
    private static String username = null;
    private static String token = null; // Optional, for JWT use later

    public static void setUser(int id, String name) {
        userId = id;
        username = name;
    }

    public static void setToken(String jwtToken) {
        token = jwtToken;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getToken() {
        return token;
    }

    public static boolean isLoggedIn() {
        return userId != -1;
    }

    public static void clear() {
        userId = -1;
        username = null;
        token = null;
    }
}
