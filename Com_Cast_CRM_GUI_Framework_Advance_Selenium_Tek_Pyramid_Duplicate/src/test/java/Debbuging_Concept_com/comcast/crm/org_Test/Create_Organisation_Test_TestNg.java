package Debbuging_Concept_com.comcast.crm.org_Test;


import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organization_Info_Page;
import Com.Com_Cast.Generic.Webdriver_Utility.UtilityClassObject;
import Com.Crm.Generic.BaseUtility_TestNg_Debugging_Concept.Base_Class_Debbugging_TestNg;


@Listeners(Com.Com_Cast.CRM.ListenerUtility.ListenerImplementationClass.class)
public class Create_Organisation_Test_TestNg extends Base_Class_Debbugging_TestNg

{

@Test
public void createOrgTest() throws Exception
{

// Step1 :read test script data from excel 
	UtilityClassObject.getTest().log(Status.INFO,"read data from Excel");
	
	String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
	String address = elib.getDataFromExcel("org", 1, 3); // FIXED COLUMN
		
	//Step2: navigate to organisation module 
	UtilityClassObject.getTest().log(Status.INFO,"navigate to Org Page");

	Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
	hp.getOrgLink().click();
		
	// step 3 : click on "create Organisation" Button
	UtilityClassObject.getTest().log(Status.INFO,"navigate to create Org Page");

	Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
	op.getCreateNewOrgBtn().click();
		
	//step4: enter all the details & create new organization
	UtilityClassObject.getTest().log(Status.INFO,"create a new Org ");

	Creating_New_Organization_Page cnop= new Creating_New_Organization_Page(driver);
	cnop.createOrg(orgName,address);
	UtilityClassObject.getTest().log(Status.INFO,orgName +"====> Create a new Org");


	//verify header msg expected Result

	Organization_Info_Page oip= new Organization_Info_Page(driver);
	String actOrgName = oip.getHeaderInform().getText();

	System.out.println(actOrgName);

	Assert.assertEquals(true, actOrgName.contains(orgName));
	}

	 
@Test
public void createOrgWithPhoneNumberTest() throws Exception
{
    // read testscript data from excel 
	UtilityClassObject.getTest().log(Status.INFO,"read data from Excel");
    String OrgName = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
    String phoneNumber = elib.getDataFromExcel("org", 7, 3);
    String shipping_address = elib.getDataFromExcel("org", 7,4);
    
    // step2: navigate to organisation module 
    UtilityClassObject.getTest().log(Status.INFO,"create a new Org");
    Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
    hp.getOrgLink().click();
    
    // step3 click on create organisation button 
    Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
    op.getCreateNewOrgBtn().click();
    
    //enter all the details and create new organisation
    UtilityClassObject.getTest().log(Status.INFO,"create a new Org With phone Number");
    Creating_New_Organization_Page cnop = new Creating_New_Organization_Page(driver);
    cnop.getOrg_name_edit().sendKeys(OrgName);
    cnop.getShipping_address_edit().sendKeys(shipping_address);
    cnop.getphone().sendKeys(phoneNumber);
    cnop.getSave_btn().click();
    
    // verify Header phone number info expected result 
    UtilityClassObject.getTest().log(Status.INFO,"verify the phone Number");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Organization_Info_Page  oip= new Organization_Info_Page(driver);

    wait.until(ExpectedConditions.visibilityOf(oip.getHeaderInform()));

    String actOrgName = oip.getHeaderInform().getText();
    Assert.assertEquals(true,actOrgName.contains(OrgName));

    String actPhoneNumber = oip.getPhone().getText();
    Assert.assertTrue(actPhoneNumber.contains(phoneNumber));
}
@Test
public void createOrgWithIndustriesTest() throws Exception
{
    // read testscript data from excel 
    UtilityClassObject.getTest().log(Status.INFO,"read data from excel");

    String OrgName = elib.getDataFromExcel("org",4,2)+jlib.getRandomNumber();
    String industry= elib.getDataFromExcel("org", 4, 3);
    String address = elib.getDataFromExcel("org",4,4);
    
    // step2 : navigate to organisation module 
    UtilityClassObject.getTest().log(Status.INFO,"navigate to organisation module");

    Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
    hp.getOrgLink().click();
    
    // step3: click on create organisation button
    UtilityClassObject.getTest().log(Status.INFO,"click on create Organisation Button");

    Organisation_Page_Object_Repository cnop= new Organisation_Page_Object_Repository(driver);
    cnop.getCreateNewOrgBtn().click();
    
    //enter all the details
    UtilityClassObject.getTest().log(Status.INFO,"enter all details");

    Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);

    cop.getOrg_name_edit().sendKeys(OrgName);
    Select indDrop = new Select(cop.getIndustry_dropdown());
    indDrop.selectByVisibleText(industry);

    // ✅ Added mandatory field (Fix)
    cop.getShipping_address_edit().sendKeys(address);

    cop.getSave_btn().click();
    
    // verify the industries info
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Organization_Info_Page  oip= new Organization_Info_Page(driver);

    wait.until(ExpectedConditions.visibilityOf(oip.getIndustry()));
    String actIndustry = oip.getIndustry().getText();

    Assert.assertTrue(actIndustry.trim().contains(industry.trim()));
}
}
