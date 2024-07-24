package first.option.forsendcv;

import java.io.IOException;

public class Synchronized {
	
public static void synchronize(String[] names) throws IOException {
	
	
	saveNewStatus(names);
	saveNewIndexes(names);
	
}	
	
private static void saveNewIndexes(String[] names) throws IOException
{
	 
	int[] indexes=ReadSvedDataExeclSheet.readFromIndexesFile();
	
	//UpdateCompaniesStatus.updateStatusCompanies();
	
	indexes[1]+=names.length;
    Update.updateIndexesFile(indexes[0], indexes[1],indexes[2]);

}
private static void saveNewStatus(String[] names) throws IOException
{
	
	int[] result=ReadSvedDataExeclSheet.readFromIndexesFile();
	int startIndex=result[1];
	int endIndex=result[1]+names.length;
	UpdateCompaniesStatus.updateCompaiesStatus(startIndex, endIndex);


}
  
	
	

}
