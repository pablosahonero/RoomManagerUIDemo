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
 * The VerifyOutOfOrderNotSavedWithInvalidTimes class contains 
 * the test case: Verify OutOfOrder not saved with invalid hours in the room
 * 
 * @author Daneiva Gamboa
 * 
 */
public class VerifyOutOfOrderNotSavedWithInvalidTimes extends TestBase{
	
	String setDescription = "Out-Of-Order in the room";
	
	/** roomSelected: It contains Title for a Out-Of-Order*/
	String nameTitle ="";
	
	/** isPresentOutOfOrder: Boolean value, that indicates whether or not there is an 
	* Out-Of-Order is created
	**/
	boolean isPresentOutOfOrder= false;
	  
	/** 
    * errorMessage: It contains the error message that would appear 
	* if test case fails
	*/	  
	String msgError= "The Out Of Order was not created!";
    	  
	/** roomSelected: Name of room to be selected for create a Out-Of-Order*/	  
    String roomSelected = "Room06";
    
    /** isPresentErrorMessage: Boolean value, that indicates whether or not 
     * that indicates whether or not there is Error Message, 
     * In case: "To" field must be greater than "From" field 
	**/ 
    boolean isPresentErrorMessage =false;
    
    @BeforeTest
	public void beforeTest() {
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
	}
    
    /**
	* This method performs the test case: Verify if is possible create 
	* a Out-Of-Order with correct values("From" field is less than "To").
	*/
	@Test
	public void verifyOutOfOrderNotSavedWithInvalidTimes() {
		LoginPage login = new LoginPage(driver);
		HomePage adminHome = login
				.setCredentials()
				.clickSignInButton();
			
	    ConferenceRoomPage conferenceRoom = adminHome.selectConferenceRoomsLink();
		  
		OutOfOrderPage outOfOrderPage = conferenceRoom.doubleClickOnRoom(roomSelected)
													  .clickOnOutOfOrderPlanning()													  
													  .clickBottomArrowStartHourToField()
													  .clickBottomArrowStartHourToField()
													  .setDescription(setDescription)													  
													  .clickSaveButtonOutOfOrder();
		isPresentErrorMessage = outOfOrderPage.errorMessageToGreaterFrom();

		Assert.assertTrue( isPresentErrorMessage,msgError);		
	}
}
