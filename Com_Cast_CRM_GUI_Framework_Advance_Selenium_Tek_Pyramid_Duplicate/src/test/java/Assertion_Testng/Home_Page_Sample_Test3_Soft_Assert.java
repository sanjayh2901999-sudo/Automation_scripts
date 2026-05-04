package Assertion_Testng;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
//note : whenever we use soft assert if the assert 
//got fail the compiler will collect the expection it Stores/ignores 
//and continues the execution 
public class Home_Page_Sample_Test3_Soft_Assert
{
@Test
public void homePageTest(Method mtd)
{
	System.out.println(mtd.getName()+ " Test Start");
	SoftAssert assertObj = new SoftAssert();
	
	System.out.println("step-1");
	System.out.println("step-2");
	// Softassert
	assertObj.assertEquals("Home","Home-Page");
			
	System.out.println("step-3");
	assertObj.assertEquals("Home-CRM","Home-CRM");
	System.out.println("step-4");
	assertObj.assertAll();// at the end it will print the exception and also fail the test case 
	System.out.println(mtd.getName()+" Test End");
	
}

@Test
public void verifyLogoHomePageTest(Method mtd)
{
	System.out.println(mtd.getName()+ " Test Start");
	SoftAssert assertObj = new SoftAssert();
	// Softassert
	System.out.println("step-1");
	System.out.println("step-2");
	assertObj.assertTrue(true);
	System.out.println("step-3");
	System.out.println("step-4");
	assertObj.assertAll();
	System.out.println(mtd.getName()+" Test End");
	

}
}
