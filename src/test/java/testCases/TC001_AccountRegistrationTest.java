package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups =   {"Sanity","Regression","Master"})
	public void verify_account_registrationTest() {

		try {
			logger.info("**Staring TC001_AccountRegistrationTest*****");

			
			LogInPage loginpage = new LogInPage(driver);

			// Home page
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("***Clicked on my Account***");

			homePage.clickRegister();
			logger.info("***Clicked on my Register***");

			// Registration form
			AccountRegistrationPage accountRegistration = new AccountRegistrationPage(driver);

			logger.info("**Providing coustmer details**");
			accountRegistration.setFirstName(getRandomString());

			accountRegistration.setLastName(getRandomString());

			accountRegistration.setEmail(getRandomString() + "@gmail.com");
			accountRegistration.setTelephoneNumber(getRandomNumber());
			String randomPassword = getPassword();
			accountRegistration.setPassword(randomPassword);
			accountRegistration.setConfirmPassword(randomPassword);
			accountRegistration.checkPolicy();
			accountRegistration.clickContinue();

			logger.info("**Validating Expected message **");

			// Verification Part
			Assert.assertEquals(accountRegistration.getConfirmationMessage(), "Your Account Has Been Created!");
			MyAccountPage myAccount = new MyAccountPage(driver);
			homePage.clickMyAccount();
			myAccount.clicklogout();
			
		} catch (Exception e) {
			logger.error("Test case failed");
			logger.debug("Debug logs....");
			Assert.fail();
		}

		logger.info("**Finished TC001_AccountRegistrationTest*****");

	}

}
