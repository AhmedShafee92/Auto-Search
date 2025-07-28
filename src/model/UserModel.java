package model;

import util.HttpClientHelper; // Adjust import based on your project

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject; // You need org.json library for this

public class UserModel {

	
	public static String signup(String email, String password) {
		try {
			URL url = new URL("http://localhost:3000/api/signup");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);

			String jsonInput = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", email, password);

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInput.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int responseCode = conn.getResponseCode();

			BufferedReader reader;
			if (responseCode >= 200 && responseCode < 300) {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
			}

			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line.trim());
			}
			reader.close();

			if (responseCode == 201) {
				return "success";
			} else if (responseCode == 409 && response.toString().contains("User already exists")) {
				return "exists";
			} else {
				return response.toString(); // Return actual error from server
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Connection error: " + e.getMessage();
		}
	}

	public static boolean login(String username, String password) {
		String url = "http://localhost:3000/api/login";

		JSONObject json = new JSONObject();
		json.put("username", username);
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
