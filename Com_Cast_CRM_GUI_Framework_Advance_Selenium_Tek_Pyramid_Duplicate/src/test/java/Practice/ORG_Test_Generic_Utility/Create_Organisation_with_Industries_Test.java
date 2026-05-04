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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Create_Organisation_with_Industries_Test 
{
	public static void main(String[] args) throws Throwable, IOException
	{
		// read the common data from property file
		FileInputStream fis = new FileInputStream("E:\\Tekpyramid Selenium 4.12.0 jar files "
				+ "and important files foe automation\\Advance_selenium_Tek_pyramid" + "\\commondata.properties");
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
		FileInputStream fis1 = new FileInputStream("E:\\Tekpyramid " + "Selenium 4.12.0 jar files and important "
				+ "files foe automation\\Advance_selenium_Tek_pyramid" + "\\Test_Script_Data_For_Vtiger.xlsx");

		// Step2: Open the workbook read mode
		Workbook wb = WorkbookFactory.create(fis1);

		// Step3: get the control of the ‘org’ sheet
		Sheet sh = wb.getSheet("Org");

		// Step4: get the control of ‘1st’ row
		Row row = sh.getRow(4);

		// Step5: get the control of the 2nd cell & read the string data
		String org_Name = row.getCell(2).toString() + randomInt;
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
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
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("sanjay" + randomInt);
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
