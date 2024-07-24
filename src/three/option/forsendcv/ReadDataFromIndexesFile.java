package three.option.forsendcv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromIndexesFile {
	
private static final String fileLocation="C:\\AutomationSearchJoB\\SearchOption3\\SaveDataIndexExcelSheet.xlsx";	

 public static int[] readFromIndexesFile() throws IOException
  {

	 return readData();	
	 
	
  }
	
 
   private static int[] readData() throws IOException
   {
	int[] indexes=new int[3];

	
	File file=new File(fileLocation);
	FileInputStream fis=new FileInputStream(file);

	try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
		XSSFSheet sheet1=wb.getSheetAt(0);

			
		   for(int i=0,j=0;i<indexes.length;j++,i++)
		   {

			   indexes[j]=(int) sheet1.getRow(1).getCell(i).getNumericCellValue();		   
			   
		   }
	}
	return indexes;	
   }
	

}

