package first.option.forsendcv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel{

private static final String fileLoaction="C:\\AutomationSearchJoB\\SearchOption1\\Emails-List.xlsx";
private static final int numberOfcompanes=1500; // this the number of the companies we want to send for them the CV.  
	
		
	public static String[] readDataFromExcelSheet(int[] indexes)throws Exception 
	{
		
		   return readData(numberOfcompanes,indexes);  
	}
	
	private static String[] readData(int number,int[] indexes) throws IOException
	{
		
		String[] companiesName=new String[number];
 	   //int[] savedIndexes= SaveTheDataInExcel.readSavedIndexes();
		File file=new File(fileLoaction);
		FileInputStream fis=new FileInputStream(file);
		
		try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			XSSFSheet sheet1=wb.getSheetAt(indexes[0]);
					
			for(int i=indexes[1],j=0;i<companiesName.length+indexes[1];j++,i++)
				companiesName[j]=sheet1.getRow(i).getCell(indexes[2]).getStringCellValue();
		}
		return companiesName;
	
	}


}	
	
	

