package org.roommanager.framework.pages.tablet.settings;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.setting.NavigationConstant;
import org.roommanager.framework.pages.tablet.common.TopMenuPage;

public class NavigationPage extends TopMenuPage{
	@FindBy (xpath = NavigationConstant.SAVE_BUTTON)
	private WebElement saveButton;
	@FindBy (xpath = NavigationConstant.ROOMS_LIST) 
	private WebElement roomsList;
	@FindBy (xpath = NavigationConstant.DEFAULT_ROOM_COMBO_BOX) 
	private WebElement defaultRoomComboBox;
	private WebDriver driver;
	
	public NavigationPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public NavigationPage clickDefaultRoomComboBox(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(defaultRoomComboBox));
		defaultRoomComboBox.click();
		return this;
	}
	
	private WebElement getConferenceRoomByName(String name){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(roomsList));
		List<WebElement> rooms = roomsList.findElements(By
				.xpath(NavigationConstant.ROOM_ELEMENT));
		for(WebElement room : rooms){
			String roomName = room.findElement(By
					.xpath(NavigationConstant.ROOM_NAME)).getText();
			if(roomName.equals(name)){
				return room;
			}
		}
		return null;
	}
	
	public NavigationPage selectConferenceRoomByName(String name){		
		WebElement room = getConferenceRoomByName(name);
		room.click();
		return this;
	}
	
	public NavigationPage clickSaveButton(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.elementToBeClickable(saveButton));
		saveButton.click();
		return this;
	}
}
