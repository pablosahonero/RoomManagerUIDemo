package org.roommanager.framework.pages.admin.locations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.LocationsTopMenuConstant;

public class LocationsTopMenu {
	WebDriver  driver;
	@FindBy (xpath = LocationsTopMenuConstant.LOCATION_INFO_LINK) 
	private WebElement locationInfoLink;
	@FindBy (xpath = LocationsTopMenuConstant.LOCATION_ASSOCIATIONS_PAGE) 
	private WebElement locationAssociationsLink;
	
	public LocationsTopMenu(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method clicks on Location Info Link
	 * @return LocationsInfoPage
	 */
	public LocationsInfoPage clickOnLocationInfoLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(locationInfoLink));
		locationInfoLink.click();
		return new LocationsInfoPage(driver);
	}
	
	/**
	 * This method clicks on Location Associations Link
	 * @return LocationAssociationsPage
	 */
	public LocationAssociationsPage clickOnLocationAssociationsInfoLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(locationAssociationsLink));
		locationAssociationsLink.click();
		return new LocationAssociationsPage(driver);
	}
}
