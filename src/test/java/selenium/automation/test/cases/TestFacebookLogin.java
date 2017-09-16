package selenium.automation.test.cases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.automation.excel.ExcelReader;
import selenium.automation.test.base.DriverTestBase;
import selenium.automation.test.pom.FacebookLogin;
import selenium.automation.test.reponsitory.FacebookReponsitory;
import selenium.automation.webdriver.UIOperation;

public class TestFacebookLogin extends DriverTestBase {
	private static final String FACEBOOK_PATH = "testcases/Facebook.xlsx";
	private static final String FACEBOOK_LOGIN_SHEET = "Login";
	private static final String FACEBOOK_LOGIN_SHEET_POM = "POM";
	
	private ExcelReader excel = new ExcelReader(FACEBOOK_PATH);
	
	@Test(dataProvider="LoginRecords")
	public void loginPage(String testid, String keyword, String objectName, String objectType, String value, String result) throws Exception {
		
		if (testid.isEmpty()) {
			System.out.println("	" + keyword + "----" + objectName + "----" + objectType + "----" + value);
			
		} else {
			System.out.println("New Testcase->"+ testid +" Started");
			return;
		}
		FacebookReponsitory reponsitory = new FacebookReponsitory();
		UIOperation operation = new UIOperation(driver, reponsitory.getObjectReponsitory());
		operation.performance(keyword, objectName, objectType, value);
	}
	
	@DataProvider(name="LoginRecords")
	public Object[][] obtainDataFromProvider() {
		excel.setSheetName(FACEBOOK_LOGIN_SHEET);
		return excel.read();
	}
	
	@Test(dataProvider="LoginRecordsPOM")
	public void loginPagePOM(String testid, String userName, String password) {
		driver.get("https://en-gb.facebook.com/");
		FacebookLogin facebook = new FacebookLogin(driver);
		facebook.login(userName, password);
	}
	
	@DataProvider(name="LoginRecordsPOM")
	public Object[][] obtainDataFromProviderPOM() {
		excel.setSheetName(FACEBOOK_LOGIN_SHEET_POM);
		return excel.read();
	}
}
