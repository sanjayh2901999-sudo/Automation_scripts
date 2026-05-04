package Com.Crm.Generic.BaseUtility_Parallel_CrossBrowser_TestNg;

import java.sql.SQLException;

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
import org.testng.annotations.Parameters;

import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.Com_Cast.CRM.Generic.DataBase_Utility.Data_Base_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;

public class Base_Class_TestNg3 
{
	Data_Base_Utility dblib= new Data_Base_Utility();
	
	public File_Utility flib = new File_Utility();
	public Excel_Utility elib= new Excel_Utility();
	public JavaUtility jlib= new JavaUtility();
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

	public WebDriver getDriver() {
	    return tdriver.get();
	}
	@BeforeSuite(alwaysRun = true)
	public void configBS() throws SQLException
	{
		System.out.println("====connect to DB , Report Config====");
		dblib.getDbConnection();
	}
	@Parameters("BROWSER")
	@BeforeClass
	public void configBC(String browser) {
	    System.out.println("====launch the BROWSER===");

	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--disable-notifications");

	    if (browser.equalsIgnoreCase("chrome")) 
	    {
	        tdriver.set(new ChromeDriver(options));
	    } 
	    else if (browser.equalsIgnoreCase("firefox"))
	    {
	        tdriver.set(new FirefoxDriver());
	    } 
	    else if (browser.equalsIgnoreCase("edge"))
	    {
	        tdriver.set(new EdgeDriver());
	    } 
	    else
	    {
	        tdriver.set(new ChromeDriver());
	    }

	    getDriver().manage().window().maximize();
	    getDriver().manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void configBM() throws Exception {
	    System.out.println("==login==");

	    String URL = flib.getDataFromPropertiesFile("url");
	    String USERNAME = flib.getDataFromPropertiesFile("username");
	    String PASSWORD = flib.getDataFromPropertiesFile("password");

	    Login_Page_Object_Repository lplib = new Login_Page_Object_Repository(getDriver());
	    lplib.loginToApp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(alwaysRun = true)
	public void configAM() {
	    System.out.println("==logout==");

	    try {
	        if (getDriver() != null) {

	            Home_Page_Object_Repository hp = new Home_Page_Object_Repository(getDriver());

	            // Hover on admin image before logout
	            hp.logout();  // make sure this method handles hover internally

	        }
	    } catch (Exception e) {
	        System.out.println("Logout skipped due to exception: " + e.getMessage());
	    }
	}
	@AfterClass(alwaysRun = true)
	public void configAC() {
	    System.out.println("===close the BROWSER===");

	    try {
	        if (getDriver() != null) {
	            getDriver().quit();
	        }
	    } catch (Exception e) {
	        System.out.println("Browser already closed");
	    }
	}
	
	@AfterSuite(alwaysRun = true)
	public void configAS() throws SQLException
	{
		System.out.println("===close DB,,Report back up=====");
		dblib.closeDbConnection();
	}
}
