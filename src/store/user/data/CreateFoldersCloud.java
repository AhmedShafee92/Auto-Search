package store.user.data;

public class CreateFoldersCloud 
{

	//TODO :should add the options folders that have the search engines 
	public static boolean createFoldersCloud() 
	{
		// TODO Auto-generated method stub
		if (createPersonalUserData() &&
		createAnalyseUserData() &&
		createPrivacyUserData() )
		{
			// Success to create the folders 
			return true;
		}
		
		// success to create all the folders 
		return false ;
		
	}
	
	private static boolean createPersonalUserData() {

		
		return true; 
	}
	
	
	private static boolean createAnalyseUserData() {

		
		return true; 
	}
	
	
	private static boolean createPrivacyUserData() {

		
		return true; 
	}
	
	
	
	
	
	
	
	
	
	
	
}
