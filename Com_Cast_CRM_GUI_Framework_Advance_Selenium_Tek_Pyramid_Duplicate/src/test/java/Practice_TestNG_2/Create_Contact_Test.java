package Practice_TestNG_2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Create_Contact_Test
{
	
@Test
public void createContactTest()
{
	System.out.println("createContactTest");
}
@BeforeMethod
public void ConfigBM()
{
	System.out.println("execute BM");
}
}
