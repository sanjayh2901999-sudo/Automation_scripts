package Com.Com_Cast.CRM.Contact_Test_Generic_Utility_Concept;

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
import Com.Com_Cast.Generic.Webdriver_Utility.WebDriverUtility;

public class Create_Contact_Test 
{
public static void main(String[] args) throws Exception 
{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				
				// create object 
				File_Utility flib = new File_Utility();
				Excel_Utility elib= new Excel_Utility();
				JavaUtility jlib= new JavaUtility();
				WebDriverUtility wlib = new WebDriverUtility();
				
				// read the common data from property file

				String BROWSER = flib.getDataFromPropertiesFile("browser");
				String URL = flib.getDataFromPropertiesFile("url");
				String USERNAME = flib.getDataFromPropertiesFile("username");
				String PASSWORD = flib.getDataFromPropertiesFile("password");

				

				// read test script data from excel file
				/*
				 * // Step1: get the excel path location and java object of the physical
				 * excelFile FileInputStream fis1 = new
				 * FileInputStream("./Test_Data/Test_Script_Data_For_Vtiger.xlsx");
				 * 
				 * // Step2: Open the workbook read mode Workbook wb =
				 * WorkbookFactory.create(fis1);
				 * 
				 * // Step3: get the control of the ‘org’ sheet Sheet sh =
				 * wb.getSheet("contact");
				 * 
				 * // Step4: get the control of ‘1st’ row Row row = sh.getRow(1);
				 * 
				 * // Step5: get the control of the 2nd cell & read the string data String
				 * lastName = row.getCell(2).toString() + randomInt; wb.close();
				 */
				String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();
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
				wlib.waitForPageToLoad(driver);
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
				// Step4: Enter all the details and create new organization
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
				System.out.println("value entered in the text field");
                Thread.sleep(2000);
				// Actions action=new Actions(driver);
				driver.findElement(By.xpath("(//input[contains(@value,'Save')])[1]")).click();
				System.out.println("sucessfully entered and saved the input");

				// verify Header phone Number info Expected Result
				String act_last_name = driver.findElement(By.id("dtlview_Last Name")).getText();


				if (act_last_name.equals(lastName))
				{
					System.out.println(lastName + "information is verified ==PASS");
				} 
				else 
				{
					System.out.println(lastName + "information is not verified==FAIL");
				}

				// Step5 :Logout

				driver.quit();
			}
}

