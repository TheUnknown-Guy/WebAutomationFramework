package testLogic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageFactory.PF_Home;
import utility.ExtentReportUtility;
import utility.ReusableFunctions;

public class TL_Login extends ReusableFunctions
{
	public static final Logger log = LogManager.getLogger(TL_Login.class.getName());
	public void LoginToApplication()
	{
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		Wait(2);
		WaitUntilElementFound(PF_Home.emailField, 10);
		TypeIn(PF_Home.emailField, username);
		TypeIn(PF_Home.passwordField, password);
		ClickOn(PF_Home.loginbtn);
		Wait(4);
		if(IsDisplayed(PF_Home.headerProduct))
		{
			ExtentReportUtility.ReportPass("User has successfully logged into the application.<br>"
					+ "User has LOGGED IN to the application using Username -  " + username + " and Password - " + password);
			log.info("User has LOGGED IN to the application using Username -  " + username + " and Password - " + password);
		}
		else
		{
			ExtentReportUtility.ReportFail("User has NOT successfully logged into the application.<br>"
					+ "User has NOT LOGGED IN to the application using Username -  " + username + " and Password - " + password);
			log.error("User NOT LOGGED IN to the application using Username -  " + username + " and Password - " + password);
		}
	}//End of method
	
	public void LoginToApplicationUsingExcelData(String username, String password)
	{
		Wait(2);
		WaitUntilElementFound(PF_Home.emailField, 10);
		TypeIn(PF_Home.emailField, username);
		TypeIn(PF_Home.passwordField, password);
		ClickOn(PF_Home.loginbtn);
		Wait(4);
		if(IsDisplayed(PF_Home.headerProduct))
		{
			ExtentReportUtility.ReportPass("User has successfully logged into the application.<br>"
					+ "User has LOGGED IN to the application using Username -  " + username + " and Password - " + password);
			log.info("User has LOGGED IN to the application using Username -  " + username + " and Password - " + password);
		}
		else
		{
			ExtentReportUtility.ReportFail("User has NOT successfully logged into the application.<br>"
					+ "User has NOT LOGGED IN to the application using Username -  " + username + " and Password - " + password);
			log.error("User NOT LOGGED IN to the application using Username -  " + username + " and Password - " + password);
		}
	}//End of method
	
	public void LogoutFromApplication()
	{
		Wait(2);
		MouseHoverAndClick(PF_Home.hamburgerIcon, PF_Home.hamburgerIcon);
		ClickOn(PF_Home.txtLogOut);
		Wait(2);
		if(IsDisplayed(PF_Home.headerSwagLabs))
		{
			ExtentReportUtility.ReportPass("User has successfully logged out of the application.");
			log.info("User has successfully logged out of the application.");
		}
		else
		{
			ExtentReportUtility.ReportFail("User has NOT successfully logged out of the application.<br> Swag Labs header on the home page is not displayed.");
			log.error("User has NOT successfully logged out of the application.  Swag Labs header on the home page is not displayed.");
		}
	}//End of method
}
