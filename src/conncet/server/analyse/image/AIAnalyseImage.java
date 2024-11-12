package conncet.server.analyse.image;
/*

import com.google.cloud.vision.v1.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class AIAnalyseImage 
{

    @PostMapping("/analyze")
    public String analyzeImage(@RequestParam("image") MultipartFile file) throws IOException 
    {
        try (ImageAnnotatorClient visionClient = ImageAnnotatorClient.newBuilder().build()) {
            byte[] imageBytes = file.getBytes();
            Image image = Image.newBuilder().setContent(ByteString.copyFrom(imageBytes)).build();

            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .setImage(image)
                    .addFeatures(Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build())
                    .build();

            BatchAnnotateImagesResponse response = visionClient.batchAnnotateImages(request);
            List<EntityAnnotation> labels = response.getResponses(0).getLabelAnnotationsList();

            StringBuilder result = new StringBuilder("Detected labels: ");
            for (EntityAnnotation label : labels) {
                result.append(label.getDescription()).append(", ");
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error analyzing image: " + e.getMessage();
        }
    }
}
*/