package personal.set.data;


	
	// gets the file of the user (pdf or word )
	
	// convert the user data to the string 
	// analyse the text of the user 
	
	// create a Json Format  which will content the data 
	// sort the personal user data according to the fields in the Json format file to fill as possible as we can 
	// save the Json in the local machine of the user in the directory of personal data of the user 
		
	/*

	// create a json file that have format similar to the file down  : 
	{
  "personal_info": {
    "first_name": "John",
    "last_name": "Doe",
    "middle_name": "A.",
    "date_of_birth": "1990-01-01",
    "gender": "Male",
    "nationality": "American",
    "marital_status": "Single",
    "photo": "https://example.com/photo.jpg"
    address  : countery, city , street (if possible )
  },
  "contact_info": {
    "email": "john.doe@example.com",
    "phone": {
      "mobile": "+1234567890",
      "home": "+0987654321"
    },
    "address": {
      "street": "123 Main St",
      "city": "Springfield",
      "state": "Illinois",
      "postal_code": "62701",
      "country": "USA"
    },
    "social_media": {
      "linkedin": "https://linkedin.com/in/johndoe",
      "twitter": "https://twitter.com/johndoe",
      "facebook": "https://facebook.com/johndoe"
    }
  },
  "employment_info": {
    "current_position": "Software Engineer",
    "company": "TechCorp Inc.",
    "start_date": "2018-06-15",
    "previous_jobs": [
      {
        "position": "Junior Developer",
        "company": "CodeWorks",
        "start_date": "2015-09-01",
        "end_date": "2018-05-30"
      }
    ]
  },
  "education_info": {
    "highest_degree": "Bachelor's in Computer Science",
    "university": "State University",
    "graduation_year": 2015,
    "other_degrees": [
      {
        "degree": "High School Diploma",
        "institution": "Springfield High School",
        "graduation_year": 2011
      }
    ]
  },
  "financial_info": {
    "bank_accounts": [
      {
        "bank_name": "Bank of America",
        "account_number": "123456789",
        "account_type": "Checking"
      }
    ],
    "credit_score": 720
  },
  "health_info": {
    "blood_type": "O+",
    "allergies": ["Peanuts"],
    "medications": ["Ibuprofen"],
    "insurance": {
      "provider": "HealthCare Inc.",
      "policy_number": "HC123456"

	*/
	
	// convert the file (word of pdf ) to string text 
	// create a function that gets the values for all the json file ()fields that the json file have 
	// Create a function that fill the data inside the json 
	// 
	
import org.apache.poi.xwpf.usermodel.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class CreatePersonalData 
{
    public static void main(String[] args) {
        String cvFilePath = "user_cv.docx"; // Path to the CV Word file
        String jsonFilePath = "user_cv.json"; // Path to the output JSON file

        try 
        {
            // Step 1: Extract data from the CV Word file
            Map<String, Object> extractedData = extractCVData(cvFilePath);

            // Step 2: Write extracted data to JSON
            writeDataToJson(extractedData, jsonFilePath);

            System.out.println("CV data extracted and saved to JSON successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to extract data from the CV Word file
    private static Map<String, Object> extractCVData(String filePath) throws IOException 
    {
        Map<String, Object> cvData = new HashMap<>();
        String currentSection = null;
        StringBuilder sectionContent = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText().trim();

                // Check for section headers (assume they're bold or uppercase for simplicity)
                if (isSectionHeader(text)) {
                    // Save previous section content
                    if (currentSection != null && sectionContent.length() > 0) 
                    {
                        cvData.put(currentSection, sectionContent.toString().trim());
                        sectionContent.setLength(0); // Clear the buffer
                    }
                    currentSection = text; // Set new section
                } else if (!text.isEmpty()) {
                    // Append content to the current section
                    sectionContent.append(text).append("\n");
                }
            }

            // Save the last section content
            if (currentSection != null && sectionContent.length() > 0) 
            {
                cvData.put(currentSection, sectionContent.toString().trim());
            }
        }

        return cvData;
    }

    // Function to determine if a paragraph is a section header
    private static boolean isSectionHeader(String text) {
        return text.matches("^[A-Z ]{3,}$"); // Simple check: all-uppercase text
    }

    // Function to write data to JSON file
    private static void writeDataToJson(Map<String, Object> data, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), data);
    }
}
































