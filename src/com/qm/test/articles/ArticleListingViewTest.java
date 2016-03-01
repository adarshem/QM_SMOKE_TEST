package com.qm.test.articles;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qm.configurationbase.ConfigurationBase;
import com.qm.testobjects.ArticleObjects;
import com.qm.testobjects.HomePageActions;
import com.qm.testobjects.LoginObjects;
import com.rvsautomation.actions.UtilityActionHelper;
import com.rvsautomation.actions.ValidationActionHelper;
import com.rvsautomation.data.PropertiesDataHandler;
import com.rvsautomation.exception.AutomationException;

public class ArticleListingViewTest extends ConfigurationBase {

	public ArticleListingViewTest() throws AutomationException {
		super();
		// TODO Auto-generated constructor stub
	}

	LoginObjects lo = new LoginObjects();
	HomePageActions home = new HomePageActions();
	UtilityActionHelper util = new UtilityActionHelper();
	ArticleObjects ao = new ArticleObjects();
	ValidationActionHelper validationHelper = new ValidationActionHelper();

	@BeforeClass
	public void loginToApp() throws AutomationException {
		try {
			
			lo.loginApp(driver, "adarsh", "Ash@889900");
			System.out.println("Logged in successfully.");

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
	 * Verify that listing view displays the article titles properly.
	 */
	@Test
	public void test_a_ArticleTitleTest() throws AutomationException {
		try {
			// Open Article Component.
			home.openArticles(driver);
			// Get Articles Title
			String[] title = ao.getArticleTitleList(driver);
			// Validate No articles have empty title.
			validationHelper.assertNotNull(title);

		} catch (Exception e) {
			e.printStackTrace();
			throw new AutomationException(getExceptionMessage());
		}
	}

	/*
	 * Verify the date is shown in the expected format in listing view if the
	 * article has a post date.
	 */
	@Test
	public void test_b_ArticleDateTestWithValidPostDate()
			throws AutomationException {
		try {

			String validDate = PropertiesDataHandler.getProperty(
					"elements_android", "articlewithdate_date.text");
			// Validation of article date if the article has date given from QP.
			validationHelper.assertEquals(
					ao.isArticleDateValid(driver,validDate), true,
					"The date provided is not displayed in the list view.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new AutomationException(getExceptionMessage());
		}
	}

	/*
	 * Verify if the Post Date of an Article is 00-00-0000, the Date does not
	 * display in the Article listing view
	 */
	@Test
	public void test_c_ArticleDateTestWithNoPostDate()
			throws AutomationException {
		try {

			String invalidDate = PropertiesDataHandler.getProperty(
					"elements_android", "articlewithinvalid_date.text");

			validationHelper.assertEquals(
					ao.isArticleDateValid(driver,invalidDate), false,
					"The invalid post date : " + invalidDate
							+ " is displayed in the listing view.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new AutomationException(getExceptionMessage());
		}
	}

}
