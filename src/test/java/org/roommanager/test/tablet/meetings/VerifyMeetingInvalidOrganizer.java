package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.settings.RegisterPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyMeetingInvalidOrganizer extends TestBase{	
	private String invalidOrganizer = "";
	private String roomName = "SM-Room10";
	private String subject = "Subject Test";
	private String errorMessage = "The Test failed because the Meeting "
			+ "accepts invalid username";
	
	@Test 
	public void VerifyErrorMeetingInvalidUsername(){
		
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
					
		scheduler
			.setOrganizerTextField(invalidOrganizer)
			.setSubjectTextField(subject)
			.clickCreateButton();
		
		Assert.assertTrue(scheduler.isOrganizerFieldErrorMessagePresent(),
				errorMessage);
	}
	
	@BeforeTest
	public void beforeTest(){
		roomName = PropertiesReader.getRoomName();
		if(EmailServerApi.getEmailServiceId() == null)
			EmailServerApi.createEmailServer(PropertiesReader.getUsername(),
											 PropertiesReader.getPassword(),
											 PropertiesReader.getExchangeHostName());
	}
}
