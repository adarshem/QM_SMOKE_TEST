package com.qm.testobjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.rvsautomation.actions.UtilityActionHelper;
import com.rvsautomation.actions.ValidationActionHelper;
import com.rvsautomation.exception.AutomationException;

import io.appium.java_client.AppiumDriver;

/**
 * 
 * Page objects for Home screen. Includes all the methods used in Home Page.
 * 
 * @author Adarsh E M
 * @since Jan-30-2016
 * 
 */
public class HomePageActions {
	public HomePageActions() throws AutomationException {
		super();
	}

	UtilityActionHelper actionHeleper = new UtilityActionHelper();

	ValidationActionHelper validationHelper = new ValidationActionHelper();

	private int pageNumber = 1;

	/*
	 * Swipe to next page
	 */
	public void swipeToNextPage(AppiumDriver<WebElement> driver)
			throws AutomationException {

		actionHeleper.swipeFromRightToLeft(driver, 1);
		pageNumber++;
	}

	/*
	 * Swipe to previous page
	 */
	public void swipeToPreviousPage(AppiumDriver<WebElement> driver)
			throws AutomationException {

		actionHeleper.swipeFromLeftToRight(driver, 1);
		if (pageNumber > 1) {
			pageNumber--;
		}
	}

	public void clickOnFeatureInNineIconView(AppiumDriver<WebElement> driver,
			String featureName) throws AutomationException {

		// Ensure the app is in first page
		while (pageNumber > 1) {
			swipeToPreviousPage(driver);
		}

		WebElement ele = null;
		// Search for the feature in available pages.
		while (ele == null && this.pageNumber < 6) {
			try {
				if (validationHelper.isElementPresent(driver, featureName))
					ele = actionHeleper.waitForElementPresent(driver,
							featureName);
			} catch (Exception e) {
				ele = null;
			}

			if (ele != null) {
				ele.click();
			} else {
				swipeToNextPage(driver);
			}
		}

		if (ele == null) {
			Assert.fail("Component is not available in the Nine icon view : "
					+ featureName);
		}
	}

	/*
	 * Method to find and click on Article component in Nine-icon view.
	 */
	public void openArticles(AppiumDriver<WebElement> driver)
			throws AutomationException {
		clickOnFeatureInNineIconView(driver, "articles_text");
	}

	/*
	 * Method to find and click on MyNotes component in Nine-icon view.
	 */
	public void openMyNotes(AppiumDriver<WebElement> driver)
			throws AutomationException {
		clickOnFeatureInNineIconView(driver, "mynotes_text");
	}

}
