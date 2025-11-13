package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage  extends BasePage{
	
	//constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")
	WebElement linkLogin;
	
	//Actions
	public void clickMyAccount()
	{
		linkAccount.click();
	}
	
	public void clickRegister()
	{
		linkRegister.click();
	}
	
	public void clickLogin()
	{
		linkLogin.click();
	}

}
