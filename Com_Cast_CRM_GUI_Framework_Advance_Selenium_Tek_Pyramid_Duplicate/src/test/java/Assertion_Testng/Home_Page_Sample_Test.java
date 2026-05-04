package Assertion_Testng;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
// first test case should fail and second test case should pass
// when ever hard Assert fails my TestNg execution will not continue it is going to stop the execution 
//and then generate AssertionError exception and 
//it continues 2nd test case execution and continue executing remaining test script
public class Home_Page_Sample_Test
{
@Test
public void homePageTest(Method mtd)
{
	System.out.println(mtd.getName()+ " Test Start");
	System.out.println("step-1");
	System.out.println("step-2");
	// Hard Assert
	Assert.assertEquals("Home","Home-Page");
			
	System.out.println("step-3");
	Assert.assertEquals("Home-CRM","Home-CRM");
	System.out.println("step-4");
	
	System.out.println(mtd.getName()+" Test End");
	
}

@Test
public void verifyLogoHomePageTest(Method mtd)
{
	System.out.println(mtd.getName()+ " Test Start");
	
	System.out.println("step-1");
	System.out.println("step-2");
	Assert.assertTrue(true);
	System.out.println("step-3");
	System.out.println("step-4");
	
	System.out.println(mtd.getName()+" Test End");
	

}
}
