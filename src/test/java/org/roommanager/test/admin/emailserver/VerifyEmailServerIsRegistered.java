package org.roommanager.test.admin.emailserver;

import org.roommanager.framework.pages.admin.emailserver.CreateEmailServerPage;
import org.roommanager.framework.pages.admin.emailserver.EmailServerPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyEmailServerIsRegistered extends TestBase{

	  @BeforeTest
	  public void beforeTest(){
		  if(EmailServerApi.getEmailServiceId() != null){
			  EmailServerApi.removeEmailServer(
					  PropertiesReader.getExchangeServerDescription());
		  }
	  }
	  
	  @Test
	  public void registerEmailServer() {
		  String expected = PropertiesReader.getExchangeHostName()+ "\n" + 
				  PropertiesReader.getExchangeServerDescription();
		  
		  String errorMessage = "The email server was not registered";  
		  
		  LoginPage login = new LoginPage(driver);
		  
		  HomePage home = login
				  .setCredentials()
				  .clickSignInButton();
		  
		  EmailServerPage emailServer = home.selectEmailServerLink();
		  
		  CreateEmailServerPage addServer = emailServer.clickAddButton();
		  
		  addServer.setHostname(PropertiesReader.getExchangeHostName());
		  
		  addServer.setUsername(PropertiesReader.getExchangeUserName());
		  
		  addServer.setPassword(PropertiesReader.getExchangePassWord());
		  
		  emailServer = addServer.clickSaveButton();
		  
		  Assert.assertEquals(emailServer.getEmailServer(), expected, errorMessage);
	  }
	  
	  @AfterTest
	  public void afterTest(){
		  EmailServerApi.removeEmailServer(PropertiesReader
				  .getExchangeServerDescription());
	  }
}
