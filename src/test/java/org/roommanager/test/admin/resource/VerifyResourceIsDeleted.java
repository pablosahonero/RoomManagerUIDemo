package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.RemoveResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyResourceIsDeleted class contains the test case 
 * with PRE - POST Conditions by API
 * Verify that a Resource is removed 
 * 
 * @author Rodrigo Zarate
 *
 */
public class VerifyResourceIsDeleted extends TestBase{
	
	 /** resourceName Name of the resource to be Created*/
	 private String resourceName = "Resource01";
	 
	 /** resourceDisplayName Display Name of the resource to be Created*/
    private String resourceDisplayName = "Resource01";
    
    /** resourceDescription Description of the resource to be Created*/
    private String resourceDescription = "Description Resource01";
    
    /** resourceIcon Icon of the resource to be Created*/
    private String resourceIcon = "";
    
    /** errorMessage error value that is displayed on the report if the test case is failed*/
	private String errorMessage = "The test failed because the created Resource was not deleted";
	
	 /**
	 * Method BeforeTest Create a resource to be updated in the test.
	 */
	@BeforeTest
	 public void BeforeTest(){
	    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}
	
	/**
	 * Method that execute the test case to verify that a Resource  
	 * is Deleted
	 */
  	@Test
	public void verifyResourceIsDeleted() throws Exception {
		
		LoginPage login = new LoginPage(driver);
		
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
					 resources.clickOnResourceFromTable(resourceName);
				
		RemoveResourcePage removeResource = resources.clickRemoveResourceButton();
							removeResource.clickRemoveResourceButton();
										
		Assert.assertTrue(resources.verifyElementDoesNotExist(resourceName)
						,errorMessage);
  	}
	
}
