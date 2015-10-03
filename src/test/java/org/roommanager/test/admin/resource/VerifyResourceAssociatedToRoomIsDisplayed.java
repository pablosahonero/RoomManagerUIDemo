package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourceAssociationsPage;
import org.roommanager.framework.pages.admin.resource.ResourceInfoPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyResourceAssociatedToRoomIsDisplayed class contains the test case 
 * Verify that room associated to a Resource is Displayed
 * in the Resource Association Page 
 * 
 * @author Rodrigo Zarate
 *
 */
public class VerifyResourceAssociatedToRoomIsDisplayed extends TestBase {
	
	/** resourceName Name of resource to be created*/
	private String resourceName = "TestResource";
	
	/** resourceDisplayName Display name of resource to be created*/
	private String resourceDisplayName = "TestResource";
	
	/** resourceDescription Description of resource to be created*/
	private String resourceDescription = "Description TestResource";
	
	/** resourceIcon Icon of resource to be created*/
	private String resourceIcon = "fa fa-desktop";
	
	/** quantity Quantity of resource to be created*/
	private String quantity = "10";
	
	/** roomName name of room to associate the resource*/
	private String roomName = "Room01";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the "
			+ "room associated to the resource was not found";
	
	/**
	 * Method BeforeTest Create a resource by API
	 * and then associate this resource to a room by API.
	 */
	@BeforeTest
    public void beforeTest() {
		roomName = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null){
    		EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
    				PropertiesReader.getExchangePassWord(),
    				PropertiesReader.getExchangeHostName());
		}
    	ResourceApi.createResource(resourceName, resourceDisplayName
    			, resourceIcon, resourceDescription);
    	RoomApi.associateResourceToRoom(roomName, resourceName, quantity);
    }
	
	 @Test
    public void verifyResourceAssociatedToRoomIsDisplayed() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		ResourceInfoPage roomInfo = resources
				.doubleClickOnResourceFromTable(resourceName);

		ResourceAssociationsPage association = roomInfo
				.clickResourceAssociationLink();
		
		String actualRoom = association
				.getRoomNameAssociated(roomName);
		
		Assert.assertEquals(actualRoom, roomName, errorMessage);
    }
	 
	/**
	 * Method afterTest dissociate the resource of the room by API
	 * and then delete the resource created by API.
	 */  
    @AfterTest
    public void afterTest() {
    	RoomApi.dissociateAllResourceFromRoom(roomName);
    	ResourceApi.deleteResourceByName(resourceName);
    }

}
