package org.roommanager.test.admin.conferencerooms;

import org.junit.Assert;
import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.ResourceAssociationsPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * The VerifyDisassociateResourceFromRoom class contains the test case: 
 * "Verify that is possible to dissociate a resource from a room".
 */
public class VerifyDisassociateResourceFromRoom extends TestBase {

	/** roomName: Name of room with the associated resource */
	private String roomName = null;

	/** resourceName: Name of resource to be created */
	private String resourceName = "ResourceTest";

	/** resourceDisplayName: Display name of resource to be created */
	private String resourceDisplayName = "ResourceTest";

	/** resourceDescription: Description of resource to be created */
	private String resourceDescription = "Description ResourceTest";

	/** resourceIcon: Icon of resource to be created */
	private String resourceIcon = "fa fa-desktop";
	
	/** quantity: Quantity of resources to be created */
	private String quantity = "2";

	/**
	 * errorMessage: It contains the error message that would appear if test
	 * case fails
	 */
	private String errorMessage = "The test failed because the associated "
			+ "resource was not disassociated from the room";

	/**
	 * BeforeTest method creates a resource and
	 * it associates to a room .
	 */
	@BeforeTest
	public void beforeTest() {
		roomName = PropertiesReader.getRoomName();

		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		ResourceApi.createResource(resourceName, resourceDisplayName,
				resourceIcon, resourceDescription);
		RoomApi.associateResourceToRoom(roomName, resourceName, quantity);
		
	}

	/**
	 * The verifyDisassociateResourceFromRoom method performs the test case:
	 * Verify that is possible to dissociate a resource from a room.
	 */
	@Test
	public void verifyDisassociateResourceFromRoom(){
		LoginPage login = new LoginPage(driver);
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		ConferenceRoomPage conferenceRoom = home.selectConferenceRoomsLink();
		ResourceAssociationsPage resourceAssociation = conferenceRoom
				.doubleClickOnRoom(roomName)
				.clickOnResourceAssociations()
				.clickOnDesassociatedResourceButton(resourceDisplayName)
				.clickSaveButton().doubleClickOnRoom(roomName)
				.clickOnResourceAssociations();
		boolean resourceIsAvailable = resourceAssociation.isResourceAvailableOnTheRoom(resourceName);
		Assert.assertTrue(errorMessage, resourceIsAvailable);
		
	}

	/**
	 * AfterTest method deletes the created resource in the BeforeTest method.
	 */
	@AfterTest
	public void afterTest() {
		ResourceApi.deleteResourceByName(resourceName);
	}
}
