package Com.Crm.Generic.BaseUtility_Advance_Reporting_TestNg;

import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.Com_Cast.CRM.Generic.DataBase_Utility.Data_Base_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;

public class Base_Class_TestNg111 
{
	public Data_Base_Utility dblib = new Data_Base_Utility();
	public File_Utility flib = new File_Utility();
	public Excel_Utility elib = new Excel_Utility();
	public JavaUtility jlib = new JavaUtility();
	public static WebDriver sdriver;
	

	@BeforeSuite
	public void configBS() throws SQLException 
	{
		System.out.println("====connect to DB , Report Config====");
		dblib.getDbConnection();
			
		}

	@BeforeClass
	public void configBC() throws Exception {
		System.out.println("====launch the BROWSER===");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		String BROWSER = flib.getDataFromPropertiesFile("browser");

		if (BROWSER.equals("chrome")) {
			sdriver = new ChromeDriver(options); // initialisation
		} else if (BROWSER.equals("firefox")) {
			sdriver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			sdriver = new EdgeDriver();
		} else {
			sdriver = new ChromeDriver();
		}
	}

	@BeforeMethod
	public void configBM() throws Exception {
		sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    System.out.println("DEBUG: BeforeMethod started");

	    String URL = flib.getDataFromPropertiesFile("url");
	    System.out.println("URL = " + URL);

	    String USERNAME = flib.getDataFromPropertiesFile("username");
	    String PASSWORD = flib.getDataFromPropertiesFile("password");

	    Login_Page_Object_Repository lplib = new Login_Page_Object_Repository(sdriver);
	    lplib.loginToApp(URL, USERNAME, PASSWORD);

	    System.out.println("DEBUG: Login successful");
	}
	@AfterMethod
	public void configAM() {
	    System.out.println("==logout==");
	    try {
	        Thread.sleep(2000); // temporary (or use WebDriverWait)

	        Home_Page_Object_Repository hp = new Home_Page_Object_Repository(sdriver);
	        hp.logout();

	    } catch (Exception e) {
	        System.out.println("Logout failed: " + e.getMessage());
	    }
	    
	}


	@AfterClass
	public void configAC() {
		System.out.println("===close the BROWSER===");
		sdriver.quit();

	}

	@AfterSuite
	public void configAS() throws SQLException {
		System.out.println("===close DB,,Report back up=====");
		dblib.closeDbConnection();
	
	}
}
