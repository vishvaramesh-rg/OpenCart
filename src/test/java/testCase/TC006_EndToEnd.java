package testCase;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.AddtoCartPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;
import pageObject.SearchPage;
import pageObject.ShoppingCart;
import testBase.BaseClass;

public class TC006_EndToEnd extends BaseClass {
	HomePage hp;
	RegistrationPage rp;
	LoginPage lp;
	SearchPage sp;
	AddtoCartPage atc;
	ShoppingCart spc;
	
	@Test(priority = 1,groups = {"master"})
	public void VerifyEndToEnd() throws AWTException, IOException {
		
		try {
			
			SoftAssert sfa = new SoftAssert();
			hp=new HomePage(driver);
			rp=new RegistrationPage(driver);
			sp= new SearchPage(driver);
			lp=new LoginPage(driver);
			atc=new AddtoCartPage(driver);
			spc= new ShoppingCart(driver);
			
			//register
			logger.info("***TC006_EndToEnd - started***");
			hp.clickMyAccount();
			hp.clickRegister();
			Thread.sleep(1000);
			rp.setFirstname(randomStrings());
			rp.setLastname(randomStrings());
			String email=randomStrings()+"@gm.com";
			String pass = StringNumeric();
			rp.setEmail(email);
			rp.setPhnumber(randomNumber());
			rp.setpassword(pass);
			rp.setconfirmPassword(pass);
			rp.clickterms();
			rp.clickSubmit();
			Thread.sleep(3000);
			boolean registerVerification = rp.RegisterVerification();
			sfa.assertEquals(registerVerification, true);
			logger.info("Registered successfully");
			lp.clickLogout();
			logger.info("logged out successfully");
			
	
			//login
			hp.clickMyAccount();
			hp.clickLogin();
			Thread.sleep(1000);
			lp.setEmail(email);
			lp.setPassword(pass);
			lp.clickLogin();
			Thread.sleep(1000);
			boolean verifyLoggedIn = lp.verifyLoggedIn();
			sfa.assertEquals(registerVerification, true);
			logger.info("logged in successfully");
			
			//search and add product
			Thread.sleep(1000);
			sp.setItemName(p.getProperty("item"));
			sp.clickSearch();
			atc.clickAddtoCart();
			boolean verifyMsg = atc.verifyMsg();
			sfa.assertEquals(verifyMsg, true);
			logger.info("Add to cart happened successfully");
			
			atc.clickCart();
			atc.clickCheckout();
			Thread.sleep(1000);
			boolean verifyShopingPage = atc.VerifyShopingPage();
			sfa.assertEquals(verifyShopingPage, true);
			logger.info("Entered shopping page  successfully");
			Thread.sleep(1000);
			
			//Shopping page
			spc.setQuantity(p.getProperty("qty"));
			spc.clickRefresh();
			Thread.sleep(1000);
			String matchingQtyA = spc.matchingQtyA();
			String matchingQtyB = spc.matchingQtyB();
			if(matchingQtyA.equalsIgnoreCase(matchingQtyB)) {
				sfa.assertTrue(true);
				logger.info("matched successfully");
			}
			else {
				sfa.fail();
				logger.error("Total mismatching");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			screenShot();
			Assert.fail();
			logger.error("test failed");
			logger.debug("Application log ended");
		}
	}

}
