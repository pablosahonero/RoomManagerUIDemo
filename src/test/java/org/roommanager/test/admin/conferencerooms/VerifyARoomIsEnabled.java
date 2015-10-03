package org.roommanager.test.admin.conferencerooms;

import org.testng.Assert;
import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class contains a test case about verify
 * that a room is enabled
 *
 */
public class VerifyARoomIsEnabled extends TestBase {
	
	/** roomName: Name of the room  */
	private String roomName = null;
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the room was not enabled";
	
	/**
	 * BeforeTest: This method disables the room 
	 */
	@BeforeTest
	public void beforeTest() {
		roomName = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		RoomApi.disableRoom(roomName);	
	}
	/**
	 * The verifyARoomIsEnabled method performs the test case:
	 * "Verify if saved the "Enabled" state of the room when the On/Off Room button this pressed".
	 */
	@Test
	public void verifyARoomIsEnabled(){
		String stateGreen="text-glow-green fa fa-power-off";
		LoginPage login = new LoginPage(driver);
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		ConferenceRoomPage conferenceRoom = home.selectConferenceRoomsLink()
				.doubleClickOnDisabledRoom(roomName)
				.clickOnRoomInfoLink()
				.clickPowerOffButton()
				.clickButtonSaveInfoRoom();	
		Assert.assertEquals(conferenceRoom.getStateColorOnEnabledRoom(roomName),
				stateGreen, errorMessage);
	}
	/**
	 * After Test: This method enables the room .
	 */
	@AfterTest
	public void afterTest() {
		RoomApi.enableRoom(roomName);
	}
}

