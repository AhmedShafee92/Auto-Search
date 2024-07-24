package first.option.forsendcv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class UpdateCompaniesStatus {
	
private static final String fileLoaction="C:\\AutomationSearchJoB\\SearchOption1\\Emails-List.xlsx";

public static void updateCompaiesStatus(int startIndex, int endIndex) {

	updateResponceCompanies(startIndex, endIndex);
	updateStatusCompanies(startIndex, endIndex);
	
}


private static void updateResponceCompanies(int startIndex, int endIndex) {
	
	
	 String strDate ="No Responce";

		try {
			
		  	File xlsxFile = new File(fileLoaction);

			// Creating input stream
	        FileInputStream inputStream = new FileInputStream(xlsxFile);
	        // Creating workbook from input stream
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        // Reading first sheet of excel file
	        Sheet sheet = workbook.getSheetAt(0);
	        
	    	for(int i=startIndex;i<endIndex;i++)
	    	{
	    		
	    	   Cell cell=sheet.getRow(i).getCell(4); 
               cell.setCellValue(strDate);
	    	}
			
	    	
	    	//Close input stream
	        inputStream.close();

	        //Crating output stream and writing the updated workbook
	        FileOutputStream os = new FileOutputStream(xlsxFile);
	        workbook.write(os);
	         
	        //Close the workbook and output stream
	        workbook.close();
	        os.close();
	                         
	    } catch (EncryptedDocumentException | IOException e) {
	        System.err.println("Exception while updating an existing excel file.");
	        e.printStackTrace();
	    }	
	
		
}


private static void updateStatusCompanies(int startIndex, int endIndex) {
	
	  Date date = Calendar.getInstance().getTime();  
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	  String strDate = dateFormat.format(date);

	try {
		
	  	File xlsxFile = new File(fileLoaction);

		 // Creating input stream
        FileInputStream inputStream = new FileInputStream(xlsxFile);
        // Creating workbook from input stream
        Workbook workbook = WorkbookFactory.create(inputStream);
        // Reading first sheet of excel file
        Sheet sheet = workbook.getSheetAt(0);
        
    	for(int i=startIndex;i<endIndex;i++)
    	{
    	    Cell cell=sheet.getRow(i).getCell(3);
    	    cell.setCellValue(strDate);
    	}
    	//Close input stream
        inputStream.close();

        //Crating output stream and writing the updated workbook
        FileOutputStream os = new FileOutputStream(xlsxFile);
        workbook.write(os);
         
        //Close the workbook and output stream
        workbook.close();
        os.close();
                         
    } catch (EncryptedDocumentException | IOException e) {
        System.err.println("Exception while updating an existing excel file.");
        e.printStackTrace();
    }	

}	



}
