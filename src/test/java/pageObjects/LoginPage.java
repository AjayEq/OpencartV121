package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	//constructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locator
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	//Action method
	public void LoginEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void Loginpassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void Loginbtn()
	{
		btnLogin.click();
	}
	

}
