package Advance_Reporting;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Sample_Report_Test2
{
	public ExtentReports report;
	@BeforeSuite
	public void configBS()
	{
		// Spark report config 
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		// add Env information & create test
		 report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows 11");
		report.setSystemInfo("BROWSER","CHrome-146.0.7680.80");
	}
	@AfterSuite
	public void configAS()
	{
		report.flush();
	}
@Test
public void createContactTest()
{
	WebDriver driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://49.249.28.218:8888/index.php?module=Accounts&action=index");
	
		
	
	ExtentTest test=report.createTest("createContactTest");
	
	test.log(Status.INFO,"login to app");
	test.log(Status.INFO,"navigate to contact page");
	test.log(Status.INFO,"create contact");
	if ("HDFC".equals("ICICI")) 
	{
		test.log(Status.PASS,"conatct is created");
	}
	else 
	{
	    test.log(Status.FAIL,"contact is not created");
	    TakesScreenshot eDriver = (TakesScreenshot)driver;
		String file_path = eDriver.getScreenshotAs(OutputType.BASE64);
	    test.addScreenCaptureFromBase64String(file_path, "ErrorFile");
	}
	driver.quit();
}
}
//@Test
//public void createContactWithOrg()
//{
//	
//	ExtentTest test=report.createTest("createContactWithOrg");
//	
//	test.log(Status.INFO,"login to app");
//	test.log(Status.INFO,"navigate to contact page");
//	test.log(Status.INFO,"create contact");
//	if ("HDFC".equals("HDFC")) 
//	{
//		test.log(Status.PASS,"conatct is created");
//	}
//	else 
//	{
//		test.log(Status.FAIL,"contact is not created");
//	}
//	
//}
//@Test
//public void createContactWithPhoneNumber()
//{
//	
//	ExtentTest test=report.createTest("createContactWithPhoneNumber");
//	
//	test.log(Status.INFO,"login to app");
//	test.log(Status.INFO,"navigate to contact page");
//	test.log(Status.INFO,"create contact");
//	if ("HDFC".equals("HDFC")) 
//	{
//		test.log(Status.PASS,"conatct is created");
//	}
//	else 
//	{
//		test.log(Status.FAIL,"contact is not created");
//	}
//	
//}
//
//}
