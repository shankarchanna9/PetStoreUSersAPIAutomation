package com.qa.petstore.excelutilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="Data")
	public String[][] getAllData() {
		String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";
		ExcelUtility excelutility = new ExcelUtility(path);
		int rowNum = excelutility.getRowCount("Sheet1");
		int colNum = excelutility.getColumnCount("Sheet1", 1);

		String cellData[][] = new String[rowNum][colNum];

		for (int i = 1; i <=rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				cellData[i - 1][j] = excelutility.getCellData("Sheet1", i, j);
			}

		}
		return cellData;
	}
}
