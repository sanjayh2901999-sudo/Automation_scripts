package Assertion_Testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class HomePageSampleTest_Reporting_Basic_Reports2
{
@Test
public void homePageTest(Method mtd)
{
	Reporter.log(mtd.getName()+" Test Start");
	
	Reporter.log("step-1",true);
	Reporter.log("step-2",true);
	Reporter.log("step-3",true);
	Reporter.log("step-4",true);
	Reporter.log(mtd.getName()+" Test End");
}
@Test
public void verifyLogoHomePageTest(Method mtd)
{
	Reporter.log(mtd.getName()+" Test Start");
	
	Reporter.log("step-1",true);
	Reporter.log("step-2",true);
	Reporter.log("step-3",true);
	Reporter.log("step-4",true);
	Reporter.log(mtd.getName()+" Test End");

	
}

}
