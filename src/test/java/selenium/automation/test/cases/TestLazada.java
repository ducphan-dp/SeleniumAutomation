package selenium.automation.test.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import selenium.automation.test.base.DriverTestBase;

public class TestLazada extends DriverTestBase{

	@Test
	public void testMainPage() throws InterruptedException {
		Wait<WebDriver> wait;
		wait = new WebDriverWait(driver, 30);
		driver.get("https://www.lazada.vn/");
		driver.findElement(By.xpath("//div[1]/div/div/header/div[1]/nav/ul/li[6]/a")).click();
		new Select(driver.findElement(By.id("RegistrationForm_day"))).selectByValue("10");
		new Select(driver.findElement(By.id("RegistrationForm_month"))).selectByValue("09");
		
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
		driver.quit();
	}
}
