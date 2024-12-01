package testCase;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import pageObject.HomePage;
import pageObject.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestWithDDTMethod extends BaseClass {
	HomePage hp;
	LoginPage lp;
	
	@Test(dataProvider = "dataDDT",dataProviderClass = DataProviders.class,groups = {"regression"})
	public void LoginWithDDT(String userN, String passW, String rec) throws AWTException, IOException {
		
		try {
			logger.info("*** Finished TC_002_VerifyLoginTest ***");
			hp = new HomePage(driver);
			lp = new LoginPage(driver);

			hp.clickMyAccount();
			hp.clickLogin();

			lp.setEmail(userN);
			lp.setPassword(passW);
			lp.clickLogin();
			Thread.sleep(1000);
			boolean verify = lp.verifyLoggedIn();

			if(rec.equalsIgnoreCase("valid")) {
				if(verify==true) {
					lp.clickLogout();
					Assert.assertTrue(true);
				}else {
					driver.navigate().refresh();
					logger.error("Valid cred but it's not logged in");
					Assert.assertTrue(false);
				}
			}
			if(rec.equalsIgnoreCase("invalid")) {
				if(verify==true) {
					lp.clickLogout();
					logger.error("invalid cred but it's logged in");
					Assert.assertTrue(false);
				}else {
					driver.navigate().refresh();
					Assert.assertTrue(true);
				}

			}

		}
		catch(Exception e) {
			e.printStackTrace();
			screenShot();
			AssertJUnit.fail();
			logger.error("test failed");
			logger.debug("Application log ended");
		}
		logger.info("*** Finished TC_002_VerifyLoginTest ***");
	}

}
