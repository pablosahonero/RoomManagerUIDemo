package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyLocationRoomIsSet class contains the test case 
 * (with pre and post conditions): Verify that the location 
 * of a room is set.
 * @author Milenca Ventura
 *
 */

public class VerifyLocationRoomIsSet extends TestBase{
	
	/** locationName: Name of location to be created*/
	private String locationName = "locationName";
	
	/** locationDisplayName: Display name of location to be created*/
	private String locationDisplayName = "locationDisplayName";
	
	/** locationDescription: Description of location to be created*/
	private String locationDescription = "Description location";
	
	/** roomName: Name of the room*/
    private String roomName = "Room08";
	 
    /** 
	  * errorMessage: It contains the error message that would appear 
	  * if test case fails.
	  */
	private String errorMessage = "The test cases failed: the location"
			+ "of the room does not set.";
	
	@BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
	
	  /**
	  * This method performs the test case:Verify that the location of 
	  * a room is set.
	  */
	@Test
	public void VerifyRoomNameUpdateIsDisable() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.setCredentials()
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage = homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage roomInfoPage = conferenceRoomPage
				.doubleClickOnRoom(roomName);
		
		roomInfoPage.clickLocationButton()
				.clickLocationTypeButton()
				.clickOnLocation(locationName)
				.clickLocationButton();
		
		boolean IsFill = roomInfoPage.IsLocationTextFieldFilled(locationName);
		
		Assert.assertEquals( IsFill, true,errorMessage);
		
		roomInfoPage.clickButtonCancelInfoRoom();
	}
	
	/**
	 * Method BeforeTest: Create the location that was used in the Test.
	 */
	@BeforeTest
	public void beforeTesr(){
		LocationApi.createLocation(locationName, locationDisplayName, 
				 locationDescription);
	}
	
	/**
	 * Method AfterTest Delete the location that was created in the Test.
	 */
	@AfterTest
	public void afterTest(){
		LocationApi.deleteLocationByName(locationName);
	}
}
