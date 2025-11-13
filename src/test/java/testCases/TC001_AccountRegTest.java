package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.AccountRegPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegTest extends BaseClass{
	
	@Test(groups={"regression","master"})
	public void verify_reg()
	{
		
		try {		
		//HomePage
		log.info("======= Test Started: verifyLogin =======");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		//RegisterPage
		log.info("===== Test Started: AccountRegPage =====");
		AccountRegPage reg = new AccountRegPage(driver);
		reg.setfirstname(generateRandomString().toUpperCase());
		reg.setlastname(generateRandomString().toUpperCase());
		reg.setemail(generateRandomString()+"@gmail.com");
		reg.settelephone(generateRandomNumber());
		
		String password = generateRandomStringNumber();
		reg.setpassword(password);
		reg.setconfirmPwd(password);
		reg.agree();
		reg.btnsubmit();
		String conMsg = reg.confirmMsgs();
		Assert.assertEquals(conMsg, "Your Account Has Been Created!");
		
		}
		catch(Exception e)
		{
			log.error("Test Failed....");
			Assert.fail();
		}
		log.info("===== Test Completed Successfully =====");
	}
	
	
	
	

}
