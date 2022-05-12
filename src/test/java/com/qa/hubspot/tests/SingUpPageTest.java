package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.SignupPage;
import com.qa.hubspot.utils.Constants;

public class SingUpPageTest extends BaseTest{
	
	
	//SignupPage signupPage;
	
	@BeforeClass
	
	public void signUpPageSetUp() {
		signupPage = new SignupPage(driver); // this driver is coming from base test
	
	}
	
	/*BasePage basePage;
	Properties prop;
	WebDriver driver;
	SignupPage signupPage;
	
	@BeforeTest
	public void setUp() {
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(prop);
		SignupPage signupPage = new SignupPage(driver);
			
	}*/
	
	@Test(priority=1)
	public void verifyClickOnSignUpTest() {
		signupPage.clickSignUp();
	}
	
	@Test(priority=3)
	public void verifySingUpPageTitleTest() {
		String title =signupPage.getSignUpPageTitle();
		System.out.println("The pagetitle is :"+title);
		Assert.assertEquals(title, Constants.SIGNUP_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyGoogleLinkExistsTest() {
		boolean flag = signupPage.googleLinkIsExists();
		System.out.println(flag);
	}
	
	@Test(priority=5)
	public void verifyHeaderAvaialbleTest() {
		String text =signupPage.headerIsAvaialble();
		System.out.println("Header text is : "+text);
		Assert.assertEquals(text, "Create your free account");
	}
	
	@Test(priority=4)
	public void verifyEnterValuesTest() {
		signupPage.enterValues("Ram", "Charan", "email@gmail.com");
	}
	
	@Test(priority=5)
	public void verifyGmailTest() {
		boolean flag = signupPage.isGmailDisplayed();
		System.out.println(flag);
	}
	
	/*@AfterTest
	public void teardown(){
		driver.quit();
	}
*/
}
