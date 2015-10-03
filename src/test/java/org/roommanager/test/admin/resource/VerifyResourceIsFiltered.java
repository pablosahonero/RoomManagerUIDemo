package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyResourceIsFiltered class contains the test case 
 * (with pre and post conditions):  Verify if resources that matched with 
 * the filter name are displayed in the Roms table when items quantity are 
 * from 1-50 also page size
 * 
 * @author Samuel Vargas A.
 *
 */
public class VerifyResourceIsFiltered extends TestBase{
	
	/** resourceName value the resource to be created*/
	private String resourceName = "TestResource";	
	
	/** resourceDisplayName property value of the resource to be created */
	private String resourceDisplayName = "TestResource";
	
	/** resourceDescription property value of the resource to be created*/
	private String resourceDescription = "Description TestResource";
	
    /** resourceIcon Icon of the resource to be Created*/
    private String resourceIcon = "";
	
	/** 
	 * errorMessage error value that is displayed on the report if the test 
	 * case is failed
	 * */
	private String errorMessage = "The test failed because the filter Resource "
			+ "was not found";

	 /**
	 * Method BeforeTest Create a resource to be search by search text.
	 */
	
	@BeforeTest
	 public void BeforeTest(){
	    ResourceApi.createResource(resourceName, resourceDisplayName,resourceIcon,
	    		resourceDescription);
	}
	
	@Test
    public void VerifyIfResourceIsFiltered() {
    		
	LoginPage login = new LoginPage(driver);
	
	HomePage home = login
			.setCredentials()
			.clickSignInButton();
	
	ResourcePage resources = home.selectResourcesLink()	
		.filterResourceByName(resourceName);
		String expectedResource = resources.getResourceNameInTable(resourceName);
		
		Assert.assertEquals(resourceName, expectedResource,errorMessage);		
	}
	
	/**
	 * Method AfterTest delete a created resource in the test by API.
	 */
	@AfterTest
	public void AfterTest(){
		ResourceApi.deleteResourceByName(resourceName);
	}
}
