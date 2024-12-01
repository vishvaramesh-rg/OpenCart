package testCase;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AddtoCartPage;
import pageObject.SearchPage;
import testBase.BaseClass;

public class TC005_AddtoCartItem extends BaseClass {
	SearchPage sp;
	AddtoCartPage atc;
	
	@Test(priority = 1,groups = {"sanity"})
	public void VerifyAddtoCart() throws AWTException, IOException {

		try {

			sp=new SearchPage(driver);
			atc=new AddtoCartPage(driver);

			logger.info("***TC005_AddtoCartItem - started***");
			sp.setItemName(p.getProperty("item"));
			sp.clickSearch();
			Thread.sleep(1000);
			atc.clickAddtoCart();
			Thread.sleep(1000);
			boolean verifyMsg = atc.verifyMsg();
			Assert.assertEquals(verifyMsg, true);

		}
		catch(Exception e) {
			e.printStackTrace();
			screenShot();
			Assert.fail();
			logger.error("test failed");
			logger.debug("Application log ended");
		}
		logger.info("***TC005_AddtoCartItem - ended***");
	}

}
