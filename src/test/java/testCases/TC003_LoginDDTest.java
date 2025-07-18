package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups =   {"Regression","Master","DataDriven"})
	public void verifyLoginDDT(String email, String password, String exp) {
		// Home page Elements
		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickLogin();

		// login page Enter Login Details
		LogInPage loginpage = new LogInPage(driver);

		loginpage.setUserName(email);
		loginpage.setPassword(password);
		loginpage.clickLoginButton();

		// verification part
		MyAccountPage myAccount = new MyAccountPage(driver);
		Assert.assertEquals(true, myAccount.isMyAccountPageExists(), "Login Faild");

		

		if (exp.equalsIgnoreCase("Valid")) {
			if (myAccount.isMyAccountPageExists() == true) {
				homepage.clickMyAccount();

				myAccount.clicklogout();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

		if (exp.equalsIgnoreCase("Invalid")) {
			if (myAccount.isMyAccountPageExists() == true) {
				
				homepage.clickMyAccount();
				myAccount.clicklogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
		
		logger.info("**TC003_LoginDDTest*****");


	}

}
