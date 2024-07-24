package three.option.forsendcv;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchIntoLinkedIn {
	private static String linkedInEmailString;
	private static String linkedInPasswordString;

	
	//Step1: Read data from excel sheet 
	
    //Step2 use the data that we read to search Into LinkedIn
	
	//Step3 after searching save the indexes of the excel sheet in anther  excel sheet 
	
public static void searchIntoLinkedIn(String[] namesCompanies) throws Exception
{   
   /// here we will go into linkedIn to search the companies 
   for(String company:namesCompanies)
   {
	   applyJobIntoLinkedIn(company);
   }
  /// SaveTheDataInExcel.saveDataIndexs(0,1,namesCompanies.length);	    
}	   
private static void applyJobIntoLinkedIn(String companyName) throws Exception
 {  
		  System.setProperty("webdriver.chrome.driver",
		  ".//Drivers//chromedriver.exe"); 
		  WebDriver driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  String url ="https:\\www.linkedin.com"; 
		  driver.get(url);

		//Delay execution for 5 seconds to view the maximize operation
		 Thread.sleep(5000);
		  driver.findElement(By.id("session_key")).sendKeys(linkedInEmailString);
		  driver.findElement(By.id("session_password")).sendKeys(linkedInPasswordString);
		  driver.findElement(By.className("sign-in-form__submit-button")).click();
		  driver.findElement(By.className("search-global-typeahead__input")).
		  sendKeys(companyName); 
		  WebElement linkedInPage=driver.findElement(By.className("search-global-typeahead__input")); 
		  linkedInPage.sendKeys(Keys.ENTER);
			  try { driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[2]/section/div/nav/div/ul/li[3]/button")).click();
			  }catch(Exception e) { System.out.println(e);  
			  } 
		   try {
		  driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[4]/div/div/main/div/section[2]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[1]/div[3]/div/div/div/button"
		    )).click();
		  }catch(Exception e) 
		   {
			  System.out.println(e); 
			  } 
		  //// we need to complete the process of the search into linkedIn   
			 Thread.sleep(5000);	  
	     ////	  
	    //////////////	   
	     driver.close();	    
  }
public static String getLinkedInPasswordString() {
	return linkedInPasswordString;
}
public static void setLinkedInPasswordString(String password) {
	linkedInPasswordString=password;
}
public static String getLinkedInEmailString() {
	return linkedInEmailString;
}
 public static void setLinkedInEmailString(String email) {
	linkedInEmailString=email;
}

	

}
