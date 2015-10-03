package org.roommanager.test.admin.conferencerooms;


import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.OutOfOrderPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The Verify Out Of Order Is Created With OnOff Calendar Button class contains 
 * the test case: Check if saved the state of the room when the OnOff Calendar 
 * button this pressed
 * @author Daneiva Gamboa
 *
 */
public class VerifyOutOfOrderIsCreatedWithOnOffCalendarButton extends TestBase{
    
	/** 
	 * isPresentOutOfOrder: Boolean value, that indicates whether or 
	 * not there is an Out-Of-Order is created 
	 */
	boolean isPresentOutOfOrder= false;
	
	/** setDescription: It contains Description for a Out-Of-Order*/
	private String setDescription = "Out-Of-Order in the room";
	
	/** roomSelected: It contains Title for a Out-Of-Order*/
	private String nameTitle ="Temporarily Out of Order";
	
	/** 
	 * isPresentOutOfOrder: Boolean value, that indicates whether or 
	 * not there is an Out-Of-Order is Enabled SendMailCheckbox
	 */	
    boolean isEnabledSendMailCheckbox= false; 
    
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */     
	private String msgError= "The Out Of Order was not created!";	  
	
	/** roomSelected: Name of room to be selected for create a Out-Of-Order*/	  
	private String roomSelected = null;
        
	@Test
	public void verifyOutOfOrderIsCreatedWithOnOffCalendarButton() {
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
				.setCredentials()
				.clickSignInButton();
		
	  	ConferenceRoomPage conferenceRoom = 
	  			adminHome.selectConferenceRoomsLink();
	  	
	  	OutOfOrderPage outOfOrderPage = 
	  			conferenceRoom.doubleClickOnRoom(roomSelected)
						  	  .clickOnOutOfOrderPlanning();
	  	
	  	outOfOrderPage.setTitle(nameTitle)										
						  .setDescription(setDescription)
					      .clickScheduleButton()
					      .checkSendMailCheckbox()
					      .clickSaveButtonOutOfOrder();	
	  	isPresentOutOfOrder = conferenceRoom.existOutOfOrder(roomSelected);
	  	
	  	Assert.assertTrue( isPresentOutOfOrder,msgError);
	}
	
	 /**
     * afterTest: This method deletes the: Out-Of-Order and Meetings, 
     * created in the beforeTest method.
     */
	@AfterTest
	public void testTearDown(){
		RoomApi.deleteAllOutOfOrders(roomSelected);
	}
	
	/**
    * beforeTest: This method verify for the Email service was created.
    */	
	@BeforeTest
	public void beforeTest(){
		roomSelected = PropertiesReader.getRoomName();

		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getUsername(), 
							PropertiesReader.getPassword(), 
							PropertiesReader.getExchangeHostName());
		}
		RoomApi.deleteAllOutOfOrders(roomSelected);
		MeetingApi.deleteAllRoomMeetings(roomSelected);		
	}
  }
