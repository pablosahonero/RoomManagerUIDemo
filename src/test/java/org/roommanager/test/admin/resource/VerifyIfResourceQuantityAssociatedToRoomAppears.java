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
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

/**
 * The VerifyIfResourceQuantityAssociatedToRoomAppears class contains
 * the test case:  
 * "Verify if resource quantity associated to a room is displayed on [Quantity] column 
 * in [Resource Associations] form when it is selected"
 */
public class VerifyIfResourceQuantityAssociatedToRoomAppears extends TestBase{
	
	/** resourceName: Name of resource to be created*/
	private String resourceName = "Resource";
	
	/** resourceDisplayName: Display name of resource to be created*/
	private String resourceDisplayName = "Resource";
	
	/** resourceDescription: Description of resource to be created*/
	private String resourceDescription = "Description Resource";
	
	/** resourceIcon: Folder Icon of resource to be created*/
	private String resourceIcon = "fa fa-folder";
	
	/** 
	 * roomName represents the conference room to be associated to 
	 * a resource
	 * */
	private String roomName = null;
	
	/**
	 * quantity represents the resource's quantity associated to 
	 * the conference room
	 * */
	private String quantity = "5";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The resource quantity associated to a room is not displayed";
    
	/**
	 * Method BeforeTest:
	 * Verify first if exists an email server in Room manager then
	 * Create a resource by API
	 * Associate this resource with a quantity to a room by API.
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
    public void verifyIfResourceQuantityAssociatedToRoomAppears() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		ResourceInfoPage roomInfo = resources
				.doubleClickOnResourceFromTable(resourceName);

		ResourceAssociationsPage association = roomInfo
				.clickResourceAssociationLink();

		Assert.assertTrue(association
				.isResourceQuantityEquals(quantity, roomName)
				, errorMessage);
    }

	/**
	 * Method afterTest:
	 * Dissociate the resource of the room by API
	 * Delete the resource created by API.
	 */
    @AfterTest
    public void afterTest() {
    	RoomApi.dissociateAllResourceFromRoom(roomName);
    	ResourceApi.deleteResourceByName(resourceName);
    }

}
