package selenium.automation.data;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import selenium.automation.domain.LoginInfo;
import selenium.automation.excel.ReadExcel;

public class LoginTestCase {
	private static String FILE_PATH="testcases/Login.xlsx";
	ReadExcel excel;
	
	public LoginTestCase() {
		excel = new ReadExcel(FILE_PATH);
	}
	
	public LoginInfo obtainRecord() {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUserName(excel.readCell(1, 1));
		loginInfo.setPassword(excel.readCell(1, 2));
		
		return loginInfo;
	}
	
	public List<LoginInfo> obtainRecords() {
		List<LoginInfo> loginList = new ArrayList<LoginInfo>();
		Iterator<Row> iterator = excel.getSheet().iterator();
		iterator.next();
		
		while(iterator.hasNext()) {
			LoginInfo login = new LoginInfo();
			Row row = iterator.next();
			login.setUserName(row.getCell(1).getStringCellValue());
			login.setPassword(row.getCell(2).getStringCellValue());
			
			loginList.add(login);
		}
		
		return loginList;
	}
}
