package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This class contains the a Negative test case for the 
 * Resources feature
 * @author Jimmy Maldonado
 *
 */
public class VerifyEmptyDisplayNameIsNotCreated extends TestBase{
	
	/** resourceName: Name of resource to be created*/
	private String resourceName = "TestResource";
	
	/** resourceDisplayName: Display name of resource to be created*/
	private String emptyDisplayName = "";
	
	/** resourceDescription: Description of resource to be created*/
	private String resourceDescription = "Description TestResource";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the error "
			+ "message was not displayed";

	/**
	 * This method performs the test case: Verify that the [Display Name] 
	 * text_box field does not allow empty values, when creating a resource.
	 */
    @Test
    public void verifyEmptyDisplayNameIsNotCreated() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		CreateResourcePage create = resources.clickAddResourceButton()
				.enterResourceName(resourceName)
				.enterResourceDisplayName(emptyDisplayName)
				.enterResourceDescription(resourceDescription);
		
		create.clickSaveButtonInvalidData();
		
		Assert.assertTrue(create.isEmptyDisplayNameMessagePresent()
				,errorMessage);
    }

}
