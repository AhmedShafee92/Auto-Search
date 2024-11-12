package conncet.server.analyse.file;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import java.io.IOException;

public class ConnectVertexAI 
{
	
  public static String SendVertexAI(String promot) throws IOException 
  {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "testvertexai-429708";
    String location = "us-central1";
    String modelName = "gemini-1.5-flash-001";
    String textPrompt = "please analyse which job this person can work  :\n";
    textPrompt +=promot;
    String output = textInput(projectId, location, modelName, textPrompt);
    
    return output;
  }

  // Passes the provided text input to the Gemini model and returns the text-only response.
  // For the specified textPrompt, the model returns a list of possible store names.
  public static String textInput(String projectId, String location, String modelName, String textPrompt) throws IOException 
  {
    // Initialize client that will be used to send requests. This client only needs
    // to be created once, and can be reused for multiple requests.
    try (VertexAI vertexAI = new VertexAI(projectId, location)) 
    {
      GenerativeModel model = new GenerativeModel(modelName, vertexAI);
      GenerateContentResponse response = model.generateContent(textPrompt);
      String output = ResponseHandler.getText(response);
      return output;
    }
    
  }
  
  
}