package selenium.automation.test.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;

public abstract class IEDriverTestBase {
	
	public static String WEBDRIVER_IE_DRIVER="webdriver.ie.driver";
	
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
		
		String webdriverPath = application.getProperty(WEBDRIVER_IE_DRIVER);
		System.setProperty(WEBDRIVER_IE_DRIVER, webdriverPath);
		
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
	}

}
