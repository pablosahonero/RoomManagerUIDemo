package org.roommanager.test.admin.locations;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.locations.LocationsPage;
import org.roommanager.framework.pages.admin.locations.RemoveLocationsPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

/**
 * This class contains a test case of Locations feature
 * @author Jimmy Maldonado
 *
 */
public class VerifyLocationDeleted extends TestBase{
	
	/** name represents the location's name to be created*/
	private String name = "Location Test";
	
	/** 
	 * displayName represents the location's display name 
	 * to be created
	 */
	private String displayName = "Location Display Name";
	
	/** 
	 * description represents the location's description 
	 * to be created
	 */
	private String description = "Location Description";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the location "
			+ "was found";
	
	/**
	 * Test case: The application must allow to delete of a Locations 
	 * in the locations Forms by selecting a location.
	 */
    @Test
    public void verifyLocationDeleted() {
    	
    	LoginPage login = new LoginPage(driver);
  	  
    	HomePage home = login
				.setCredentials()
				.clickSignInButton();
	  
    	LocationsPage locations = home.selectLocationsLink();
	  
    	RemoveLocationsPage removeLocation = locations
    			.checkLocation(name)
    			.clickRemoveButton();
    	
    	locations = removeLocation.clickRemoveButton();
    	
    	boolean existLocation = locations.isLocationPresent(name);
    	
    	Assert.assertFalse(existLocation, errorMessage);
    }
    
    /**
     * This method creates a new locations in order to use it 
     * in the test case
     */
    @BeforeTest
    public void beforeTest() {
    	LocationApi.createLocation(name, displayName, description);
    }
}
