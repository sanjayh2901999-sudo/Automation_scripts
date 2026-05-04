package POM_implementation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Com.ComCast.CRM.Object_Repository_Utility.Contact_Information_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Contact_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Contact_Page2;
import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;

import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;
import Com.Com_Cast.Generic.Webdriver_Utility.WebDriverUtility;

public class Create_Contact_with_Org_Test 
{

    public static void main(String[] args) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        File_Utility flib = new File_Utility();
        Excel_Utility elib = new Excel_Utility();
        JavaUtility jlib = new JavaUtility();
        WebDriverUtility wlib = new WebDriverUtility();

        // Read common data from Property File
        String BROWSER = flib.getDataFromPropertiesFile("browser");
        String URL = flib.getDataFromPropertiesFile("url");
        String USERNAME = flib.getDataFromPropertiesFile("username");
        String PASSWORD = flib.getDataFromPropertiesFile("password");

        // Read test data from Excel
        String org_Name = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
        String contactLastName = elib.getDataFromExcel("contact", 7, 3);

        WebDriver driver = null;

        // Launch Browser
        if (BROWSER.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver(options);
        } 
        else if (BROWSER.equalsIgnoreCase("firefox")) 
        {
            driver = new FirefoxDriver();
        } 
        else if (BROWSER.equalsIgnoreCase("edge")) 
        {
            driver = new EdgeDriver();
        } 
        else
        {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        wlib.waitForPageToLoad(driver);

        // Step 1: Login
        Login_Page_Object_Repository lp = new Login_Page_Object_Repository(driver);
        lp.loginToApp(URL, USERNAME, PASSWORD);

        // Step 2: Navigate to Organization module
        Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
        hp.getOrgLink().click();

        // Step 3: Click Create Organization
        Organisation_Page_Object_Repository op = new Organisation_Page_Object_Repository(driver);
        op.getCreateNewOrgBtn().click();

        // Step 4: Create Organization
        Creating_New_Organization_Page cnop = new Creating_New_Organization_Page(driver);
        cnop.getOrg_name_edit().sendKeys(org_Name);
        cnop.getShipping_address_edit().sendKeys("Pune");
        cnop.getSave_btn().click();

        wlib.waitForPageToLoad(driver);
            Thread.sleep(3000);
        // Step 5: Navigate to Contacts
        hp.clickOnContacts();

        // Step 6: Create Contact
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        Creating_New_Contact_Page2 ccp = new Creating_New_Contact_Page2(driver);
        
        Contact_Page cp = new Contact_Page(driver);
        cp.getCreateContact().click();
       
        
       
        ccp.getSelectOrgIcon().click();

        // Step 7: Switch to Organization Window
        wlib.switchToWindow(driver, "Accounts");

        // Step 8: Search Organization
        op.getSearchEdt().sendKeys(org_Name);
        op.getSearchBtn1().click();

        driver.findElement(By.xpath("//a[text()='" + org_Name + "']")).click();

        // Step 9: Switch Back to Contact Window
        wlib.switchToWindow(driver, "Contacts");
        
        Thread.sleep(3000);
        ccp.getLastName().sendKeys(contactLastName);
        // Step 10: Save Contact
        ccp.getSaveBtn().click();

        // Step 11: Verify Contact Organization
        Contact_Information_Page cip = new Contact_Information_Page(driver);
        Thread.sleep(3000);
        
        String actual_org = cip.getOrgName().getText().trim();

        if (actual_org.contains(org_Name))
        {
            System.out.println("Contact created with " + org_Name + " === PASS");
        } 
        else 
        {
            System.out.println("Contact not created with " + org_Name + " === FAIL");
        }

        // Step 12: Logout
        hp.logout();

        driver.quit();
    }
}