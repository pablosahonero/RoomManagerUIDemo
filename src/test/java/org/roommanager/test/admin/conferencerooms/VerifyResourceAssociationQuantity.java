package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.ResourceAssociationsPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyResourceAssociationQuantity class contains the test case 
 * (with pre and post conditions): Verify that the configuration on 
 * the “ResourceAssociations” Tab is saved with a quantity of the 
 * resource  between 1-3 characters.
 * 
 * @author Milenca Ventura
 *
 */

public class VerifyResourceAssociationQuantity extends TestBase{
	/** resourceName: Name of resource to be created*/
	private String resourceName = "Resource01";
	 
	/** resourceDisplayName: Display name of resource to be created*/
    private String resourceDisplayName = "Resource01";
    
    /** quantity: Quantity of the resources*/
    private String quantity = "789";
    
    /** resourceDescription: Description of resource to be created*/
    private String resourceDescription = "Description Resource01";
    
    /** resourceIcon: Icon of resource to be created*/
	private String resourceIcon = "fa fa-desktop";
	  
	/** roomName: Name of the room*/
    private String roomName = "Room01";
    
     /** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails.
	 */
	 private String errorMessage = "The Resource"+resourceName+"with:"
			 +quantity+ " resources is not displayed"; 
	  
	 /**
	 * This method performs the test case: Verify that the configuration on the 
	 * “ResourceAssociations” Tab is saved with a quantity of the resource 
	 * between 1-3 characters
	 */
	  
	  @Test
	  public void resourceAssociationQuantity() {
		  LoginPage login = new LoginPage(driver);
		  
		  HomePage home = login
				  .setCredentials()
				  .clickSignInButton();
		  
		  ConferenceRoomPage conferenceRoom = home.selectConferenceRoomsLink();
		  
		  ResourceAssociationsPage resourceAssociation = conferenceRoom
				  .doubleClickOnRoom(roomName)
				  .clickOnResourceAssociations();
		  
		  resourceAssociation.clickOnAddResourceButton(resourceDisplayName)
		  		  .setQuantityOfResource(resourceName, quantity)
		  		  .clickSaveButton();

		  conferenceRoom.doubleClickOnRoom(roomName)
		  		.clickOnResourceAssociations();
		  
		  Boolean quantityResourceAssociated = resourceAssociation
					.isQuantityResourceDisplayed(resourceDisplayName, quantity);
		  
		  Assert.assertTrue( quantityResourceAssociated,errorMessage);
		  
		  resourceAssociation.clickCancelButton();
	  }
	  
	  /**
	  * beforeTest: This method creates a resource that will be used 
	  * in the test case.
	  */
	  @BeforeTest
	  public void beforeTest(){
		  if(EmailServerApi.getEmailServiceId() == null){
				EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
												 PropertiesReader.getExchangePassWord(),
												 PropertiesReader.getExchangeHostName());
		  }
		  ResourceApi.createResource(resourceName, resourceDisplayName, 
				  					 resourceIcon, resourceDescription);
	  }
	  
	  /**
	  * afterTest: This method deletes the created resource in the 
	  * beforeTest method.
	  */
	  @AfterTest
	  public void afterTest(){
		ResourceApi.deleteResourceByName(resourceName);
	  }
}
