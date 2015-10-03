package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.OutOfOrderPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyOutOfOrderTitleIsObligatory class contains the test case: 
 * Verify OutOfOrder Title Is Obligatory for Create a Out Of Order in the Room
 * 
 * @author Daneiva Gamboa
 * 
 */
public class VerifyOutOfOrderTitleIsObligatory extends TestBase{
	
	/** setDescription: It contains Description for a Out-Of-Order*/
	String setDescription = "Out-Of-Order in the room";
	
	/** roomSelected: It contains Title for a Out-Of-Order*/
	String nameTitle ="";
	
	/**errorMessage: It contains the error message that would appear 
	* if test case fails
	*/	  
	String msgError= "The Out Of Order was not created!";
    	  
	/** roomSelected: Name of room to be selected for create a Out-Of-Order*/	  
    String roomSelected = "Room06";
    
    /** 
     * isPresentErrorMessage: Boolean value, that indicates whether or not 
     * there is Error Message, in case it is published the title of Out-Of-Order. 
	 */ 
    boolean isPresentErrorMessage =false;
    
    /**
	* This method performs the test case: Verify if is possible create 
	* a Out-Of-Order without OutOfOrder Title.
	*/  
    
    @BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
    
    @Test
    public void verifyOutOfOrderTitleIsObligatory() {
		LoginPage login = new LoginPage(driver);
		HomePage adminHome = login
				.setCredentials()
				.clickSignInButton();
			
		ConferenceRoomPage conferenceRoom = adminHome.selectConferenceRoomsLink();
		  
		OutOfOrderPage outOfOrderPage = conferenceRoom.doubleClickOnRoom(roomSelected)
													  .clickOnOutOfOrderPlanning()
													  .setTitle(nameTitle)
													  .setDescription(setDescription)
													  .clickSaveButtonOutOfOrder();
	    isPresentErrorMessage = outOfOrderPage.existErrorMessageWhithoutTitle();

	    Assert.assertTrue( isPresentErrorMessage,msgError);			
	  }
}
