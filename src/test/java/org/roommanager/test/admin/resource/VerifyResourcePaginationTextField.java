package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This class contains a resouce's test case
 * @author Jimmy Maldonado
 *
 */
public class VerifyResourcePaginationTextField extends TestBase{
	
	/** 
	 * expectedPage represents the page number from resource's 
	 * page
	 * */
	private String expectedPage = "10";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because entered page "
			+ "was not found";
	
	/**
	 * This method contains the steps for the test case: Verify 
	 * that the pagination text field in the table of the resource 
	 * accept only int numbers.
	 */
    @Test
    public void verifyResourcePaginationTextField() {
    	
    	LoginPage login = new LoginPage(driver);
		
		HomePage homePage = login
				.setCredentials()
				.clickSignInButton();
		
		ResourcePage resource =  homePage.selectResourcesLink();
		
		resource.setPaginationTextField(expectedPage);
		
		String actualPage = resource.getPaginationTextField();
		
		Assert.assertEquals(actualPage, expectedPage, errorMessage);
    }
}
