package selenium.automation.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import selenium.automation.domain.LoginInfo;

public class ReadExcelUtils {

	private static XSSFWorkbook wb;

	public static LoginInfo readLoginTestCase() {
		LoginInfo login = new LoginInfo();
		
		try {
			
			File excel = new File("testcases/Login.xlsx");
			FileInputStream fis = new FileInputStream(excel);
			wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			String userName = sheet.getRow(1).getCell(1).getStringCellValue();
			String password = sheet.getRow(1).getCell(2).getStringCellValue();
			login.setUserName(userName);
			login.setPassword(password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return login;
	}
	
	public static void main(String[] args) {
		File directory = new File("testcases");
		System.out.println(directory.getAbsolutePath());
		System.out.println(readLoginTestCase().getUserName());
	}
}
