package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.Constants;

public class LoginPageTest extends BaseTest{	
	
	//LoginPage loginPage;
	
	// LoginPage Test is not getting connected to base page u have to create local driver reference
	
	
		
	@Test(priority=2)
	public void verifyLoginPageTitleTest() {
		
		String title =loginPage.getLoginPageTitle();
		System.out.println("The loginPage title is: "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		System.out.println("The title is: "+title);
	}
	
	@Test(priority=1)
	public void VerifySignUpLinkExists() {
		boolean flag =loginPage.isSignUpLinkExists();
		System.out.println(flag);
		//assertTrue(loginPage.isSignUpLinkExists());
	}
	
	@Test(priority=3)
	public void VerifydoLoginTest() {
	
		loginPage.doLogin(prop.getProperty("email"), prop.getProperty("password"));
				
	}
	
	}
