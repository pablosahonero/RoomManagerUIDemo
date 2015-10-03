package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyOutOfOrderIsCreated class contains the test case: 
 * Verify that created an OutOfOrder for a specific room with values
 * -From- field equals -To- field and the range of hours of -From- must be 
 * less than -To-, to be saved
 * @author Daneiva Gamboa
 *
 */
public class VerifyOutOfOrderIsCreated extends TestBase{
	
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
    String roomSelected = null;
    
    @BeforeTest
	public void beforeTest() {
		roomSelected = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		RoomApi.deleteAllOutOfOrders(roomSelected);
	}
    
    /**
	* This method performs the test case: Verify if is possible create 
	* a Out-Of-Order with correct values("From" field is less than "To").
	*/
    @Test
    public void verifyOutOfOrderIsCreated() {	  
    	
    	/** setDescription: It contains Description for a Out-Of-Order*/
    	String setDescription = "Out-Of-Order in the room";
    	
    	/** roomSelected: It contains Title for a Out-Of-Order*/
    	String nameTitle ="Temporarily Out of Order";
    	
    	LoginPage login = new LoginPage(driver);
    	HomePage adminHome = login
				.setCredentials()
				.clickSignInButton();
		
    	ConferenceRoomPage conferenceRoom = adminHome
    		.selectConferenceRoomsLink();
	  
    	conferenceRoom.doubleClickOnRoom(roomSelected)
    		.clickOnOutOfOrderPlanning()
			.setTitle(nameTitle)
			.setDescription(setDescription)
			.clickSaveButtonOutOfOrder();
		isPresentOutOfOrder = conferenceRoom.existOutOfOrder(roomSelected);

		//needs to add an assertion that verifies by API
		Assert.assertTrue( isPresentOutOfOrder,msgError + "BUG: UI - After creating the an OutOfOrder the icon it is not displayed in the GRID" );
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
