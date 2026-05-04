package Assertion_Testng;


import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Com.ComCast.CRM.Object_Repository_Utility.Contact_Information_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Contact_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Contact_Page2;
import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;
import Com.Crm.Generic.BaseUtility_TestNg.Base_Class_TestNg;

public class Create_Contact_Test_TestNg extends Base_Class_TestNg 
{
	@Test(groups= {"smokeTest"})
	public void createContactTest() throws Exception 
	{
		
// read test script data from excel file
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// step2 : navigate to contact module
		Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
		hp.getContactLink().click();

		// step 3: click on the create Contact Button
		Contact_Page cp = new Contact_Page(driver);
		cp.getCreateContact().click();

		// Step4: Enter all the details and create new Contact
		Creating_New_Contact_Page2 ccp = new Creating_New_Contact_Page2(driver);
		ccp.creatingNewContact(lastName);
		// verify Header phone Number info Expected Result
		String act_Header = cp.getHeaderMsg().getText();
		boolean status =act_Header.contains(lastName);
		//hard assert
        Assert.assertEquals(status, true);
       
		String act_lastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		// soft assert
		SoftAssert soft= new SoftAssert();
		soft.assertEquals(act_lastName, lastName);
	}
// remaining should be implemented in the script
	@Test(groups="regression")
	public void createContactWithSupportDateTest() throws Exception 
	{

		// read test script data from excel file

		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// Step2: Navigate to Contacts module
		Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
		hp.getContactLink().click();
		// Step3: Click on create "Create contact" Button
Contact_Page cp = new Contact_Page(driver);
		cp.getCreateContact().click();
		
		// Step4: Enter all the details in the contacts
		
		String startDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYYYDDMM(30);
		
		 Creating_New_Contact_Page2 ccp = new Creating_New_Contact_Page2(driver);
		ccp.creatingNewContactWithSupportDate(lastName,startDate,endDate);
	
		
		// verify header phone number info expected result 
		String actStartDate= driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startDate))
		{
			System.out.println(startDate + "information is verified ==PASS");
		} 
		else 
		{
			System.out.println(startDate + "information is not verified==FAIL");
		}
		// verify Start Date  and end Date 
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();


		if (actEndDate.equals(endDate))
		{
			System.out.println(endDate + " information is verified ==PASS");
		} 
		else 
		{
			System.out.println(endDate + " information is not verified==FAIL");
		}
	}

	@Test
	public void createContactWithOrgTest() throws Exception 
	{
// read test script data from excel file

		String org_Name = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 3)+jlib.getRandomNumber();
		// Step2: Navigate to organisation module
		Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
		hp.getOrgLink().click();
		// Step3: Click on create "Organisation" Button
		Organisation_Page_Object_Repository cnp = new Organisation_Page_Object_Repository(driver);
		cnp.getCreateNewOrgBtn().click();
		// Step4: Enter all the details and create new organization
		Creating_New_Organization_Page cnop = new Creating_New_Organization_Page(driver);
		cnop.getOrg_name_edit().sendKeys(org_Name);
		cnop.getShipping_address_edit().sendKeys("delhi");
		cnop.getSave_btn().click();
		// verify Header OrgName info Expected Result
		//String actOrgName =driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String actOrgName = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//span[@id='dtlview_Organization Name']"))).getText();
		if (actOrgName.trim().equals(org_Name)) 
		{
			System.out.println(org_Name + "information is veified ==PASS");
		} 
		else 
		{
			System.out.println(org_Name + "information is not verified ==FAIL");
		}
		// step 5: navigate to contact module
		hp.clickOnContacts();

		// step6: click on the create contact button
		Contact_Page cp = new Contact_Page(driver);
		cp.getCreateContact().click();

		// step7: enter all the details &create new contact
		Creating_New_Contact_Page2 ccp = new Creating_New_Contact_Page2(driver);
		ccp.creatingNewContact(contactLastName);

		// verify Header OrgName info Expected Result
		Contact_Information_Page cip = new Contact_Information_Page(driver);
		String actOrgName1 = cip.getOrgName().getText();		
		if (actOrgName1.trim().equals(org_Name)) 
		{
		    System.out.println(org_Name + "information is created ==PASS");
		} 
		else 
		{
		    System.out.println(org_Name + "information is not created==FAIL");
		}
}
}
