package Advance_Reporting;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organization_Info_Page;
import Com.Com_Cast.CRM.ListenerUtility.Base_Class_TestNg55;
import Com.Com_Cast.CRM.ListenerUtility.ListenerImplementationClass2For_Advance_Reporting;
import Com.Com_Cast.Generic.Webdriver_Utility.UtilityClassObject;
import Com.Crm.Generic.BaseUtility_TestNg.Base_Class_TestNg;


@Listeners(Com.Com_Cast.CRM.ListenerUtility.ListenerImplementationClass2For_Advance_Reporting.class)
public class Create_Organisation_Test_TestNg extends Base_Class_TestNg55

{
@Test
public void createOrgTest() throws Exception
{
//ListenerImplementationClass2For_Advance_Reporting.test.log(Status.INFO,"read data from Excel");
UtilityClassObject.getTest().log(Status.INFO,"read data from Excel");


// Step1 :read test script data from excel 
String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
String address = elib.getDataFromExcel("org", 1, 3);
	
//Step2: navigate to organisation module 
//ListenerImplementationClass2For_Advance_Reporting.test.log(Status.INFO,"navigate to Org Page");
UtilityClassObject.getTest().log(Status.INFO,"navigate toOrg Page");
Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
	hp.getOrgLink().click();
	
	// step 3 : click on "create Organisation" Button
	UtilityClassObject.getTest().log(Status.INFO,"navigate to create Org Page");

	Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
	op.getCreateNewOrgBtn().click();
	
	//step4: enter all the details & create new organization
	UtilityClassObject.getTest().log(Status.INFO,"create a new org");

	Creating_New_Organization_Page cnop= new Creating_New_Organization_Page(driver);

	cnop.createOrg(orgName,address);
	UtilityClassObject.getTest().log(Status.INFO,orgName+"=====>create a new org");

	
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
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	String actPhoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mouseArea_Phone"))
	).getText().trim();

	if (actPhoneNumber.equals(phoneNumber)) 
	{
	    System.out.println(phoneNumber + " information is verified ==PASS");
	} 
	else 
	{
	    System.out.println(phoneNumber + " information is not verified ==FAIL");
	    throw new AssertionError("Phone number mismatch");
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
