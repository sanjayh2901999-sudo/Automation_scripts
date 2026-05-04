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

// intergration test case
public class Create_Contact_With_Org_Test 
{
public static void main(String[] args) throws Exception 
		{
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	
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
			
			String org_Name = elib.getDataFromExcel("contact" , 7 , 2) +jlib.getRandomNumber();
			String contactLastName = elib.getDataFromExcel("contact", 7, 3);
			
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
            wlib.switchToTabOnURl(driver, "module=Accounts");
          
             driver.findElement(By.name("search_text")).sendKeys(org_Name);
            System.out.println("sucessfully entered data in the search text field");
            driver.findElement(By.name("search")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='"+org_Name+"']")).click();//dynamic xpath
            
            
            // switch to parent window
            wlib.switchToTabOnURl(driver, "Contacts&action");
            
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
