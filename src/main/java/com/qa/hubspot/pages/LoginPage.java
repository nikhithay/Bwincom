package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtils;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	ElementUtils elementUtil;
	
	// first in the every page class we have to create our by locators or web elements or Object repository
	// In this page class we only write page actions not tc's
	// Page classes usage is behavior of the each page and give it test page and tp is validated 
	
	By email = By.cssSelector("input#username");
	By password = By.cssSelector("input#password"); // failed test case
	By loginBtn = By.xpath("//button[@id='loginBtn']");
	By SignUpLink = By.xpath("//i18n-string[text()='Sign up']");
	
//  Constructor of the class
//	Constructor is used to perform initialization of an object
//	Initialization means while creating the object we should perform initialization of the object
//	Whenever the object got created all the instance variables photo copy will be given to object This means current object
	
	public LoginPage(WebDriver driver) { // const is used to initialize the object 
		elementUtil = new ElementUtils(driver);
		//driver is const value, obj will hold all the properties of element util class. e util driver
		//element util object lo driver undi so we call all the element util methods, so by using this object ref name we can call the methods
		//of elementUtil. obj will hold all the properties of a class
		//by using this object name we can call this driver, We use const to traverse the driver.
		//We can have multiple classes for different features but driver should be one and sync with all.
		//So it is preferable that we should sync driver whenever class getting called.
		//driver ni sync with classes cheyadaniki constr use chestham, class getting called.
		this.driver = driver;
	}
	//Page actions or page features or page methods
	
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
		
	}
	
	public boolean isSignUpLinkExists() {
		//return driver.findElement(SignUpLink).isDisplayed();
		//elementUtil.waitForAlertToBePresent(SignUpLink, 10);
		return elementUtil.isDisplayed(SignUpLink);
	}
	
	public HomePage doLogin(String un, String pwd) {
		
//		driver.findElement(email).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		
		elementUtil.doSendKeys(email, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new HomePage(driver);
		
//Whenever u are landing from one page to another page, Bcoz of one particular method it is the method responsibility to return 
//the next landing page class object ex do login is responsible to land on home page i.e return new HomePage()
		
	}
	
	
}
