package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public static final Logger log = LogManager.getLogger(ExcelUtility.class.getName());
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell xssfCell;
	public static XSSFRow xssfRow;
	public static String path = System.getProperty("user.dir");
	public static String excelFilePath = path + "\\testdata\\";
	
	public ExcelUtility(String excelFileName, String sheetName)
	{
		File file = new File(path + "\\testdata\\" + excelFileName);
		FileInputStream fis;
		try 
		{
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);		
			sheet = workbook.getSheet(sheetName);
			log.info("Excel Workbook Opened.");
			log.info("File Path of excel - " + excelFilePath + "\\"  + excelFileName);
			log.info("Excel Sheet name - " + sheetName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}//End of contructor
	
	public int GetTotalRowCount()
	{
		int rowCount = sheet.getPhysicalNumberOfRows();
		log.info("Number of Rows in the excel - " + rowCount);
		return rowCount;
	}//End of method
	
	public int GetTotalColumnCount()
	{
		int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
		log.info("Number of Columns in the excel - " + columnCount);
		return columnCount;
	}//End of method
	
	public String[][] GetDataFromExcel2DArray()
	{
	
		int rowCount = GetTotalRowCount();
		int columnCount = GetTotalColumnCount();
		DataFormatter formatter = new DataFormatter();
		int tempRow = rowCount - 1;
		String[][] data = new String[tempRow][columnCount]; //excluding header in row
		for (int i = 0; i<tempRow; i++)
		{
			for (int j=0; j<columnCount; j++)
			{
				int temp = i+1;
				Cell cell = sheet.getRow(temp).getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		log.info("Getting data from Excel in a 2D Array.");
		return data;
	}//End of method
	
	public void CloseExcelFile()
	{
		try 
		{
			workbook.close();
			log.info("Excel workbook closed.");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}//End of method
	
	public String getCellDataStringValue(int rowNum, int colNum)
	{
		xssfCell = sheet.getRow(rowNum).getCell(colNum);
		String cellData = xssfCell.getStringCellValue();
		log.info("Cell Data of String value is returned from the excel - " + cellData);
		return cellData;
	}//End of method
	
	public double getCellDataNumericValue(int rowNum, int colNum)
	{
		xssfCell = sheet.getRow(rowNum).getCell(colNum);
		double cellData = xssfCell.getNumericCellValue();
		log.info("Cell Data of Numeric value is returned from the excel - " + cellData);
		return cellData;
	}//End of method
	
	public String getCellDataDateValue(int rowNum, int colNum)
	{
		xssfCell = sheet.getRow(rowNum).getCell(colNum);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date cellDate = xssfCell.getDateCellValue();
		String dateValue = dateFormat.format(cellDate);
		log.info("Cell Data of Date value is returned from the excel - " + dateValue);
		return dateValue;
	}//End of method
	
	public void setCellDataStringValue(String value, int rowNum, int colNum)
	{
		xssfRow = sheet.getRow(rowNum);
		if(xssfRow == null)
		{
			xssfRow = sheet.createRow(rowNum);
		}
		xssfCell = xssfRow.getCell(colNum);
		if(xssfCell == null)
		{
			xssfCell = xssfRow.createCell(colNum);
			xssfCell.setCellValue(value);
		}
		else
		{
			xssfCell.setCellValue(value);
		}
		try 
		{
			FileOutputStream fis = new FileOutputStream(excelFilePath+"ExcelTestData.xlsx");
			workbook.write(fis);
			log.info("Cell Data of String type is added into the excel - " + value);
			fis.flush();
			fis.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}//End of method
	
	public void setCellDataNumericValue(int value, int rowNum, int colNum)
	{
		xssfRow = sheet.getRow(rowNum);
		if(xssfRow == null)
		{
			xssfRow = sheet.createRow(rowNum);
		}
		xssfCell = xssfRow.getCell(colNum);
		if(xssfCell == null)
		{
			xssfCell = xssfRow.createCell(colNum);
			xssfCell.setCellValue(value);
		}
		else
		{
			xssfCell.setCellValue(value);
		}
		try 
		{
			FileOutputStream fis = new FileOutputStream(excelFilePath + "ExcelTestData.xlsx");
			workbook.write(fis);
			log.info("Cell Data of Numeric type is added into the excel - " + value);
			fis.flush();
			fis.close();
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
		
	}//End of method
	
}

