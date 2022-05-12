package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.SignupPage;

public class BaseTest {
	
	 public WebDriver driver;
	 public BasePage basePage;
	 public Properties prop;
	 public LoginPage loginPage;
	 public SignupPage signupPage;
	 public HomePage homePage;	
	 public ContactsPage contactsPage;
	
	 //If u want to do cross browser testing use this below
	    /*@Parameters("browser")
		@BeforeTest
		public void setUp(String browserName) {
	    	System.out.println(browserName + " is Launching");

			basePage = new BasePage();
			prop =basePage.init_properties();// this driver is coming from basePage
			prop.setProperty("browser", browserName );
			driver = basePage.init_driver(prop);
			// Based on the value on the prop file it will launch the browser properties file stored in prop
			loginPage = new LoginPage(driver);
		}
	 */
	 
	@BeforeTest
	public void setUp() {
		
		basePage = new BasePage();
		prop =basePage.init_properties();// this driver is coming from basePage
		driver = basePage.init_driver(prop);// Based on the value on the prop file it will launch the browser properties file stored in prop
		
		loginPage = new LoginPage(driver);//this driver give it to loginPage this driver has everything like launching browser
		//Constructor will be called when object got created and class level var's got initialized 
		//this driver is responsible to call loginPage methods
		//Using object name we will call all the methods available in the page class from the test class, Coz driver is available in object
		//Do not call driver API's methods like click, get text methods
		//Object will hold all the properties of a class, This object will hold the driver so we call the methods
	}
	
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}

}
