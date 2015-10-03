package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyRoomResourcesFilter extends TestBase{
	
	/** resourceName: Name of resource to be created*/
	private String resourceName = "Test Resource";
	
	/** resourceDisplayName: Display name of resource to be created*/
	private String resourceDisplayName = "Test Resource";
	
	/** resourceIcon: Icon of resource to be created*/
	private String resourceIcon = "fa fa-desktop";
	
	/** resourceDescription: Description of resource to be created*/
	private String resourceDescription = "Description Resource";
	
	/** 
	 * errorMessage: It contains the error message that is displayed 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the created Resource wasn't available";
	
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
	 * verifyRoomResourcesFilter: Verify that a resource is displayed 
	 * as Rooms table when it is icon is selected in Resources Header
	 */
	@Test
	public void verifyRoomResourcesFilter() {
		LoginPage login = new LoginPage(driver);
		
		ConferenceRoomPage conferenceRoom = login
				.setCredentials()
				.clickSignInButton()
				.selectConferenceRoomsLink()
				.clickOnResource(resourceName);
		
		String actualResourceHeader = conferenceRoom
									  .getResourceHeaderName(resourceName);
		
		Assert.assertEquals(actualResourceHeader, resourceName, errorMessage);
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
