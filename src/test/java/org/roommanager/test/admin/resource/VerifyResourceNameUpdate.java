package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourceInfoPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the acceptance test case for verify if a room 
 * is updated
 * @author Paulo Ormachea
 *
 */
public class VerifyResourceNameUpdate extends TestBase{
	
	 /** resourceName: Name of resource to be created*/
	 private String resourceName = "Resource01";
	 
	 /** resourceDisplayName: Display Name of resource to be created*/
     private String resourceDisplayName = "Resource01";
     
     /** resourceDescription: Description of resource to be created*/
     private String resourceDescription = "Description Resource01";
     
     /** resourceIcon: Icon of the resource to be created*/
     private String resourceIcon = "";
     
     /** resourceNameUpdate: New Name for the  resource*/
     private String resourceNameUpdate = "NewTestResource";

 	/**
 	 * This method performs the test case: Verify that the [Display Name] 
 	 * text_box field does not allow empty values, when creating a resource.
 	 */
  	@Test
	public void verifyResourceNameUpdate() throws Exception {
  		
  		/** 
  		 * errorMessage: It contains the error message that would appear 
  		 * if test case fails
  		 */
  		String errorMessage = "The resource name is not changed";
		
  		LoginPage login = new LoginPage(driver);
		
  		HomePage homePage = login
				.setCredentials()
				.clickSignInButton();
		ResourcePage resource =  homePage
				.selectResourcesLink();
		
		ResourceInfoPage resourcePage = resource
				.doubleClickOnResourceFromTable(resourceName);
		
		resource = resourcePage.enterResourceName(resourceNameUpdate)
				.clickSaveButton();
		
		String actualResourceName = resource
				.getResourceNameInTable(resourceNameUpdate);
		
		assertEquals(errorMessage, actualResourceName, resourceNameUpdate);
  	}

	/** 
	 * Precondition: It contains the method for create a resource for 
	 * use during the test.
	 */
  	@BeforeTest
	 public void BeforeTest(){
	    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}

	/** 
	 * PostCondition: It contains the method for delete a resosurce 
	 * use during the test.
	 */
	@AfterTest
	public void AfterTest(){
		ResourceApi.deleteResourceByName(resourceNameUpdate);
	}
 
}