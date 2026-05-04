package Com.Com_Cast.CRM.ListenerUtility;
import org.testng.ITestListener;


import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;


public class ListenerImplementationClass implements ITestListener,ISuiteListener
{
public static ExtentReports report;
ExtentTest test;
public void onStart(ISuite suite)
{
	System.out.println("Resport Configuration");
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
public void onFinish(ISuite suite)
{
	System.out.println("Resport BackUp");
	report.flush();
}
public void onTestStart(ITestResult result)
{
	System.out.println("=== ===>"+result.getMethod().getMethodName()+">===START=====");
	test=report.createTest(result.getMethod().getMethodName());
	test.log(Status.INFO,result.getMethod().getMethodName()+"===> STARTED<=====");
	
}
public void onTestSuccess(ITestResult result)
{
	System.out.println("=== ===>"+result.getMethod().getMethodName()+">====END=====");
	test.log(Status.PASS,result.getMethod().getMethodName()+"===>COMPLETED<=====");

}
public void onTestFailure(ITestResult result)
{
	String test_name = result.getMethod().getMethodName();
	
	WebDriver driver= Base_Class_TestNg55.sdriver;
	driver.manage().window().maximize();
	 TakesScreenshot eDriver = (TakesScreenshot)driver;
	String file_path = eDriver.getScreenshotAs(OutputType.BASE64);
	String time = new Date().toString().replace(" ","_").replace(":", "_");
	test.addScreenCaptureFromBase64String(file_path,test_name+"_"+time);
	test.log(Status.FAIL,result.getMethod().getMethodName()+"===> FAILED<=====");

}
	
public void onTestSkipped(ITestResult result)
{

}
public void onTestFailedButWithSucessPercentage(ITestResult result)
{
	
}
public void onStart(ITestContext context)
{
	
}
public void onFinish(ITestContext context)
{
	
}
}
