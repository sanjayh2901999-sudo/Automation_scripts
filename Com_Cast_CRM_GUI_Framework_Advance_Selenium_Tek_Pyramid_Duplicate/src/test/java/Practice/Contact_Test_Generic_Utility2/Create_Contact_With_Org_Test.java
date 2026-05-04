package Practice.Contact_Test_Generic_Utility2;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
// intergration test case
public class Create_Contact_With_Org_Test 
{
public static void main(String[] args) throws IOException, InterruptedException 
		{
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
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
			Sheet sh = wb.getSheet("contact");

			// Step4: get the control of ‘1st’ row
			Row row = sh.getRow(7);

			// Step5: get the control of the 2nd cell & read the string data
			String org_Name = row.getCell(2).toString() + randomInt;
			String contactLastName = row.getCell(3).getStringCellValue();
			wb.close();
			WebDriver driver = null;// during compileing it null in the begning

			if (BROWSER.equals("chrome")) 
			{
				driver = new ChromeDriver(options); // initialisation
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
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(URL);
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();

			// Step2: Navigate to organisation module
			driver.findElement(By.linkText("Organizations")).click();
			// Step3: Click on create "Organisation" Button

			System.out.println("Hello");
			Actions act = new Actions(driver);
			act.click(driver.findElement(By.xpath("//img[@title='Create Organization...']"))).perform();

			System.out.println("Hi");
			// Step4: Enter all the details and create new organization
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org_Name);
			System.out.println("value entered in the text field");
			driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Pune");
			
			driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
			System.out.println("sucessfully entered and saved the input");

			// verify Header msg  with Expected Result
			String header_info = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

			if (header_info.contains(org_Name)) 
			{
				System.out.println(org_Name + " Header is verified ==PASS");
			} 
			else 
			{
				System.out.println(org_Name + "Header is not verified ==FAIL");
			}

         // Step5: Navigate to Organization  module
			driver.findElement(By.linkText("Contacts")).click();
			// Step6 : Click on create "Create contact" Button

			System.out.println("Hello");
			Actions act1 = new Actions(driver);
			act1.click(driver.findElement(By.xpath("//img[@title='Create Contact...']"))).perform();

			System.out.println("Hi");
			// Step7: Enter all the details and create new organization
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(contactLastName);
			System.out.println("value entered in the text field");
			
			String pwin= driver.getWindowHandle();
            driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
            System.out.println("Suceesfully clicked on the + button");
            
            // switch to child window
          Set<String> set = driver.getWindowHandles();
          Iterator<String> it = set.iterator();
          while (it.hasNext()) 
          {
        	  // capture the window id 
        	  String WindowId = it.next();
		driver.switchTo().window(WindowId);
		String actUrl=driver.getCurrentUrl();
		if (actUrl.contains("module=Accounts")) 
		{
			break;
		}
			
		}
             driver.findElement(By.name("search_text")).sendKeys(org_Name);
            System.out.println("sucessfully entered data in the search text field");
            driver.findElement(By.name("search")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='"+org_Name+"']")).click();//dynamic xpath
            
            
            // switch to parent window
            
     /*       Set<String> set1 = driver.getWindowHandles();
            Iterator<String> it1 = set1.iterator();
            while (it1.hasNext()) 
            {
          	  // capture the window id 
          	  String WindowId = it1.next();
  		driver.switchTo().window(WindowId);
  		String actUrl=driver.getCurrentUrl();
  		if (actUrl.contains("Contacts&action")) 
  		{
  			break;
  		}
  			
  		}  */
            
            
            driver.switchTo().window(pwin);
            
         driver.findElement(By.xpath("(//input[contains(@value,'Save')])[1]")).click();
			System.out.println("sucessfully entered and saved the input");

			// verify Header phone Number info Expected Result
			String header_info1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(header_info1.contains(contactLastName))
			{
				System.out.println(contactLastName + "header verified ==PASS");
			}
			else
			{
				System.out.println(contactLastName+ "header is not verified==FAIL");
			}
			// verify Header OrgName info Expected Result
			String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if (actOrgName.contains(org_Name)) 
			{
				System.out.println(org_Name + "information is veified ==PASS");
			}
			else 
			{
			System.out.println(org_Name + "information is not verified ==FAIL");	
			}
			driver.close();

		}

}
