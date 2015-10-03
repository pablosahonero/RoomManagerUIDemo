package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.OutOfOrderPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyOutOfOrderIsUpdated class contains the test case: 
 * Verify that created an OutOfOrder for a specific room can be updated
 * with values -From- field equals -To- field and the range of hours 
 * of -From- must be less than -To-, to be saved
 * @author Daneiva Gamboa - Alejandra Arteaga
 *
 */
public class VerifyOutOfOrderIsUpdated extends TestBase{
	
	/** 
	 * isOutOfOrderUpdated: Boolean value, that indicates whether or not there is an 
	 * Out-Of-Order is updated 
	 */
	boolean isOutOfOrderUpdated= false;
	  
	/** 
    * errorMessage: It contains the error message that would appear 
	* if test case fails
	*/	  
	String msgError= "The Out Of Order was not updated!";
    	  
	/** roomSelected: Name of room to be selected for create a Out-Of-Order*/	  
    String roomSelected = null;
    
	String startTime = Generator.getStartTime();
	
	String endTime = Generator.getEndTime();
	
    @BeforeTest
	public void beforeTest() {
		roomSelected = PropertiesReader.getRoomName();

		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
			
		}	
		RoomApi.createOutOfOrder(startTime, endTime,roomSelected);
	}
    
    /**
	* This method performs the test case: Verify if is possible updated 
	* a Out-Of-Order with correct values("From" field is less than "To").
	*/
    @Test
    public void verifyOutOfOrderIsUpdated() {
    	
    	/** setDescription: It contains Description for a Out-Of-Order*/
    	String setDescriptionExpected = "Out-Of-Order in the room updated";
    	
    	/** roomSelected: It contains Title for a Out-Of-Order*/
    	String nameTitleExpected ="Temporarily Out of Order updated"; 	   	
    	
    	LoginPage login = new LoginPage(driver);
    	HomePage adminHome = login
				.setCredentials()
				.clickSignInButton();

    	ConferenceRoomPage conferenceRoom = adminHome.selectConferenceRoomsLink();    	
    	RoomInfoPage roomInfoPage =conferenceRoom.doubleClickOnRoom(roomSelected);
    	OutOfOrderPage outOfOrderPage = roomInfoPage.clickOnOutOfOrderPlanning();
    
    	String StartHourActual = outOfOrderPage.getStartHourToField();
    	String EndHourActual = outOfOrderPage.getEndHourFromField();
    	
    	outOfOrderPage.setTitle(nameTitleExpected)
					  .setDescription(setDescriptionExpected)
					  .clickTopArrowStartHourToField()
					  .clickTopArrowStartHourToField()
					  .clickTopArrowStartHourFromField();
    	
    	String StartHourExpected = outOfOrderPage.getStartHourToField();
    	String EndHourExpeted = outOfOrderPage.getEndHourFromField();
    	
    	outOfOrderPage.clickSaveButtonOutOfOrder();
    	
    	isOutOfOrderUpdated = outOfOrderPage.isOutOfOrderUpdated(StartHourActual,
														   		 EndHourActual,
															     StartHourExpected,
															     EndHourExpeted);   	
 
		Assert.assertTrue(isOutOfOrderUpdated,msgError);
    }
        
    /**
     * afterTest: This method deletes the created Out-Of-Order in the 
     * beforeTest method.
     */
    @AfterTest
	public void afterTest(){
    	RoomApi.deleteAllOutOfOrders(roomSelected);
	}
}
