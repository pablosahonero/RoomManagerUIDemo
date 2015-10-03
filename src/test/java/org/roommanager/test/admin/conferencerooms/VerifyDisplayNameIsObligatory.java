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
 * The VerifyAssociateResourceRoom class contains the test case 
 * (with pre and post conditions): Verify that the Display Name 
 * text field is an obligatory field.
 * 
 * @author Milenca Ventura
 *
 */

public class VerifyDisplayNameIsObligatory extends TestBase{
	
	/** roomName: Name of room to be changed*/
	private String roomName = "Room09";
	
	/** displayName: Display name of room to be changed*/
	private String displayName = "";
	
	/** expectedResult: Message of error display name*/
	private String expectedResult = "Display Name must not be empty";
	
	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
	
	  /**
	  * This method performs the test case:Verify that the Display Name 
	  * text field is an obligatory field
	  */
	@Test
	public void VerifyRoomIsCanceled() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.setCredentials()
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage= homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage roomInfoPage = conferenceRoomPage
				.doubleClickOnRoom(roomName);

		roomInfoPage.setDisplayNameRoom(displayName)
				.clickButtonSaveInfoRoom();
		
		String errorMessageDisplayName = roomInfoPage
				.verificationRoomWithoutDisplayName(expectedResult);
		
		Assert.assertEquals(errorMessageDisplayName, expectedResult);
	}
}
