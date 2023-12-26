package com.qa.petstore.excelutilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {

	public FileInputStream fileInputStream;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) {
		int rowCount = 0;
		try {
			fileInputStream = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheet(sheetName);
			rowCount = sheet.getLastRowNum();
			workbook.close();
			fileInputStream.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return rowCount;
	}
	
	public int getColumnCount(String sheetName,int rowNum) {
		int colCount = 0;
		try {
			fileInputStream = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheet(sheetName);
			this.row = sheet.getRow(rowNum);
			colCount = this.row.getLastCellNum();			
			workbook.close();
			fileInputStream.close();
		} catch (IOException exp) {
			exp.printStackTrace();
		}
		return colCount;
	}
	
	public String getCellData(String sheetName, int row, int column) {
		String cellData = null;
		try {
			fileInputStream = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheet(sheetName);
			this.row = sheet.getRow(row);
			this.cell = this.row.getCell(column);
			DataFormatter data = new DataFormatter();

			cellData = data.formatCellValue(cell);
			workbook.close();
			fileInputStream.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return cellData;

	}	
	
	
}
