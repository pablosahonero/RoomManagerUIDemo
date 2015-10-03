package org.roommanager.framework.pages.admin.conferenceroom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ConferenceRoomTopMenuConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class ConferenceRoomTopMenu {
	@FindBy (xpath = ConferenceRoomTopMenuConstant.ROOM_INFO) 
	protected WebElement roomInfoLink;
	@FindBy (linkText = ConferenceRoomTopMenuConstant.RESOURCE_ASSOCIATION)
	private WebElement resourceAssociationLink;
	@FindBy (xpath = ConferenceRoomTopMenuConstant.OUT_ORDER_PLANNING) 
	private WebElement outOfOrderPlanningLink;
	@FindBy(xpath = ConferenceRoomTopMenuConstant.CLOSE_BUTTON)
	private WebElement closeButton;
	private WebDriver driver;
	
	public ConferenceRoomTopMenu(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * clickOnRoomInfoLink: It clicks on Room Info Link.
	 * @return RoomInfoPage
	 */
	public RoomInfoPage clickOnRoomInfoLink(){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(roomInfoLink));
		roomInfoLink.click();
		LogManager.info("Click on Resource: Room Info Link");
		return new RoomInfoPage(driver);
	}
	
	/**
	 * clickOnResourceAssociations: It clicks on Resource Association Link.
	 * @return ResourceAssociationsPage
	 */
	public ResourceAssociationsPage clickOnResourceAssociations(){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(resourceAssociationLink));
		resourceAssociationLink.click();
		LogManager.info("Click on Resource: Resource Associations Link");
		return new ResourceAssociationsPage(driver);
	}
	
	/**
	 * clickOnOutOfOrderPlanning: It clicks on Out Of Order Planning Link.
	 * @return OutOfOrderPage
	 */
	public OutOfOrderPage clickOnOutOfOrderPlanning(){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(outOfOrderPlanningLink));
		outOfOrderPlanningLink.click();
		LogManager.info("Click on Resource: Resource Associations Link");
		return new OutOfOrderPage(driver);
	}
	/**
	 * clickCloseButton: It clicks on the Close Button 
	 * 
	 * @return ConferenceRoomPage
	 */
	public ConferenceRoomPage clickCloseButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(closeButton));
		closeButton.click();
		LogManager.info("Close button was clicked");
		return new ConferenceRoomPage(driver);
	}
}
