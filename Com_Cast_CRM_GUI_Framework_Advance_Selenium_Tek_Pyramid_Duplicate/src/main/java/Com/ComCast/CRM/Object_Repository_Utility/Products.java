package Com.ComCast.CRM.Object_Repository_Utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products
{
@FindBy(xpath="//input[@alt='Create Product...']")
private WebElement createProductImgBtn;

@Findby (name ="search")
private WebElement ele2;
}
