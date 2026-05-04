package Com.Crm.Comcast.OrganisationTest_Parallel_Distribution_Execution_Testng;


import org.openqa.selenium.By;

import org.testng.annotations.Test;
import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organization_Info_Page;
import Com.Crm.Generic.BaseUtility_TestNg.Base_Class_TestNg;

public class Create_Organisation_Test_TestNg extends Base_Class_TestNg
{

@Test
public void createOrgTest() throws Exception
{
	{
		// Step1 :read test script data from excel 
		String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		String address = elib.getDataFromExcel("org", 1, 2);
			
		//Step2: navigate to organisation module 
		Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
			hp.getOrgLink().click();
			
			// step 3 : click on "create Organisation" Button
			Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
			op.getCreateNewOrgBtn().click();
			
			//step4: enter all the details & create new organization
			Creating_New_Organization_Page cnop= new Creating_New_Organization_Page(driver);
			cnop.createOrg(orgName,address);
			
			//verify header msg expected Result
			Organization_Info_Page oip= new Organization_Info_Page(driver);
			 String actOrgName = oip.getHeaderInform().getText();
			 System.out.println(actOrgName);
			 
			 if(actOrgName.contains(orgName))
			 {
				 System.out.println(orgName+"name is verified==PASS");
			 }
			 else
			 {
				 System.out.println(orgName+"name is not verified");
			 }
		}
}


	 
@Test
public void createOrgWithPhoneNumberTest() throws Exception
{
// read testscript data from excel 
	String OrgName = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
	String phoneNumber = elib.getDataFromExcel("org", 7, 3);
	
	// step2: navigate to organisation module 
	Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
	hp.getOrgLink().click();
	
	// step3 click on create organisation button 
	Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
	op.getCreateNewOrgBtn().click();
	
	//enter all the details and create new organisation
	Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
	cop.getOrg_name_edit().sendKeys(OrgName);
	cop.getShipping_address_edit().sendKeys("delhi");
	cop.getphone().sendKeys(phoneNumber);
	cop.getSave_btn().click();
	
	// verify Header phone number info expected result 
	String actPhoneNumber = driver.findElement(By.id("mouseArea_Phone")).getText();
	if (actPhoneNumber.contains(phoneNumber)) 
	{
		System.out.println(phoneNumber+"information is verified ==PASS");
	}
	else 
	{
	    throw new RuntimeException("Expected: " + phoneNumber + " but got: " + actPhoneNumber);
	}
}
@Test
public void createOrgWithIndustriesTest() throws Exception
{
	// read testscript data from excel 
	String OrgName = elib.getDataFromExcel("org",4,2)+jlib.getRandomNumber();
	String industry= elib.getDataFromExcel("org", 4, 3);
	String address = elib.getDataFromExcel("org",4,4);
	
	// step2 : navigate to organisation module 
	Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
	hp.getOrgLink().click();
	
	// step3: click on create organisation button
	Organisation_Page_Object_Repository cnop= new Organisation_Page_Object_Repository(driver);
	cnop.getCreateNewOrgBtn().click();
	//enter all the deatails
	Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
	cop.createOrgWithIndustry(OrgName, industry, address);
	
	// verify the industries and type info
	String actIndustries = driver.findElement(By.xpath("//td[@id='mouseArea_Industry']")).getText();
	if(actIndustries.equals(industry))
	{
		System.out.println(industry+"information is verified==PASS");
	}
	else
	{
		System.out.println(industry+"information is not verified==FAIL");
	}
}

}