package com.qm.configurationbase;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.rvsautomation.base.AutomationBase;
import com.rvsautomation.exception.AutomationException;
import com.rvsautomation.reporting.AutomationListener;
import com.rvsautomation.utils.AutomationMail;

@Listeners({ AutomationListener.class })
public class ConfigurationBase extends AutomationBase {
	public static AppiumDriver<WebElement> driver;
	
	public ConfigurationBase() throws AutomationException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	@Parameters({ "deviceName", "OSVersion", "udid", "platformname", "browser" })
	public void beforeTest(String deviceName, String osVersion, String udid, String platformName, String browser)
			throws AutomationException {
	driver=	startAppium(deviceName, osVersion, udid, platformName, browser);
		System.out.println("Appium Launching");
	}

	@AfterSuite
	public void afterSuite() throws AutomationException {
		System.out.println("Tet execution completed. Sending Test Report PDF");
		AutomationMail.sendMail();
	}

}
