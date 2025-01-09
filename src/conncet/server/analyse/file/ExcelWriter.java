package conncet.server.analyse.file;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
    
    private void writeListToExcelSerevr() 
    {
		// TODO Auto-generated method stub
    	
    	
    	
    	
    	
    	
    	
    	
    	

	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
