package com.bwin.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bwin.qa.base.Testbase;
import com.bwin.qa.pages.HomePage;
import com.bwin.qa.pages.LoginPage;

public class LoginPageTest extends Testbase{
	
 LoginPage loginpage;
 HomePage homepage;
	
	public LoginPageTest() {
		super();//calls super class constructor
	}
	
	@BeforeMethod
	public void setup() {
		initialisation();
		loginpage = new LoginPage();
	}
	
	@Test
	public void loginPageTitleTest() {
		String title =loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "Cogmento CRM");
	}
	@Test
	public void forgotPasswordtest() {
		boolean flag= loginpage.validateForgotPasswordLink();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void loginTest() {
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
}