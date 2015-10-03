package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The VerifyResourceResourceDisplayName class contains the test case 
 * (with pre ):Verify that the save a name in [Name] text_box field this 
 * does not allow empty value
 * 
 * @author Samuel Vargas A.
 *
 */ 

public class VerifyEmptyNameIsNotCreated extends TestBase {
	
	/** resourceName: Name of resource with empty values to be created*/
	private String resourceName = " ";
	
	/** resourceDisplayName: Display name of resource to be created*/
	private String resourceDisplayName = "TestResource";
	
	/** resourceDescription: Description of resource to be created*/
	private String resourceDescription = "Description TestResource";
	
	 /** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the error "
			+ "message was not desplayed";

	@Test
	public void verifyEmptyNameIsNotCreated() {
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = adminHome.selectResourcesLink();
		
		CreateResourcePage createResource = resources.clickAddResourceButton()
				.enterResourceName(resourceName)
				.enterResourceDisplayName(resourceDisplayName)
				.enterResourceDescription(resourceDescription);				
				createResource.clickSaveButtonInvalidData();
				
		Assert.assertTrue(createResource.isEmptyNameErrorMessagePresent()
				,errorMessage);
	}

}
