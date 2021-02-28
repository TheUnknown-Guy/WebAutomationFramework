package utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class ReusableFunctions extends BaseClass
{

	public static final Logger log = LogManager.getLogger(ReusableFunctions.class.getName());


	public WebElement ConstructWebElement(By by)
	{
		String locator  = by.toString();

		if(locator.contains("By.xpath"))
		{
			locator = locator.replace("By.xpath", "").trim();
			return Driver.findElement(By.xpath(locator));
		}
		else if (locator.contains("By.name"))
		{
			locator = locator.replace("By.name", "").trim();
			return Driver.findElement(By.name(locator));
		}
		else if (locator.contains("By.className"))
		{
			locator = locator.replace("By.className", "").trim();
			return Driver.findElement(By.className(locator));
		}
		else if (locator.contains("By.id"))
		{
			locator = locator.replace("By.id", "").trim();
			return Driver.findElement(By.id(locator));
		}
		else if (locator.contains("By.linkText"))
		{
			locator = locator.replace("By.linkText", "").trim();
			return Driver.findElement(By.linkText(locator));
		}
		else if (locator.contains("By.partialLinkText"))
		{		
			locator = locator.replace("By.partialLinkText", "").trim();
			return Driver.findElement(By.partialLinkText(locator));
		}
		else if (locator.contains("By.cssSelector"))
		{
			locator = locator.replace("By.cssSelector", "").trim();
			return Driver.findElement(By.cssSelector(locator));
		}
		else
		{	
			System.out.println("Please check if the correct element is provided.");
			log.error("Please check if the correct element is provided for ConstructWebElement");
			return null;
		}
	}//End of method

	public WebElement ConstructWebElement(By by, String... strings)
	{
		if(strings.length > 0)
		{
			String locator = by.toString();
			locator = locator.replace("By.xpath", "").trim();
			locator = locator.replace("By.name","").trim();
			locator = locator.replace("By.className","").trim();
			locator = locator.replace("By.id","").trim();
			locator = locator.replace("By.linkText","").trim();
			locator = locator.replace("By.partialLinkText","").trim();
			locator = locator.replace("By.cssSelector","").trim();
			for (int index=0;index<strings.length;index=index+2)
				locator = locator.replace(strings[index], strings[index+1]);
			try
			{
				WebElement element = Driver.findElement(By.xpath(locator));
				return element;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return null;
			}
		}
		else
			return Driver.findElement(by);
	}//End of method

	public By ReConstructWebElement(By by, String... strings)
	{
		String locator = by.toString();
		if(strings.length > 0)
		{
			for(int index=0; index<strings.length; index = index+2)
				locator = locator.replace(strings[index], strings[index+1]);
		}
		if(locator.contains("By.xpath"))
		{
			locator = locator.replace("By.xpath", "").trim();
			return By.xpath(locator);
		}
		else if (locator.contains("By.name"))
		{
			locator = locator.replace("By.name", "").trim();
			return By.name(locator);
		}
		else if (locator.contains("By.className"))
		{
			locator = locator.replace("By.className", "").trim();
			return By.className(locator);
		}
		else if (locator.contains("By.id"))
		{
			locator = locator.replace("By.id", "").trim();
			return By.id(locator);
		}
		else if (locator.contains("By.linkText"))
		{
			locator = locator.replace("By.linkText", "").trim();
			return By.linkText(locator);
		}
		else if (locator.contains("By.partialLinkText"))
		{		
			locator = locator.replace("By.partialLinkText", "").trim();
			return By.partialLinkText(locator);
		}
		else if (locator.contains("By.cssSelector"))
		{
			locator = locator.replace("By.cssSelector", "").trim();
			return By.cssSelector(locator);
		}
		else
		{	
			System.out.println("Please check if the correct element is provided.");
			log.error("Please check if the correct element is provided for ConstructWebElement");
			return null;
		}
	}//End of method

	public void Wait(int TimeInSecs) 
	{
		try 
		{
			TimeInSecs = TimeInSecs*1000; //converting milliseconds to seconds
			Thread.sleep(TimeInSecs);
			log.info("Applying some Wait on the WebDriver. Wait = " + TimeInSecs/1000 + " seconds." );
		}
		catch (InterruptedException e) 
		{
			System.out.println(e.getMessage());
			log.error("Not able to apply Wait time on the WebDriver.");
		}
	}//End of method

	public void BrowserBack()
	{
		Driver.navigate().back();
		ExtentReportUtility.ReportInfo("Navigated back to the previous page on the browser");
		log.info("Navigated back to the previous page on the browser.");
	}//End of method

	public void BrowserForward()
	{
		Driver.navigate().forward();
		ExtentReportUtility.ReportInfo("Navigated to the Next page/forward on the browser");
		log.info("Navigated to the Next page/forward on the browser");
	}//End of method

	public void CheckPageTitle(String expected)
	{
		String actual = Driver.getTitle().toLowerCase().trim();
		expected = expected.toLowerCase().trim();
		if(expected.equals(actual))
		{
			ExtentReportUtility.ReportInfo("Page title matched.<br>Expected Title is - " + expected+ "<br>Actual Title is - " + actual);
			log.info("Page title matched. Expected Title is - " + expected+ " Actual Title is - " + actual);
		}
		else
		{
			ExtentReportUtility.ReportFail("Page title NOT matched.<br>Expected Title is - " + expected + "<br>Actual Title is - " + actual);
			log.info("Page title NOT matched.Expected Title is - " + expected+ "Actual Title is - " + actual);
		}
	}//End of method

	public void RefreshPage()
	{
		Driver.navigate().refresh();
		Wait(5);
		ExtentReportUtility.ReportInfo("Page Refreshed Successfully.");
		log.info("Page Refreshed Successfully.");
	}//End of method

	public String GetCurrentURL()
	{
		String currentURL = "";
		currentURL =  Driver.getCurrentUrl();
		ExtentReportUtility.ReportInfo("Current URL of the page is - " + currentURL);
		log.info("Current URL of the page is - " + currentURL);
		return currentURL;
	}//End of method

	public String GetPageSource()
	{
		String pageSource = "";
		pageSource = Driver.getPageSource();
		log.info("Getting Webpage source code.");
		return pageSource;
	}//End of method

	public String GetElementText(By by)
	{
		String text = "";
		text = Driver.findElement(by).getText().trim();
		log.info("Getitng the Text of the element. Text value is - " + text);
		return text;
	}//End of method

	public String GetAttributeValue(By by,String attributeName)
	{
		String value = "";
		value = Driver.findElement(by).getAttribute(attributeName);
		log.info("Getting the attribute value of the element. Attribute name - " + attributeName + " and Value is - " + value);
		return value;
	}//End of method

	public void ClickOn(By by)
	{
		Driver.findElement(by).click();
		System.out.println("Clicked operation was performed on the element.");
		log.info("Clicked operation was performed on the element.");
	}//End of method

	public void ClickOn(By by, String... string)
	{
		By by1 = ReConstructWebElement(by, string);
		Driver.findElement(by1).click();
		log.info("Clicked operation was performed on the element.");
	}//End of method

	public void ClearInputField(By by)
	{
		Driver.findElement(by).clear();
		System.out.println("Input field data cleared.");
		log.info("Input field data cleared.");
	}//End of method

	public void TypeIn(By by, String keysToEnter)
	{
		Driver.findElement(by).click();
		Driver.findElement(by).clear();
		Driver.findElement(by).sendKeys(keysToEnter);
		ExtentReportUtility.ReportInfo("Input value - " + keysToEnter + " was provided to the element.");
		log.info("Input value - " + keysToEnter + " was provided to the element.");

	}//End of method

	public void TypeIn(By by, String keysToEnter, String... strings)
	{
		By by1 = ReConstructWebElement(by, strings);
		Driver.findElement(by1).click();
		Driver.findElement(by1).clear();
		Driver.findElement(by1).sendKeys(keysToEnter);
		ExtentReportUtility.ReportInfo("Input value - " + keysToEnter + " was provided to the element.");
		log.info("Input value - " + keysToEnter + " was provided to the element.");
	}//End of method

	public void WaitUntilElementFound(By by)
	{
		WebDriverWait wait = new WebDriverWait(Driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(Driver.findElement(by)));
		ExtentReportUtility.ReportInfo("WaitUntilElementFound - > 10 seconds wait period, Element found on the webpage");
		log.info("WaitUntilElementFound - > 10 seconds wait period, Element found on the webpage");
	}//End of method

	public void WaitUntilElementFound(By by, int TimeInSecs)
	{
		WebDriverWait wait = new WebDriverWait(Driver, TimeInSecs);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		ExtentReportUtility.ReportInfo("WaitUntilElementFound - "+ TimeInSecs +" seconds wait period, Element found on the webpage");
		log.info("WaitUntilElementFound - "+ TimeInSecs +" seconds wait period, Element found on the webpage");
	}//End of method

	public void WaitUntilElementFound(By by, int timeout, String... strings)
	{
		String locator = by.toString();
		locator = locator.replace("By.xpath", "").trim();
		locator = locator.replace("By.name","").trim();
		locator = locator.replace("By.className","").trim();
		locator = locator.replace("By.id","").trim();
		locator = locator.replace("By.linkText","").trim();
		locator = locator.replace("By.partialLinkText","").trim();
		locator = locator.replace("By.cssSelector","").trim();
		for (int index=0;index<strings.length;index=index+2)
		{
			locator = locator.replace(strings[index], strings[index+1]);
		}
		try
		{
			WebDriverWait wait = new WebDriverWait(Driver, timeout);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
			ExtentReportUtility.ReportInfo("WaitUntilElementFound - "+ timeout +" seconds wait period, Element found on the webpage");
			log.info("WaitUntilElementFound - "+ timeout +" seconds wait period, Element found on the webpage");
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportFail("Element not visible/found on the Webpage. Exception message - " + e.getMessage());
			log.error("Element not visible/found on the Webpage. Exception message - " + e.getMessage());

		}
	}//End of method

	public void WaitUntilPresenceOfElementLocated(By by, int TimeInSecs)
	{
		WebDriverWait wait = new WebDriverWait(Driver, TimeInSecs);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		ExtentReportUtility.ReportInfo("WaitUntilPresenceOfElementFound - "+ TimeInSecs +" seconds wait period, Element found on the webpage");
		log.info("WaitUntilPresenceOfElementFound - "+ TimeInSecs +" seconds wait period, Element found on the webpage");
	}//End of method

	public void WaitUntilPresenceOfElementLocated(By by, int TimeInSecs, String...strings)
	{
		By by1 = ReConstructWebElement(by, strings);
		WebDriverWait wait = new WebDriverWait(Driver, TimeInSecs);
		wait.until(ExpectedConditions.presenceOfElementLocated(by1));
		ExtentReportUtility.ReportInfo("WaitUntilElementFound - "+ TimeInSecs +" seconds wait period, Element found on the webpage");
		log.info("WaitUntilElementFound - "+ TimeInSecs +" seconds wait period, Element found on the webpage");
	}//End of method

	public boolean IsDisplayed(By by)
	{
		boolean bool = false;
		bool = Driver.findElement(by).isDisplayed();
		ExtentReportUtility.ReportInfo("Element is Displayed on the Webpage");
		log.info("Element is Displayed on the Webpage");
		return bool;
	}//End of method

	public boolean IsDisplayed(By by, String... strings)
	{
		boolean bool = false;
		WebElement element = ConstructWebElement(by, strings);
		element.isDisplayed();
		ExtentReportUtility.ReportInfo("Element is Displayed on the Webpage");
		bool = true;
		return bool;
	}//End of method

	public boolean IsElementPresentOnPage(By by)
	{
		try
		{
			Driver.findElement(by);
			ExtentReportUtility.ReportInfo("Element is present on the Webpage.");
			log.info("Element is present on the Webpage.");
			return true;
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportFail("Unable to find Element on the Webpage. Exception message - " + e.getMessage());
			log.error("Unable to find Element on the Webpage. Exception message - " + e.getMessage());
			return false;
		}
	}//End of method

	public boolean IsElementPresentOnPageWithConditions(By by, int TimeOutInSecs, String...strings)
	{
		By by1 = ReConstructWebElement(by, strings);
		WebDriverWait wait = new WebDriverWait(Driver, TimeOutInSecs);
		try
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(by1));
			ExtentReportUtility.ReportInfo("Presence of element is located on the Webpage.");
			log.info("Presence of element is located on the Webpage.");
			return true;
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportFail("Unable to find Presence of Element on the Webpage.<br>Exception message - " + e.getMessage());
			log.error("Unable to find Presence of Element on the Webpage. Exception message - " + e.getMessage());
			return false;
		}
	}//End of method

	public void WaitUntilElementIsClickable(By by, int TimeOutInSecs, String...strings)
	{
		By by1 = ReConstructWebElement(by, strings);
		WebDriverWait wait = new WebDriverWait(Driver, TimeOutInSecs);
		wait.until(ExpectedConditions.elementToBeClickable(by1));
		ExtentReportUtility.ReportInfo("Element is clickable on the Webpage.");
		log.info("Element is clickable on the Webpage.");
	}//End of method

	public void ClickOnUsingJavaScript(By by)
	{
		JavascriptExecutor executor = (JavascriptExecutor)Driver;
		executor.executeScript("arguments[0].click();", Driver.findElement(by));
		ExtentReportUtility.ReportInfo("Click action performed on element using Javascript click method");
		log.info("Click action performed on element using Javascript click method");
	}//End of method

	public void ClickOnUsingJavaScript(By by, String...strings)
	{
		By by1 = ReConstructWebElement(by, strings);
		JavascriptExecutor executor = (JavascriptExecutor)Driver;
		executor.executeScript("arguments[0].click();", Driver.findElement(by1));
		ExtentReportUtility.ReportInfo("Click action performed on element using Javascript click method");
		log.info("Click action performed on element using Javascript click method");
	}//End of method

	public void AppendTextAtInputField(By by, String appendingText) 
	{
		Driver.findElement(by).sendKeys(appendingText);
		ExtentReportUtility.ReportInfo("Text value - " + appendingText + " was appended to the Input field.");
		log.info("Text value - " + appendingText + " was appended to the Input field.");
	}//End of method

	public void AppendTextAtInputField(By by, String appendingText, String...strings) 
	{
		By by1 = ReConstructWebElement(by, strings);
		Driver.findElement(by1).sendKeys(appendingText);
		ExtentReportUtility.ReportInfo("Text value - " + appendingText + " was appended to the Input field.");
		log.info("Text value - " + appendingText + " was appended to the Input field.");
	}//End of method

	public int GetElementCount(By by)
	{
		int count = 0;
		try
		{
			List<WebElement> element = Driver.findElements(by);
			count = element.size();
			ExtentReportUtility.ReportInfo("Number of Elements present on the Webpage is - " + count);
			log.info("Number of Elements present on the Webpage is - " + count);
			return count;
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportFail("Cannot find the Elements present on the Webpage to get an Element Count.");
			log.error("Cannot find the Elements present on the Webpage to get an Element Count.");
			return -1;
		}
	}//End of method

	public int GetElementCount(By by,String...string)
	{
		int count = 0;
		try
		{
			By by1 = ReConstructWebElement(by, string);
			List<WebElement> element = Driver.findElements(by1);
			count = element.size();
			ExtentReportUtility.ReportInfo("Number of Elements present on the Webpage is - " + count);
			log.info("Number of Elements present on the Webpage is - " + count);
			return count;
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportFail("Cannot find the Elements present on the Webpage to get an Element Count.");
			log.error("Cannot find the Elements present on the Webpage to get an Element Count.");
			return -1;
		}
	}//End of method

	public void ScrollWebpageToTop()
	{
		JavascriptExecutor executor = (JavascriptExecutor)Driver;
		executor.executeScript("window.scrollTo(document.body.scrollHeight, 0)"); //x-axis & y-axis
		ExtentReportUtility.ReportPass("Webpage scrolled to the Top of the page.");
		log.info("Webpage scrolled to the Top of the page.");
	}//End of method

	public void ScrollWebpageToBottom()
	{
		JavascriptExecutor executor = (JavascriptExecutor)Driver;
		executor.executeScript("window.scrollTo(0, document.body.scrollHeight)"); //x-axis & y-axis
		ExtentReportUtility.ReportPass("Webpage scrolled to the Bottom of the page.");
		log.info("Webpage scrolled to the Bottom of the page.");

	}//End of method

	public void ScrollToElementView(By by)
	{
		JavascriptExecutor executor = (JavascriptExecutor)Driver;
		executor.executeScript("arguments[0].scrollIntoView(true)", Driver.findElement(by)); 
		ExtentReportUtility.ReportPass("Webpage scrolled to the particular Element View.");
		log.info("Webpage scrolled to the particular Element View.");
	}//End of method

	public void ScrollToElementView(By by, String...strings)
	{
		By by1 = ReConstructWebElement(by, strings);
		JavascriptExecutor executor = (JavascriptExecutor)Driver;
		executor.executeScript("arguments[0].scrollIntoView(true)", Driver.findElement(by1)); 
		ExtentReportUtility.ReportPass("Webpage scrolled to the particular Element View.");
		log.info("Webpage scrolled to the particular Element View.");
	}//End of method

	//NOT WORKING
	public void ScrollPageUsingPositionOfElement(By by)
	{
		try
		{
			WebElement element = Driver.findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor)Driver;
			executor.executeScript("arguments[0].scrollIntoView(true)", element); //it will scroll to element
			int y = element.getLocation().getY() - 200;
			String yaxis = "Window.scrollTo(0, %d)";
			String scroll = yaxis.replace("%d", Integer.toString(y));
			executor.executeScript(scroll);
			System.out.println("Webpage is scrolled using the Position of the Element.");
		}
		catch(Exception e)
		{
			System.out.println("Unable to Scrolled Webpage using the Position of the Element.\nException message - " + e.getMessage());
		}
	}//End of method

	public WebElement GetDisplayedElement(By by)
	{
		WebElement element1 = null;
		try
		{
			Wait(4);
			List<WebElement>  element = Driver.findElements(by);
			for(WebElement WE : element)
			{
				if(WE.isDisplayed() && WE.isEnabled())
				{
					element1 = WE;
					break;
				}
			}
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportFail("Unable to find the displayed element on the Webpage.\nException message - " + e.getMessage());
			log.error("Unable to find the displayed element on the Webpage.\nException message - " + e.getMessage());
			return element1;
		}
		return element1;
	}//End of method

	public boolean IsCheckboxChecked(By by)
	{
		boolean status = false;
		try
		{
			status = Driver.findElement(by).isSelected();
			if(status == true)
			{
				ExtentReportUtility.ReportInfo("Checkbox is selected.");
				log.info("Checkbox is selected.");
			}
			else
			{
				ExtentReportUtility.ReportFail("Checkbox is NOT selected.");
				log.info("Checkbox is NOT selected.");
			}
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportInfo("Unable to check the status of the checkbox element.\nException message - " + e.getMessage());
			log.error("Unable to check the status of the checkbox element.\nException message - " + e.getMessage());
		}
		return status;
	}//End of method

	public boolean IsRadiobuttonSelected(By by)
	{
		boolean status = false;
		try
		{
			status = Driver.findElement(by).isSelected();
			if(status == true)
			{
				ExtentReportUtility.ReportInfo("Radiobutton is selected.");
				log.info("Radiobutton is selected.");
			}
			else
			{
				ExtentReportUtility.ReportFail("Radiobutton is NOT selected.");
				log.info("Radiobutton is NOT selected.");
			}
		}
		catch(Exception e)
		{
			ExtentReportUtility.ReportFail("Unable to check the status of the Radiobutton element.\nException message - " + e.getMessage());
			log.error("Unable to check the status of the Radiobutton element.\nException message - " + e.getMessage());
		}
		return status;
	}//End of method

	public void SelectDropdownByVisibleText(By by, String visibleText)
	{
		WebElement element = Driver.findElement(by);
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
		ExtentReportUtility.ReportInfo("Value - " + visibleText + " is selected in the Dropdown using selectByVisibleText.");
		log.info("Value - " + visibleText + " is selected in the Dropdown using selectByVisibleText.");
	}//End of method

	public void SelectDropdownByValue(By by, String value)
	{

		WebElement element = Driver.findElement(by);
		Select select = new Select(element);
		select.selectByValue(value);
		ExtentReportUtility.ReportInfo("Value - " + value + " is selected in the Dropdown using selectByValue.");
		log.info("Value - " + value + " is selected in the Dropdown using selectByValue.");
	}//End of method

	public void SelectDropdownByIndex(By by, int index)
	{
		WebElement element = Driver.findElement(by);
		Select select = new Select(element);
		select.selectByIndex(index);
		ExtentReportUtility.ReportInfo("Index - " + index + " is selected in the Dropdown using selectByIndex.");
		log.info("Index - " + index + " is selected in the Dropdown using selectByIndex.");
	}//End of method

	public void SwitchToFrame(By by)
	{
		WebElement element = Driver.findElement(by);
		Driver.switchTo().frame(element);
		ExtentReportUtility.ReportInfo("Driver has switched to a Frame.");
		log.info("Driver has switched to a Frame.");
	}//End of method

	public void SwitchToFrame(int index)
	{
		Driver.switchTo().frame(index);
		ExtentReportUtility.ReportInfo("Driver has switched to a Frame by using Index postion.");
		log.info("Driver has switched to a Frame by using Index postion.");
	}//End of method

	public void SwitchToFrame(String NameOrId)
	{
		Driver.switchTo().frame(NameOrId);
		ExtentReportUtility.ReportInfo("Driver has switched to a Frame by using Name or ID of the element.");
		log.info("Driver has switched to a Frame by using Name or ID of the element.");
	}//End of method

	public void SwitchToDefaultContent()
	{
		Driver.switchTo().defaultContent();
		ExtentReportUtility.ReportInfo("Driver has switched to a Default content on the Webpage.");
		log.info("Driver has switched to a Default content on the Webpage.");
	}//End of method

	public void AcceptPopUpAlert()
	{
		Driver.switchTo().alert().accept();
		ExtentReportUtility.ReportInfo("Popup alert accepted.");
		log.info("Popup alert accepted.");
	}//End of method

	public void DismissPopUpAlert()
	{
		Driver.switchTo().alert().dismiss();
		ExtentReportUtility.ReportInfo("Popup alert dismissed.");
		log.info("Popup alert dismissed.");
	}//End of method

	public void MouseHoverAndClick(By hoverElement, By clickElement)
	{
		WebElement element = Driver.findElement(hoverElement);
		Actions action = new Actions(Driver);
		action.moveToElement(element).build().perform();
		ExtentReportUtility.ReportInfo("User has hovered the mouse on the preferred element.");
		log.info("User has hovered the mouse on the preferred element.");
		Wait(2);
		Driver.findElement(clickElement).click();
		ExtentReportUtility.ReportInfo("User has clicked on the element visible after mouse is hovered on it.");
		log.info("User has clicked on the element visible after mouse is hovered on it.");
	}//End of method

	public void TakeScreenshot()
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
		log.info("Screenshot taken with name as :- " + fileName);
	}//End of method

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
		log.info("Screenshot taken with name as :- " + fileName);
		return destination;
	}//End of method

	public String TakeScreenshotAsBase64()
	{
		String destination = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.BASE64);
		return destination;
	}//End of method

	public void DragAndDropAction(By startingPosition, By endingPosition)
	{
		WebElement start = Driver.findElement(startingPosition);
		WebElement end = Driver.findElement(endingPosition);
		Actions action = new Actions(Driver);
		action.dragAndDrop(start, end);
		ExtentReportUtility.ReportPass("Element is dragged from Starting position to Ending position.<br>Drag and Drop action performed.");
		log.info("Element is dragged from Starting position to Ending position.\nDrag and Drop action performed.");
	}//End of method
}
