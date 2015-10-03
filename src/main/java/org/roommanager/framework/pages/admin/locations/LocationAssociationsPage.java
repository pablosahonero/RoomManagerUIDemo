package org.roommanager.framework.pages.admin.locations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.LocationAssociationsConstant;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class LocationAssociationsPage extends LocationsTopMenu {
	private WebDriver  driver;
	@FindBy (xpath = LocationAssociationsConstant.AVAILABLE_ROOMS_LIST) 
	private WebElement availableRoomsList;
	@FindBy (xpath = LocationAssociationsConstant.ASSOCIATED_ROOMS_LIST) 
	private WebElement associatedRoomsList;
	@FindBy (xpath = LocationAssociationsConstant.ASSOCIATED_ROOMS_TABLE) 
	private WebElement associatedRoomsTable;
	@FindBy (xpath = LocationAssociationsConstant.SAVE_BUTTON) 
	private WebElement saveButton;
	
	public LocationAssociationsPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method clicks on the specified Room Association Button
	 * @param roomName
	 * @return LocationAssociationsPage
	 */
	public LocationAssociationsPage clickOnAssociateRoomButton(String roomName){
		WebElement room = getRoomByNameFromAvailableRooms(roomName);
		WebElement addRoomButton = room
							   	   .findElement(LocationAssociationsConstant
									   			.ASSOCIATE_ROOM_BUTTON);
		addRoomButton.click();
		LogManager.info("Click on Room: <"+ roomName +"> Associate Button");
		return this;
	}
	
	/**
	 * This method clicks on the specified Room Dissociation Button
	 * @param roomName
	 * @return LocationAssociationsPage
	 */
	public LocationAssociationsPage clickOnDissociateRoomButton(String roomName){
		WebElement room = getRoomByNameFromAssociatedRooms(roomName);
		WebElement removeRoomButton = room
							   	   	  .findElement(LocationAssociationsConstant
							   	   			  	   .ASSOCIATE_ROOM_BUTTON);
		removeRoomButton.click();
		LogManager.info("Click on Room: <"+ roomName +"> Dissociate Button");
		return this;
	}
	
	/**
	 * This method gets specified Room from Associated Room List
	 * @param roomName
	 * @return String 
	 */
	public String getAssociatedRoomName(String roomName){
		WebElement room = getRoomByNameFromAssociatedRooms(roomName);
		String actualRoomName = room.findElement(LocationAssociationsConstant
												 .ROOM_NAME).getText();
		LogManager.info("Room Name: <"+ actualRoomName +"> was retrieved");
		return actualRoomName; 
	}
	
	/**
	 * This method verifies if the specified Room is present in the 
	 * Associated Room List
	 * @param roomName
	 * @return boolean
	 */
	public boolean isRoomInAssociatedRooms(String roomName){
		WebElement room = getRoomByNameFromAssociatedRooms(roomName);
		boolean isRoomAssociated = room != null;
		if(isRoomAssociated){
			LogManager.info("Room: <"+ roomName +"> was found "
							+ "in Associated Rooms List");
		} else{
			LogManager.info("Room: <"+ roomName +"> wasn't found "
							+ "in Associated Rooms List");
		}
		return isRoomAssociated;
	}
	
	/**
	 * This method clicks on Save Button.
	 * @return LocationsPage
	 */
	public LocationsPage clickOnSaveButton(){
		zoomOutPage(50);
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath(LocationAssociationsConstant.SAVE_BUTTON)));
		((JavascriptExecutor) driver)
			.executeScript("arguments[0].click();", saveButton);
		LogManager.info("Click on Save button");
		restoreZoomPage();
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.invisibilityOfElementLocated(By
					.xpath(LocationAssociationsConstant.SAVE_BUTTON)));
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
	 * This method retrieves the specified Room from Available Rooms List
	 * @param roomName
	 * @return WebElement
	 */
	private WebElement getRoomByNameFromAvailableRooms(String roomName){
		new WebDriverWait(driver, 30)
			.until(ExpectedConditions.visibilityOf(availableRoomsList));
		List <WebElement> rooms = availableRoomsList
								  .findElements(LocationAssociationsConstant
										  		.LIST_ITEM_ELEMENT);
		for (WebElement room : rooms) {
			String actualRoomName = room
									.findElement(LocationAssociationsConstant
												 .ROOM_NAME).getText();
			if(actualRoomName.equals(roomName)){
				LogManager.info("Room: <"+ actualRoomName +"> was found "
								+ "in Available Rooms List");
				return room;
			}
		}
		LogManager.info("Room: <"+ roomName +"> wasn't found "
						+ "in Available Rooms List");
		return null;
	}
	
	/**
	 * This method retrieves the specified Room from Associated Rooms List
	 * @param roomName
	 * @return WebElement
	 */
	private WebElement getRoomByNameFromAssociatedRooms(String roomName){
		new WebDriverWait(driver, 30)
			.until(ExpectedConditions.visibilityOf(associatedRoomsTable));
		List <WebElement> rooms = associatedRoomsList
								  .findElements(LocationAssociationsConstant
										  		.LIST_ITEM_ELEMENT);
		for (WebElement room : rooms) {
			String actualRoomName = room
									.findElement(LocationAssociationsConstant
												 .ROOM_NAME).getText();
			if(actualRoomName.equals(roomName)){
				LogManager.info("Room: <"+ actualRoomName +"> was found "
								+ "in Associated Rooms List");
				return room;
			}
		}
		LogManager.info("Room: <"+ roomName +"> wasn't found "
						+ "in Associated Rooms List");
		return null;
	}
}
