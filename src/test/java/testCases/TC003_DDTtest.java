package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_DDTtest extends BaseClass{ 
	
	@Test(dataProvider="excelData",dataProviderClass=DataProviders.class,
		  groups="datadriven")
	public void verify_DDT(String user, String pwd, String result)
	{
		
		log.info("**********TC003_DDTTest start*************");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginEmail(user);
		lp.Loginpassword(pwd);
		lp.Loginbtn();
		
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean status = myacc.verifyMyAccount();
		
		/*
		 * Data is valid - login success - test pass--logout
		 *                 login failed -test fail
		 * Data is invalid - login success - test fail --logout
		 *                 login failed -test pass        976186880        
		 *                 
		 */
		if(result.equalsIgnoreCase("valid"))
		{
			 if(status==true)
			 {
				 myacc.Logout();
				 Assert.assertTrue(true);
			 }
			 else 
			 {
				 Assert.assertTrue(false);
			 }
		}
		if(result.equalsIgnoreCase("invalid"))
		{
			 if(status==true)
			 {
				 myacc.Logout();
				 Assert.assertTrue(false);
			 }
			 else
			 {
				 Assert.assertTrue(true);
			 }
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
		
		log.info("******TC003_DDTTest completed*******");
		
	}

}
