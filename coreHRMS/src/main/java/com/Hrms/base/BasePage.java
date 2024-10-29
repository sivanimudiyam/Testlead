package com.Hrms.base;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.Hrms.utils.CommonUtils;


public class BasePage {
	WebDriver driver;
	
	@BeforeTest
	public void setup() throws Exception {
		
	CommonUtils.initialize();
	CommonUtils.launchBrowserAndNavigateToApp();
		
	}
	
	@AfterTest
	public void close() throws Exception {
		
	//driver.quit();
		
	}
	
}
