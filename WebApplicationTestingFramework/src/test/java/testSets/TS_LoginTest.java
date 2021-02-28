package testSets;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import testLogic.TL_Login;
import utility.ExcelUtility;
import utility.ExtentReportUtility;
import utility.ReusableFunctions;
import utility.ScreenRecorderUtility;
/*
 * Author - Sachin Jagad
 * This Class is created to check the overall framework which makes use of Reusable Methods, Log4j2 logging, 
 * Extent Reports, Screen Recording, Excel files as test data and page object model.
 * Certain constant values are fetched from "projectConfigurations.properties" file and Extent Report are configured with 
 * "spark-config.xml" file all are available under "resource" folder.
 * Check the Extent Report created for each of the test methods.
 * */
public class TS_LoginTest
{
	
	public static final Logger log = LogManager.getLogger(TS_LoginTest.class.getName());
	ReusableFunctions rf = new ReusableFunctions();
	TL_Login login = new TL_Login();
	ExcelUtility eu = new ExcelUtility("ExcelTestData.xlsx", "Sheet1");
	
	@BeforeMethod
	public void Setup()
	{
		log.info("Inside the SetUp()/@BeforeMethod method");
		rf.OpenBrowser();
	}//End of method
	
	@AfterMethod
	public void TearDown(ITestResult result)
	{
		if(result.getStatus() == ITestResult.SUCCESS)
			 System.out.println("********** Test Passed **********");
		else if(result.getStatus() == ITestResult.FAILURE)
		{
				File scrFile = ((TakesScreenshot)BaseClass.Driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + result.getName() + "-" 
							+ Arrays.toString(result.getParameters()) +  ".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
					 System.out.println("********** Test Failed **********");
			}
		else if(result.getStatus() == ITestResult.SKIP)
			 System.out.println("********** Test Skipped **********");
		log.info("Inside the TearDown()/@AfterMethod method");
		rf.CleanUp();
	}//End of method

	@Test(enabled = false)
	public void Verify_User_Is_Able_To_Login_To_Application()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		rf.NavigateToURL();
		login.LoginToApplication();
		login.LogoutFromApplication();
	}
	
	@DataProvider(name="testData")
	public Object[][] getDataFromExcel()
	{
		Object[][] data = eu.GetDataFromExcel2DArray();
		return data;
	}
	
	@Test(enabled = true, dataProvider = "testData")
	public void Verify_User_Is_Able_To_Login_To_Application_Data_From_Excel(String username, String password )
	{
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		rf.NavigateToURL();
		login.LoginToApplicationUsingExcelData(username,password);
	}
	
	@Test(enabled = false)
	public void Verify_User_Is_Able_To_Login_To_Application_Test_recording() throws Exception
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		ScreenRecorderUtility.startRecording(name);
		rf.NavigateToURL();
		login.LoginToApplication();
		login.LogoutFromApplication();
		ScreenRecorderUtility.stopRecording();
	}
}
