package org.roommanager.test.tablet.homePage;

import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.testng.Assert;
import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Verify the meetings information is displayed on Now button when the meeting
 * is being held at this time.
 * 
 * @author Daneiva Gamboa
 *
 */
public class VerifyDisplayedInfoInNowButtonWithMeeting extends TestBase {
	
	/** urlTablet :It represents the URL of module Tablet */
	private String urlTablet = PropertiesReader.getRoomManagerApi();

	/** username: It represents the name of the Current User */
	private String username = PropertiesReader.getUsername();

	/** organizer: It represents the name of the Meeting's Organizer */
	private String organizer = username;

	/** attendee: It represents the Email of an attendee */
	private String attendee = "\"" + username + "@"
			+ PropertiesReader.getExchangeDomain() + "\"";

	/** conferenceRoom: It represents the name of the Room */
	private String conferenceRoom = null;

	/** startTime: It represents the Meeting's Start Time */
	private String startTime = Generator.getStartTime();

	/** startTime: It represents the eeting's End Time */
	private String endTime = Generator.getEndTime();

	/**
	 * errorMessage: It represents the Error Message that will be displayed if
	 * the test fails
	 */
	private String msgError = "The Test failed because the meeting not found "
			+ "in the Now Button";

	/** subject: It represents the Meeting's Subject */
	private String subject = "Subject Test";

	/**
	 * isPresentMeeting: Boolean value, that indicates whether or not there is a
	 * meeting is displayed in the Now Button.
	 */
	boolean isPresentMeeting = false;

	/** connection: Name of a new RegisterPage */
	RegisterPage connection;

	/**
	 * verifyDisplayedInfoInNowButtonWithMeeting: The meetings information is
	 * displayed on Now button.
	 *
	 */
	@Test
	public void verifyDisplayedInfoInNowButtonWithMeeting() {
		/*
    	RegisterPage connection = new RegisterPage(driver);
		NavigationPage navigation = connection
									.enterServiceUrl(urlTablet)
									.clickSignInButton()
									.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName(conferenceRoom)
									.clickSaveButton();
		HomePage homePage = navigation.clickOnHomePageLink();

		isPresentMeeting = homePage.existMeetingInNowButton(subject, organizer);
		/* Assert */
		Assert.assertTrue(isPresentMeeting, msgError);
	}

	/**
	 * beforeTest: This method verify the Meeting was created.
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
		MeetingApi.createMeeting(organizer, subject, startTime, endTime,
				conferenceRoom, attendee);
	}

	/**
	 * afterTest: It deletes the Meeting that was created by the test.
	 */
	@AfterTest
	public void afterTest() {
		MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}
}
