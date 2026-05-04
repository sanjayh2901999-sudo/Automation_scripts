package Com.Com_Cast.CRM.Org_Test_Generic_Utility_Concept;


import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;
import Com.Com_Cast.Generic.Webdriver_Utility.WebDriverUtility;

public class Create_Organisation_with_Industries_Test 
{
	public static void main(String[] args) throws Throwable, IOException
	{
		// read the common data from property file
		File_Utility flib = new File_Utility();
		Excel_Utility elib= new Excel_Utility();
		JavaUtility jlib= new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		
		// read test script data from excel file
		// Step5: get the control of the 2nd cell & read the string data
		String org_Name = elib.getDataFromExcel("org",4, 2)+jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org",4, 3).trim();
		String type = elib.getDataFromExcel("org", 4, 4).trim();
	
		WebDriver driver = null;// during compileing it null in the begning

		if (BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver(); // initialisation
		} 
		else if (BROWSER.equals("firefox")) 
		{
			driver = new FirefoxDriver();
		} 
		else if (BROWSER.equals("edge")) 
		{
			driver = new EdgeDriver();
		} 
		else 
		{
			driver = new ChromeDriver();
		}
		// Step 1: login to app

		// polymorphism and upcasting concept
		driver.manage().window().maximize();
		wlib.waitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step2: Navigate to organisation module
		driver.findElement(By.linkText("Organizations")).click();
		// Step3: Click on create "Organisation" Button

		System.out.println("Hello");
		Actions act = new Actions(driver);
		Thread.sleep(5000);
		act.click(driver.findElement(By.xpath("//img[@title='Create Organization...']"))).perform();

		System.out.println("Hi");
		Thread.sleep(3000);
		// Step4: Enter all the details and create new organization
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("sanjay");
		System.out.println("value entered in the text field");

		WebElement wbsele1 = driver.findElement(By.name("industry"));
		// 1st dropdown
		Select sel1 = new Select(wbsele1);
		sel1.selectByVisibleText(industry);
		// 2nd dropdown
		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(wbsele2);
		sel2.selectByVisibleText(type);

		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Pune");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		System.out.println("sucessfully entered and saved the input");

		// verify the industries and type info
		String act_industries = driver.findElement(By.id("mouseArea_Industry")).getText();

		if (act_industries.equals(industry)) 
		{
			System.out.println(industry + "information is verified==PASS");
		} 
		else 
		{
			System.out.println(industry + "information is not verified==FAIL");
		}
		// type info
		String act_type = driver.findElement(By.id("mouseArea_Type")).getText();
		System.out.println(act_type);
		System.out.println(type);
		System.out.println("1");
		if (act_type.equals(type)) 
		{
			System.out.println(type + "information is verified==PASS");
		} 
		else 
		{
			System.out.println(type + "information is not verified==FAIL");
		}

		// Step5 :Logout

		// driver1.quit();
	}
}
