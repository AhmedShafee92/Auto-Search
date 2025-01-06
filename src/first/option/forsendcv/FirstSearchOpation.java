package first.option.forsendcv;
import search.SearchJob;

public class FirstSearchOpation implements SearchJob{
	  int[] result = null;
	String[] companiesEmail = null;
	/*
	 * private static void sendEmailsTocompanies(int numberCompaniesWantToSend)
	 * throws Exception {
	 * 
	 * ///read number of companies we want to send /// send email to the number of
	 * companies we get
	 * 
	 * 
	 * }
	 */
	@Override
	public void search() 
	{
		try{
			
				// This peace of code will be in the server side 
				// So the DATA that storage should be in the server side . 
				result = ReadSvedDataExeclSheet.readFromIndexesFile();	  
				/// read data with indexes 
				companiesEmail = ReadFromExcel.readDataFromExcelSheet(result);		
				/// send data that got 
				SendMail.sendMails(companiesEmail);
				
				//Synchronized.synchronize(companiesEmail);
				// Here we sync the data base (the local storage which the excel file )
				
				// Management the email inbox
				
				
		  }
		/*
		 * catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */ catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
