package three.option.forsendcv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SaveTheDataInExcel {
	
 private static final String fileLocation="C:\\AutomationSearchJoB\\SearchOption3\\SaveDataIndexExcelSheet.xlsx";	

 
 public static int[] readSavedIndexes() throws IOException
 { 
	return  implementionReadIndexes();
 }
 
 /// 
 private static int[] implementionReadIndexes() throws IOException
 {
	    int[] indexesData=new int[3]; 
	    File file=new File(fileLocation);
		FileInputStream fis=new FileInputStream(file);
		try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			XSSFSheet sheet1=wb.getSheetAt(0);
			
			
			for(int i=0;i<=indexesData.length;i++)
			indexesData[i]=(int)sheet1.getRow(1).getCell(i).getNumericCellValue();
		}
		return indexesData;
 }

 /// Save the new indexes into excel sheet file "save indexes "	
  public static void saveDataIndexs(int sheetIndex,int colomn,int row) throws IOException
  {
   
	 int[] result =readSavedIndexes();  
	 result[0]= sheetIndex;
	 result[1]+= row;
	 result[2]= colomn;

	
   
   updateIntoFile( result[0],result[1],result[2]);      
  }
  
  private static void updateIntoFile(int sheetIndex,int rowIndex,int colomnIndex ) throws IOException
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
           Sheet sheet = workbook.createSheet("sheet1");
           
           Object[][] data= {{"IndexSheet","IndexRow","IndexColumn"},{sheetIndex,rowIndex,colomnIndex}};
           
           int row=data.length;
           int colomn=data[0].length;
                  

           for(int r=0;r<row;r++) 
           {
        	  XSSFRow rows= (XSSFRow) sheet.createRow(r);
        	   for(int c=0;c<colomn;c++)
        	   {
        		 XSSFCell cell  =rows.createCell(c);
        		Object value= data[r][c];
        		
        		if(value instanceof String)
        		 cell.setCellValue((String)value);	

        		if(value instanceof Integer)
        		 cell.setCellValue((Integer)value);	

        		if(value instanceof Boolean)
        		 cell.setCellValue((Boolean)value);		
        		 
        	   }
           }
  
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
