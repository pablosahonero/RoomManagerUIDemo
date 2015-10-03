package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
/**
 * 
 * The class is a test that verify if a resource was created
 * 
 */
public class VerifyResourceIsCreated extends TestBase {
	
	/** resourceName value the resource to be created*/
	private String resourceName = "TestResource";	
	
	/** resourceDisplayName property value of the resource to be created */
	private String resourceDisplayName = "TestResource";
	
	/** resourceDescription property value of the resource to be created*/
	private String resourceDescription = "Description TestResource";
	
	/** errorMessage error value that is displayed on the report if the test case is failed*/
	private String errorMessage = "The test failed because the created Resource was not found";

	/**
	 * Method that execute the test case to verify that a resource can be created
	 */
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
		
		resources = createResource.clickSaveResourceButton();
		
		String actualResourceName = resources
				.getResourceNameInTable(resourceName);

		//TODO: Add assertion at API LEVEL
		Assert.assertEquals( actualResourceName, resourceName,errorMessage + "BUG: UI - After creating the an OutOfOrder the icon it is not displayed in the GRID");
	}

	/**
	 * Method testTearDown delete the resource created in the Test
	 */
	@AfterTest
	public void testTearDown() {
		ResourceApi.deleteResourceByName(resourceName);
	}
}
