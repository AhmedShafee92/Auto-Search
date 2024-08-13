package communicate.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SharedMemory 
{
    public static void main(String[] args) 
    {
        try
        {
            // Command to run the Python script
            String command = "python3 C:/Users/shafe/PycharmProjects/pythonProject1/your_script.py";

            
            // Start the process
            @SuppressWarnings("deprecation")
			Process process = Runtime.getRuntime().exec(command);

            // Send input to the Python script
            String input = "Hello from Java";
            OutputStream stdin = process.getOutputStream();
            stdin.write(input.getBytes(StandardCharsets.UTF_8));
            stdin.flush();
            stdin.close();

            // Read output from the Python script
            BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = stdout.readLine()) != null) 
            {
                System.out.println("Output from Python: " + line);
            }
            stdout.close();

            // Wait for the process to finish
            process.waitFor();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
