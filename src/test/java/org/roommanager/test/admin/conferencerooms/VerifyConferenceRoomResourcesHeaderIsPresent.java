package org.roommanager.test.admin.conferencerooms;

import org.junit.Assert;
import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyConferenceRoomResourcesHeaderIsPresent extends TestBase {
	/** 
	 * errorMessage: It contains the error message that is displayed 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because Conference Room Header is not Present";
	
	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
	
	/**
	 * verifyConferenceRoomResourcesHeaderIsPresent: Check that after 
	 * clicking on any Room of RoomTable, the Room Form page is displayed.
	 */
	@Test
	public void verifyConferenceRoomResourcesHeaderIsPresent() {
		LoginPage login = new LoginPage(driver);
		
		ConferenceRoomPage conferenceRoom = login
				.setCredentials()
				.clickSignInButton()
				.selectConferenceRoomsLink();
			
		boolean isConferenceRoomHeaderPresent = 
				conferenceRoom.isResourceHeaderPresent() && 
				conferenceRoom.isRoomsTableHeaderPresent();
		
		Assert.assertTrue(errorMessage ,isConferenceRoomHeaderPresent);
	} 
}
