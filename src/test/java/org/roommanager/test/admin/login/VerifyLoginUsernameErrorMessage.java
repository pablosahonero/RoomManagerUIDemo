package org.roommanager.test.admin.login;

import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyLoginUsernameErrorMessage extends TestBase {

	@Test
	public void VerifyErrorMessageLoginUsername(){
		  String ExpectedErrorMessage = ("Expected Message");
		  
		  String ActualErrorMessage = "Actual Message";  
		  
		  LoginPage login = new LoginPage(driver);
		  
		  login.setUserName("").setPassword("").clickSignInButton();
		  
		  Assert.assertEquals(ExpectedErrorMessage, "Message Error",ActualErrorMessage);

	}
}
