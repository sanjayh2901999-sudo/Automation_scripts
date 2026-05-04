package POM_implementation;

	import java.time.Duration;
	
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
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

public class Create_Organisation_with_Phone_Number_test
	{
		public static void main(String[] args) throws Exception 
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			// read the common data from property file
			// create object 
					File_Utility flib = new File_Utility();
					Excel_Utility elib= new Excel_Utility();
					JavaUtility jlib= new JavaUtility();
					
			String BROWSER = flib.getDataFromPropertiesFile("browser");
			String URL = flib.getDataFromPropertiesFile("url");
			String USERNAME = flib.getDataFromPropertiesFile("username");
			String PASSWORD = flib.getDataFromPropertiesFile("password");

	// read test script data from excel file
	
			String org_Name = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
			String phone_Number = elib.getDataFromExcel("org", 7, 3);
			WebDriver driver = null;// during compileing it null in the begning

			if (BROWSER.equals("chrome")) 
			{
				driver = new ChromeDriver(options); // initialisation
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
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			Login_Page_Object_Repository lp = new Login_Page_Object_Repository(driver);
			lp.loginToApp(URL, USERNAME, PASSWORD);
			// Step2: Navigate to organisation module
			 Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
			 hp.getOrgLink().click();
			// Step3: Click on create "Organisation" Button
             System.out.println("Hello");
             Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
			op.getCreateNewOrgBtn().click();
			// Step4: Enter all the details and create new organization
Creating_New_Organization_Page cop = new Creating_New_Organization_Page(driver);
            cop.getOrg_name_edit().sendKeys(org_Name);
			cop.getShipping_address_edit().sendKeys("Pune");
			cop.getphone().sendKeys(phone_Number);
			cop.getSave_btn().click();
	// verify Header phone Number info Expected Result
		Organization_Info_Page oip = new Organization_Info_Page(driver);
String actual_phone_number = oip.getPhone().getText().trim();

			if (actual_phone_number.equals(phone_Number))
			{
				System.out.println("Organisation is created with"+phone_Number+"Sucessfull====PASSS");
			} 
			else 
			{
				System.out.println("Organisation is not created with"+phone_Number+"Sucessfull====FAIL");
			}

			// Step5 :Logout

			driver.quit();
		}
	}

