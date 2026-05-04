package POM_implementation;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import Com.ComCast.CRM.Object_Repository_Utility.Creating_New_Organization_Page;
import Com.ComCast.CRM.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Login_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organisation_Page_Object_Repository;
import Com.ComCast.CRM.Object_Repository_Utility.Organization_Info_Page;
import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;
import Com.Com_Cast.CRM.Generic.FileUtility.File_Utility;
import Com.Com_Cast.Generic.Webdriver_Utility.JavaUtility;

public class Create_Organisation_Test {
	public static void main(String[] args) throws Exception {

		// create object
		File_Utility flib = new File_Utility();
		Excel_Utility elib = new Excel_Utility();
		JavaUtility jlib = new JavaUtility();

		// read the common data from json file

		String BROWSER = flib.getDataFromPropertiesFile("browser").trim();
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// random number
		int random_number = jlib.getRandomNumber();

		String org_Name = elib.getDataFromExcel("org", 10, 2) + jlib.getRandomNumber();
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
		Login_Page_Object_Repository lp = new Login_Page_Object_Repository(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

//			lp.usernameEtd.sendKeys("admin");
//			lp.passwordEtd.sendKeys("admin");
//			lp.loginBtn.click();
//			lp.getUsernameEtd().sendKeys("admin");
//			System.out.println("data entered in the username textfield");
//			lp.getPasswordEtd().sendKeys("admin");
//			System.out.println("data entered in the password textfield");
//			lp.getLoginBtn().click();
//			System.out.println("clicked on the login button");
		// lp.loginToApp(URL,USERNAME,PASSWORD);
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
			String actual_industry = oip.getIndustry().getText();
			String actual_type = oip.getType().getText();
			System.out.println(org_Name + " name is verified ==PASS");
		} else {
			System.out.println(org_Name + " name is not verified ==FAIL");
		}
// verify organization name info
		String verify_Orgz = oip.getHeaderInform().getText().trim();
		if (verify_Orgz.contains(org_Name)) {
			System.out.println("Organization created sucessfully==PASS");
		} else {
			System.out.println("Organization not created sucessfully==FAIL");
		}
		hp.logout();
	}
}
