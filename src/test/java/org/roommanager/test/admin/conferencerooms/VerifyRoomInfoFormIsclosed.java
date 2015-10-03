package org.roommanager.test.admin.conferencerooms;

import org.junit.Assert;
import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyRoomInfoFormIsclosed extends TestBase {
	
	/** roomName: Name of the room */
	private String roomName = "Room10";
	
	/**
	 * errorMessage: It contains the error message that would appear if test
	 * case fails
	 */
	private String errorMessage = "The test failed because the Room Info Page was not closed";

	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}

	/**
	 * The verifyRoomInfoFormIsclosed method performs the test case:
	 * "Verify that the Room Info form can be closed when pressing the close icon"
	 * .
	 */
	@Test
	public void verifyRoomInfoFormIsclosed() {
		LoginPage login = new LoginPage(driver);
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		ConferenceRoomPage conferenceRoom = home.selectConferenceRoomsLink()
				.doubleClickOnRoom(roomName)
				.clickOnRoomInfoLink()
				.clickCloseButton();
		
		Assert.assertTrue(errorMessage,conferenceRoom.isRoomFormClosed());
				
	}
}
