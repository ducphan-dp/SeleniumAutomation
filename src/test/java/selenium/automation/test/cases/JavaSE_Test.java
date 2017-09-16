package selenium.automation.test.cases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import selenium.automation.test.base.DriverTestBase;

public class JavaSE_Test extends DriverTestBase {
	@Test
	public void Login() {

		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Launching the Site.
		driver.get("http://demo.guru99.com/V4/");

		// Fetching the Domain Name of the site. Tostring() change object to name.
		String DomainName = js.executeScript("return document.domain;").toString();
		System.out.println("Domain name of the site = " + DomainName);

		// Fetching the URL of the site. Tostring() change object to name
		String url = js.executeScript("return document.URL;").toString();
		System.out.println("URL of the site = " + url);

		// Method document.title fetch the Title name of the site. Tostring() change
		// object to name
		String TitleName = js.executeScript("return document.title;").toString();
		System.out.println("Title of the page = " + TitleName);

		// Navigate to new Page i.e to generate access page. (launch new url)
		js.executeScript("window.location = 'http://demo.guru99.com/'");
	}
}
