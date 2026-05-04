package ScreenShots_TestNg;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class SampleTestForScreenShots 
{
@Test
public void amazonTest() throws IOException
{
	WebDriver driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://amazon.com");
	// step1 : create an object to EventFiring Webdriver
	EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>();
	WebDriver edriver = decorator.decorate(driver);
	// step2: Use get ScreenshotAs method  to get file type of screenshot
	File src_file = ((TakesScreenshot)edriver).getScreenshotAs(OutputType.FILE);
	// Step 3: store screenshot in local driver
	File dst_file = new File("./Screenshots/test.png");
	FileUtils.copyFile(src_file,dst_file);
	

	
	

	
}
}
