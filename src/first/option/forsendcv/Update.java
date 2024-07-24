package first.option.forsendcv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Update {
	

	private static final String fileLocation="C:\\AutomationSearchJoB\\SearchOption1\\Email_List_Indexes.xlsx";	

    public static void updateIndexesFile(int sheetIndex,int rowIndex,int colomnIndex)
    {
    	// Creating file object of existing excel file
  	  File xlsxFile = new File(fileLocation);
               
  	   //Creating workbook from input stream	   
  	   try {
             // Creating input stream
             FileInputStream inputStream = new FileInputStream(xlsxFile);
             // Creating workbook from input stream
             Workbook workbook = WorkbookFactory.create(inputStream);
             // Reading first sheet of excel file
             Sheet sheet = workbook.getSheetAt(sheetIndex);
            
             
             //Getting first sheet of workbook
             
             Cell cell1= sheet.getRow(1).getCell(0); 
             //Updating the cell value with new data
             cell1.setCellValue(sheetIndex);
             
             Cell cell2= sheet.getRow(1).getCell(1); 
             //Updating the cell value with new data
             cell2.setCellValue(rowIndex);
             
             Cell cell3= sheet.getRow(1).getCell(2); 
             //Updating the cell value with new data
             
             cell3.setCellValue(colomnIndex);
             
             //Close input stream
             inputStream.close();

             //Crating output stream and writing the updated workbook
             FileOutputStream os = new FileOutputStream(xlsxFile);
             workbook.write(os);
              
             //Close the workbook and output stream
             workbook.close();
             os.close();
                              
         System.out.println("Excel file has been updated successfully.");
         } catch (EncryptedDocumentException | IOException e) {
             System.err.println("Exception while updating an existing excel file.");
             e.printStackTrace();
         }	


   }	
}
