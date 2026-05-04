package Listner_TestNg;
import org.testng.Assert;
import org.testng.annotations.Test;

import Com.Com_Cast.CRM.ListenerUtility.Base_Class_TestNg55;


public class Invoice_Test3 extends Base_Class_TestNg55 
{
@Test(retryAnalyzer = Com.Com_Cast.CRM.ListenerUtility.Retry_Listener_Implementation_Class.class)
public void activateSim()
{
	System.out.println("execute create Invoice Test");
	//Assert.assertEquals("","Login");
	System.out.println("Step-1");
	System.out.println("Step-2");
	System.out.println("Step-3");
	System.out.println("Step-4");
	
}
}
