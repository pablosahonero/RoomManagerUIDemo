package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyRoomCanBeCanceled class contains the test case 
 * (with pre and post conditions): Verify that the configuration 
 * of a room can be canceled when pressing the Cancel button
 * 
 * @author Milenca Ventura
 *
 */

public class VerifyRoomCanBeCanceled extends TestBase{
	
	/** roomName: Name of room to be changed*/
	private String roomName = "Room01";
	
	/** displayName: Display name of room to be changed*/
	private String displayName = "DisplayName-Updated";
	 
	/** getDisplayName: get the display name of the room*/
	private String getDisplayName;
	
    /** 
	* errorMessage: It contains the error message that would appear 
	* if test case fails.
	*/
	private String errorMessage = "The test cases failed: the datas"
			+ "does not cancel.";
	
	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
	
	/**
	* This method performs the test case: Verify that the configuration 
	* of a room can be canceled when pressing the Cancel button.
	*/
	@Test
	public void verifyRoomCanBeCanceled() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.setCredentials()
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage= homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage roomInfoPage = conferenceRoomPage
				.doubleClickOnRoom(roomName);
		getDisplayName = roomInfoPage.getDisplayNameRoom();

		roomInfoPage.setDisplayNameRoom(displayName);

		roomInfoPage.clickButtonCancelInfoRoom();

		Assert.assertTrue( conferenceRoomPage
				.isDisplayNameUpdated(getDisplayName),errorMessage);
	}
	
}
