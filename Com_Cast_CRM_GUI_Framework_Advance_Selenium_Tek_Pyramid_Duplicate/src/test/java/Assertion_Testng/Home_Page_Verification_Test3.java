package Assertion_Testng;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
// first test case should fail and second test case should pass
public class Home_Page_Verification_Test3
{
@Test
public void homePageTest(Method mtd)
{
	System.out.println(mtd.getName()+ " Test Start");
	String expectedPage="Home Page";
	WebDriver driver =new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://49.249.28.218:8888/index.php?module=Accounts&action=index");
	System.out.println("Sucessfully entered the url");
	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
	System.out.println("sucessfully entered in the username textfield");
	driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
	System.out.println("Sucessfully entered in the password textfield");
	driver.findElement(By.id("submitButton")).click();
	System.out.println("sucessfully clicked on the login button");
	driver.findElement(By.xpath("//a[@href='index.php?module=Home&action=index&parenttab=My Home Page']")).click();
	System.out.println("sucessfully clicked on the home page");
	String actual_title = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
	//Hard Assert
	Assert.assertEquals(actual_title, expectedPage);
	driver.close();
	System.out.println(mtd.getName()+" Test End");
	
}

@Test
public void verifyLogoHomePageTest(Method mtd)
{
	System.out.println(mtd.getName()+ " Test Start");
	
	WebDriver driver =new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://49.249.28.218:8888/index.php?module=Accounts&action=index");
	System.out.println("Sucessfully entered the url");
	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
	System.out.println("sucessfully entered in the username textfield");
	driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
	System.out.println("Sucessfully entered in the password textfield");
	driver.findElement(By.id("submitButton")).click();
	System.out.println("sucessfully clicked on the login button");
	driver.findElement(By.xpath("//a[@href='index.php?module=Home&action=index&parenttab=My Home Page']")).click();
	System.out.println("sucessfully clicked on the home page");
	boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
	//Hard Assert
	Assert.assertTrue(status);
	driver.close();
	System.out.println(mtd.getName()+" Test End");
	

}
}
