package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	@DataProvider(name="excelData")
	public String[][] getExcelData() throws IOException
	{
		String path = ".//testData//OpenCart_LoginData.xlsx";
		 ExcelUtils xlUtils = new ExcelUtils(path);
		 int rows = xlUtils.getRowCount("Sheet1"); // total rows including header
		 int cols = xlUtils.getCellCount("Sheet1", 1); // column count
		 
		 String[][] data = new String[rows][cols];// data rows only (skip header)
		 
		 for(int i=1;i<=rows;i++) // FIX 1: <= instead of <
		 {
			 for(int j=0;j<cols;j++)
			 {
				 data[i-1][j] = xlUtils.getCellData("Sheet1", i, j);
			 }
		 }
		 return data; 
	}

}
