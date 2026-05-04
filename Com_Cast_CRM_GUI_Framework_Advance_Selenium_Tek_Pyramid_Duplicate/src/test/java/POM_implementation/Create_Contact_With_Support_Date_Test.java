package POM_implementation;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;

public class Create_Contact_With_Support_Date_Test {
	public static void main(String[] args) throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-notifications");

		File_Utility flib = new File_Utility();
		Excel_Utility elib = new Excel_Utility();
		JavaUtility jlib = new JavaUtility();

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// read test script data from excel file

		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		WebDriver driver = null;// during compileing it null in the begning

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver(); // initialisation
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver(options);
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		// Step 1: login to app

		// polymorphism and upcasting concept
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step2: Navigate to Contacts module
		driver.findElement(By.linkText("Contacts")).click();
		// Step3: Click on create "Create contact" Button

		System.out.println("Hello");
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.click(driver.findElement(By.xpath("//img[@title='Create Contact...']"))).perform();

		System.out.println("Hi");
		Thread.sleep(2000);
		// Step4: Enter all the details in the contacts

		String startDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYYYDDMM(30);

		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		System.out.println("value entered in the text field");
		// start date
		driver.findElement(By.name("support_start_date")).clear();
		System.out.println("Sucessfully Start Date cleared");
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		System.out.println("Start date entered succesfully");
		// end date
		driver.findElement(By.name("support_end_date")).clear();
		System.out.println("End date  cleared succesfully");
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		System.out.println("End date entered succesfully");

		Thread.sleep(2000);
		// Actions action=new Actions(driver);
		driver.findElement(By.xpath("(//input[contains(@value,'Save')])[1]")).click();
		System.out.println("sucessfully entered and saved the input");

		// verify Header phone Number info Expected Result
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		System.out.println("Successfully fetched get text from actStartDate");

		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + "information is verified ==PASS");
		} else {
			System.out.println(startDate + "information is not verified==FAIL");
		}

		// verify Start Date and end Date
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		System.out.println("Successfully fetched get text from actEndDate");

		if (actEndDate.equals(endDate)) {
			System.out.println(endDate + " information is verified ==PASS");
		} else {
			System.out.println(endDate + " information is not verified==FAIL");
		}

		// Step5 :Logout

		driver.quit();
	}
}
