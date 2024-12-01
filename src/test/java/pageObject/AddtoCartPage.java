package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddtoCartPage extends BasePage {

	public AddtoCartPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//div[@id='content']//div[1]//div[1]//div[2]//div[2]//button[1]")   WebElement btn_addToCart;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")   WebElement div_alert_loc;
	@FindBy(xpath="//div[@id='cart']//button") WebElement btn_cart_loc;
	@FindBy(xpath="//strong[normalize-space()='Checkout']//parent::a")   WebElement btn_checkout_loc;
	@FindBy(xpath="//h1[contains(text(),'Shopping Cart')]") WebElement h1_shoppingCart_loc;
	
	//action
	public void clickAddtoCart() {
		btn_addToCart.click();
	}
	
	public boolean verifyMsg() {
		boolean displayed = div_alert_loc.isDisplayed();
		return displayed;
	}
	
	public void clickCart() {
		btn_cart_loc.click();
	}
	
	public void clickCheckout() {
		btn_checkout_loc.click();
	}
	
	public boolean VerifyShopingPage() {
		boolean displayed = h1_shoppingCart_loc.isDisplayed();
		return displayed;
	}

}
