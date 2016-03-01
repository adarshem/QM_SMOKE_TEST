package com.qm.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qm.configurationbase.ConfigurationBase;
import com.qm.testobjects.ArticleObjects;
import com.qm.testobjects.HomePageActions;
import com.qm.testobjects.LoginObjects;
import com.qm.testobjects.MyNotesObjects;
import com.rvsautomation.actions.UtilityActionHelper;
import com.rvsautomation.actions.ValidationActionHelper;
import com.rvsautomation.data.PropertiesDataHandler;
import com.rvsautomation.exception.AutomationException;

public class DemoTest extends ConfigurationBase {

	public DemoTest() throws AutomationException {
		super();
		// TODO Auto-generated constructor stub
	}

	LoginObjects lo = new LoginObjects();
	HomePageActions home = new HomePageActions();
	UtilityActionHelper util = new UtilityActionHelper();
	ArticleObjects ao = new ArticleObjects();
	ValidationActionHelper validationHelper = new ValidationActionHelper();
	MyNotesObjects myNotesHelper = new MyNotesObjects();

	@BeforeClass
	public void loginToApp() throws AutomationException {
		try {
			// lo.loginApp(driver, "adarsh", "Ash@889900");
			// System.out.println("Logged in successfully.");
			util.sleep(10);

		} catch (Exception e) {
			e.printStackTrace();
			throw new AutomationException(getExceptionMessage());
		}
	}

	@AfterClass
	public void closeApp() throws AutomationException {
		driver.navigate().back();

	}

	/*
	 * Verify that user is able to create a note successfully and the note is
	 * displayed in the listing view.
	 */
	@Test
	public void test_a_NoteCreationTest() throws AutomationException {
		try {

			String noteContent = PropertiesDataHandler.getProperty(
					"elements_android", "mynote_test_content.text");

			// Open My Notes component.
			home.openMyNotes(driver);

			// Create new note.
			myNotesHelper.clickOnTypeNoteArea(driver);
			myNotesHelper.typeContentInNote(driver, noteContent);
			myNotesHelper.clickOnMyNoteSaveButton(driver);

			// Validate new note created.
			validationHelper
					.assertEquals(myNotesHelper.isNoteDisplayedInLisView(
							driver, noteContent), true,
							"Recently created note does not displayed in the listing view.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new AutomationException(getExceptionMessage());
		}
	}

	/*
	 * Verify that user is able to delete a note by tap and hold the note from
	 * listing view.
	 */
	@Test
	public void test_b_DeleteNoteByTapAndHold() throws AutomationException {
		try {
			String noteContent = PropertiesDataHandler.getProperty(
					"elements_android", "mynote_test_content.text");

			myNotesHelper.tapAndHoldNote(driver, noteContent);

			myNotesHelper.tapOnDeleteButtonInAlertPopUp(driver);

			validationHelper
					.assertEquals(myNotesHelper.isNoteDisplayedInLisView(
							driver, noteContent), false,
							"Deleted note does displayed in the listing view.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new AutomationException(getExceptionMessage());
		}
	}

}
