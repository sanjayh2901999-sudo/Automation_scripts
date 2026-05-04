package Practice.Contact_Test_Generic_Utility2;

import java.io.FileInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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


public class Create_Contact_With_Support_Date_Test2 
{
public static void main(String[] args) throws IOException, InterruptedException 
{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				// read the common data from property file
				FileInputStream fis = new FileInputStream(
						"E:\\Tekpyramid Selenium 4.12.0 jar files and "
						+ "important files foe automation\\Advance_selenium_Tek_pyramid\\"
						+ "commondata.properties");
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
						+ "files foe automation\\Advance_selenium_Tek_pyramid" + 
						"\\Test_Script_Data_For_Vtiger.xlsx");

				// Step2: Open the workbook read mode
				Workbook wb = WorkbookFactory.create(fis1);

				// Step3: get the control of the ‘org’ sheet
				Sheet sh = wb.getSheet("contact");

				// Step4: get the control of ‘1st’ row
				Row row = sh.getRow(4);

				// Step5: get the control of the 2nd cell & read the string data
				String lastName = row.getCell(2).toString() + randomInt;
				wb.close();
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
				
				Date dateObj =new Date();
				// System.out.println(dateObj.toString());
				// how to capture current system date 
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sim.format(dateObj);
				
				// how to capture date for next 30 days
				Calendar cal = sim.getCalendar();
				// cal.add(Calendar.DAY_OF_MONTH,-30);
				cal.add(Calendar.DAY_OF_MONTH,30);
				String endDate =sim.format(cal.getTime());

				
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


				if (actStartDate.equals(startDate))
				{
					System.out.println(startDate + "information is verified ==PASS");
				} 
				else 
				{
					System.out.println(startDate + "information is not verified==FAIL");
				}
				
				// verify Start Date  and end Date 
				String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
				System.out.println("Successfully fetched get text from actEndDate");

				if (actEndDate.equals(endDate))
				{
					System.out.println(endDate + " information is verified ==PASS");
				} 
				else 
				{
					System.out.println(endDate + " information is not verified==FAIL");
				}

				// Step5 :Logout

				driver.quit();
			}
}
	


