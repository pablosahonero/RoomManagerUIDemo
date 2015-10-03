package org.roommanager.framework.pages.tablet.settings;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.setting.SettingsConstant;
import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class SettingsPage {
	@FindBy (id = SettingsConstant.SEARCH_TEXT_FIELD) 
	private WebElement searchTextField;
	@FindBy (xpath = SettingsConstant.ACCEPT_BUTTON) 
	private WebElement acceptButton;
	@FindBy (xpath = SettingsConstant.ROOMS_LIST) 
	private WebElement roomsList;
	@FindBy (css = SettingsConstant.ROOMS_TABLE_FIRST_ELEMENT) 
	private WebElement roomsTableFirstElement;
	@FindBy (xpath = SettingsConstant.ROOM_ELEMENT_NAME) 
	private WebElement roomsElementName;
	@FindBy (xpath = SettingsConstant.DIV_ELEMENT) 
	private WebElement divElement;
	private By divElementLocator = SettingsConstant.DIV_ELEMENT_LOCATOR;
	private By roomElementNameLocator = SettingsConstant.ROOM_ELEMENT_NAME_LOCATOR;
	private WebDriver driver;
	
	public SettingsPage(WebDriver driver){
		this.driver = driver;
		driver.get(PropertiesReader.getLoginUrlTabletModule());
		driver.navigate().refresh();
		PageFactory.initElements(driver, this);
	}
	
	public SettingsPage waitForSettingPageToLoad(){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(searchTextField));
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(roomsTableFirstElement));
		return this;
	}
	
	public SettingsPage searchRoomByName(String roomName){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(searchTextField));
		searchTextField.clear();
		searchTextField.sendKeys(roomName);
		LogManager.info("Room Name: <" + roomName + "> was entered in the Search Room Text Field");
		return this;
	}
	
	public SettingsPage clickRoomItem(String roomName){
		WebElement room = getRoomItem(roomName);
		room.click();
		return this;
	}
	
	private WebElement getRoomItem(String roomName){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(roomsList));
		List <WebElement> rooms = roomsList.findElements(divElementLocator);
		
		for (WebElement room : rooms) {
			String roomItemName = room.findElement(roomElementNameLocator).getText();
			if(roomItemName.equals(roomName)){
				LogManager.info("Room: <"+ roomItemName +"> was found on the Available Rooms List");
				return room;
			}
		}
		LogManager.info("Room: <"+ roomName +"> wasn't found on the Available Rooms List");
		return null;
	}
	
	public HomePage clickAcceptButton(){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(acceptButton));
		acceptButton.click();
		LogManager.info("Accept Button was clicked");
		return new HomePage(driver);
	}
}
