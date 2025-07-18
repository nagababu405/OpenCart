package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;


import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	
	@Test(groups =   {"Sanity","Regression","Master"})
	void verify_loginTest()
	{
		//Home page Elements
		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickLogin();
		
		
		//login page Enter Login Details
		LogInPage loginpage = new LogInPage(driver);
		
		loginpage.setUserName(properties.getProperty("userName"));
		loginpage.setPassword(properties.getProperty("password"));
		loginpage.clickLoginButton();
		
		//verification part
		MyAccountPage myAccount = new MyAccountPage(driver);
		Assert.assertEquals(true, myAccount.isMyAccountPageExists(), "Login Faild");
		
		//Logout
		homepage.clickMyAccount();

		myAccount.clicklogout();
	
		
	}

}
