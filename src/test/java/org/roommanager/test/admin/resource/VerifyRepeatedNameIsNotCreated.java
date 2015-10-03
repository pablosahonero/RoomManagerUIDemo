package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyRepeatedNameIsNotCreated class contains the test case 
 * (with pre and post conditions): Verify that is not possible 
 * to create a resource with the repeated [Name] value.
 * 
 * @author Jimmy Maldonado
 *
 */
public class VerifyRepeatedNameIsNotCreated extends TestBase{
	
	/** resourceName: Name of resource to be created*/
	private String resourceName = "TestResource";
	
	/** resourceDisplayName: Display name of resource to be created*/
	private String resourceDisplayName = "TestResource";
	
	/** resourceDescription: Description of resource to be created*/
	private String resourceDescription = "Description TestResource";
	
	/** resourceIcon: Icon of resource to be created*/
	private String resourceIcon = "fa fa-desktop";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the error "
			+ "message was not displayed";

	/**
	 * This method performs the test case: Verify if is possible create 
	 * a resource with the repeated [Name] value.
	 */
    @Test
    public void verifyRepeatedNameIsNotCreated() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		CreateResourcePage create = resources.clickAddResourceButton()
				.enterResourceName(resourceName)
				.enterResourceDisplayName(resourceDisplayName)
				.enterResourceDescription(resourceDescription);
		
		create.clickSaveButtonInvalidData();
		
		Assert.assertTrue(create.isRepeatedNameErrorMessagePresent()
				,errorMessage);
    }
    
    /**
     * beforeTest: This method creates a resource that will be used 
     * in the test case.
     */
    @BeforeTest
    public void beforeTest(){
    	ResourceApi.createResource(resourceName, resourceDisplayName
    			, resourceIcon, resourceDescription);
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
