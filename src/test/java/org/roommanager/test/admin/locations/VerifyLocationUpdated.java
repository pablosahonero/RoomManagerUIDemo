package org.roommanager.test.admin.locations;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.locations.LocationsInfoPage;
import org.roommanager.framework.pages.admin.locations.LocationsPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * This class contains a test - Verify can set the Name, Display Name and 
 * Description textfield of a Location is editable
 * @author Samuel Vargas
 *
 * */
public class VerifyLocationUpdated extends TestBase {


	/** name represents the location's name to be created*/
	private String name = "Location Test";
	
	/** displayName represents the location's display name to be created*/
	private String displayName = "Location Display Name";
	
	/** description represents the location's description to be created*/
	private String description = "Location Description";
	
	/** nameUpdated represents the location's name to be updated*/
	private String nameUpdated = "Location Test Updated";
	
	/** displayNameUpdated represents the location's display name to be updated*/
	private String displayNameUpdated = "Location Display Name Updated";
	
	/** descriptionUpdated represents the location's description to be updated*/
	private String descriptionUpdated = "Location Description Updated";
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the location "
			+ "was not found";
	
	@BeforeTest
	public void BeforeTest(){
		LocationApi.createLocation(name, displayName, description);
	}
	
	@Test
    public void verifyLocationCreated() {
    	
    	LoginPage login = new LoginPage(driver);
	  
    	HomePage home = login
				.setCredentials()
				.clickSignInButton();
	  
    	LocationsPage locations = home.selectLocationsLink();
	  
    	LocationsInfoPage locationInfo = locations.doubleClickOnLocation(name);
    	locations = locationInfo
    			.setNameTextField(nameUpdated)
    			.setDisplayNameTextField(displayNameUpdated)
    			.setDescriptionTextArea(descriptionUpdated)
    			.clickSaveButton();
    	
    	boolean existLocation = locations.isLocationPresent(nameUpdated);
    	
    	Assert.assertTrue(existLocation, errorMessage);
    }
	
	/**
     * This method removes the created location by the test
     */
    @AfterTest
    public void afterTest(){
    	LocationApi.deleteLocationByName(nameUpdated);
    }
}
