package testSets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import io.github.bonigarcia.wdm.WebDriverManager;
import pageFactory.PF_Home;
/*
 * Author - Sachin Jagad
 * This Class is created to check the ExtentReport functionality, it is an independent class.
 * Run this as TestNG individually to check if the report is getting created under reports folder directly.
 * */
public class TS_ExtentReportDemo 
{
	ExtentReports report;
	ExtentSparkReporter spark;
	ExtentTest test;
	File xmlFile;
	WebDriver Driver;
	
	public void SetReportInstance()
	{
		String path = System.getProperty("user.dir");
		xmlFile = new File(path + "\\resources\\spark-config.xml");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH_mm_ss");  
		LocalDateTime now = LocalDateTime.now();  
		String reportName = dtf.format(now)+".html"; 
		String destination = path+"\\reports\\" + reportName;
		spark = new ExtentSparkReporter(destination); 
		try 
		{
			spark.loadXMLConfig(xmlFile);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Author", "Sachin Jagad");
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo(reportName, destination);
		test = report.createTest("Extent Report Demo");
	}
	
	public String TakeScreenshotReturnLocation()
	{
		String destination = "";
		String path = System.getProperty("user.dir");
		String directory = path + "\\screenshots\\";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String name = dtf.format(now);
		String fileName = name + ".png";
		File sourceFile = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(directory + fileName);
		destination = directory + fileName;
		try 
		{
			FileUtils.copyFile(sourceFile, destinationFile);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return destination;
	}//End of method
	
	public String TakeScreenshotAsBase64()
	{
		String destination = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.BASE64);
		return destination;
	}//End of method
	
	@BeforeMethod
	public void SetUp()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		Driver = new ChromeDriver(options);
		SetReportInstance();
		test.log(Status.INFO, "Chrome Browser opened.");
		//maximize browser window
		Driver.manage().window().maximize();
		//delete all cookies
		Driver.manage().deleteAllCookies();
		Driver.get("https://www.saucedemo.com/");
		test.log(Status.INFO, "Navigated to URL - https://www.saucedemo.com/");
		//Implicit wait
		Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // wait further after page load is finished, some elements still be loading (e-commerce web sites)
	}
	
	@AfterMethod
	public void TearDown()
	{
		Driver.quit();
		test.log(Status.INFO, "Driver QUIT operation performed.");
		report.flush();
	}
	
	
	
	@Test 
	public void Test_ExtentReport_Demo() throws InterruptedException
	{
		String username = "standard_user" ;
		String password = "secret_sauce";
		Thread.sleep(3000);
		Driver.findElement(PF_Home.emailField).sendKeys(username);;
		Driver.findElement(PF_Home.passwordField).sendKeys(password);
		String image = TakeScreenshotReturnLocation();
		test.log(Status.PASS, "Username & Password is provided.", MediaEntityBuilder.createScreenCaptureFromPath(image).build());
		Driver.findElement(PF_Home.loginbtn).click();;
		test.log(Status.INFO,"User has LOGGED IN to the application using Username -  " + username + " and Password - " + password);
		Thread.sleep(3000);
		String destination = TakeScreenshotAsBase64();
		if(Driver.findElement(PF_Home.headerProduct).isDisplayed())
			test.log(Status.PASS, "Test case Passed.", MediaEntityBuilder.createScreenCaptureFromBase64String(destination).build());
		else
		{
			String destination1 = TakeScreenshotAsBase64();
			test.log(Status.FAIL, "Test case Failed.", MediaEntityBuilder.createScreenCaptureFromBase64String(destination1).build());
		}
	}
	
}
