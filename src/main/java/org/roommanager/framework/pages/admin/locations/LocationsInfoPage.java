package org.roommanager.framework.pages.admin.locations;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.LocationsInfoConstant;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

/**
 * This class contains the actions about Locations Info Page
 * @author Jimmy Maldonado
 *
 */
public class LocationsInfoPage extends LocationsTopMenu{

	WebDriver driver;
	@FindBy (xpath = LocationsInfoConstant.SAVE_BUTTON) 
	private WebElement saveButton;
	@FindBy (xpath = LocationsInfoConstant.CANCEL_BUTTON) 
	private WebElement cancelButton;
	@FindBy (xpath = LocationsInfoConstant.NAME_TEXT_FIELD) 
	private WebElement nameTextField;
	@FindBy (xpath = LocationsInfoConstant.DISPLAY_NAME_TEXT_FIELD) 
	private WebElement displayNameTextField;
	@FindBy (xpath = LocationsInfoConstant.DESCRIPTION_TEXT_FIELD) 
	private WebElement descriptionTextArea;
	@FindBy (xpath = LocationsInfoConstant.ADD_PARENT_LOCATION_BUTTON) 
	private WebElement addParentLocationButton;
	@FindBy (xpath = LocationsInfoConstant.EXPAND_BUTTON) 
	private WebElement expandButton;
	
	/**
	 * Constructor
	 * @param driver
	 */
	public LocationsInfoPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method performs a click on the Save button
	 * @return LocationsPage
	 */
	public LocationsPage clickSaveButton(){
		zoomOutPage(50);
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath(LocationsInfoConstant.SAVE_BUTTON)));
		((JavascriptExecutor) driver)
			.executeScript("arguments[0].click();", saveButton);
		restoreZoomPage();
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.invisibilityOfElementLocated(By
					.xpath(LocationsInfoConstant.SAVE_BUTTON)));
		driver.navigate().refresh();
		return new LocationsPage(driver);
	}
	
	/**
	 * This method performs a zoom out to the page
	 * @param percentage
	 */
	public void zoomOutPage(int percentage){
		String browser = PropertiesReader.getBrowser();
		if(browser.equals("CHROME")){
			((JavascriptExecutor) driver)
				.executeScript("document.body.style.zoom='" + percentage + "%'");
		} else if(browser.equals("FIREFOX")){
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}
	
	/**
	 * This method restores the zoom page (100%)
	 */
	public void restoreZoomPage(){
		String browser = PropertiesReader.getBrowser();
		if(browser.equals("CHROME")){
			((JavascriptExecutor) driver)
				.executeScript("document.body.style.zoom='100%'");
		} else if(browser.equals("FIREFOX")){
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}
	}
	
	/**
	 * This method enters a text on the Name text field
	 * @param name
	 * @return LocationsInfoPage
	 */
	public LocationsInfoPage setNameTextField(String name){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(nameTextField));
		nameTextField.clear();
		nameTextField.sendKeys(name);
		LogManager.info("Location Name: <" + name + "> was entered");
		return this;
	}
	
	/**
	 * This method enters a text on the Display Name text field
	 * @param displayName
	 * @return LocationsInfoPage
	 */
	public LocationsInfoPage setDisplayNameTextField(String displayName){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(displayNameTextField));
		displayNameTextField.clear();
		displayNameTextField.sendKeys(displayName);
		LogManager.info("Location Display Name: <" + displayName + 
				"> was entered");
		return this;
	}
	
	/**
	 * This method enters a text on the Description text area
	 * @param description
	 * @return LocationsInfoPage
	 */
	public LocationsInfoPage setDescriptionTextArea(String description){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(descriptionTextArea));
		descriptionTextArea.clear();
		descriptionTextArea.sendKeys(description);
		LogManager.info("Location Description: <" + description + 
				"> was entered");
		return this;
	}
	
}
