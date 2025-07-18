package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties properties;
	
	@BeforeClass(groups =   {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String browser) throws IOException {
		
		//Reading
		FileReader file = new FileReader("./src//test//resources//config.properties");
		
		//Loading properties 
		properties = new Properties();
		properties.load(file);
		
		//Logger
		logger = LogManager.getLogger(this.getClass());
		
		//Execution environment 
				if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
				{
						DesiredCapabilities capabilities=new DesiredCapabilities();
						
						//os
						if(os.equalsIgnoreCase("Windows"))
						{
							System.out.println("Windows set");
							capabilities.setPlatform(Platform.WIN11);
						}
						else if (os.equalsIgnoreCase("mac"))
						{
							capabilities.setPlatform(Platform.MAC);
						}
						else if(os.equalsIgnoreCase("linux"))
						{
							capabilities.setPlatform(Platform.LINUX);
						}
						else
						{
							System.out.println("No matching os");
							return;
						}
					
					//Launching Browser
					switch(browser.toLowerCase())
					{
					case "chrome": capabilities.setBrowserName("chrome"); break;
					case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
					case "firefox": capabilities.setBrowserName("firefox"); break;
					default: System.out.println("No matching browser"); return;
					}
					
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

				}
				
				if(properties.getProperty("execution_env").equalsIgnoreCase("local"))
				{

					switch(browser.toLowerCase())
						{
							case "chrome" : driver=new ChromeDriver(); break;
							case "edge" : driver=new EdgeDriver(); break;
							case "firefox": driver=new FirefoxDriver(); break;
							default : System.out.println("Invalid browser name.."); return;
						}
				}
				
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				//Opening URL
				driver.get(properties.getProperty("appUrl"));
				driver.manage().window().maximize();
				
			}
				
		
		
		

	
	
	@AfterClass(groups =   {"Sanity","Regression","Master"})
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

	}
	
	
	
	public String getRandomString() {

		return RandomStringUtils.randomAlphabetic(5);
		

	}
	
	public String getRandomNumber() {

		return RandomStringUtils.randomNumeric(10);

	}
	
	public String getPassword() {

		return (RandomStringUtils.randomAlphabetic(3)+RandomStringUtils.randomNumeric(3));

	}

	public String captureScreen(String tname) {
		
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}

	
	
	
	

}
