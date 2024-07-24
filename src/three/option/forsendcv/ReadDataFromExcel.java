package three.option.forsendcv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
	
	private static final String fileLocation="C:\\AutomationSearchJoB\\SearchOption3\\List  Companies for job search.xlsx";
	
// function to read data from Excel file -the file inside folder -SearchOption3
   public static String[] readDataFromExcelSheet(int numberOfcompanies,int[] indexes)throws Exception 
   {
	
	   return readData(numberOfcompanies,indexes);  
   }
	
	private static String[] readData(int number,int[] indexes) throws IOException
	{
		String[] companiesName=new String[number];
    	//int[] savedIndexes= SaveTheDataInExcel.readSavedIndexes();
		
		File file=new File(fileLocation);
		FileInputStream fis=new FileInputStream(file);
		
		try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			XSSFSheet sheet1=wb.getSheetAt(indexes[0]);
			
				
			for(int i=indexes[1],j=0;i<companiesName.length+indexes[1];j++,i++)
				companiesName[j]=sheet1.getRow(i).getCell(1).getStringCellValue();
		}
		return companiesName;
	
	}

}
