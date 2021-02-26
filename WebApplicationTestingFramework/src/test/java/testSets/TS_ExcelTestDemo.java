package testSets;

import utility.ExcelUtility;
/*
 * Author - Sachin Jagad
 * This Class is created to check the ExcelUtility methods.
 * Run this as Java application to check if the data is getting fetched from the excel sheet and printed on console.
 * */
public class TS_ExcelTestDemo {

	public static void main(String[] args) 
	{
		ExcelUtility eu = new ExcelUtility("ExcelTestData.xlsx", "Sheet2");
		eu.GetTotalRowCount();
		eu.GetTotalColumnCount();
		eu.getCellDataStringValue(1, 0);
		eu.getCellDataNumericValue(5, 1);
		eu.getCellDataDateValue(5, 0);
		eu.setCellDataStringValue("Test", 2, 2);
		eu.setCellDataNumericValue(1020, 2, 2);
		eu.CloseExcelFile();
	}

}
