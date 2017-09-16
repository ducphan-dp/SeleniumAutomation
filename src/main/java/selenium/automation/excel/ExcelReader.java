package selenium.automation.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static final String XLSX = "XLSX";
	public static final String XLS = "XLS";

	private String filePath;
	private String sheetName;

	private Workbook workbook;
	private Sheet sheet;
	FileInputStream fis;

	/**
	 * ExcelReader
	 * @param filePath
	 * @param sheetName
	 */
	public ExcelReader(String filePath, String sheetName) {
		this(filePath);
		
		this.sheetName = sheetName;
		sheet = workbook.getSheet(sheetName);
	}

	/**
	 * ExcelReader
	 * @param filePath
	 */
	public ExcelReader(String filePath) {
		this.filePath = filePath;
		String fileExtensionName = filePath.substring(filePath.indexOf(".") + 1);

		try {
			fis = new FileInputStream(new File(filePath));
			
			if (XLSX.equalsIgnoreCase(fileExtensionName)) {
				workbook = new XSSFWorkbook(fis);
			} else if (XLS.equalsIgnoreCase(fileExtensionName)) {
				workbook = new HSSFWorkbook(fis);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * readCell
	 * @param row
	 * @param cell
	 * @return
	 */
	public String readCell(int row, int cell) {
		sheet = workbook.getSheet(sheetName);
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}
	
	/**
	 * obtainRecords
	 * @param firstRowHeader
	 * @return
	 */
	public List<List<String>> readRecords(boolean firstRowHeader) {
		List<List<String>> data = new ArrayList<>();
		
		Iterator<Row> iterator = sheet.iterator();
		if (firstRowHeader) {
			iterator.next();
		}
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
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
	
	/**
	 * obtainRecords
	 * @return
	 */
	public List<List<String>> readRecords() {
		return readRecords(true);
	}
	
	/**
	 * read
	 * @param firstRowHeader
	 * @return
	 */
	public Object[][] read(boolean firstRowHeader) {
		List<List<String>> list = readRecords(firstRowHeader);
		Object[][] array = list.stream()
			    .map(l -> l.stream().toArray(String[]::new))
			    .toArray(String[][]::new);
		
		return array;
	}
	
	/**
	 * read
	 * @return
	 */
	public Object[][] read() {
		return read(true);
	}
	
	/**
	 * write
	 * @param data
	 */
	public void write(String[] data) {
		
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
		sheet = workbook.getSheet(sheetName);
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

}
