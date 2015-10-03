package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourceInfoPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyEmptyNameIsNotUpdated class contains the test case 
 * (with pre and post conditions): Verify that is not possible 
 * to update a resource with the empty name.
 * 
 * @author Rodrigo Zarate
 *
 */
public class VerifyEmptyNameIsNotUpdated extends TestBase {

	/** resourceName Name of the resource to be Created*/
	 private String resourceName = "Resource01";
	 
	 /** resourceName Name of the resource to be Created*/
	 private String resourceNameEmpty = "";
	 
	 /** resourceDisplayName Display Name of the resource to be Created*/
    private String resourceDisplayName = "Resource01";
    
    /** resourceDescription Description of the resource to be Created*/
    private String resourceDescription = "Description Resource01";
    
    /** resourceIcon Icon of the resource to be Created*/
    private String resourceIcon = "";
    
    /** 
	 * errorMessage It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the error "
			+ "message was not displayed";
	
	
	 /**
	 * Method BeforeTest Create a resource to be updated in the test by API.
	 */
	@BeforeTest
	 public void BeforeTest(){
	    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}
	
	/**
	 * Method that execute the test case to verify that Display Name of a 
	 * Resource is updated
	 */
	@Test
    public void verifyEmptyNameIsNotUpdated() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		ResourceInfoPage create = resources
				.doubleClickOnResourceFromTable(resourceName)
				.enterResourceName(resourceNameEmpty);
		
		create.clickSaveButtonInvalidData();
		
		Assert.assertTrue(create.isEmptyNameFieldErrorMessagePresent()
				,errorMessage);
    }
	
	/**
	 * Method AfterTest delete a created resource in the test by API.
	 */
	@AfterTest
	public void AfterTest(){
		ResourceApi.deleteResourceByName(resourceName);
	}

}
