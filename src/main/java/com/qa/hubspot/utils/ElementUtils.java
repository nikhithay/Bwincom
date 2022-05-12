package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtils {
	// We should have a driver to use these element util methods
	
	WebDriver driver;
	JavaScriptUtils jsUtil;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtils(driver);
	}

	public WebElement getElement(By locator) { // give me the by locator i'll give u the web element

		WebElement element = driver.findElement(locator); // based on the given locator it will create the web element
		if(Boolean.parseBoolean(BasePage.flashElement)) {
			jsUtil.falsh(element);
		}
		
		return element; // this method is for find the web element

	}

	public void doSendKeys(By locator, String value) {

		getElement(locator).sendKeys(value); // getEle method return Web element and perform action with send keys

	}
    //Sendkeys with Actions and click
	
	public void doActionSendKeys(By locator, String value) {
        
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		
	}

	public void doActionClick(By locator) {

		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public String getText(By locator) {
		return getElement(locator).getText();
	}
    // This method is used to click on link on a list of links
	public void doLinkClick(List<WebElement> linklist, String value) {

		System.out.println(linklist.size());

		for (WebElement ele : linklist) {
			String linktext = ele.getText();
			System.out.println(linktext);
			if (linktext.equals(value)) {
				ele.click();
				break;
			}

		}

	}

	// ******************** Dropdown Generic Methods ********************************

	public void slectDrodownbyIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void slectDrodownbyValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void slectDrodownbyVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
		

	}

	// This is method returns list Dropdown count size
	public int getDropDownOptionsCount(By locator) {

		Select select = new Select(getElement(locator));
		List<WebElement> OptionsList = select.getOptions();
		return OptionsList.size();

	}

	// This is method returns list of dropdown values, Using select class
	public List<String> getDropDownOptionsValues(By locator) {

		List<String> textList = new ArrayList<String>();

		WebElement DropdownList = getElement(locator);
		Select select = new Select(DropdownList);
	
		List<WebElement> OptionsList = select.getOptions();

		for (WebElement ele : OptionsList) {
			String text = ele.getText();
			textList.add(text);
		}
		return textList;

	}
	
	// **************** Using WebElements method*************************

	public List<WebElement> getElements(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		return elements;

	}

	// Using web elements method or we can implement like give me the list i'll click on the method
	// This method keep search for the value after that match it will select
	// This method will click on dropdown option on the basis of value given
	// Without select class
	public void selectValueFromDropDownWithoutSelect(By locator, String value) {

		List<WebElement> list = getElements(locator);
        System.out.println(list.size());
		for (WebElement ele : list) {
			String text = ele.getText();
			System.out.println(text);
			if (text.equalsIgnoreCase(value)) {
				ele.click();
				break;
			}
		}

	}
	// This is method returns list of dropdown values, If we don't have any select class
	
	public List<String> ListofDropDownWithoutSelect(By locator) {

		List<String> textList = new ArrayList<String>();
		
		List<WebElement> list = getElements(locator);
        System.out.println(list.size());
		for (WebElement ele : list) {
			String text = ele.getText();
			textList.add(text);
		}
		return textList;

	}
	
	// this method is used to select all or multi or single in a multi select dropdown values
	//variable arguments varargs allows the method to accept zero or multiple parameters, 
	//If we don't know how many argument we will have to pass in the method, varargs is the better approach.
	//return_type method_name(data_type... variableName){}  
	// At a time we can check mult dd's using 3 dot parameter, this String... behave as like String[]
	// It means you can pass in an array of parameters without explicitly creating the array.
	// if u want to select all the dropdowns u can put a if condition
	
	public void Single_Multi_AllSelectDropDown(By locator, String... value) { 
		
		// while calling this method we have to give the values the 1st value is 0 index is 0
																	
		List<WebElement> list = getElements(locator);
		// for single and multi
		if (!value[0].equalsIgnoreCase("all")) { 
			// why value is 0 here coz the the value we are passing only all index starts from 0
													
			// ! operator is for reverse the condition (! = false)
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				String text = list.get(i).getText();
				System.out.println(text);
				// to iterate String... value we should use one more for loop, for size of array use length
				for (int j = 0; j < value.length; j++) {
					if (text.equalsIgnoreCase(value[j])) {
						list.get(i).click();
						break;

					}

				}

			}

		}
		// For select the all values in a dropdown
		else {
			for (int all = 0; all < list.size(); all++) {
				list.get(all).click();
			}
		}

	}
	
	// **********************Waits*************************
	
	// This methods are used to wait for visibility of links and give list of all links
	public List<WebElement> visibilityOfAllElements(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public void getAllPagerLinksText(By locator, int timeOut) {
		
		List<WebElement>list =visibilityOfAllElements(locator, timeOut);
		for (WebElement ele : list) {
			String text = ele.getText();
			System.out.println(text);
		}
		}
    // for particular web element
	public WebElement waitForElementPresent(By locator, int timeUnit) {

		WebDriverWait wait = new WebDriverWait(driver,timeUnit);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		// until method returns webelement
    // Both the methods are same used to find the element
	}
	public WebElement waitForElementToBeVisible(By locator,int timeUnit) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver,timeUnit);
		return wait.until(ExpectedConditions.visibilityOf(element)); // getelement 
	}
	
    // To get the title, these 3 methods are used to get title alert pop up using explicit wait
	public String waitForTitlePresent(String titleValue, int timeUnit) {

		WebDriverWait wait = new WebDriverWait(driver,timeUnit);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();

	}
	// Imp wait is can't wait for url
	public boolean waitForUrl(String url, int timeUnit) {

		WebDriverWait wait = new WebDriverWait(driver,timeUnit);
		return wait.until(ExpectedConditions.urlToBe(url));
	}
     // using this we can accept the alert
	
	public Alert waitForAlertToBePresent(By locator, int timeOut) {

		// getElement(locator).click();
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	// This method is used only for which are clickable it could be button, image, Link
	// using this method u can click or sendkeys or displayed purpose
	public WebElement waitForElementToBeClickable(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	// Both these methods are same thing 
    // This method is only for clicking
	public void clickWhenReady(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}
	
	
	
}
