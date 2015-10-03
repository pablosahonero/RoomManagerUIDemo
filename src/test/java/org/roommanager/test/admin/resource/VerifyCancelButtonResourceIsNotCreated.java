package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The VerifyCancelButtonResourceIsNotCreated class contains the test case 
 * Verify that after clicking on the Cancel button 
 * the resource is not created
 * 
 * @author Samuel Vargas A.
 *
 */ 

public class VerifyCancelButtonResourceIsNotCreated extends TestBase{
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the Resource "
			+ "is created";
	/** resourceName value the resource to be created*/
	private String resourceName = "TestResource";	
	
	/** resourceDisplayName property value of the resource to be created */
	private String resourceDisplayName = "TestResource";
	
	/** resourceDescription property value of the resource to be created*/
	private String resourceDescription = "Description TestResource";
		

	@Test
    public void verifyAResourceIsCreated() {
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = adminHome.selectResourcesLink();
		
		CreateResourcePage createResource = resources.clickAddResourceButton()
				.enterResourceName(resourceName)
				.enterResourceDisplayName(resourceDisplayName)
				.enterResourceDescription(resourceDescription);		
		resources = createResource.clickCancelResourceButton();
						
		Assert.assertTrue(resources.verifyElementDoesNotExist(resourceName), errorMessage);
	}
	
}
