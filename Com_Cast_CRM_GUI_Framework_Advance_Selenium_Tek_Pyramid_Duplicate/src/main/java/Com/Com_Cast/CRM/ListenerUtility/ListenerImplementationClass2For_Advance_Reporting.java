package Com.Com_Cast.CRM.ListenerUtility;
import java.util.Date;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Com.Com_Cast.Generic.Webdriver_Utility.UtilityClassObject;



public class ListenerImplementationClass2For_Advance_Reporting implements ITestListener,ISuiteListener
{

public ExtentReports report;
public static ExtentTest test;

public void onStart(ISuite suite)
{
	System.out.println("Resport Configuration");
    String time = new Date().toString().replace(" ","_").replace(":","_");

	// Spark report config 
				ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
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
    test = report.createTest(result.getMethod().getMethodName());

    // ✅ ADD THIS LINE (MOST IMPORTANT FIX)
    UtilityClassObject.setTest(test);
}
@Override
public void onTestSuccess(ITestResult result)
{
    System.out.println("===========> "+result.getMethod().getMethodName()+">=====END======");
    test.log(Status.PASS,result.getMethod().getMethodName()+"===>COMPLETED<=====");
}
public void onTestFailure(ITestResult result)
{
    String test_name = result.getMethod().getMethodName();

    TakesScreenshot ts = (TakesScreenshot) Base_Class_TestNg55.sdriver;
    String file_path = ts.getScreenshotAs(OutputType.BASE64);

    String time = new Date().toString().replace(" ","_").replace(":","_");

    test.log(Status.FAIL, test_name + " ==> FAILED");
    test.log(Status.FAIL, result.getThrowable());

    test.addScreenCaptureFromBase64String(file_path, test_name + "_" + time);
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
