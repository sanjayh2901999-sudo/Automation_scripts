package Practice_TestNG_2;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Com.Com_Cast.CRM.Generic.FileUtility.Excel_Utility;

public class Get_Product_Info_Test2
{
@Test(dataProvider = "getData")
public void getProductInfoTest(String brandName , String productName)
{
	 WebDriver driver = new ChromeDriver();

	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    driver.manage().window().maximize();
	    driver.get("https://www.amazon.in");

	    driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);

	    String x = "//span[contains(text(),'"+productName+"')]"
	            + "/ancestor::div[@data-component-type='s-search-result']"
	            + "//span[@class='a-price-whole']";

	    try
	    {
	        String price = driver.findElement(By.xpath(x)).getText();
	        System.out.println(productName+" price : "+price);
	    }
	    catch(Exception e)
	    {
	        System.out.println(productName+" product not found");
	    }

	    driver.quit();
	}
@DataProvider
public Object[][] getData() throws Exception, Exception
{
Excel_Utility elib = new Excel_Utility();
int row_count=elib.getRowCount("Product");
	Object[][] objArr = new Object[row_count][2];
for(int i =0;i<row_count;i++)
{
	objArr[i][0] = elib.getDataFromExcel("Product", i+1,0 );
	objArr[i][1] = elib.getDataFromExcel("Product", i+1,1 );

}

	return objArr;
}
}

