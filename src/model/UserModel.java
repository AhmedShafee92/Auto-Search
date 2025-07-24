package model;

import util.HttpClientHelper; // Adjust import based on your project
import org.json.JSONObject;   // You need org.json library for this

public class UserModel {

    public static boolean signup(String email, String password) 
    {
        String url = "http://localhost:3000/api/signup"; // Adjust as needed

		/*
		 * JSONObject json = new JSONObject(); json.put("email", email);
		 * json.put("password", password);
		 */
        
        String json = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", email, password);
        try {
            String response = HttpClientHelper.sendPost(url, json);
            System.out.println("Signup response: " + response);
            return response.toLowerCase().contains("success"); // adjust depending on actual server response
        } catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String email, String password) 
    {
        String url = "http://localhost:3000/api/login";

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);

        try {
            String response = HttpClientHelper.sendPost(url, json.toString());
            System.out.println("Login response: " + response);

            return response.toLowerCase().contains("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
