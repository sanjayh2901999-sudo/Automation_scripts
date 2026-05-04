package Practice.Contact_Test_Generic_Utility2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Capture_System_Date 
{
public static void main(String[] args)
{
	Date dateObj =new Date();
	// System.out.println(dateObj.toString());
	// how to capture current system date 
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	String actDate = sim.format(dateObj);
	System.out.println("Actual Date : "+ actDate);
	
	//System.out.println( sim.format(dateObj));
	// how to capture date for next 30 days
	Calendar cal = sim.getCalendar();
	// cal.add(Calendar.DAY_OF_MONTH,-30);
	cal.add(Calendar.DAY_OF_MONTH,30);
	String date_required =sim.format(cal.getTime());
	System.out.println("Before Date :" + date_required);

	
	
	
	
	
}
}
