package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.scheduler.CredentialsPage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

public class VerifyMeetingIsRemoved extends TestBase{

	/** username: It represents the name of the Current User*/
	private String username = PropertiesReader.getUsername();
	
	/** password: It represents the password of the Current User*/
	private String password = PropertiesReader.getPassword();
	
	/** organizer: It represents the name of the Meeting's Organizer*/
	private String organizer = username;
	
	/** attendee: It represents the Email of an attendee*/
	private String attendee = "\"" + username + "@" 
			+ PropertiesReader.getExchangeDomain() + "\"";
	
	/** conferenceRoom: It represents the name of the Room*/
	private String conferenceRoom = "Room01";
	
	/** subject: It represents the Meeting's Subject*/
	private String subject = "Subject Test";
	
	/** startTime: It represents the Meeting's Start Time*/
	private String startTime = Generator.getStartTime();
	
	/** startTime: It represents the eeting's End Time*/
	private String endTime = Generator.getEndTime();
	
	/** errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails*/
	private String errorMessage = "The Test failed because the deleted meeting "
			+ "could be found in the Scheduler Page";
	
	/**
     * beforeTest: It creates a Meeting is created. 
     */
	@BeforeTest
    public void beforeTest() {
		conferenceRoom = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		MeetingApi.deleteAllRoomMeetings(conferenceRoom);
    	MeetingApi.createMeeting(organizer, subject, startTime, 
    							 endTime, conferenceRoom, attendee);
    }
	
	/**
	 * VerifyAMeetingIsRemoved: Verifies that a Room can be removed from a Room.
	 */
    @Test
    public void VerifyAMeetingIsRemoved() {
    	
    	RegisterPage connection = new RegisterPage(driver);
		connection.enterServiceUrl(PropertiesReader.getRoomManagerApi())
		.clickSignInButton();
		
		NavigationPage navigation = connection.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName(conferenceRoom)
									.clickSaveButton();
		
		SchedulerPage scheduler = navigation.clickOnSchedulerPageLink();
			
		CredentialsPage credential = scheduler
									 .clickOnMeetingBox(subject)
									 .clickRemoveButton();
		
		scheduler = credential
					.enterPassword(password)
					.clickOkButton();
		
		Assert.assertFalse(scheduler.existSubjectOnTimeline(subject), errorMessage);
    }
}
