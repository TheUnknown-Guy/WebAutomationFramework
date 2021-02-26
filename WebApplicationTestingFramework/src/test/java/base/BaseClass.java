package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExtentReportUtility;

public class BaseClass 
{
	public static final Logger log = LogManager.getLogger(BaseClass.class.getName());
	public static WebDriver Driver;
	public static Properties prop;
	public static FileInputStream fis;
	
	public void OpenBrowser()
	{
		prop = new Properties();
		String userDirectory = System.getProperty("user.dir");
		//passing the config file as Input
		try 
		{
			fis = new FileInputStream(userDirectory+"\\resources\\projectConfiguration.properties");
			log.info("Data read from the projectConfiguration.properties file.");
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Configuration Properties field not found.\n" +e.getMessage());
			log.error("Configuration Properties file not found."+ e.getStackTrace());
		}
		//loading the properties file using Properties class object with FileInputStream object having the file path
		try 
		{
			prop.load(fis);
		} 
		catch (IOException e) 
		{
			System.out.println("IOException error occured while loading the porperties file.\n" +e.getMessage());
			log.error("IOException error occured while loading the porperties file."+ e.getStackTrace());
		}
		//getting values from the properties file
		String browser = prop.getProperty("browser");
		browser = browser.toLowerCase();
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			Driver = new ChromeDriver(options);
			log.info("Chrome Browser is opened.");
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			Driver = new FirefoxDriver();
			log.info("Firefox Browser is opened.");
		}
		else if(browser.equals("ie"))
		{
			WebDriverManager.iedriver().setup();
			Driver = new InternetExplorerDriver();
			log.info("Internet Explorer Browser is opened.");
		}
		else
		{
			System.out.println("Please check if the correct browser name is provided in properties file. We support Chrome, Firefox & IE browsers only.");
			log.error("Please check if the correct browser name is provided in properties file. We support Chrome, Firefox & IE browsers only.");
		}
		//maximize browser window
		Driver.manage().window().maximize();
		//delete all cookies
		Driver.manage().deleteAllCookies();
	}//End of method

	public void NavigateToURL() 
	{
		String browser = prop.getProperty("browser");
		ExtentReportUtility.ReportInfo(browser.toUpperCase() + " Browser is opened.");
		String url = prop.getProperty("URL");
		Driver.get(url);
		//Implicit wait
		Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // wait further after page load is finished, some elements still be loading (e-commerce web sites)
		//Driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // wait unit page is loaded
		log.info("Driver navigated to the URL -> " + url);
		ExtentReportUtility.ReportPass("Driver navigated to the URL -> " + url);
	}//End of method

	public void CleanUp()
	{
		Driver.quit();
		log.info("Driver QUIT operation performed.");
		ExtentReportUtility.ReportInfo("Driver QUIT operation performed.");
		ExtentReportUtility.EndReport();
	}

	public String GetCurrentTestMethodName()
	{
		// getStackTrace() method return 
		// current method name at 0th index 
		String nameofCurrMethod = new Throwable() 
				.getStackTrace()[1] 
						.getMethodName(); 

		System.out.println("Name of current @Test method: "
				+ nameofCurrMethod); 
		return nameofCurrMethod;
	}//End of method

	
	
	
}
