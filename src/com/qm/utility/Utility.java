package com.qm.utility;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;




public class Utility {

	Properties prop = new Properties();
	
	
	
	public static MobileElement waitForElementPresent(AppiumDriver<WebElement> driver,By locator){
		
		MobileElement element = null;
		
		//WebDriverWait wait = new WebDriverWait(driver,20);
		return element;
	}
}
