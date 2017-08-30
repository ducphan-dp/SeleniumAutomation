package selenium.automation.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	private String filePath;
	private String sheetName;

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	FileInputStream fis;

	public ReadExcel(String filePath, String sheetName) {
		this(filePath);
		
		this.sheetName = sheetName;
		sheet = workbook.getSheet(sheetName);
	}

	public ReadExcel(String filePath) {
		this.filePath = filePath;

		try {
			fis = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(fis);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String readCell(int row, int cell) {
		sheet = workbook.getSheet(sheetName);
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}
	
	public Iterator<Row> readRows(boolean firstRowHeader) {
		sheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = sheet.iterator();
		if (firstRowHeader) {
			iterator.next();
		}
		return iterator;
	}
	
	public Iterator<Row> readRows() {
		return readRows(true);
	}
	
	public List<List<String>> obtainRecords(boolean firstRowHeader) {
		List<List<String>> data = new ArrayList<>();
		
		Iterator<Row> rowList = readRows(firstRowHeader);
		while (rowList.hasNext()) {
			Row row = rowList.next();
			Iterator<Cell> cellList = row.cellIterator();
			List<String> rowData = new ArrayList<>();
			
			while (cellList.hasNext()) {
				Cell cell = cellList.next();
				rowData.add(cell.getStringCellValue());
			}
			data.add(rowData);
		}
		
		
		return data;
	}
	
	public List<List<String>> obtainRecords() {
		return obtainRecords(true);
	}
	
	public Object[][] obtainProviderData(boolean firstRowHeader) {
		List<List<String>> list = obtainRecords(firstRowHeader);
		Object[][] array = list.stream()
			    .map(l -> l.stream().toArray(String[]::new))
			    .toArray(String[][]::new);
		
		return array;
	}
	
	public Object[][] obtainProviderData() {
		return obtainProviderData(true);
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		workbook.close();
		fis.close();
	}

	/*
	 * Support methods
	 */
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
