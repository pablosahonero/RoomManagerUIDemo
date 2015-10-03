package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyResourcePaginationInvalid extends TestBase{
	
	/** invalidPage represents an invalid page*/
	private String invalidPage = "abc";
	
	/** 
	 * expectedPage represents the expected result to the pagination 
	 * text field.
	 * */
	private String expectedPage = "";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails.
	 */
	private String errorMessage = "The test failed because entered page "
			+ "was found";
	
	/**
	 * This method contains the steps for the test case: Verify that 
	 * the pagination text field in the table of the resource does not 
	 * accepted alphabetic values.
	 */
    @Test
    public void verifyResourcePaginationInvalid() {

		LoginPage login = new LoginPage(driver);
		
		HomePage homePage = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resource =  homePage.selectResourcesLink();
		
		resource.setPaginationTextField(invalidPage);
		
		String actualPage = resource.getPaginationTextField();
		
		Assert.assertEquals(expectedPage, actualPage, errorMessage);
		
    }
}
