package selenium.automation.test.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

public abstract class ChromeDriverTestBase {
	
	public static String WEBDRIVER_CHROME_DRIVER="webdriver.chrome.driver";
	
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
		
		String webdriverPath = application.getProperty(WEBDRIVER_CHROME_DRIVER);
		System.setProperty(WEBDRIVER_CHROME_DRIVER, webdriverPath);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

}
