package org.roommanager.framework.pages.tablet.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.search.SearchConstant;
import org.roommanager.framework.pages.tablet.common.TopMenuPage;
import org.roommanager.framework.utilities.common.LogManager;

public class SearchPage extends TopMenuPage {

	@FindBy(xpath = SearchConstant.ADVANCED_BUTTON)
	private WebElement advancedButton;
	@FindBy(xpath = SearchConstant.ROOM_NAME_TEXT_FIELD)
	private WebElement roomNameTextField;
	@FindBy(xpath = SearchConstant.MINIMUN_CAPACITY_TEXT_FIELD)
	private WebElement minimunCapacityTextField;
	@FindBy(xpath = SearchConstant.ROOM_LIST)
	private WebElement roomList;
	@FindBy(xpath = SearchConstant.DIV_ELEMENT)
	private WebElement divElement;
	@FindBy (xpath = SearchConstant.ROOM_NAME)
	private WebElement roomName;
	@FindBy (xpath = SearchConstant.SELECT_LOCATION) 
	private WebElement selectLocation;
	@FindBy (xpath = SearchConstant.LOCATION_NAME) 
	private WebElement locationName;
	private WebDriver driver;
	WebElement locationElements;
	
	public SearchPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * clickAdvancedButton: It clicks on the advanced search button.
	 *        
	 * @return SearchPage
	 */
	public SearchPage clickAdvancedButton(){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(advancedButton));
		advancedButton.click();
		LogManager.info("Click on advanced Button");
		return this;
	}

	/**
	 * enterRoomName: It sets the room name for search.
	 *        
	 * @param roomName
	 *          : It represents the Room's Name
	 *          
	 * @return SearchPage
	 */
	public SearchPage enterRoomName (String roomName){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(roomNameTextField));
		roomNameTextField.clear();
		roomNameTextField.sendKeys(roomName);
		LogManager.info("RoomName: <" + roomName + "> was entered");
		return this;
	}
	
	/**
	 * enterCapacity: It sets the Room's capacity for search.
	 *        
	 * @param capacity
	 *          : It represents the Room's Capacity
	 *          
	 * @return SearchPage
	 */
	public SearchPage enterCapacity(String capacity) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(minimunCapacityTextField));
		minimunCapacityTextField.clear();
		minimunCapacityTextField.sendKeys(capacity);
		LogManager.info("Minimun Capacity: <" + capacity + "> was entered");
		return this;
	}
	
	/**
	 * getRoomPosition: It gets the room's position on the Schedule Table
	 * by display name room.
	 *        
	 * @param roomName
	 *          : It represents the Room's Display Name
	 *          
	 * @return Room's Position
	 */
	private Integer getRoomPosition(String roomName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomList));
		List<WebElement> rooms = roomList.findElements(By
				.xpath(SearchConstant.DIV_ELEMENT));
		for (WebElement room : rooms) {

			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.visibilityOf(room));
			String roomItemName = room.findElement(
					By.xpath(SearchConstant.ROOM_NAME)).getText();
			if (roomItemName.equals(roomName)) {
				Integer index = rooms.indexOf(room) + 1;
				LogManager.info("The index of the room: <" + roomItemName
						+ "> is: " + index);

				return index;
			}
		}
		LogManager.info("The index of the room: <" + roomName
				+ "> wasn't found on Room list");
		return null;
	}
	
	/**
	 * getMeetingByRoom: It gets a meeting on the room's row on 
	 * the Schedule Table by display name room.
	 *        
	 * @param roomName
	 *          : It represents the Room's Display Name
	 *          
	 * @return Meeting's Subject
	 */
	public String getMeetingByRoom(String roomName) {
		Integer index = getRoomPosition(roomName);
		String path = "//div[2]/div/div/div[3]/div[2]/div/div[2]/div/rm-timeline/div/div[4]/div[1]/div/div[2]/div["
				+ index + "]/div/div/div/div[1]";
		WebElement roomMeeting = driver.findElement(By.xpath(path));
		String subject = roomMeeting.getText();
		LogManager.info("Room: <" + roomName + "> has a meeting with subject: <" + subject+">");
		return subject;
	}
	
	/**
	 * getRoomByName: It gets the room by display name room.
	 *        
	 * @param roomDisplayName
	 *          : It represents the Room's Display Name
	 *          
	 * @return room
	 */
	public WebElement getRoomByName(String roomDisplayName){
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomList));
			List<WebElement> rooms = roomList.findElements(By.xpath(
					SearchConstant.DIV_ELEMENT));
			for (WebElement room : rooms) {
				(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.visibilityOf(room));
				String roomItemName = room.findElement(By.xpath(
									SearchConstant.ROOM_NAME)).getText();
				
				if (roomItemName.equals(roomDisplayName)) {
					LogManager.info("Room: <" + roomItemName
									+ "> was found on Room list");
					return room;
				}
			}
			LogManager.info("Room: <" + roomDisplayName
							+ "> wasn't found on Room list");
			return null; 
	}
	
	/**
	 * isRoomPresent: It searches the room by display name room.
	 *        
	 * @param roomDisplayName
	 *          : It represents the Room's Display Name
	 *          
	 * @return boolean
	 */
	public boolean isRoomPresent(String roomDisplayName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomList));
			List<WebElement> rooms = roomList.findElements(By.xpath(
					SearchConstant.DIV_ELEMENT));
			for (WebElement room : rooms) {
				(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.visibilityOf(room));
				String roomItemName = room.findElement(By.xpath(
									SearchConstant.ROOM_NAME)).getText();
				
				if (roomItemName.equals(roomDisplayName)) {
					LogManager.info("Room: <" + roomItemName
									+ "> was found on Room list");
					return true;
				}
			}
			LogManager.info("Room: <" + roomDisplayName
							+ "> wasn't found on Room list");
			return false;
	}
	
	/**
	 * selectLocation: It clicks on the combo-box location.
	 *           
	 * @return SearchPage
	 */
	public SearchPage selectLocation(){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(selectLocation));
		selectLocation.click();
		LogManager.info("Click on [Location] combo box");
		return this;
	}
	
	/**
	 * getLocationByName: It searches the location by display name.
	 *        
	 * @param locationDisplayName
	 *          : It represents the Location's Display Name
	 *          
	 * @return WebElement
	 */
	public WebElement getLocationByName(String locationDisplayName){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(selectLocation));
		
		List<WebElement> locationTable = selectLocation
				.findElements(By.xpath(SearchConstant.LOCATION_NAME));
		for (WebElement locationElement : locationTable){
			
		String location = locationElement.getText();
		if (location.equals(locationDisplayName)) {
			LogManager.info("Location: <" + location+ "> was retrieved from Location Table");
			return locationElement;	
		}
	}
	LogManager.info("Location: <" + locationDisplayName + "> wasn't found");
	return null;
	}
	
	/**
	 * clickOnSelectLocation: It clicks on the location by display name.
	 *        
	 * @param locationDisplayName
	 *          : It represents the Location's Display Name
	 *          
	 * @return SearchPage
	 */
	public SearchPage clickOnSelectLocation(String locationDisplayName){
		WebElement location = getLocationByName(locationDisplayName);
		location.click();
		LogManager.info("Location: <" + locationDisplayName 
				+ "> was selected");
		return this;
	}
}
