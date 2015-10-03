package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.OutOfOrderPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyOutOfOrderPageIsDisplayed extends TestBase{
	private String roomName = "Room09";
	/** 
	 * errorMessage: It contains the error message that is displayed 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because Out Of Order Page "
			+ "Link wasn't displayed";
	
	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
	
	/**
	 * verifyOutOfOrderPageIsDisplayed: Check that after clicking 
	 * on Out of Order tab in the Room Form, page is displayed.
	 */
	@Test
	public void verifyOutOfOrderPageIsDisplayed() {
		LoginPage login = new LoginPage(driver);
		
		ConferenceRoomPage conferenceRoom = login
				.setCredentials()
				.clickSignInButton()
				.selectConferenceRoomsLink();
			
		RoomInfoPage roomInfo = conferenceRoom
			.doubleClickOnRoom(roomName)
			.clickOnRoomInfoLink();
		
		OutOfOrderPage outOfOrder = roomInfo
			.clickOnOutOfOrderPlanning();
		
		boolean isOutOfOrderPageAvailable = outOfOrder
				.isOutOfOrderPageIsAvailable();
				
		Assert.assertTrue(isOutOfOrderPageAvailable, errorMessage);
	} 
}
