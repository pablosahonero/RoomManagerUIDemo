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

public class VerifyMeetingInvalidSubject extends TestBase{
	private String username = PropertiesReader.getUsername();
	private String roomName = "SM-Room9";
	private String invalidMeetingSubject = "";
	private String errorMessage = "The Test failed because the Meeting "
			+ "doesn't display an Error Message when invalid values are "
			+ "entered in \"Subject\" text field";
	
	@Test 
	public void VerifyMeetingInvalidSubjectErrorMessage(){
		
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
			.setOrganizerTextField(username)
			.setSubjectTextField(invalidMeetingSubject)
			.clickCreateButton();
		
		boolean isErrorMessagePresent = scheduler
			.isSubjectFieldErrorMessagePresent();
		
		Assert.assertTrue(isErrorMessagePresent, errorMessage);
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
