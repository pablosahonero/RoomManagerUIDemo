package org.roommanager.test.admin.locations;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.locations.LocationsInfoPage;
import org.roommanager.framework.pages.admin.locations.LocationsPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/**
 * This class contains a test case of Locations feature
 * @author Jimmy Maldonado
 *
 */
public class VerifyLocationCreated extends TestBase{
	
	/** name represents the location's name to be created*/
	private String name = "Location Test";
	
	/** displayName represents the location's display name to be created*/
	private String displayName = "Location Display Name";
	
	/** description represents the location's description to be created*/
	private String description = "Location Description";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the location "
			+ "was not found";
	
	/**
	 * Test method: Verify than a new location can be created
	 */
    @Test
    public void verifyLocationCreated() {
    	
    	LoginPage login = new LoginPage(driver);
    	HomePage home = login
						.setCredentials()
						.clickSignInButton();

    	LocationsPage locations = home.selectLocationsLink();
	  
    	LocationsInfoPage locationInfo = locations.clickAddButton();
    	
    	locations = locationInfo
    			.setNameTextField(name)
    			.setDisplayNameTextField(displayName)
    			.setDescriptionTextArea(description)
    			.clickSaveButton();
    	
    	boolean existLocation = locations.isLocationPresent(name);
    	
    	Assert.assertTrue(existLocation, errorMessage);
    }
  
    /**
     * This method removes the created location by the test
     */
    @AfterTest
    public void afterTest(){
    	LocationApi.deleteLocationByName(name);
    }
}
