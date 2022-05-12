package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author ramakrishna.p
 *
 */

public class BasePage {
	
	// Class variables we can use throughout the class
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String flashElement;
	
	//Thread local class provides the thread local variables, these lv are diff from the normal variables. coz thread local variables 
	//are visible only within the thread and each thread have it's own thread local variables. and the values cannot be visible for other threads  
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to get the properties from config.properties file
	 * @return this returns prop
	 */
	
    public Properties init_properties() { // This method will interact with config properties file
		
		prop = new Properties(); //whenever we need to read the properties from a properties file use properties class
		try {
			FileInputStream fs = new FileInputStream("C:\\Users\\rperabathula\\OneDrive - Entain Group\\Documents\\Secure\\HubSpot\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties"); // fetch the file
			try {
				prop.load(fs); // properties are available in fis, to load all properties into properties class use load method
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
    
    /**
	 * This method is used to initialize the web driver on the basis of given browserName from the prop files
	 * @param browserName
	 * @return this returns driver
	 */
	
	public WebDriver init_driver(Properties prop) { // give me the list of all properties will pick one from the prop file same as list
		
		flashElement =prop.getProperty("highlight").trim(); // flash coming from java script util
		
		String browserName = prop.getProperty("browser");
		System.out.println(browserName + " is Launching");
		
		optionsManager = new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome")) {
    		WebDriverManager.chromedriver().setup();
    		//driver = new ChromeDriver(optionsManager.getChromeOptions());
    		//Set method is used to set the value to the threadLocal variable, setting the web driver value
    		tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
    	}
    	else if(browserName.equalsIgnoreCase("firefox")) {
    		WebDriverManager.firefoxdriver().setup();
    		tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
    	}
    	else if(browserName.equalsIgnoreCase("ie")) {
    		WebDriverManager.iedriver().setup();
    		tlDriver.set(new InternetExplorerDriver());
    		//driver = new InternetExplorerDriver();
    	}
    	else {
    		System.out.println(browserName  + "is not found please give valid browser");
    	}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		//getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		}
	
	//getDriver using ThreadLocal to provide sync between threads use synchronized keyword
	//This helps achieve communication between threads such that only one thread accesses the 
	//synchronized resource and other threads wait for the resource to become free.
	
	public static synchronized WebDriver getDriver() { // this method is giving the driver will replace with tldriver instead of driver
		//Get method is used to get what is the value is set by the threadLocal
		return tlDriver.get();
	}
	
	/**
	 * This method is used to take the screenshot whenever the test case got failed
	 * @return 
	 */
	
	public String getScreenshot() {
		
		TakesScreenshot ts = ((TakesScreenshot)getDriver());
		
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
				
		try {
			FileUtils.copyFile(src, destination);
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
