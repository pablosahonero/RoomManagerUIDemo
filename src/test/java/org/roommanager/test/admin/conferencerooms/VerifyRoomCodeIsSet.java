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
 * VerifyRooCodeIsSet class contains the test case:  
 * Verify that the code of a room is set 
 * 
 * @author Paulo Ormachea
 *
 */
public class VerifyRoomCodeIsSet extends TestBase {
	
	/** roomSelected: Name of the room*/ 
	private String roomSelected =  "Room01";
	
	/**codeRoomUpdated: Code of the room*/
    private String codeRoomUpdated = "Micode";
        
	/** 
	  * errorMessage: It contains the error message that would appear 
	  * if test case fails.
	  */
	private String msgError = "Conferece Room Page - the room code is not set";
	
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
	  * Verify that the code of a room is set
	  */
	@Test
	public void verifyRoomCodeIsSet() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.setCredentials()
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage = homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage updateRoomName = conferenceRoomPage
				.doubleClickOnRoom(roomSelected).setCodeRoom(codeRoomUpdated);
		
		String code = updateRoomName.getCodeRoom();
		
		conferenceRoomPage = updateRoomName.clickButtonCancelInfoRoom();
		
		Assert.assertEquals(codeRoomUpdated, 
							code, msgError);
		
				
	}
}
