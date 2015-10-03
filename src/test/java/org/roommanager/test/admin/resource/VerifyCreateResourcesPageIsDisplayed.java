package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The VerifyCreateResourcesPageIsDisplayed class contains the test case 
 * Verify that CreateResource Page  is displayed 
 * 
 * @author Rodrigo Zarate
 *
 */
public class VerifyCreateResourcesPageIsDisplayed extends TestBase {
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the CreateResource Page "
			+ "is not displayed";

	/**
	 * This method performs the test case: Verify that CreateResource Form  
	 * is displayed.
	 */
    @Test
    public void verifyCreateResourcePageIsDisplayed() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		CreateResourcePage create = resources.clickAddResourceButton();
		
		Assert.assertTrue(create.isCreateResourcePagePresent()
				,errorMessage);
    }
	
	

}
