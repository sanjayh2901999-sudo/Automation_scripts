package Com.Com_Cast.CRM.ListenerUtility;

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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.Com_Cast.CRM.Generic.DataBase_Utility.Data_Base_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;
import Com.Com_Cast.Generic.Webdriver_Utility.UtilityClassObject;

public class Base_Class_TestNg55
{
	public Data_Base_Utility dblib = new Data_Base_Utility();
	public File_Utility flib = new File_Utility();
	public Excel_Utility elib = new Excel_Utility();
	public JavaUtility jlib = new JavaUtility();
	
	public  WebDriver driver = null; // declare it as the global variable
	
	public static WebDriver sdriver = null; // declare it as the global variable
	
	public ExtentSparkReporter sparkReporter;
	
	
	
	
	//public static ExtentReports  reports;

	@BeforeSuite
	public void configBS() throws SQLException 
	{
		System.out.println("====connect to DB , Report Config====");
		dblib.getDbConnection();
//		// Spark report config 
//		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
//		spark.config().setDocumentTitle("CRM Test Suite Results");
//		spark.config().setReportName("CRM Report");
//		spark.config().setTheme(Theme.DARK);
//		// add Env information & create test
//		report = new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("OS","Windows 11");
//		report.setSystemInfo("BROWSER","CHrome-146.0.7680.80");
		
	}

	@BeforeClass
	public void configBC() throws Exception 
	{
		System.out.println("====launch the BROWSER===");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		String BROWSER = flib.getDataFromPropertiesFile("browser");

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
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod
	public void configBM() throws Exception 
	{
		System.out.println("==login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		Login_Page_Object_Repository lplib = new Login_Page_Object_Repository(driver);
		lplib.loginToApp(URL, USERNAME, PASSWORD);

	}

	@AfterMethod
	public void configAM() 
	{
		System.out.println("==logout==");
		Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
		hp.logout();
	}

	@AfterClass
	public void configAC() {
		System.out.println("===close the BROWSER===");
		driver.quit();

	}

	@AfterSuite
	public void configAS() throws SQLException 
	{
		System.out.println("===close DB,,Report back up=====");
		dblib.closeDbConnection();
		//report.flush();
	}
}
