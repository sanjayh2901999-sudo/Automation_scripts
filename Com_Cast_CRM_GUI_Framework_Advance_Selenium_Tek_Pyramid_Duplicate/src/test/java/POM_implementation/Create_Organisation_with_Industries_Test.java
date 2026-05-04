package POM_implementation;

	import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organization_Info_Page;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;
import Com.Com_Cast.Generic.Webdriver_Utility.WebDriverUtility;

	public class Create_Organisation_with_Industries_Test 
	{
public static void main(String[] args) throws Throwable, IOException
		{
			// read the common data from property file
			File_Utility flib = new File_Utility();
			Excel_Utility elib= new Excel_Utility();
			JavaUtility jlib= new JavaUtility();
			WebDriverUtility wlib = new WebDriverUtility();
			
			
			String BROWSER = flib.getDataFromPropertiesFile("browser");
			String URL = flib.getDataFromPropertiesFile("url");
			String USERNAME = flib.getDataFromPropertiesFile("username");
			String PASSWORD = flib.getDataFromPropertiesFile("password");

			//random number
			int random_number = jlib.getRandomNumber();
			
			// read test script data from excel file
			// Step5: get the control of the 2nd cell & read the string data
			String org_Name = elib.getDataFromExcel("org",4, 2)+jlib.getRandomNumber();
			String industry = elib.getDataFromExcel("org",4, 3).trim();
			String Type = elib.getDataFromExcel("org", 4, 4).trim();
		
			WebDriver driver = null;// during compileing it null in the begning

			if (BROWSER.equals("chrome"))
			{
				driver = new ChromeDriver(); // initialisation
			} 
			else if (BROWSER.equals("firefox")) 
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
			lp.loginToApp(URL,USERNAME, PASSWORD);
			System.out.println("After login title: " + driver.getTitle());

			// Step2: Navigate to organisation module
			Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
			hp.getOrgLink().click();
			System.out.println("After clicking Org link title: " + driver.getTitle());
			// Step3: Click on create "Organisation" Button
			Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
			op.getCreateNewOrgBtn().click();
			// Step4: Enter all the details and create new organization
			Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
			cop.getOrg_name_edit().sendKeys(org_Name);
			cop.getShipping_address_edit().sendKeys("Pune");
			
			WebElement industryDropdown = cop.getIndustry_dropdown();
			wlib.toSelectFromDropDown(industry, industryDropdown);
			WebElement typeDropdown = cop.gettype();
            wlib.toSelectFromDropDown(Type, typeDropdown);
            cop.getSave_btn().click();
			
			// verify the industries and type info
			Organization_Info_Page oip = new Organization_Info_Page(driver);
			String actual_industry = oip.getIndustry().getText();
		    String actual_type = oip.getType().getText();
		
			if (actual_industry.equals(industry)) 
			{
				System.out.println(industry + "information is verified==PASS");
			} 
			else 
			{
				System.out.println(industry + "information is not verified==FAIL");
			}
			// type info
			String act_type = oip.getType().getText();
			
			if (act_type.equals(Type)) 
			{
				System.out.println(Type +"information is verified==PASS");
			} 
			else 
			{
				System.out.println(Type + "information is not verified==FAIL");
			}

			// Step5 :Logout

			driver.quit();
		}
	}

