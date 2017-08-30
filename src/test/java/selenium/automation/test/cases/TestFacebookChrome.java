package selenium.automation.test.cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.automation.data.LoginTestCase;
import selenium.automation.domain.LoginInfo;
import selenium.automation.test.base.ChromeDriverTestBase;

public class TestFacebookChrome extends ChromeDriverTestBase{
	
	private LoginTestCase loginTestCase;
	
	@BeforeTest
	public void obtainTestCaseInfo() {
		loginTestCase = new LoginTestCase();
	}
	
	@Test(dataProvider="LoginRecords")
	public void loginPage(String userName, String password) {
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
		List<LoginInfo> loginList = loginTestCase.obtainRecords();
		Object[][] data = new Object[loginList.size()][2];
		
		for(int i = 0; i < loginList.size(); i++) {
			data[i][0] = loginList.get(i).getUserName();
			data[i][1] = loginList.get(i).getPassword();
			
			System.out.println(i + " " + data[i][0]);
		}
		
		return data;
	}
	
	@AfterTest
	public void quite() {
		driver.close();
	}

}
