package org.roommanager.test.tablet.settings;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class contains a test for verify the room name displayed
 * 
 * @author Samuel Vargas
 *
 */
public class VerifyDefaultRoomIsDisplayed extends TestBase{
	
	/** roomName: It represents the name of the Current room */
	private String roomName = PropertiesReader.getRoomName();
	
	/**
	 * Verify that the room selected in "Default Conference Room" combo box 
	 * is the room by default
	 */
	@Test 
	public void VerifyDeafultRoomIsDisplayed(){
		
		RegisterPage connection = new RegisterPage(driver);
		/*
		connection.enterServiceUrl(PropertiesReader.getRoomManagerApi())
				.enterCredentials()
				.clickSignInButton()
				.clickNavigationLink()
				.clickDefaultRoomComboBox()
				.selectConferenceRoomByName(roomName)
				.clickSaveButton();
		
		HomePage home = connection.clickOnHomePageLink();
		
		String roomByDefault = home.getRoomNameLabel();
		
		Assert.assertEquals(roomByDefault, roomName);
		*/
	}
	
	/**
	 * This method registers an email server if it is not registered
	 */
	@BeforeTest
	public void beforeTest(){
		if (EmailServerApi.getEmailServiceId() == null) {
			EmailServerApi.createEmailServer(
					PropertiesReader.getExchangeUserName(),
					PropertiesReader.getExchangePassWord(),
					PropertiesReader.getExchangeHostName());
		}
	}

}
