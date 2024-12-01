package testCase;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.HomePage;
import pageObject.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {
	HomePage hp;
	RegistrationPage rp;
	
	@Test(priority = 1,groups = {"sanity"})
	public void name() throws AWTException, IOException {
		logger.info("****TC001_AccountRegistration - Started****");
		logger.debug("Application logs started");
		hp = new HomePage(driver);
		rp = new RegistrationPage(driver);
		
		try {
		
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Moved to login page");
		Thread.sleep(1000);
		rp.setFirstname(randomStrings());
		rp.setLastname(randomStrings());
		rp.setEmail(randomStrings()+"@gmail.com");
		rp.setPhnumber(randomNumber());
		String pass = StringNumeric();
		rp.setpassword(pass);
		rp.setconfirmPassword(pass);
		rp.clickterms();
		logger.info("Datas are passed to the respective filed");
		rp.clickSubmit();
		Thread.sleep(2000);
		boolean registered = rp.RegisterVerification();
		Assert.assertEquals(registered, true);
		logger.info("Registered successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			screenShot();
			logger.error("Registered Unsuccessfull");
			logger.debug("logs closed");
			
			Assert.fail();
		}
		logger.debug("Application logs ended");
		logger.info("****TC001_AccountRegistration - ended****");
	}

}
