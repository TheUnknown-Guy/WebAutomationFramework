package testSets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageFactory.PF_LetsKodeIt;
import testLogic.TL_Login;
import utility.ExtentReportUtility;
import utility.ReusableFunctions;
/*
 * Author - Sachin Jagad
 * This Class is created to check the ReusableFunctions class methods.
 * Run this as TestNG to check if the all the methods are working as expected.
 * Check the Reports generated for each of the Test methods using Extent Reports.
 * */
public class TS_TestingMethods extends ReusableFunctions
{
	
	private String baseURL = "https://letskodeit.teachable.com/";
	ReusableFunctions rf = new ReusableFunctions();
	TL_Login login = new TL_Login();
	
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
			 System.out.println("********** Test Failed **********");
		else if(result.getStatus() == ITestResult.SKIP)
			 System.out.println("********** Test Skipped **********");
		log.info("Inside the TearDown()/@AfterMethod method");
		rf.CleanUp();
	}//End of method
	/*
	@BeforeMethod
	public void SetUP()
	{
		System.out.println("Inside the setUp() method");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		Driver = new ChromeDriver(options);
		Driver.manage().window().maximize();
		Driver.get(baseURL);
		Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Driver.manage().deleteAllCookies();
		System.out.println("Launched Chrome Driver & opened letskodeit.teachable.com");
	}//End of BeforeMethod
	
	@AfterMethod
	public void TearDown()
	{
		Wait(2);
		Driver.quit();
	}//End of AfterMethod
	*/
	@Test
	public void RaioButtonAndCheckbox()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice); Wait(3);
		WaitUntilElementFound(PF_LetsKodeIt.radiobtnBenz);
		ClickOn(PF_LetsKodeIt.radiobtnBenz);
		Wait(2);
		ClickOn(PF_LetsKodeIt.radiobtnBMW);
		Wait(2);
		if(IsRadiobuttonSelected(PF_LetsKodeIt.radiobtnBMW))
			ClickOn(PF_LetsKodeIt.radiobtnHonda);
		Wait(3);
		ClickOn(PF_LetsKodeIt.chkboxBenz);
		ClickOn(PF_LetsKodeIt.chkboxBMW);
		ClickOn(PF_LetsKodeIt.chkboxHonda);
		if(IsCheckboxChecked(PF_LetsKodeIt.chkboxBenz) && IsCheckboxChecked(PF_LetsKodeIt.chkboxBMW)
				&& IsCheckboxChecked(PF_LetsKodeIt.chkboxHonda))
			System.out.println("All the checkboxes are selected.");
		Wait(4);
	}//End of Test method
	
	@Test
	public void SelectClassDropdown()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		Wait(2);
		SelectDropdownByVisibleText(PF_LetsKodeIt.drpdwnSelectClass, "Honda");
		Wait(2);
		SelectDropdownByValue(PF_LetsKodeIt.drpdwnSelectClass, "bmw");
		Wait(2);
		SelectDropdownByIndex(PF_LetsKodeIt.drpdwnSelectClass, 1);
	}//End of Test method

	@Test
	public void MultiSelectOperation()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		Wait(2);
		List<WebElement> element = Driver.findElements(PF_LetsKodeIt.multiSelect);
		Actions action = new Actions(Driver);
		for(WebElement WE : element)
		{
			action.keyDown(Keys.CONTROL).build().perform();
			WE.click();
		}
		System.out.println("All the values are selected under Multiselect picklist.");
	}//End of Test method
	
	@Test
	public void SwitchWindowOperation()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		Wait(2);
		String parentWindow = Driver.getWindowHandle();
		System.out.println("Parent Window handle Id - " + parentWindow);
		ClickOn(PF_LetsKodeIt.btnOpenWindow);
		Set<String> allWindowHandles = Driver.getWindowHandles();
		for (String handle : allWindowHandles)
			System.out.println("Available Handle Id - " + handle);
		for (String childHandle : allWindowHandles)
		{
			if(childHandle.equals(parentWindow))
				continue;
			else
			{
				Driver.switchTo().window(childHandle);
				System.out.println("Driver is Swiched to the Child Window Handle");
			}
		}
		TypeIn(PF_LetsKodeIt.inputSearchbox, "Python");
		Wait(5);
		Driver.close();
		System.out.println("Driver closed the Child Window handle");
		Driver.switchTo().window(parentWindow);
		System.out.println("Driver is Swiched to the Parent Window Handle");
		Wait(2);
		ClickOn(PF_LetsKodeIt.chkboxBenz);
		ClickOn(PF_LetsKodeIt.chkboxBMW);
		ClickOn(PF_LetsKodeIt.chkboxHonda);
		Wait(4);
	}//End of Test method
	
	@Test
	public void SwitchTabsInSamewindow()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		Wait(2);
		ClickOn(PF_LetsKodeIt.btnSwitchTab);
		ArrayList<String> tabs = new ArrayList<String>(Driver.getWindowHandles());
		System.out.println("Number of tabs available on the Window - " + tabs.size());
		Wait(4);
		System.out.println("Switching to newly open tab on Window.");
		Driver.switchTo().window(tabs.get(1));
		System.out.println("Driver switched to newly open tab on Window.");
		TypeIn(PF_LetsKodeIt.inputSearchbox, "Python");
		Wait(5);
		System.out.println("Switching back to Old tab on Window.");
		Driver.switchTo().window(tabs.get(0));
		System.out.println("Driver switched to Old tab on Window.");
		ClickOn(PF_LetsKodeIt.chkboxBenz);
		ClickOn(PF_LetsKodeIt.chkboxBMW);
		ClickOn(PF_LetsKodeIt.chkboxHonda);
		Wait(4);
	}//End of Test method
	
	@Test
	public void SwitchToAlertExample()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		Wait(2);
		ScrollToElementView(PF_LetsKodeIt.chkboxHonda);
		Wait(2);
		TypeIn(PF_LetsKodeIt.inputAlertField, "Automation");
		ClickOn(PF_LetsKodeIt.btnAlert);
		Wait(2);
		AcceptPopUpAlert();
		Wait(2);
		TypeIn(PF_LetsKodeIt.inputAlertField, "Automation");
		ClickOn(PF_LetsKodeIt.btnConfirm);
		Wait(2);
		AcceptPopUpAlert();
		Wait(2);
		TypeIn(PF_LetsKodeIt.inputAlertField, "Automation");
		ClickOn(PF_LetsKodeIt.btnConfirm);
		Wait(2);
		DismissPopUpAlert();
		Wait(2);
	}//End of Test method
	
	@Test
	public void BrowserForwardBackwardRefreshPage()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		Wait(8 );
		BrowserBack();
		Wait(8);
		BrowserForward();
		Wait(8);
		RefreshPage();
		Wait(8);
	}//End of Test method
	
	@Test
	public void GetURLPageSourcePageTitle()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		GetCurrentURL();
		String page = GetPageSource();
		System.out.println("Page Source Data - " + page);
		String title = Driver.getTitle(); 
		CheckPageTitle(title);
	}//End of Test method

	@Test
	public void ElementTextAttributeValue()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice);
		Wait(3);
		String text = GetElementText(PF_LetsKodeIt.btnOpenWindow);
		System.out.println("Text value is - " + text);
		String type = GetAttributeValue(PF_LetsKodeIt.radiobtnHonda, "type");
		String value =  GetAttributeValue(PF_LetsKodeIt.radiobtnHonda, "value");
		System.out.println("Attribute type is - " + type + "\nAttribute value is - " + value);
	}//End of Test method
	
	@Test
	public void TypeClearAppend()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.chkboxHonda);
		TypeIn(PF_LetsKodeIt.inputAlertField, "Automation"); Wait(3);
		ClearInputField(PF_LetsKodeIt.inputAlertField); Wait(3);
		TypeIn(PF_LetsKodeIt.inputAlertField, "Automation"); Wait(3);
		AppendTextAtInputField(PF_LetsKodeIt.inputAlertField, "TextAppended"); Wait(3);
		ClearInputField(PF_LetsKodeIt.inputAlertField);
		
	}//End of Test method
	
	@Test
	public void ElementDisplayedAndPresent()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.inputAlertField);
		TypeIn(PF_LetsKodeIt.inputHideShow, "Hide/Show test");
		if(IsDisplayed(PF_LetsKodeIt.inputHideShow))
			ClickOn(PF_LetsKodeIt.btnHide);
		Wait(3);
		if(IsElementPresentOnPage(PF_LetsKodeIt.btnShow))
			ClickOn(PF_LetsKodeIt.btnShow);
		Wait(3);
		WaitUntilElementFound(PF_LetsKodeIt.inputHideShow);
		System.out.println("Element is displayed and present on the webpage.");
	}//End of Test method
	
	@Test
	public void ScrollCheck()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice); Wait(3);
		ScrollWebpageToBottom(); Wait(3);
		ScrollWebpageToTop(); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.inputAlertField); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.frameHeader); Wait(3);
	}//End of Test method
	
	@Test
	public void JavascriptClickElementCount()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.chkboxHonda); Wait(3);
		ClickOnUsingJavaScript(PF_LetsKodeIt.btnHide); Wait(3);
		ClickOnUsingJavaScript(PF_LetsKodeIt.btnShow); Wait(3);
		if(IsDisplayed(PF_LetsKodeIt.tableHeader))
			System.out.println("Table is present on the webpage.");
		int header = GetElementCount(PF_LetsKodeIt.tableHeader);
		int row = GetElementCount(PF_LetsKodeIt.tableRow);
		int data = GetElementCount(PF_LetsKodeIt.tableData);
		System.out.println("Table has " + header + " headers on the webpage.");
		System.out.println("Table has " + row + " row on the webpage.");
		System.out.println("Table has " + data + " data on the webpage.");
	}//End of Test method
	
	@Test
	public void MouseHoverClickActions()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.btnHide); Wait(3);
		MouseHoverAndClick(PF_LetsKodeIt.btnMouseHover, PF_LetsKodeIt.txtTop); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.btnHide); Wait(3);
		MouseHoverAndClick(PF_LetsKodeIt.btnMouseHover, PF_LetsKodeIt.txtReload); Wait(3);
	}//End of Test method
	
	@Test
	public void SwichingToFrame()
	{
		log.info("Inside the @Test method");
		String name = rf.GetCurrentTestMethodName();
		ExtentReportUtility.StartReport(name);
		Driver.get(baseURL);
		Wait(3);
		ClickOn(PF_LetsKodeIt.lnkPractice); Wait(3);
		ScrollToElementView(PF_LetsKodeIt.frameHeader); Wait(3);
		SwitchToFrame(PF_LetsKodeIt.iFrameID);
		TypeIn(PF_LetsKodeIt.searchBox_Frame, "Python");
		ScrollWebpageToBottom(); Wait(3);
		ScrollWebpageToTop(); Wait(3);
		SwitchToDefaultContent();
		ScrollWebpageToTop(); Wait(3);
		ClickOn(PF_LetsKodeIt.chkboxHonda);
	}//End of Test method
	
}
