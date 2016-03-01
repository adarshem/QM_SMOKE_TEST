package com.qm.testobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rvsautomation.actions.UtilityActionHelper;
import com.rvsautomation.exception.AutomationException;

import io.appium.java_client.AppiumDriver;

public class ArticleObjects {

	public ArticleObjects() throws AutomationException {
		super();
	}

	UtilityActionHelper actionHelper = new UtilityActionHelper();

	/************************************* Article Listing View ******************************************/

	/*
	 * Get list of Article Title.
	 */
	public List<WebElement> articleList(AppiumDriver<WebElement> driver) {
		// try to find the element.
		try {
			List<WebElement> ele = actionHelper.waitForElementsVisible(driver,
					By.name("articleRowTitle"));
			if (ele != null) {
				System.out
						.println("Found expected element by name: articleRowTitle ");
				return ele;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out
				.println("Not Found expected element by name: articleRowTitle ");
		return null;
	}

	public String[] getArticleTitleList(AppiumDriver<WebElement> driver)
			throws AutomationException {

		List<WebElement> articleList = actionHelper.waitForElementsVisible(
				driver, By.name("articleRowTitle"));
		int size = articleList.size();
		String[] rtn = new String[size];
		for (int i = 0; i < size; i++) {
			String temp = articleList.get(i).getText();
			if (temp != null && !temp.isEmpty()) {
				rtn[i] = temp;
			}
		}
		return rtn;
	}

	/**
	 * Get list of article date
	 * 
	 * @param driver
	 * @return
	 */
	public List<WebElement> getArticleDateList(AppiumDriver<WebElement> driver) {

		// try to find the element.
		try {
			List<WebElement> ele = actionHelper.waitForElementsVisible(driver,
					By.name("articleRowDate"));
			if (ele != null) {
				System.out
						.println("Found expected element by name: articleRowDate ");
				return ele;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out
				.println("Not Found expected element by name: articleRowDate");
		return null;

	}

	/**
	 * Validate Article date by checking whether the date provided is displayed
	 * in the list view.
	 * 
	 * NOTE: You need to set a specific date for the test article and should be
	 * used as a parameter in the method.
	 * 
	 * @param driver
	 * @param date
	 * @return
	 */
	public boolean isArticleDateValid(AppiumDriver<WebElement> driver,
			String date) {
		boolean isValid = false;
		List<WebElement> eleDateList = getArticleDateList(driver);
		int size = eleDateList.size();
		for (int i = 0; i < size; i++) {
			String s = eleDateList.get(i).getText();
			if (s.equalsIgnoreCase(date)) {
				isValid = true;
			}
		}
		return isValid;
	}

}
