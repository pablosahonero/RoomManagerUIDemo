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
 * VerifyRoomNameUpdateIsDisable class contains the test case:  
 * Verify that is not possible to update the Name of a room.
 * 
 * @author Paulo Ormachea
 *
 */
public class VerifyUpdateRoomNameIsDisabled extends TestBase {
	
	/** roomSelected: Name of the room*/ 
	private String roomSelected =  "Room01";
    
	/** 
	  * errorMessage: It contains the error message that would appear 
	  * if test case fails.
	  */
	private String msgError = "Conferece Room Page - The room Name is Enabled";
	
	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
	
	  /**
	  * This method performs the test case:Verify that the rename of a room 
	  * is disabled
	  */
	@Test
	public void VerifyRoomNameUpdateIsDisable() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.setCredentials()
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage = homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage updateRoomName = conferenceRoomPage
				.doubleClickOnRoom(roomSelected);
		
		updateRoomName.setDisplayNameRoom(roomSelected);
		
		boolean IsDisabled = updateRoomName.RoomNameIsDisable();
		
		Assert.assertEquals( IsDisabled, true,msgError);
		
		updateRoomName.clickButtonCancelInfoRoom();
	}
}


