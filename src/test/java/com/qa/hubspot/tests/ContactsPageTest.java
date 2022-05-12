package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtils;

public class ContactsPageTest extends BaseTest {
	
	/*BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;*/
	ContactsPage contactsPage;
	
	//@BeforeTest
	
	@BeforeClass
	public void ContactssetUp() {
		
		/*basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);*/
		
		homePage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	   
	}
	
	@Test(priority=1)
	public void contactsPageTitleTest() {
		
		String title =contactsPage.getContactsPageTitle();
		System.out.println("The ContactsPage Title is: "+title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	@DataProvider()
	public Object[][] getContactsTestData(){
		Object data[][] = ExcelUtils.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	/*@Test(priority=3)
	public void createContactTest() {
		contactsPage.doCreateContact("Ram@gmail.com", "Ram", "Charan", "QA");
		
	}*/
	
	@Test(priority=3,dataProvider="getContactsTestData")
	public void createContactTest(String emailID, String fn, String ln, String jobTitle) {
		contactsPage.doCreateContact(emailID,fn,ln,jobTitle);
		
	}
	
	@Test(priority=2)
	public void headerTest() {
		String header = contactsPage.getContactsHeader();
		System.out.println("The contacts header is: "+header);
		Assert.assertTrue(header.contains(Constants.CONTACTS_PAGE_HEADER));
	}
	
		
	/*@AfterTest
	public void teardown() {
		driver.quit();
	}
*/
}
