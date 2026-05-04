package Com.Com_Cast.CRM.ListenerUtility;

import org.testng.IRetryAnalyzer;

import org.testng.ITestResult;


public class Retry_Listener_Implementation_Class implements IRetryAnalyzer
{
	int count =0;
	int limitCount=5; // this means if any test case fails you execute the test script for 5times 
	public boolean retry(ITestResult result)
	{
		if(count<limitCount)
		{
			count++;
			return true;
		}
		return false;
		
	}
}
