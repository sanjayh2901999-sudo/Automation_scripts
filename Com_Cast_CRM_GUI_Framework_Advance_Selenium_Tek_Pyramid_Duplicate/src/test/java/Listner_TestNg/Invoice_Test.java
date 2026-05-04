package Listner_TestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Com.Com_Cast.CRM.ListenerUtility.Base_Class_TestNg55;

@Listeners(Com.Com_Cast.CRM.ListenerUtility.ListenerImplementationClass.class)
public class Invoice_Test extends Base_Class_TestNg55 
{
@Test
public void createInvoice()
{
	System.out.println("execute create Invoice Test");
	String actual_title = driver.getTitle();
	Assert.assertEquals(actual_title,"Login");
	System.out.println("Step-1");
	System.out.println("Step-2");
	System.out.println("Step-3");
	System.out.println("Step-4");
	
}
@Test
public void createInvoiceWithContact()
{
	System.out.println("createInvoiceWithContact");
	System.out.println("Step-1");
	System.out.println("Step-2");
	System.out.println("Step-3");
	System.out.println("Step-4");
}
}
