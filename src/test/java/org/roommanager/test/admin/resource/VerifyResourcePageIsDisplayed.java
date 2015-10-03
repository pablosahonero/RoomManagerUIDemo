package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *  The VerifyResourcePage class contains the test case 
 *  Verify that after clicking on the Resources Tab, 
 *  the Resources Page is displayed.
 * 
 * @author Samuel Vargas A.
 *
 */ 

public class VerifyResourcePageIsDisplayed extends TestBase{
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the Resource Page "
			+ "is not displayed";
	
	@Test
	public void VerifyResourcePageDisplayed() throws Exception {
		
		LoginPage login = new LoginPage(driver);
		HomePage homePage = login
				.setCredentials()
				.clickSignInButton();
		ResourcePage resource =  homePage
				.selectResourcesLink();
		
		Assert.assertTrue(resource.isResourcePagePresent(), errorMessage);				 								
  	}
	
	

}
