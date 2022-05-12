package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtils;

public class ContactsPage extends BasePage {
	
	private WebDriver driver;
	ElementUtils elementUtil;
	
    //By locators
	By primary = By.xpath("//span[text()='Create contact']");
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTilte = By.xpath("//input[@data-field='jobtitle']");
	
	By createContact = By.xpath("(//span[contains(text(),'Create contact')])[position()=2]");
	By header = By.xpath("(//*[text()='Contacts'])[position()=2]");
	By contactsBack = By.xpath("(//*[text()='Contacts'])[position()=1]");
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(driver);
	}
	
	
	public String getContactsPageTitle() {
		return elementUtil.waitForTitlePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}
	
	public void doCreateContact(String emailID, String fn, String ln, String JT) {
		elementUtil.waitForElementToBeClickable(primary, 10).click();
		elementUtil.waitForElementPresent(email, 10).sendKeys(emailID);
		elementUtil.waitForElementPresent(firstName, 10).sendKeys(fn);
		elementUtil.waitForElementPresent(lastName, 10).sendKeys(ln);
		elementUtil.waitForElementPresent(jobTilte, 10).sendKeys(JT);
		
		elementUtil.waitForElementToBeVisible(createContact, 10).click();
		
//		elementUtil.waitForElementToBeVisible(contactsBack, 20);
//		elementUtil.doClick(contactsBack);
		
	}
	
	public String getContactsHeader() {
		
	if(elementUtil.isDisplayed(header)) {
		return elementUtil.getText(header);
	}	
	else {
		return null;
	}
	}
	
	
	
	
	
}
