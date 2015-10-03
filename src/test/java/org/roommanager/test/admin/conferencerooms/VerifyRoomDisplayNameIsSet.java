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
 * VerifyRoomDisplayNameIsSet class contains the test case:  
 * Verify that the display name of a room is set
 * 
 * @author Paulo Ormachea
 *
 */
public class VerifyRoomDisplayNameIsSet extends TestBase {
	
	/** roomName: Name of the room*/ 
	private String roomSelected =  "Room10";
	
	/***/
    private String displayNameRoomUpdated = "SM-Room1-Update";
       
	/** 
	  * errorMessage: It contains the error message that would appear 
	  * if test case fails.
	  */
	private String msgError = "Conferece Room Page - The room name is not set";
	
	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
	
	  /**
	  * This method performs the test case:
	  */
	@Test
	public void verifyRoomDisplayNameIsSet() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.setCredentials()
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage = homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage updateRoomName = conferenceRoomPage
				.doubleClickOnRoom(roomSelected)
				.setDisplayNameRoom(displayNameRoomUpdated);
		
		String DisplayName = updateRoomName.getDisplayNameRoom();
	
		conferenceRoomPage = updateRoomName.clickButtonCancelInfoRoom();
		
		Assert.assertEquals( displayNameRoomUpdated, 
				DisplayName,msgError);
				
	}
}
