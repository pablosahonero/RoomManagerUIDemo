package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.OutOfOrderPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyOptionSendingMailsEnabledWithMeeting class contains the test case: 
 * In the Room with meeting created, when click on On/Off ScheduleButton, 
 * it verify if enabled the SendMail Checkbox
 * @author Daneiva Gamboa
 *
 */
public class VerifyOptionSendingMailsEnabledWithMeetingOnOffEnabled extends TestBase{
	/** username: It contains Username for a Meeting*/
	private String username = PropertiesReader.getUsername();
	
	/** subject: It contains Subject for a Meeting*/
	private String subject = "Subject Test";
	
	/** organizer: It contains organizer for a Meeting*/
	private String organizer = username;
	
	/** attendee: It contains attendee for a Meeting*/
	private String attendee = "\"" + username + "@" + PropertiesReader.getExchangeDomain() + "\"";
	
	/** startTime: It contains startTime for a Meeting*/
	private String startTime = Generator.getStartTime();
	
	/** endTime: It contains endTime for a Meeting*/
	private String endTime = Generator.getEndTime();
	
	/** meetingSubject: It contains meetingSubject for a Meeting*/
	private String meetingSubject = "Subject Meeting";
	
	/** setDescription: It contains Description for a Out-Of-Order*/
	private String setDescription = "Out-Of-Order in the room";
	
	/** roomSelected: It contains Title for a Out-Of-Order*/
	private String nameTitle ="Temporarily Out of Order";
	
	/** 
	 * isPresentOutOfOrder: Boolean value, that indicates whether or 
	 * not there is an Out-Of-Order is Enabled SendMailCheckbox.
	 */
    boolean isEnabledSendMailCheckbox= false; 
	
    /** 
     * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */	  
	private String msgError= "The Out Of Order was not created!";	  
	
	/** roomSelected: Name of room to be selected for create a Out-Of-Order*/	  
	private String roomSelected = null;
        
    /**
	* This method performs the test case: It verify if enabled the SendMail 
	* Checkbox,when is enabled On/Off ScheduleButton with created meeting.
	*/
	@Test
	public void verifyOptionSendingMailsEnabledWithMeeting() {
		
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
					  .checkSendMailCheckbox();
    	isEnabledSendMailCheckbox = outOfOrderPage.enabledSendMailCheckbox();	    	
    	/*Asserts*/
		Assert.assertTrue(isEnabledSendMailCheckbox,msgError);	
	}
	
	 /**
     * afterTest: This method deletes the created Out-Of-Order and Meetings in the 
     * beforeTest method.
     */
	@AfterTest
	public void testTearDown(){
		MeetingApi.deleteMeetingBySubjectName(roomSelected, meetingSubject);
		RoomApi.deleteAllOutOfOrders(roomSelected);
	}
	
	 /**
     * beforeTest: This method verify for the Email service was created.
     * Delete all meetings and created a new Meeting.
     */
	@BeforeTest
	public void beforeTest(){
		roomSelected = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getUsername(), 
								PropertiesReader.getPassword(), 
								PropertiesReader.getExchangeHostName());
		}
		MeetingApi.deleteAllRoomMeetings(roomSelected);
		MeetingApi.createMeeting(organizer, 
								 subject, 
								 startTime, 
								 endTime, 
								 roomSelected, 
								 attendee);		
	}
}
