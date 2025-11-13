package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"sanity","master"})
	public void verify_Login()
	{
		
		//home page
		log.info("********verify_Login start*********");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		log.info("********clickLogin start*********");
		hp.clickLogin();
		
		//login page
		log.info("********LoginPage start*********");
		LoginPage lp = new LoginPage(driver);
		lp.LoginEmail(prop.getProperty("username"));
		lp.Loginpassword(prop.getProperty("password"));
		lp.Loginbtn();
		log.info("********LoginPage complete*********");
		
		//myAccount page
		log.info("********myAccount start*********");
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean status = myacc.verifyMyAccount();
		Assert.assertEquals(status, true,"Login Failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		log.info("********myAccount login sussefully*********");
		
	}

}
