package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {

	
	public LogInPage(WebDriver driver) {
		super(driver);
	}
	
	
	


	@FindBy(xpath = "//input[@id='input-email']") WebElement  inputEmail;
	
	@FindBy(xpath = "//input[@id='input-password']") WebElement  inputPassword;
	
	@FindBy(xpath = "//input[@value='Login']") WebElement loginBtn;

	public void setUserName(String userName)
	{
		inputEmail.sendKeys(userName);
	}
	
	
	public void setPassword(String password)
	{
		inputPassword.sendKeys(password);
	}
	
	public void clickLoginButton()
	{
		loginBtn.click();
	}
	
	
}
