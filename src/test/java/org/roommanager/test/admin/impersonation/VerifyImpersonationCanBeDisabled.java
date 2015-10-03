package org.roommanager.test.admin.impersonation;

import org.roommanager.framework.pages.admin.impersonation.ImpersonationPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.ImpersonationApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyImpersonationCanBeDisabled class contains the test case: 
 * "Check if the impersonation can be disabled."
 * 
 * @author Milenca Ventura
 * 
 */
public class VerifyImpersonationCanBeDisabled extends TestBase{

	/** 
	* expected: It contains the result expected.
	*/
	private String expected = "Impersonation is now disabled.";
	
	/** 
	* errorMessage: It contains the error message that is displayed 
	* if test case fails
	*/
	private String errorMessage = "Impersonate was not disabled";
	
	/** 
	 * impersonation: It change the status of the impersonation
	 */
	private String impersonation = "true";
	
	/**
	 * The verifyImpersonationCanBeDisabled method performs the test case:
	 * "Check if the impersonation can be disabled.".
	 */
	@Test
	public void verifyImpersonationCanBeDisabled (){
		
		LoginPage login = new LoginPage(driver);
		
		ImpersonationPage impersonation = login
				.setCredentials()
				.clickSignInButton()
				.selectImpersonationLink();
		
		impersonation.clickImpersonationCheckBox().clickSaveButton();
		
		Assert.assertEquals(impersonation.getConfirmationMessage(), 
				expected,errorMessage);
	}
	
	/**
	 * BeforeTest: This method verify the status of the impersonation
	 * and verify if exists an email server in Room manager.
	 */
	@BeforeTest
	public void beforeTest(){
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		ImpersonationApi.setImpersonation(impersonation, 
				PropertiesReader.getExchangeServerDescription());
	}
}