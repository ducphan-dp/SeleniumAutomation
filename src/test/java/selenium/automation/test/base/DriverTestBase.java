package selenium.automation.test.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class DriverTestBase {
	
	public static final String WEBDRIVER_CHROME_DRIVER		="webdriver.chrome.driver";
	public static final String WEBDRIVER_FIREFOX_DRIVER		="webdriver.firefox.marionette";
	public static final String WEBDRIVER_IE_DRIVER			="webdriver.ie.driver";
	
	public static final String WEBDRIVER_CHROME		 		= "CHROME";
	public static final String WEBDRIVER_FIREFOX 			= "FIREFOX";
	public static final String WEBDRIVER_IE 				= "IE";
	
	private Properties application;
	protected WebDriver driver;
	
	
	@BeforeClass
	@Parameters("browser")
	public void initDriver(@Optional("CHROME") String browser) {
		
		// Optional, if not specified, WebDriver will search your path for chromedriver
		String webdriverPath;
		try (InputStream in = getClass().getClassLoader().getResourceAsStream("applications.properties")) {
			application = new Properties();
			application.load(in);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		switch (browser) {
		case WEBDRIVER_CHROME:
			webdriverPath = application.getProperty(WEBDRIVER_CHROME_DRIVER);
			System.setProperty(WEBDRIVER_CHROME_DRIVER, webdriverPath);
			driver = new ChromeDriver();
			
			break;
		
		case WEBDRIVER_FIREFOX:
			webdriverPath = application.getProperty(WEBDRIVER_FIREFOX);
			System.setProperty(WEBDRIVER_FIREFOX, webdriverPath);
			driver = new FirefoxDriver();
			
			break;
		
		case WEBDRIVER_IE:
			webdriverPath = application.getProperty(WEBDRIVER_IE);
			System.setProperty(WEBDRIVER_IE, webdriverPath);
			driver = new InternetExplorerDriver();
			
			break;

		default:
			break;
		}
		
		driver.manage().window().maximize();
	}

}
