package org.roommanager.test.tablet.search;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.StatusPage;
import org.testng.Assert;
import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class contains a test case of Search feature
 * 
 * @author Alejandra Arteaga
 *
 */
public class VerifyMeetingIsDisplayedOnScheduleTable extends TestBase {

	/** username: It represents the name of the Current User */
	private String username = PropertiesReader.getUsername();

	/** organizer: It represents the name of the Meeting's Organizer */
	private String organizer = username;

	/** attendee: It represents the Email of an attendee */
	private String attendee = "\"" + username + "@"
			+ PropertiesReader.getExchangeDomain() + "\"";

	/** conferenceRoom: It represents the name of the Room */
	private String conferenceRoom = "Room02";

	/** subject: It represents the Meeting's Subject */
	private String subject = "Meeting Test";

	/** startTime: It represents the Meeting's Start Time */
	private String startTime = Generator.getStartTime();

	/** endTime: It represents the Meeting's End Time */
	private String endTime = Generator.getEndTime();

	/**
	 * errorMessage: It contains the error message that would appear if test
	 * case fails
	 */
	private String errorMessage = "The test failed because the Meeting "
			+ "was not displayed on the Scredule Tabled";

	/** connection: Name of a new RegisterPage */
	RegisterPage connection;

	/*Status page*/
	StatusPage statusPage;

	/*HomePage*/
	HomePage homePage;

	/**
	 * This method creates a meeting to a Room and creates a new connection if
	 * there is not
	 */
	@BeforeTest
	public void beforeTest() {

		conferenceRoom = PropertiesReader.getRoomName();
		if (EmailServerApi.getEmailServiceId() == null) {
			EmailServerApi.createEmailServer(
					PropertiesReader.getExchangeUserName(),
					PropertiesReader.getExchangePassWord(),
					PropertiesReader.getExchangeHostName());
		}
		MeetingApi.deleteAllRoomMeetings(conferenceRoom);
		MeetingApi.createMeeting(organizer, subject, startTime, endTime,
				conferenceRoom, attendee);
		String url = PropertiesReader.getRoomManagerApi();

		/*Opening the page*/
		connection = new RegisterPage(driver);

		statusPage = connection.enterServiceUrl(url)
				.enterCredentials()
				.clickSignInButton();
		homePage = statusPage
				.selectRoom(conferenceRoom)
				.clickStartButton();

	}

	/**
	 * Test method: Verify that a meeting of an specific room is displayed on
	 * [Rooms List] schedule table after clicking on the search page
	 */
	@Test
	public void verifyMeetingIsDisplayedOnScheduleTable() {

		SearchPage search = homePage.clickSearchButton();
		
		Assert.assertEquals(search.getMeetingByRoom(conferenceRoom), subject,
				errorMessage);
	}

	/**
	 * This method deletes the meeting created
	 */
	@AfterTest
	public void Aftertest() {
		MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}

}
