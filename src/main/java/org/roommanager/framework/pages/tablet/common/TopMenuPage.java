package org.roommanager.framework.pages.tablet.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.common.TopMenuConstant;
import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.roommanager.framework.utilities.common.LogManager;

public class TopMenuPage {
	@FindBy (xpath = TopMenuConstant.HOME_PAGE_LINK)
	private WebElement homePageLink;
	@FindBy (xpath = TopMenuConstant.SCHEDULER_PAGE_LINK)
	private WebElement schedulerPageLink;
	@FindBy (xpath = TopMenuConstant.SEARCH_PAGE_LINK)
	private WebElement searchPageLink;
	@FindBy (xpath = TopMenuConstant.SETTINGS_PAGE_LINK)
	private WebElement settingsPageLink;
	private WebDriver driver;
	
	public TopMenuPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method clicks on Home Page Link
	 * @return HomePage
	 */
	public HomePage clickOnHomePageLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(homePageLink));
		homePageLink.click();
		LogManager.info("Home Page Link was clicked");
		return new HomePage(driver);
	}
	
	/**
	 * This method clicks on Scheduler Page Link
	 * @return SchedulerPage
	 */
	public SchedulerPage clickOnSchedulerPageLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(schedulerPageLink));
		schedulerPageLink.click();
		LogManager.info("Scheduler Page Link was clicked");
		return new SchedulerPage(driver);
	}
	
	/**
	 * This method clicks on Search Page Link
	 * @return SearchPage
	 */
	public SearchPage clickOnSearchPageLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(searchPageLink));
		searchPageLink.click();
		LogManager.info("Search Page Link was clicked");
		return new SearchPage(driver);
	}
	
	/**
	 * This method clicks on Settings Connection Page Link
	 * @return RegisterPage
	 */
	public RegisterPage clickOnSeetingsPageLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(settingsPageLink));
		settingsPageLink.click();
		LogManager.info("Setings Connection Page Link was clicked");
		return new RegisterPage(driver);
	}
}
