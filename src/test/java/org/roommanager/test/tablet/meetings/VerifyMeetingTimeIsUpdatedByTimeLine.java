package org.roommanager.test.tablet.meetings;

import java.util.Date;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class VerifyMeetingTimeIsUpdatedByTimeLine extends TestBase{
	
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
	private String roomName = "Room03";
	
	/** subject: It represents the Meeting's Subject*/
	private String subject = "Subject Test";
	
	/** startTime: It represents the Meeting's Start Time*/
	private String startTime = Generator.getStartTime();
	
	/** startTime: It represents the meeting's End Time*/
	private String endTime = Generator.getEndTime();
	
	/** 
	 * startHourToUpdate: It represents the meeting's start hour
	 * to be updated
	 */
	@SuppressWarnings("deprecation")
	private int startHourToUpdate =  (new Date()).getHours() - 2;
	
	/** 
	 * endHourToUpdate: It represents the eeting's end hour
	 * to be updated
	 */
	@SuppressWarnings("deprecation")
	private int endHourToUpdate = (new Date()).getHours() + 2;
	
	/** 
	 * errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails
	 */
	private String errorMessage = "The Test failed because the updated"
			+ " meeting could be found in the Scheduler Page";	
	/**
	 * This method contains the test case's steps and assertions
	 */
	@Test
	public void verifyMeetingUpdateMeetingTimeByTimeLine() {
		
		RegisterPage connection = new RegisterPage(driver);
		
		connection
			.enterServiceUrl(PropertiesReader.getRoomManagerApi())
			.clickSignInButton();
		
		NavigationPage navigation = connection
			.clickNavigationLink()
			.clickDefaultRoomComboBox()
			.selectConferenceRoomByName(roomName)
			.clickSaveButton();
		
		SchedulerPage scheduler = navigation
			.clickOnSchedulerPageLink();

		CredentialsPage credentials = scheduler
			.clickOnMeetingBox(subject)
			.dragTimeLineBoxRightEnd(subject,endHourToUpdate)
			.dragTimeLineBoxLeftEnd(subject, startHourToUpdate)
			.clickUpdateButton();
		
		scheduler = credentials
			.enterPassword(password)
			.clickOkButton();
		
		boolean  meetingUpdated = scheduler
			.compareMeetingTime(endHourToUpdate, startHourToUpdate);
		
		Assert.assertTrue(meetingUpdated,errorMessage);
	}
	/**
	 * This method deletes the meeting updated by the test case
	 */
	@AfterTest
	public void afterTest(){
		MeetingApi.deleteMeetingBySubjectName(roomName, subject);
	}	
	 
	/**
	 * This method creates a new meeting to be user by the test case
	 */
	@BeforeTest
	public void beforeTest() {
		roomName = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		MeetingApi.deleteAllRoomMeetings(roomName);
		MeetingApi.createMeeting(organizer, subject, startTime, endTime,
				roomName, attendee);
	}
}
