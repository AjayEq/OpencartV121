package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	//Constructor
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}

	//Locator
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement msgMyAccount;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement linkLogout;
	
	//Action Method
	public boolean verifyMyAccount()
	{
		try
		{
			return msgMyAccount.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void Logout()
	{
		linkLogout.click();
	}
}
