package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtils;

public class HomePage extends BasePage{
	
	private WebDriver driver;
	ElementUtils elementUtil;
	
	//By locators
	By header = By.tagName("h1");
	By accountButton = By.cssSelector("#account-menu-container");
	By accountName = By.xpath("//div[text()='Ram Perabathula']");
	By startImport = By.xpath("//a[@type='button']/child::i18n-string[text()='Start import']");
	By importBtn = By.xpath("//span[contains(text(),'Import your data')]"); 
	By settings = By.cssSelector("#navSetting");
	
	By primaryContacts = By.xpath("(//a[@id='nav-primary-contacts-branch'])[position()=1]");
	By secondaryContacts = By.xpath("//ul//li//div[contains(text(),'Contacts')]");
	
	By companies = By.xpath("(//a/child::div[contains(text(),'Companies')])[position()=1]");
	
	
	public HomePage(WebDriver driver) {
		
		elementUtil = new ElementUtils(driver);
	    this.driver = driver; // plz remove driver becoz we dont't use driver in this class
		//using element util reference we can use all the methods in the element utils class
		//As a part of selenium , we need to synchronize object of WebDriver .ex. - driver
       //We can have multiple classes for different features but driver should be one and sync with all.
		//So it is preferable that we should sync driver whenever class getting called.
	}
	

	public String getHomePageTitle() {
		//return driver.getTitle();
		return elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 5);
	}
	/**
	 * This method says that if header is available return text, If not return null 
	 * @return
	 */
	public String getHeaderValue() {
		if(elementUtil.isDisplayed(header)) {
			return elementUtil.getText(header);
		}
		else {
			return null;
		}

	}
	
	public String getAccountName() {
		
		
		elementUtil.getElement(accountButton).click();
		if(elementUtil.getElement(accountName).isDisplayed()) {
			return elementUtil.getText(accountName);
		}
		else {
			return null;
		}
	}
	
	public void doClickOnStartImport() {
		
		if(elementUtil.getElement(startImport).isDisplayed()) {
			elementUtil.getElement(startImport).click();
		}
	}
	
	public void doClickOnImport() {
		
		elementUtil.getElement(importBtn).click();
	}
	
	public boolean isSettingsExists() {
		
		return elementUtil.getElement(settings).isDisplayed();
	}
	
	public ContactsPage goToContactsPage() {
		elementUtil.waitForElementPresent(primaryContacts, 10);
		elementUtil.doClick(primaryContacts);
		elementUtil.waitForElementPresent(secondaryContacts, 10);
		elementUtil.doClick(secondaryContacts);
		return new ContactsPage(driver);
	}
	
	public void goToCompaniesPage() {
		
		elementUtil.waitForElementPresent(primaryContacts, 10);
		elementUtil.doClick(primaryContacts);
		elementUtil.waitForElementToBeClickable(companies, 10).click();
		
		
		
	}
	
}