package POM_implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import Com.ComCast.CRM.Object_Repository_Utility.Contact_Information_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Contact_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Contact_Page2;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;
import Com.Com_Cast.Generic.Webdriver_Utility.WebDriverUtility;

	public class Create_Contact_Test 
	{
	public static void main(String[] args) throws Exception 
	{
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					
					// create object 
					File_Utility flib = new File_Utility();
					Excel_Utility elib= new Excel_Utility();
					JavaUtility jlib= new JavaUtility();
					WebDriverUtility wlib = new WebDriverUtility();
					
					// read the common data from property file

					String BROWSER = flib.getDataFromPropertiesFile("browser");
					String URL = flib.getDataFromPropertiesFile("url");
					String USERNAME = flib.getDataFromPropertiesFile("username");
					String PASSWORD = flib.getDataFromPropertiesFile("password");

					

					// read test script data from excel file
					/*
					 * // Step1: get the excel path location and java object of the physical
					 * excelFile FileInputStream fis1 = new
					 * FileInputStream("./Test_Data/Test_Script_Data_For_Vtiger.xlsx");
					 * 
					 * // Step2: Open the workbook read mode Workbook wb =
					 * WorkbookFactory.create(fis1);
					 * 
					 * // Step3: get the control of the ‘org’ sheet Sheet sh =
					 * wb.getSheet("contact");
					 * 
					 * // Step4: get the control of ‘1st’ row Row row = sh.getRow(1);
					 * 
					 * // Step5: get the control of the 2nd cell & read the string data String
					 * lastName = row.getCell(2).toString() + randomInt; wb.close();
					 */
					String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();
					//launching browser
					WebDriver driver = null;// during compileing it null in the begning

					if (BROWSER.equals("chrome")) 
					{
						driver = new ChromeDriver(options); // initialisation
					} else if (BROWSER.equals("firefox")) 
					{
						driver = new FirefoxDriver();
					} 
					else if (BROWSER.equals("edge")) 
					{
						driver = new EdgeDriver();
					} 
					else 
					{
						driver = new ChromeDriver();
					}
					// Step 1: login to app

					// polymorphism and upcasting concept
					driver.manage().window().maximize();
					wlib.waitForPageToLoad(driver);
					Login_Page_Object_Repository lp = new Login_Page_Object_Repository(driver);
					lp.loginToApp(URL, USERNAME, PASSWORD);
					// Step2: Navigate to Contacts module
					Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
					hp.getContactLink().click();
					// Step3: Click on  "Create contact" Button
                    Contact_Page cp = new Contact_Page(driver);
                    cp.getCreateContact().click();
					
					// Step4: Enter all the details in the create contact 
                    Creating_New_Contact_Page2 ccp = new Creating_New_Contact_Page2(driver);
                    ccp.getLastName().sendKeys(lastName);
                    ccp.getSaveBtn().click();
					
					// verify contact name
	Contact_Information_Page cip = new Contact_Information_Page(driver);
	String actual_lastname = cip.getHeaderInf().getText().trim();
	if(actual_lastname.contains(lastName))
	{
      System.out.println(lastName + "information is verified ==PASS");
    } 
   else 
    {
	System.out.println(lastName + "information is not verified==FAIL");
    }
    // verifying contact name in contact information
	String actual_lstname = cip.getLastName().getText().trim();
	if(actual_lstname.trim().equals(lastName))
	{
		System.out.println("contact created sucessfully ===PASS");
	}
	else
	{
		System.out.println("contact not created==FAIL");
	}
	//logout

	driver.close();
	
	
	
	}


}
