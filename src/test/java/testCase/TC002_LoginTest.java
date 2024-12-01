package testCase;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	HomePage hp;
	LoginPage lp;
	
	@Test(priority = 1,groups = {"sanity"})
	public void VerifyLogin() throws AWTException, IOException  {

		logger.info("***TC002_LoginTest - started***");
		logger.debug("Application log started");

		try {

			hp = new HomePage(driver);
			lp = new LoginPage(driver);

			hp.clickMyAccount();
			hp.clickLogin();
			Thread.sleep(1000);
			logger.info("Entered into login page");
			lp.setEmail(p.getProperty("username"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			Thread.sleep(1000);
			boolean verifyLoggedIn = lp.verifyLoggedIn();
			Assert.assertEquals(verifyLoggedIn, true);
			logger.info("Logged in successfully");
			Thread.sleep(1000);
			lp.clickLogout();

		}
		catch(Exception e) {
			e.printStackTrace();
			screenShot();
			Assert.fail();
			logger.error("test failed");
			logger.debug("Application log ended");
		}
	}

}
