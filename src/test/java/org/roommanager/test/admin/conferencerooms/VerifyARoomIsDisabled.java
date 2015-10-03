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
 * that a room is disabled
 *
 */
public class VerifyARoomIsDisabled extends TestBase {
	
	/** roomName: Name of the room  */
	private String roomName = null;
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the room was not disabled";
	
	/**
	 * BeforeTest: This method enables the room 
	 */
	@BeforeTest
	public void beforeTest() {
		roomName = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		RoomApi.enableRoom(roomName);	
	}
	
	/**
	 * The verifyARoomIsDisable method performs the test case:
	 * "Verify if saved the "Disabled" state of the room when the On/Off Room button this pressed".
	 */
	@Test
	public void verifyARoomIsDisable(){
		String stateGray="text-glow-gray fa fa-power-off";
		LoginPage login = new LoginPage(driver);
		HomePage home = login
							.setCredentials()
							.clickSignInButton();
		ConferenceRoomPage conferenceRoom = home.selectConferenceRoomsLink()
				.doubleClickOnRoom(roomName)
				.clickOnRoomInfoLink()
				.clickPowerOnButton()
				.clickButtonSaveInfoRoom();
		Assert.assertEquals(conferenceRoom.getStateColorOnDisabledRoom(roomName),
				stateGray, errorMessage);

	}
	
	/**
	 * After Test: This method enables the room .
	 */
	@AfterTest
	public void afterTest() {
		RoomApi.enableRoom(roomName);
	}
}
