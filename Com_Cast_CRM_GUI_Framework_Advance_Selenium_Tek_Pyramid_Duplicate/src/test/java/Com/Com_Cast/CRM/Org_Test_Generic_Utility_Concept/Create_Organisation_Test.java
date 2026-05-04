package Com.Com_Cast.CRM.Org_Test_Generic_Utility_Concept;

import java.time.Duration;
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

public class Create_Organisation_Test 
{
	public static void main(String[] args) throws Exception 
	{
		
		// create object 
		File_Utility flib = new File_Utility();
		Excel_Utility elib= new Excel_Utility();
		JavaUtility jlib= new JavaUtility();
		
		// read the common data from json file
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		

		// read test script data from excel file
		int num = jlib.getRandomNumber();
	
		String org_Name = elib.getDataFromExcel("org",1,2) + jlib.getRandomNumber();
	
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
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org_Name+num);
		System.out.println("value entered in the text field");

		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Pune");
		Thread.sleep(5000);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		System.out.println("sucessfully entered and saved the input");

		// verify Header msg Expected Result
		String header_info = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (header_info.contains(org_Name)) 
		{
			System.out.println(org_Name + " is created ==PASS");
		} 
		else 
		{
			System.out.println(org_Name + "is not created==FAIL");
		}

		// verify Header orgName info Expected Result
		String act_org_name = driver.findElement(By.id("dtlview_Organization Name")).getText().trim();
		System.out.println(act_org_name);
		if (act_org_name.equals(org_Name+num)) 
		{
			System.out.println(org_Name + "information is  created ==PASS");
		} 
		else 
		{
			System.out.println(org_Name + "information is not created==FAIL");
		}

		// Step5 :Logout

		driver.quit();
	}
}
