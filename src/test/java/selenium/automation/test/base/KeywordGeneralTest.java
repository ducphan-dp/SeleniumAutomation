package selenium.automation.test.base;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.automation.excel.ExcelReader;
import selenium.automation.webdriver.UIOperation;

public class KeywordGeneralTest extends DriverTestBase {
	private static final String OBJECT_PATH = "testcases/Object.xlsx";
	private static final String OBJECT_LOGIN_SHEET = "Object";
	private static final String SIMPLE_LOGIN_SHEET = "Simple";
	
	private ExcelReader excel = new ExcelReader(OBJECT_PATH);
	
	@Test(dataProvider="LoginRecords")
	public void loginPage(String testid, String keyword, String objectName, String objectType, String value, String result) throws Exception {
		if (testid.isEmpty()) {
			System.out.println("	" + keyword + "----" + objectName + "----" + objectType + "----" + value);
			//LogUtil.writeLog("	" + keyword + "----" + objectName + "----" + objectType + "----" + value);
			
		} else {
			System.out.println("New Testcase->"+ testid +" Started");
			//LogUtil.writeLog("New Testcase->"+ testid +" Started");
			return;
		}
		
		UIOperation operation = new UIOperation(driver);
		operation.performance(keyword, objectName, objectType, value, simpleFlag);
	}
	
	@DataProvider(name="LoginRecords")
	public Object[][] obtainDataFromProvider() {
		String sheetName = simpleFlag ? SIMPLE_LOGIN_SHEET : OBJECT_LOGIN_SHEET;
		excel.setSheetName(sheetName);
		return excel.read();
	}
}
