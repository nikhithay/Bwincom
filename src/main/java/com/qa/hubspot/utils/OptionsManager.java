package com.qa.hubspot.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	ChromeOptions co;
	Properties prop;
	FirefoxOptions fo;
	//Whenever you are reading from any properties from the config file it should in written in the form of string 
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) { //here we are converting value from string to boolean, by using if 
		co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		return co;
		}
	//In this we are creating different properties (parameter) so that's why we created diff if conditions
	//hare we are checking two conditions so that is the reason we are not creating else if blocks, If u created if else blocks then 
	// if the condition is true it will not come to the next block

    public FirefoxOptions getFireFoxOptions() {
		
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) { //here we are converting value from string to boolean, by using if 
		fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}
		return fo;
}

}





