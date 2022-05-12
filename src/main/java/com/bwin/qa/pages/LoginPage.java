package com.bwin.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bwin.qa.base.Testbase;

public class LoginPage extends Testbase{
	
	//pagefactory-OR
	@FindBy(name="email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	WebElement submit;
	
	@FindBy(xpath ="//a[contains(text(),'Forgot your password?')]")
	WebElement forgotPasswrd;
	
	//initializing page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//action on login page
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateForgotPasswordLink() {
		return forgotPasswrd.isDisplayed();
	}
	
	public HomePage login(String UN, String PWD) {
		username.sendKeys(UN);
		password.sendKeys(PWD);
		submit.click();
		return new HomePage();
	}
	
	
	
}
