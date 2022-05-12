package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtils;

public class SignupPage extends BasePage{
	
	//private WebDriver driver;
	ElementUtils elementUtil;
	//By locators
	
	By SignUpLink = By.linkText("Sign up");
	By header = By.tagName("h1");
	By button = By.xpath("//button[@type='button']");
	By firstName = By.cssSelector("#uid-firstName-5");
	By lastName = By.cssSelector("#uid-lastName-6");
	By emailaddress = By.cssSelector("#uid-email-7");
	By next = By.xpath("//span[text()='Next']//parent::button");
	By Gmail = By.xpath("//button[@type='button']");
	
	
	public SignupPage(WebDriver driver) {
		//this.driver = driver;
		elementUtil = new ElementUtils(driver);
	}
	
	//Page actions
	
	public void clickSignUp() {
		
		elementUtil.doClick(SignUpLink);
	}
	
	public String getSignUpPageTitle() {
		
		return elementUtil.waitForTitlePresent(Constants.SIGNUP_PAGE_TITLE, 5);
	}
	
	
	public boolean googleLinkIsExists() {
		
		return elementUtil.isDisplayed(button);
	}
	
	public String headerIsAvaialble() {
		return elementUtil.getText(header);
	}
	
	public void enterValues(String fn, String ln, String email) {
		
		elementUtil.doActionSendKeys(firstName, fn);
		elementUtil.doActionSendKeys(lastName, ln);
		elementUtil.doActionSendKeys(emailaddress, email);
		
	}
	
	public boolean isGmailDisplayed() {
		
		return elementUtil.isDisplayed(Gmail);
	}

}
