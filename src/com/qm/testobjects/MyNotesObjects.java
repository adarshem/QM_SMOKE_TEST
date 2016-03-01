package com.qm.testobjects;

import java.util.List;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.rvsautomation.actions.ClickActionHelper;
import com.rvsautomation.actions.SendKeyActionHelper;
import com.rvsautomation.actions.UtilityActionHelper;
import com.rvsautomation.exception.AutomationException;

public class MyNotesObjects {

	public MyNotesObjects() throws AutomationException {
		super();
	}

	UtilityActionHelper actionHelper = new UtilityActionHelper();
	SendKeyActionHelper sendKeyHelper = new SendKeyActionHelper();
	ClickActionHelper clickHelper = new ClickActionHelper();

	/*
	 * Click on Type A Note Area.
	 */
	public void clickOnTypeNoteArea(AppiumDriver<WebElement> driver)
			throws AutomationException {

		actionHelper.waitForElementPresent(driver, "typeanote_field").click();

	}

	/*
	 * Enter text in Notes text field.
	 */
	public void typeContentInNote(AppiumDriver<WebElement> driver,
			String content) {
		sendKeyHelper.sendKeys(driver, "enteryournote_field", content);
	}

	/*
	 * Click on my notes save button.
	 */
	public void clickOnMyNoteSaveButton(AppiumDriver<WebElement> driver)
			throws AutomationException {
		clickHelper.click(driver, "mynote_save_button");

	}

	/*
	 * Get list of My Notes Title.
	 */
	public List<WebElement> getMyNoteList(AppiumDriver<WebElement> driver) {
		// try to find the element.
		try {
			List<WebElement> ele = actionHelper.waitForElementsVisible(driver,
					By.name("myNotesTextView"));
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

	/**
	 * Validate note displayed in the list view.
	 * 
	 * @param driver
	 * @param noteContent
	 * @return
	 */
	public boolean isNoteDisplayedInLisView(AppiumDriver<WebElement> driver,
			String noteContent) {
		try {
			boolean isNoteDisplayed = false;
			List<WebElement> noteList = getMyNoteList(driver);
			int size = noteList.size();
			for (int i = 0; i < size; i++) {
				String s = noteList.get(i).getText();
				if (s.equalsIgnoreCase(noteContent)) {
					isNoteDisplayed = true;
				}
			}
			return isNoteDisplayed;
		} catch (Exception e) {
			return false;
		}
	}

	public WebElement locateNoteInListingView(AppiumDriver<WebElement> driver,
			String noteContent) {
		WebElement ele = null;
		List<WebElement> noteList = getMyNoteList(driver);
		int size = noteList.size();
		for (int i = 0; i < size; i++) {
			String s = noteList.get(i).getText();
			if (s.equalsIgnoreCase(noteContent)) {
				ele = noteList.get(i);
			}
		}
		return ele;
	}

	/*
	 * Tap and hold on a note in the listing view.
	 */
	public void tapAndHoldNote(AppiumDriver<WebElement> driver,
			String noteContent) throws AutomationException {

		clickHelper.clickAndHold(driver, By.name(noteContent));

	}

	/*
	 * Click on Delete button in Delete Note Alert.
	 */
	public void tapOnDeleteButtonInAlertPopUp(AppiumDriver<WebElement> driver)
			throws AutomationException {

		clickHelper.click(driver, "delet_button_in_alert");

	}

}
