package selenium.automation.excel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	private String filePath;
	private String sheetName;
	private int sheetIndex;

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ReadExcel(String filePath, String sheetName) {
		this(filePath);
		
		this.sheetName = sheetName;
		sheet = workbook.getSheet(sheetName);
	}

	public ReadExcel(String filePath, int sheetIndex) {
		this(filePath);
		
		this.sheetIndex = sheetIndex;
		sheet = workbook.getSheetAt(sheetIndex);
	}

	public ReadExcel(String filePath) {
		this.filePath = filePath;

		try {

			File excel = new File(filePath);
			FileInputStream fis = new FileInputStream(excel);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String readCell(int row, int cell) {
		if (sheet == null) {
			sheet = workbook.getSheetAt(0);
		}
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public XSSFSheet getSheet() {
		if (sheet == null) {
			sheet = workbook.getSheetAt(0);
		}
		
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

}
