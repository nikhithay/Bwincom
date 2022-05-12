package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;

public class HomePageTest extends BaseTest{
	
	//HomePage homePage;
	
	
//@BeforeClass	The @BeforeClass annotated method will be executed before the first method of the current class is invoked.
	
	@BeforeClass
	public void homePageSetUp() {
		
		homePage = loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
		
	}
	
	@Test(priority=2)
	public void verifyHomePageTitleTest() {
		
		String title = homePage.getHomePageTitle();
		System.out.println("The HomePage Title is: "+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE); // we stored in constants page
	}
	@Test(priority=1)
	public void verifyHeaderValueTest() {
		String header = homePage.getHeaderValue();
		System.out.println("The header value is: "+header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyGetAccountNameTest() {
		String text = homePage.getAccountName();
		System.out.println("The Account name is: "+text);
		Assert.assertEquals(text, prop.getProperty("AccountName").trim());
		// Better to use trim whenever we are calling this becoz unfortunately we may give space also
		//In the properties file the space is not visible so that is the reason we should use trim or remove spaces
		
	}
	
	@Test(priority=4)
	public void verifyClickOnStartImportTest() {
		homePage.doClickOnStartImport();
	}
	
	/*@Test(priority=6)
	public void verifyClickOnImport() {
		homePage.doClickOnImport();
	}*/
	
	@Test(priority=5)
	public void isSettingsExistTest() {
		boolean settings = homePage.isSettingsExists();
		System.out.println(settings);
	}
	
	
	

}
