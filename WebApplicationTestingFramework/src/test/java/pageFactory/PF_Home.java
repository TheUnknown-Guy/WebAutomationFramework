package pageFactory;

import org.openqa.selenium.By;

public class PF_Home 
{
	
	//Email input field
	public static By emailField = By.id("user-name");
	
	//Password input field
	public static By passwordField = By.id("password");
	
	//login button
	public static By loginbtn = By.xpath("//input[@value='LOGIN' and @type='submit']");
	
	//product header
	public static By headerProduct = By.xpath("//div[text()='Products']");

	//Hamburger icon
	public static By hamburgerIcon = By.xpath("//button[text()='Open Menu']");
	
	//logout text
	public static By txtLogOut = By.xpath("//a[text()='Logout']");
	
	//SwagLabs logo header on LOGIN page
	public static By headerSwagLabs = By.xpath("//div[@class='login_logo']");
	
	//dynamic xpath
	public static By dynamicXpath_ByLabel = By.xpath("//div[text()='<Label>']");
	
	//back button
	public static By backButton  = By.xpath("//button[text()='<- Back']");
}
