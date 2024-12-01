package testCase;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.SearchPage;
import testBase.BaseClass;

public class TC004_SearchItem extends BaseClass {
	SearchPage sp;
	
	@Test(priority = 1,groups = {"sanity"})
	public void VerifysearchIsWorking() throws AWTException, IOException {
		
		try {
			
			sp = new SearchPage(driver);
			
			logger.info("***TC004_SearchItem - started***");
			sp.setItemName(p.getProperty("item"));
			sp.clickSearch();
			Thread.sleep(2000);
			boolean verifySearch = sp.verifySearch();
			Assert.assertEquals(verifySearch, true);	
		}
		catch(Exception e) {
			e.printStackTrace();
			screenShot();
			Assert.fail();
			logger.error("test failed");
			logger.debug("Application log ended");
		}
		
		logger.info("***TC004_SearchItem - ended***");
	}

}
