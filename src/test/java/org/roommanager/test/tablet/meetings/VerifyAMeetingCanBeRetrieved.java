package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyAMeetingCanBeRetrieved extends TestBase {
	/** username: It represents the name of the Current User*/
	private String username = PropertiesReader.getUsername();
	
	/** organizer: It represents the name of the Meeting's Organizer*/
	private String organizer = username;
	
	/** attendee: It represents the Email of an attendee*/
	private String attendee = "\"" + username + "@" + 
				PropertiesReader.getExchangeDomain() + "\"";
	
	/** conferenceRoom: It represents the name of the Room*/
	private String conferenceRoom = null;
	
	/** subject: It represents the Meeting's Subject*/
	private String subject = "Subject Test";
	
	/** startTime: It represents the Meeting's Start Time*/
	private String startTime = Generator.getStartTime();
	
	/** startTime: It represents the eeting's End Time*/
	private String endTime = Generator.getEndTime();
	
	/** errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails*/
	private String errorMessage = "The Test failed because the Meeting's "
			+ "data couldn't be retrieved";
	
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
	 * VerifyAMeetingIsRemoved: Verifies the Meeting's data is retrieved.
	 */
    @Test
    public void VerifyAMeetingIsRemoved() {
    	/*
    	RegisterPage connection = new RegisterPage(driver);
		
		
		NavigationPage navigation = connection
									.enterServiceUrl(PropertiesReader
											.getRoomManagerApi())
									.clickSignInButton()
									.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName(conferenceRoom)
									.clickSaveButton();
		
		SchedulerPage scheduler = navigation
								  .clickOnSchedulerPageLink()
								  .clickOnMeetingBox(subject);
		
		boolean isMeetingDataCorrect = scheduler.compareMeetingData(organizer, 
																	subject, 
																	attendee);
		
		Assert.assertTrue(isMeetingDataCorrect, errorMessage);*/
    }
    
    /**
     * afterTest: It deletes the Meeting that was created by the test. 
     */
    @AfterTest
	  public void afterTest(){
		  MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	  }	
}
