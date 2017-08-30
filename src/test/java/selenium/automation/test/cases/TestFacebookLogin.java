package selenium.automation.test.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.automation.excel.ReadExcel;
import selenium.automation.test.base.DriverTestBase;

public class TestFacebookLogin extends DriverTestBase {
	private static final String FACEBOOK_PATH = "testcases/Facebook.xlsx";
	private static final String FACEBOOK_LOGIN_SHEET = "Login";
	
	private ReadExcel excel;
	
	@BeforeTest
	public void readExcel() {
		excel = new ReadExcel(FACEBOOK_PATH);
	}
	
	@Test(dataProvider="LoginRecords")
	public void loginPage(String testid, String userName, String password) {
		driver.get("https://en-gb.facebook.com/");
		WebElement txtUserName = driver.findElement(By.id("email"));
		txtUserName.sendKeys(userName);
		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.sendKeys(password);
		WebElement btnLogin = driver.findElement(By.xpath("//input[@value='Log In']"));
		btnLogin.click();
	}
	
	@DataProvider(name="LoginRecords")
	public Object[][] obtainDataFromProvider() {
		excel.setSheetName(FACEBOOK_LOGIN_SHEET);
		return excel.obtainProviderData();
	}
	
	@AfterTest
	public void quite() {
		driver.close();
	}
}
