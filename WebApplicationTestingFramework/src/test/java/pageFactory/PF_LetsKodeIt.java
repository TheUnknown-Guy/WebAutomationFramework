package pageFactory;

import org.openqa.selenium.By;

public class PF_LetsKodeIt 
{
	//practice link
	public static By lnkPractice = By.xpath("//a[contains(text(),'Practice')]");
	//radiobutton
	public static By radiobtnBMW = By.id("bmwradio");
	public static By radiobtnBenz = By.id("benzradio");
	public static By radiobtnHonda = By.id("hondaradio");
	//checkbox
	public static By chkboxBMW = By.id("bmwcheck");
	public static By chkboxBenz = By.id("benzcheck");
	public static By chkboxHonda = By.id("hondacheck");
	//select class dropdown
	public static By drpdwnSelectClass = By.id("carselect");
	//Multi select options
	public static By multiSelect = By.xpath("//select[@name='multiple-select-example']//option");
	//switch window button
	public static By btnOpenWindow = By.id("openwindow");
	//child window search box 
	public static By inputSearchbox = By.id("search-courses");
	//switch tab button
	public static By btnSwitchTab = By.xpath("//a[text()='Open Tab']");
	//Alert input box 
	public static By inputAlertField = By.name("enter-name");
	public static By btnAlert = By.xpath("//input[@value='Alert' and @type='submit']");
	public static By btnConfirm = By.xpath("//input[@value='Confirm' and @type='submit']");
	//Field input box for Hide/Show
	public static By inputHideShow = By.name("show-hide");
	//Hide button
	public static By btnHide = By.id("hide-textbox");
	//Show button
	public static By btnShow = By.id("show-textbox");
	//Frame legend header
	public static By frameHeader = By.xpath("//legend[text()='iFrame Example']");
	//table 
	public static By tableHeader = By.xpath("//table[@name='courses']//th");
	public static By tableRow = By.xpath("//table[@name='courses']//tr");
	public static By tableData = By.xpath("//table[@name='courses']//td");
	//mouse hover
	public static By btnMouseHover = By.id("mousehover");
	public static By txtTop = By.xpath("//a[text()='Top']");
	public static By txtReload = By.xpath("//a[text()='Reload']");
	//IFrame
	public static By iFrameID = By.xpath("//iframe[@id='courses-iframe']");
	public static By searchBox_Frame = By.xpath("//input[@id='search-courses']");
	
}
