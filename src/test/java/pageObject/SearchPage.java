package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[@placeholder='Search']")   WebElement inp_search_loc;
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")   WebElement btn_search_loc;
	@FindBy(xpath="//img[@title='iMac']")   WebElement img_item_loc;
	
	//action
	public void setItemName(String product) {
		inp_search_loc.sendKeys(product);
	}
	
	public void clickSearch() {
		btn_search_loc.click();
	}
	
	public boolean verifySearch() {
		boolean displayed = img_item_loc.isDisplayed();
		return displayed;
	}

}
