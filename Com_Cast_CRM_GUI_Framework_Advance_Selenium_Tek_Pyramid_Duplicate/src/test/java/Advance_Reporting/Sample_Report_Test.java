package Advance_Reporting;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Sample_Report_Test
{
@Test
public void createContactTest()
{
	// Spark report config 
	ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
	spark.config().setDocumentTitle("CRM Test Suite Results");
	spark.config().setReportName("CRM Report");
	spark.config().setTheme(Theme.DARK);
	
	// add Env information & create test
	ExtentReports report = new ExtentReports();
	report.attachReporter(spark);
	report.setSystemInfo("OS","Windows 11");
	report.setSystemInfo("BROWSER","CHrome-146.0.7680.80");
	ExtentTest test=report.createTest("createContactTest");
	
	test.log(Status.INFO,"login to app");
	test.log(Status.INFO,"navigate to contact page");
	test.log(Status.INFO,"create contact");
	if ("HDFC".equals("HDFC")) 
	{
		test.log(Status.PASS,"conatct is created");
	}
	else 
	{
		test.log(Status.FAIL,"contact is not created");
	}
	report.flush();
	test.log(Status.INFO,"login to app");
}

}
