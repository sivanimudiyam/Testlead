package com.Hrms.utils;

import java.io.FileInputStream;

import java.io.IOException;

import java.time.Duration;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;



public class CommonUtils{
	
	public static WebDriverWait wait;
	public static Properties properties;
	public static WebDriver driver;
	public static final String configFilePath = "\\src\\test\\resources\\config.properties";


	public static void initialize() throws Exception{
		
			properties = readPropertiesFile(System.getProperty("user.dir") + configFilePath);
			
			
	}
	
	//
	
	public static void launchBrowserAndNavigateToApp() {

		try {
			String browserName = properties.getProperty("browser");
			if(browserName.equalsIgnoreCase("chrome")) {
				
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}else if(browserName.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}else if(browserName.equalsIgnoreCase("edge")) {
				
				driver = new EdgeDriver();
				driver.manage().window().maximize();
			}
				
			driver.get(properties.getProperty("url"));
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// ---------------------- Selenium Implementations -------------------------
	public static WebElement findElement(By by){
		WebElement element = null;
		try {
			element = driver.findElement(by);
			return element;
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return element;
	}
	
	public static void switchToFrame(By by){
		WebElement iframe = null;
		try {
			//iframe = driver.findElement(By.cssSelector("#modal>iframe"));
			
			iframe = driver.findElement(by);
			driver.switchTo().frame(iframe);
			
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}

	public static List<WebElement> findElements(By by){
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(by);
			return elements;
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return elements;
	}
	
	
	
	
	public static void clickElement(By by){
		try {
			
			findElement(by).click();
		
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	public static void enterValue(By by, String value, Boolean isClear){
		try {
		
			if(isClear)
				clearWholeText(by);
			findElement(by).sendKeys(value);
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	public static void clearWholeText(By by) throws Exception {
		try {
			findElement(by).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	public static void selectDropDownValue(By DropDown, String option) {
		try {
			
			clickElement(DropDown);
			
			clickElement(getCommonLocatorUsingText(option));
		}catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	
	
	public static void switchToWindow() {
		try {
			String parentWindow = driver.getWindowHandle();
			
			for (String window : driver.getWindowHandles()) {
			    if(!parentWindow.contentEquals(window)) {
			        driver.switchTo().window(window);
			        break;
			    }
			}
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	
	
	public static By getCommonLocatorUsingText(String value) throws Exception {
		By by = null;
		try {
			
			by = By.xpath("//*[text()='" + value + "']");
			
		}catch (Exception e) {
			
			
			TestNGUtility.assertFail(e.getMessage());
		}
		return by;
	}
	
	public static String getWindowTitle() {
		String title = null ;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return title;
	}
	
	public static String getCurrentPageUrl() {
		String url= null;
		try {
			url = driver.getCurrentUrl();
		}catch(Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return url;
	}
	
	public static String getElementText(By by) {
		String text = null ;
		try {
			text = findElement(by).getText();
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return text;
	}
	
	public static String getElementAttributeValue(By by, String attributeName) {
		String attributeValue = null ;
		try {
			attributeValue = findElement(by).getAttribute(attributeName);
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return attributeValue;
	}
	
	public static void hardWait(int millis) {
		try {
			Thread.sleep(millis * 1000);
		}catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	public static Boolean isElementDisplayed(By by) {
		Boolean result = false;
		try {
		
			result = findElement(by).isDisplayed();
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return result;
	}
	
	public static Boolean isElementSelected(By by) {
		Boolean result = false;
		try {
			result = findElement(by).isSelected();
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return result;
	}
	
	
	
	
	public static void scrollToElement(By by) {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", findElement(by));
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	

	public static boolean waitForElementToBeVisible(By by) {
		boolean flag = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(properties.getProperty("loadTime"))));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			flag = true;
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		return flag;
	}

	
	public static void dragAndDrop(By byDrag, By byDrop) {
		try {
			(new Actions(driver)).dragAndDrop(findElement(byDrag), findElement(byDrop)).build().perform();
		}catch(Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	public static void moveToElement(By by) {
		try {
			if(waitForElementToBeVisible(by)) {
				new Actions(driver).moveToElement(findElement(by)).perform();
			}
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
	}
	
	
	
	

	// -------------------- Properties Implementations ----------------------
	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			properties = new Properties();
			properties.load(fis);
		} catch (Exception e) {
			TestNGUtility.assertFail(e.getMessage());
		}
		
		return properties;
	}
	
	
	
}
