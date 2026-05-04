package Com.Com_Cast.CRM.Org_Test_Generic_Utility_Concept;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;

public class Create_Organisation_With_Phone_Number_Test 
{
	public static void main(String[] args) throws Exception 
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// read the common data from property file
		// create object 
				File_Utility flib = new File_Utility();
				Excel_Utility elib= new Excel_Utility();
				JavaUtility jlib= new JavaUtility();
				
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read test script data from excel file
		// Step5: get the control of the 2nd cell & read the string data
		String org_Name = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
		String phone_Number = elib.getDataFromExcel("org", 7, 3);
		WebDriver driver = null;// during compileing it null in the begning

		if (BROWSER.equals("chrome")) 
		{
			driver = new ChromeDriver(options); // initialisation
		} else if (BROWSER.equals("firefox")) 
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
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
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("application_Name" + randomInt);
		System.out.println("value entered in the text field");

		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phone_Number);

		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Pune");
		Thread.sleep(3000);
		// Actions action=new Actions(driver);
		driver.findElement(By.xpath("(//input[contains(@value,'Save')])[1]")).click();
		System.out.println("sucessfully entered and saved the input");

		// verify Header phone Number info Expected Result
		String act_phone_number = driver.findElement(By.id("dtlview_Phone")).getText();


		if (act_phone_number.equals(phone_Number))
		{
			System.out.println(phone_Number + "information is verified ==PASS");
		} 
		else 
		{
			System.out.println(phone_Number + "information is not verified==FAIL");
		}

		// Step5 :Logout

		driver.quit();
	}
}
