package create.search.stratigy;

import java.util.Scanner;

import first.option.forsendcv.FirstSearchOpation;
import search.SearchJob;
import second.opation.forsendcv.SecondSearchOpation;
import three.option.forsendcv.ThreeSearchOpation;

public class CreatSearchOpt {
	
  public  SearchJob getSearchOption()
  {

		System.out.println("Please choose the search opation: One, Two, Three");
		Scanner input=new Scanner(System.in);
		String optNumber=input.nextLine();
	  
	    SearchJob searchJob=null;
	  
		  if(optNumber.equals("One")) { 
		 
			  searchJob=new FirstSearchOpation(); 
			  
		  }
		  else if(optNumber.equals("Two"))
		  {
			  searchJob=new SecondSearchOpation();
		  }  
		  else if(optNumber.equals("Three")){ 
			  searchJob =new ThreeSearchOpation();
		  
		  }
		 
	  /// need to complete the code later 
	  input.close();  
	  return searchJob;  
  }

	
	
	
	

}
