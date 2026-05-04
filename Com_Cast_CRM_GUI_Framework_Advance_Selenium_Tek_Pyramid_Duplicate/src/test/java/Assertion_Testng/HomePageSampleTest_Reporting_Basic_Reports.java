package Assertion_Testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class HomePageSampleTest_Reporting_Basic_Reports
{
@Test
public void homePageTest(Method mtd)
{
	Reporter.log(mtd.getName()+" Test Start");
	
	Reporter.log("step-1");
	Reporter.log("step-2");
	Reporter.log("step-3");
	Reporter.log("step-4");
	Reporter.log(mtd.getName()+" Test End");
}
@Test
public void verifyLogoHomePageTest(Method mtd)
{
	Reporter.log(mtd.getName()+" Test Start");
	
	Reporter.log("step-1");
	Reporter.log("step-2");
	Reporter.log("step-3");
	Reporter.log("step-4");
	Reporter.log(mtd.getName()+" Test End");

	
}

}
