package conncetServerAnalyseFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FileUploadExample 
{
    public static void main(String[] args) throws IOException 
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
        	HttpPost uploadFile = new HttpPost("http://localhost:5000/hello/");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // Read the text file from the local file system
            File textFile = new File("C:\\Users\\shafe\\OneDrive\\Desktop\\demofile.txt");  
            String text = readFileAsString(textFile);                   
            // Add the text file as a part to the HTTP request
            builder.addTextBody("file", text, ContentType.DEFAULT_BINARY);
            HttpEntity entity = builder.build();
            // Why we need to upload file here 
            uploadFile.setEntity(entity);
            System.out.println("Executing request: " + uploadFile.getRequestLine());
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() 
            {
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException
                {

                	int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = (String) httpClient.execute(uploadFile, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } 
        finally 
        {
            httpClient.close();
        }
    }
    
    private static String readFileAsString(File file) throws IOException {
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream f = new FileInputStream(file);
        f.read(buffer);
        f.close();
        return new String(buffer);
    }
}
