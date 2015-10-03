package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourceInfoPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

/**
 * The VerifyResourceDisplayName contains the test case of UI
 * 
 * @author Jimmy Maldonado
 *
 */
public class VerifyResourceDisplayName extends TestBase{
	
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
	private String errorMessage = "The test failed because the resource's "
			+ "display name was not found";
	
	/**
	 * This method performs the test case: Verify that resource 
	 * display name is showed on the Resources Tab.
	 */
    @Test
    public void verifyResourceDisplayName() {
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		ResourceInfoPage create = resources
				.doubleClickOnResourceFromTable(resourceName);

		String displayName = create.getResourceDisplayName();
		
		Assert.assertEquals(displayName, resourceDisplayName
				,errorMessage);
    }
    
    /**
     * beforeTest: This method creates a resource that will be used 
     * in the test case.
     */
    @BeforeTest
    public void beforeTest() {
    	ResourceApi.createResource(resourceName, resourceDisplayName
    			, resourceIcon, resourceDescription);
    }
    
    /**
     * afterTest: This method deletes the created resource in the 
     * beforeTest method.
     */
    @AfterTest
    public void afterTest() {
    	ResourceApi.deleteResourceByName(resourceName);
    }

}
