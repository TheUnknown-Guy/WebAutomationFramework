package utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.BaseClass;

public class ExtentReportUtility extends BaseClass
{
		public static ExtentReports report;
		public static ExtentSparkReporter spark;
		public static ExtentTest test;
		
		public static void StartReport(String testCaseName)
		{
			String path = System.getProperty("user.dir");
			File xmlFile = new File(path + "\\resources\\spark-config.xml");
			//For report name
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH_mm_ss");  
			LocalDateTime now = LocalDateTime.now();
			//For report directory
			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MMM dd yyyy");  
			LocalDateTime now1 = LocalDateTime.now();
			String dir = dtf1.format(now1);
			File directory = new File(path+"\\reports\\" + dir);
			directory.mkdir();
			String browser = prop.getProperty("browser");
			String OS = prop.getProperty("OS");
			String reportName = "Extent_Report_"+ testCaseName +" (" + OS + ", " + browser + ") "+ dtf.format(now)+".html"; 
			String destination = directory +"\\" + reportName;
			spark = new ExtentSparkReporter(destination);
			report = new ExtentReports();
			try  
			{
				spark.loadXMLConfig(xmlFile);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			report.attachReporter(spark);
			report.setSystemInfo("Author", prop.getProperty("Author"));
			report.setSystemInfo("OS", prop.getProperty("OS"));
			report.setSystemInfo("Browser", prop.getProperty("browser"));
			test = report.createTest(testCaseName);
		}//End of method
		
		public static void EndReport()
		{
			report.flush();
		}//End of method
		
		public static void TakeScreenshot()
		{
			String path = System.getProperty("user.dir");
			String directory = path + "\\screenshots\\";
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy HH-mm-ss");
			LocalDateTime now = LocalDateTime.now();
			String name = dtf.format(now);
			String fileName = name + ".png";
			File sourceFile = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
			File destinationFile = new File(directory + fileName);
			try 
			{
				FileUtils.copyFile(sourceFile, destinationFile);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			System.out.println("Screenshot taken with name as :- " + fileName);
		}//End of method
		
		public static String TakeScreenshotAsBase64()
		{
			String destination = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.BASE64);
			return destination;
		}//End of method
		
		public static void ReportPass(String msg)
		{
			String screenshotPath = TakeScreenshotAsBase64();
			test.log(Status.PASS, msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		}//End of method
		
		public static void ReportFail(String msg)
		{
			String screenshotPath = TakeScreenshotAsBase64();
			test.log(Status.FAIL, msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		}//End of method
		
		public static void ReportInfo(String msg)
		{
			test.log(Status.INFO, msg);
		}//End of method
		
		public static void ReportSkip(String msg)
		{
			test.log(Status.SKIP, msg);
		}//End of method
		
		public static void ReportWarning(String msg)
		{
			String screenshotPath = TakeScreenshotAsBase64();
			test.log(Status.WARNING, msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
		}//End of method
		
}
