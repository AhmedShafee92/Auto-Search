package conncet.server.analyse.file;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;


public class ExcelWriter {

    public static void writeListToExcelLocal(List<String> dataList, String fileName) 
    {
        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Write each string in the list to a new row
        int rowIndex = 0;
        for (String data : dataList) {
            Row row = sheet.createRow(rowIndex++);
            Cell cell = row.createCell(0); // Write to the first column
            cell.setCellValue(data);
        }

        // Save the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static StringBuilder writeListToExcelSerevr(List<String> places_lost,List<String> positions_list) 
    {
		// TODO Auto-generated method stub
    	// connect the server side and store the data in the excel file in the server side 
    	// should send the two arrays list inside two string and put the two string with title in json 
    	// and send the JSON file to the server . 
    	
    	String str_places_list = String.join(",", places_lost);
    	String str_positions_list = String.join(",", positions_list);
      
    	String serverUrl = "http://localhost:8000/storage_second_stage_analysing";
        StringBuilder response = new StringBuilder();

    	
        try {
            
            // Step 1: Create a URL object and open a connection
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            // Step 2: Configure the connection for POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Step 3: Create the JSON request body
            String jsonInputString = "{ \"str_places_lost\": \"" + str_places_list + "\", \"str_positions_lost\": \"" + str_positions_list + "\" }";


            // Step 4: Send the JSON data
            try (OutputStream os = connection.getOutputStream()) 
            {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
                System.out.println("success");
            }

            // Step 5: Read the JSON response
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) 
            {
               
            	try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) 
            	{
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) 
                    {
                        response.append(responseLine.trim());
                    }

                }	
                       
            } 
            else 
        	{
        		System.out.println("Server responded with status code: " + status);
        		return null;
        	}

    } catch (IOException e) 
    {
        e.printStackTrace();
        return null ; 
    }
        return response; 

 
    }
    
    
    public static StringBuilder  positionsListForUser(String inputString) 
    {
        String serverUrl = "http://localhost:4000/process";
        StringBuilder response = new StringBuilder();

        try {
       
	            // Step 1: Create a URL object and open a connection
	            URL url = new URL(serverUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	
	            // Step 2: Configure the connection for POST
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");
	            connection.setDoOutput(true);
	
	            // Step 3: Create the JSON request body
	            String jsonInputString = "{ \"inputString\": \"" + inputString + "\" }";
	
	            // Step 4: Send the JSON data
	            try (OutputStream os = connection.getOutputStream()) 
	            {
	                byte[] input = jsonInputString.getBytes("utf-8");
	                os.write(input, 0, input.length);
	                System.out.println("success");
	            }
	
	            // Step 5: Read the JSON response
	            int status = connection.getResponseCode();
	            if (status == HttpURLConnection.HTTP_OK) 
	            {
	               
	            	try (BufferedReader br = new BufferedReader(
	                        new InputStreamReader(connection.getInputStream(), "utf-8"))) 
	            	{
	                    String responseLine;
	                    while ((responseLine = br.readLine()) != null) 
	                    {
	                        response.append(responseLine.trim());
	                    }
	
	                }	
	                       
	            } 
	            else 
            	{
            		System.out.println("Server responded with status code: " + status);
            		return null;
            	}

        } catch (IOException e) 
        {
            e.printStackTrace();
            return null ; 
        }
        return response; 
   
    
    }
    
    
    
}
  
    
    
    
   