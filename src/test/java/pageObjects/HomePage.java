package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	
	

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccountLink;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement logInLink;
	
	

	public void clickMyAccount() {
		myAccountLink.click();
	}

	public void clickRegister() {
		registerLink.click();
	}
	
	public void clickLogin() {
		logInLink.click();
	}
	
	

}
