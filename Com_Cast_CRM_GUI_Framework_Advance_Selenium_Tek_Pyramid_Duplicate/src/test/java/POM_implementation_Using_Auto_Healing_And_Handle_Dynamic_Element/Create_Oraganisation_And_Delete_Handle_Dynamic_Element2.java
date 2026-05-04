package POM_implementation_Using_Auto_Healing_And_Handle_Dynamic_Element;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organization_Info_Page;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;
import Com.Com_Cast.Generic.Webdriver_Utility.WebDriverUtility;

public class Create_Oraganisation_And_Delete_Handle_Dynamic_Element2 {
	public static void main(String[] args) throws Exception {

		// create object
		File_Utility flib = new File_Utility();
		Excel_Utility elib = new Excel_Utility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		// read the common data from json file

		String BROWSER = flib.getDataFromPropertiesFile("browser").trim();
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// read test script data from excel file
		int num = jlib.getRandomNumber();

		String org_Name = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
		String address = elib.getDataFromExcel("org", 1, 3);

		WebDriver driver = null;// during compileing it null in the begning
		System.out.println("BROWSER value = " + BROWSER);

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver(); // initialisation
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
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

		Login_Page_Object_Repository lp = new Login_Page_Object_Repository(driver);

//			lp.usernameEtd.sendKeys("admin");
//			lp.passwordEtd.sendKeys("admin");
//			lp.loginBtn.click();
//			lp.getUsernameEtd().sendKeys("admin");
//			System.out.println("data entered in the username textfield");
//			lp.getPasswordEtd().sendKeys("admin");
//			System.out.println("data entered in the password textfield");
//			lp.getLoginBtn().click();
//			System.out.println("clicked on the login button");
		lp.loginToApp(USERNAME, PASSWORD, address);
		// System.out.println("After login title: " + driver.getTitle());

		// Step2: Navigate to organisation module

		Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
		hp.getOrgLink().click();
		// System.out.println("After clicking Org link title: " + driver.getTitle());

		// Step3: Click on create "Organisation" Button

		Organisation_Page_Object_Repository cnp = new Organisation_Page_Object_Repository(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step4: Enter all the details and create new organization

		Creating_New_Organization_Page cnop = new Creating_New_Organization_Page(driver);
		cnop.createOrg(org_Name, address);

		// verify Header OrgName info Expected Result

		Organization_Info_Page oip = new Organization_Info_Page(driver);
		String actOrgName = oip.getHeaderInform().getText();
		System.out.println(actOrgName);

		if (actOrgName.contains(org_Name)) {
			System.out.println(org_Name + " name is verified ==PASS");
		} else {
			System.out.println(org_Name + " name is not verified ==FAIL");
		}
		// go back to Organisation Page
		hp.getOrgLink().click();

// Search for Organisation 

		cnp.getSearchEdt().sendKeys(org_Name);
		wlib.Select(cnp.getSearch_drop_down(), "Organization Name");
		cnp.getSearchBtn().click();

// In dynamic webtable select & delete Org
driver.findElement(By.xpath("//a[text()='" + org_Name + "']/ancestor::tr[@class='lvtColData']/descendant::a[text()='del']"))
				.click();

// Step5 :Logout
		hp.logout();

		driver.quit();
	}
}
