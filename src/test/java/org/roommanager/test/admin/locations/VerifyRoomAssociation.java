package org.roommanager.test.admin.locations;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.locations.LocationAssociationsPage;
import org.roommanager.framework.pages.admin.locations.LocationsInfoPage;
import org.roommanager.framework.pages.admin.locations.LocationsPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyRoomAssociation extends TestBase {
	
	/** resourceName: Name of room to be used*/
	private String roomName = null ;
	
	/** name: represents the location's name to be created*/
	private String locationName = "Location Test";
	
	/** displayName: represents the location's display name to be created*/
	private String locationDisplayName = "Location Display Name";
	
	/** description: represents the location's description to be created*/
	private String locationDescription = "Location Description";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The Test failed because the Location "
								  + "couldn't be associated a the specified Room";
	
	/**
     * This method creates a Location
     */
    @BeforeTest
    public void beforeTest(){
		roomName = PropertiesReader.getRoomName();
    	if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
    	LocationApi.createLocation(locationName, locationDisplayName, 
    							   locationDescription);
    }
	
	/**
	 * Test method: Verify that a Room can be associated to a Location.
	 */
    @Test
    public void verifyRoomAssociation() {
    	
    	LoginPage login = new LoginPage(driver);
	  
    	HomePage home = login
				.setCredentials()
				.clickSignInButton();
	  
    	LocationsPage locations = home.selectLocationsLink();
	  
    	LocationsInfoPage locationInfo = locations
    									 .doubleClickOnLocation(locationName);
    	
    	LocationAssociationsPage associations = locationInfo
    		.clickOnLocationAssociationsInfoLink()
    		.clickOnAssociateRoomButton(roomName);
    	
    	locations = associations
    				.clickOnSaveButton();
    	
    	locationInfo = locations
				 	   .doubleClickOnLocation(locationName);
    	
    	associations = locationInfo
        			   .clickOnLocationAssociationsInfoLink();
    	
    	String actualRoomName = associations
    							.getAssociatedRoomName(roomName);

		//TODO: Assertions at API level
    	Assert.assertEquals(actualRoomName, roomName, errorMessage);

    }
  
    /**
     * This method removes the Location that was created by the test
     */
    @AfterTest
    public void afterTest(){
    	LocationApi.deleteLocationByName(locationName);
    }
}
