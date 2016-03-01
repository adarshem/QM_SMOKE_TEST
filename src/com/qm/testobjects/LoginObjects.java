package com.qm.testobjects;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rvsautomation.actions.ClickActionHelper;
import com.rvsautomation.actions.SendKeyActionHelper;
import com.rvsautomation.actions.UtilityActionHelper;
import com.rvsautomation.actions.ValidationActionHelper;
import com.rvsautomation.exception.AutomationException;

/**
 * Page objects for Login Screen.
 * 
 * @author Adarsh E M
 * @since Jan-30-2016
 * 
 */
public class LoginObjects {

	public LoginObjects() throws AutomationException {
		super();
	}

	SendKeyActionHelper sendKeysHelper = new SendKeyActionHelper();
	ValidationActionHelper va = new ValidationActionHelper();
	ClickActionHelper clickHelper = new ClickActionHelper();
	UtilityActionHelper actionHelper = new UtilityActionHelper();

	/*
	 * Login method 
	 */
	public void loginApp(AppiumDriver<WebElement> driver, String userName,
			String passWord) throws AutomationException {

		// Wait till the login screen shows successfully.
		actionHelper.sleep(15);
		actionHelper.waitForElementPresent(driver, "username_textbox");

		sendKeysHelper.sendKeys(driver, "username_textbox", userName);
		clickHelper.click(driver, By.id("com.quickmobile.app2515:id/editText"),
				1);
		sendKeysHelper.sendKeys(driver,
				By.id("com.quickmobile.app2515:id/editText"), 1, passWord);
		// Hides the keyboard before clicking Login button.
		driver.hideKeyboard();
		clickHelper.click(driver, "login_button");

		// Wait till the home page loads successfully.
		actionHelper.sleep(8);
		actionHelper.waitForElementPresent(driver, "your_event_logo");

	}

}
