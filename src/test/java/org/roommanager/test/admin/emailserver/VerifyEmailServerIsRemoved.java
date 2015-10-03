package org.roommanager.test.admin.emailserver;

import org.roommanager.framework.pages.admin.emailserver.EmailServerPage;
import org.roommanager.framework.pages.admin.emailserver.RemoveEmailServerPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyEmailServerIsRemoved extends TestBase {
	
	 @BeforeTest
	 public void beforeTest(){
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	 }
	 @Test
	  public void removeEmailServer(){
		 
		  String errorMessage = "The email server was not removed";
		  
		  LoginPage login = new LoginPage(driver);
		  
		  HomePage home = login
				  			.setCredentials()
				  			.clickSignInButton();
		  
		  EmailServerPage emailServer = home.selectEmailServerLink();
		  
		  RemoveEmailServerPage removeEmailServer = emailServer.clickRemoveButton();
		  
		  emailServer = removeEmailServer.clickOnYesButton();
		  
		  Assert.assertTrue(emailServer.existsEmailServerRegistered(), errorMessage);
	 }
}
