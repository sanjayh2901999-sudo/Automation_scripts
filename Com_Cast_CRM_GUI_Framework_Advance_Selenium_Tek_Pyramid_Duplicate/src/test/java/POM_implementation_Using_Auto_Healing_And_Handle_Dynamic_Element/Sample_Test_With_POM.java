package POM_implementation_Using_Auto_Healing_And_Handle_Dynamic_Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Sample_Test_With_POM 
{
@FindBy(name="user_name")
WebElement ele1;

@FindBy(name="user_password")
WebElement ele2;

@FindAll ({@FindBy(id="sub"),@FindBy(xpath = "//input[@type='submit']")}) // atleast one condition should match
private WebElement ele3;

//@FindBys({@FindBy(id="sub"),@FindBy(xpath = "//input[@type='submit']")}) // both the condition should match 
//private WebElement ele3;
@Test

public void sampleTest()
{
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://49.249.28.218:8888/index.php?module=Accounts&action=index");
	
	Sample_Test_With_POM s = PageFactory.initElements(driver,Sample_Test_With_POM.class);
	s.ele1.sendKeys("admin");
	s.ele2.sendKeys("admin");
	driver.navigate().refresh();
	 s = PageFactory.initElements(driver,Sample_Test_With_POM.class);
	
	s.ele1.sendKeys("admin");
	s.ele2.sendKeys("admin");
	
	s.ele3.click();
}

}
