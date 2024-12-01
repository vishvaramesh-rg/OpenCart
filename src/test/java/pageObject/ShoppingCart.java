package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCart extends BasePage {

	public ShoppingCart(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="(//span[@class='input-group-btn'])[2]//parent::div//input")   WebElement inp_quantity_loc;
	@FindBy(xpath="((//span[@class='input-group-btn'])[2]//child::button)[1]")   WebElement i_refresh_loc;
	@FindBy(xpath="(//table[@class='table table-bordered'])[2]//tbody//td[6]")   WebElement tbl_match1_loc;
	@FindBy(xpath="(//table[@class='table table-bordered'])[3]//tbody//tr[4]//td[2]")   WebElement tbl_match2_loc ;
	
	//locator
	public void setQuantity(String qty) {
		inp_quantity_loc.clear();
		inp_quantity_loc.sendKeys(qty);
	}
	
	public void clickRefresh() {
		i_refresh_loc.click();
	}
	
	public String matchingQtyA() {
		String text = tbl_match1_loc.getText();
		return text;
	}
	
	public String matchingQtyB() {
		String text = tbl_match2_loc.getText();
		return text;
	}

}
