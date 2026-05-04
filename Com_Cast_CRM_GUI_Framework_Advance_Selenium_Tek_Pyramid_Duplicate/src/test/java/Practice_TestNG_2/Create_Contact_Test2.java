package Practice_TestNG_2;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Create_Contact_Test2
{
	@BeforeSuite
	public void configBS()
	{
		System.out.println("execute BS");
	}
	@AfterSuite
	public void ConfigAS()
	{
		System.out.println("exceute AS");
	}

	@BeforeClass
	public void configBC()
	{
		System.out.println("execute BC");
	}
	@BeforeMethod
	public void ConfigBM()
	{
		System.out.println("execute BM");
	}
@Test
public void createContactTest()
{
	System.out.println("createContactTest");
}
@Test
public void createContactWithSupportDate()
{
	System.out.println("createContactWithSupportDate");
}

@AfterMethod
public void configAfterMtd()
{
	System.out.println("execute Am");
}
@AfterClass
public void ConfigAC()
{
	System.out.println("execute AC");
}
}
