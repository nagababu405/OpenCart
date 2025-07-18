package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAcountHeading;
	
	
	@FindBy(xpath = "//a[normalize-space()='Logout']") WebElement logOutLink;
	
	
	
	
	public void clicklogout()
	{
		logOutLink.click();
	}

	public boolean isMyAccountPageExists() {

		try {
			return myAcountHeading.isDisplayed();

		}
		catch(Exception e)
		{
			return false;
		}
		
		

	}

}
