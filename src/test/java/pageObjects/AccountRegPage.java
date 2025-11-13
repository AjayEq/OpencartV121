package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegPage extends BasePage
{
	//constructor
	public AccountRegPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locater
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtlastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkagree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmMsg;
	
	
	public void setfirstname(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
	
	public void setlastname(String lname)
	{
		txtlastname.sendKeys(lname);
	}
	
	public void setemail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void settelephone(String tphone)
	{
		txttelephone.sendKeys(tphone);
	}
	
	public void setpassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void setconfirmPwd(String pwd)
	{
		txtconfirmpassword.sendKeys(pwd);
	}
	
	public void agree()
	{
		chkagree.click();
	}
	
	public void btnsubmit()
	{
		btnContinue.click();
	}
	
	public String confirmMsgs()
	{
		try
		{
			return(confirmMsg.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
		
	}
	
	
	
	
	

}
