package Coding_Standards;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.Crm.Generic.BaseUtility_BatchExecution_TestNg.Base_Class_TestNg;

/**
 * test class for Contact Module
 * @author SANJAY
 * 
 * 
 */
public class SearchContactTest extends Base_Class_TestNg
{
/**
 * You can even write scenario as well: login()===> Navigate to contact()===> create contact () ==> verify 
 * 
 * 	
 */
	
@Test
public void searchContactTest()
{
	/* Step 1 : Login to app
	 * 
	 */
	          
			Login_Page_Object_Repository lp =  new Login_Page_Object_Repository(driver);
			lp.loginToApp("url", "username","password");
	          
}

}
