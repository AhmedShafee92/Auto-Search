package three.option.forsendcv;

import java.io.IOException;
import search.SearchJob;

public class ThreeSearchOpation implements SearchJob{
	
  private static void searchOpation3(int numberCompanies) throws IOException
  {
	  
	  // we need the update indexes for the search 
	 int[]indexes=ReadDataFromIndexesFile.readFromIndexesFile();
	 
	 // we try to read data from excel file 
	 try {
		String[] names=ReadDataFromExcel.readDataFromExcelSheet(numberCompanies, indexes);
		SearchIntoLinkedIn.searchIntoLinkedIn(names);
		SynchronousData.synchronIndexs(names);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	   
 }
@Override
public void search() {
	// TODO Auto-generated method stub	 
		int numberCompanies=30;
		
		   try {
			searchOpation3(numberCompanies);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
   }
	

}
