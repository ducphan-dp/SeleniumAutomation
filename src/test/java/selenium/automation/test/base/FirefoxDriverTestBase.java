package selenium.automation.test.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

public abstract class FirefoxDriverTestBase {
	
	public static String WEBDRIVER_FIREFOX_DRIVER="webdriver.firefox.marionette";
	
	private Properties application;
	protected WebDriver driver;
	
	
	@BeforeSuite
	public void initDriver() {
		
		// Optional, if not specified, WebDriver will search your path for chromedriver.
		try (InputStream in = getClass().getClassLoader().getResourceAsStream("applications.properties")) {
			application = new Properties();
			application.load(in);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String webdriverPath = application.getProperty(WEBDRIVER_FIREFOX_DRIVER);
		System.setProperty(WEBDRIVER_FIREFOX_DRIVER, webdriverPath);
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

}
