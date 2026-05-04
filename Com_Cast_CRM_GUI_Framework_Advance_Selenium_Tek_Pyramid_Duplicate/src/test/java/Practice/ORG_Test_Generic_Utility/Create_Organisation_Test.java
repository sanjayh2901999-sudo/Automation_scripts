package Practice.ORG_Test_Generic_Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Create_Organisation_Test 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		// read the common data from property file
		FileInputStream fis = new FileInputStream(
				"E:\\Tekpyramid Selenium 4.12.0 jar "
				+ "files and important files foe automation"
				+ "\\Advance_selenium_Tek_pyramid\\commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");

		// generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read test script data from excel file
		// Step1: get the excel path location and java object of the physical excelFile
		FileInputStream fis1 = new FileInputStream("E:\\Tekpyramid " + 
		"Selenium 4.12.0 jar files and important "
				+ "files foe automation\\Advance_selenium_Tek_pyramid" + 
		"\\Test_Script_Data_For_Vtiger.xlsx");

		// Step2: Open the workbook read mode
		Workbook wb = WorkbookFactory.create(fis1);

		// Step3: get the control of the ‘org’ sheet
		Sheet sh = wb.getSheet("Org");

		// Step4: get the control of ‘1st’ row
		Row row = sh.getRow(1);

		// Step5: get the control of the 2nd cell & read the string data
		String org_Name = row.getCell(2).toString() + randomInt;
		wb.close();
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
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org_Name+randomInt);
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
		if (act_org_name.equals(org_Name+randomInt)) 
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
