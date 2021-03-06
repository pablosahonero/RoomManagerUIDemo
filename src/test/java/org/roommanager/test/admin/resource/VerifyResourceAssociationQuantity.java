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
 * This class contains a test case to Resources feature 
 * @author Jimmy Maldonado
 *
 */
public class VerifyResourceAssociationQuantity extends TestBase{
	
	/** resourceName: Name of resource to be created*/
	private String resourceName = "TestResource";
	
	/** resourceDisplayName: Display name of resource to be created*/
	private String resourceDisplayName = "TestResource";
	
	/** resourceDescription: Description of resource to be created*/
	private String resourceDescription = "Description TestResource";
	
	/** resourceIcon: Icon of resource to be created*/
	private String resourceIcon = "fa fa-desktop";
	
	/** 
	 * roomName represents the conference room to be associated to 
	 * a resource
	 * */
	private String roomName = "Room01";
	
	/**
	 * quantity represents the resource's quantity associated to 
	 * the conference room
	 * */
	private String quantity = "10";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the "
			+ "resource's quantity was not found";
	
    @Test
    public void verifyResourceAssociationQuantity() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		ResourceInfoPage roomInfo = resources
				.doubleClickOnResourceFromTable(resourceName);

		ResourceAssociationsPage association = roomInfo
				.clickResourceAssociationLink();
		
		boolean correctQuantity = association
				.isResourceQuantityEquals(quantity, roomName);
		
		Assert.assertTrue(correctQuantity, errorMessage);
    }
    
    /**
     * This method creates a resource and associate it with a conference
     * room
     * */
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

    /**
     * This method removes the resource's association and the resource
     * created
     */
    @AfterTest
    public void afterTest() {
    	RoomApi.dissociateAllResourceFromRoom(roomName);
    	ResourceApi.deleteResourceByName(resourceName);
    }

}
