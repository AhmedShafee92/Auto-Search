package three.option.forsendcv;

import java.io.IOException;

public class SynchronousData {

  public static void synchronIndexs(String[] names) throws IOException {
	
	 int[] indexes= ReadDataFromIndexesFile.readFromIndexesFile();
	  indexes[1]+=names.length;
	  UpdateFile.updateIndexesFile(indexes[0], indexes[1], indexes[2]);	
   }
  
  


}