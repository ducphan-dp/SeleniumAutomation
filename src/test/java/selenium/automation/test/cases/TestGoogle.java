package selenium.automation.test.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.automation.test.base.DriverTestBase;

public class TestGoogle extends DriverTestBase{

	@Test
	public void testMainPage() {
		WebDriver driver;
		Wait<WebDriver> wait;
		
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://www.google.com/");
		boolean result;

		try {

			// type search query
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("qa automation");
			searchBox.submit();

			// click search
			driver.findElement(By.name("btnG")).click();

			// Wait for search to complete
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webDriver) {
					System.out.println("Searching ...");
					return webDriver.findElement(By.id("resultStats")) != null;
				}
			});

			// Look for QAAutomation.net in the results
			result = driver.findElement(By.tagName("body")).getText().contains("qaautomation.net");
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			driver.close();
		}

		System.out.println("Test " + (result ? "passed." : "failed."));
		if (!result) {
			System.exit(1);
		}
	}
}
