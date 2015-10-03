package org.roommanager.framework.pages.admin.locations;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.LocationsConstant;
import org.roommanager.framework.utilities.common.LogManager;

/**
 * This class contains the methods to Locations Page
 * @author Jimmy Maldonado
 *
 */
public class LocationsPage extends PageFactory{
	
	WebDriver  driver;
	@FindBy (xpath = LocationsConstant.ADD_BUTTON) 
	private WebElement addButton;
	@FindBy (xpath = LocationsConstant.REMOVE_BUTTON) 
	private WebElement removeButton;
	@FindBy (xpath = LocationsConstant.LOCATIONS_TABLE) 
	private WebElement locationsTable;
	
	/**
	 * Constructor
	 * @param driver
	 */
	public LocationsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method performs a click on Add button from Locations Page
	 * @return LocationsInfoPage
	 */
	public LocationsInfoPage clickAddButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(addButton));
		addButton.click();
		LogManager.info("Add button was clicked");
		return new LocationsInfoPage(driver);
	}
	
	/**
	 * This method performs a click on Remove button from Locations Page
	 * @return RemoveLocationsPage
	 */
	public RemoveLocationsPage clickRemoveButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.elementToBeClickable(removeButton));
		removeButton.click();
		LogManager.info("Remove button was clicked");
		return new RemoveLocationsPage(driver);
	}
	
	/**
	 * This method performs a  check on a specific location
	 * @param name that represents the location's name
	 * @return
	 */
	public LocationsPage checkLocation(String name){
		WebElement locationCheckBox = getLocationByName(name)
				.findElement(By.xpath(LocationsConstant.LOCATION_CHECK_BOX));
		locationCheckBox.click();
		LogManager.info("Location: < " + name + " > was checked");
		return this;
	}
	
	/**
	 * This method performs a search of a location in the Locations
	 * table
	 * @param name
	 * @return WebElement that represents the location
	 */
	private WebElement getLocationByName(String name){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(locationsTable));
		List<WebElement> locations = locationsTable.findElements(By.xpath("div"));
		for (WebElement location : locations) {
			String locationName = location.findElement(By
					.xpath(LocationsConstant.LOCATION_NAME)).getText();
			if(locationName.equals(name)){
				LogManager.info("Location: < " + locationName + " > was found");
				return location;
			}
		}
		LogManager.info("Location: < " + name + " > was not found");
		return null;
	}
	
	/**
	 * This method performs a double click on a specific location
	 * @param name
	 * @return LocationsInfoPage
	 */
	public LocationsInfoPage doubleClickOnLocation(String name){
		WebElement location = getLocationByName(name).findElement(By
				.xpath(LocationsConstant.LOCATION_DISPLAY_NAME));
		(new Actions(driver)).doubleClick(location).perform();
		LogManager.info("Double Click on Location: <" + 
				name + "> from Locations Table");
		return new LocationsInfoPage(driver);
	}
	
	/**
	 * This method returns true if the location's name is found in
	 * the Locations Table
	 * @param name
	 * @return true if the location exists 
	 */
	public boolean isLocationPresent(String name){
		boolean exist = getLocationByName(name) != null ? true : false;
		LogManager.info("Exist location: < " + exist + " >");
		return exist;
	}
}

