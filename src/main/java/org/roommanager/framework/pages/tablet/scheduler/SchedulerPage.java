package org.roommanager.framework.pages.tablet.scheduler;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.scheduler.SchedulerConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class SchedulerPage extends PageFactory{
	WebDriver driver = null;
	@FindBy (xpath = SchedulerConstant.ATTENDEE_TEXT) 
	WebElement attendeeText;
	@FindBy (xpath = SchedulerConstant.ATTENDEES_LIST) 
	WebElement attendeesList;
	@FindBy (xpath = SchedulerConstant.BODY_TEXT_AREA) 
	WebElement bodyTextArea;
	@FindBy (xpath = SchedulerConstant.UPDATE_BUTTON) 
	WebElement updateButton;
	@FindBy (xpath = SchedulerConstant.REMOVE_BUTTON) 
	WebElement removeButton;
	@FindBy (xpath = SchedulerConstant.CONFIRMATION_MESSAGE) 
	WebElement confirmationMessage;
	@FindBy (xpath = SchedulerConstant.CREATE_BUTTON) 
	WebElement createButton;
	@FindBy (xpath = SchedulerConstant.ATTENDEES_TEXT_FIELD) 
	WebElement attendeesTextField;
	@FindBy (xpath = SchedulerConstant.SUBJECT_TEXT_FIELD) 
	WebElement subjectTextField;
	@FindBy (xpath = SchedulerConstant.ORGANIZER_TEXT_FIELD) 
	WebElement organizerTextField;
	@FindBy (xpath = SchedulerConstant.MEETING_SUBJECT_ERROR_MESSAGE) 
	WebElement meetingSubjectErrorMessage;
	@FindBy (xpath = SchedulerConstant.MEETING_ORGANIZER_ERROR_MESSAGE) 
	WebElement organizerSubjectErrorMessage;
	@FindBy (xpath = SchedulerConstant.ROOM_TIMELINE) 
	WebElement roomTimeline;
	@FindBy (xpath = SchedulerConstant.MEETING_BOX) 
	WebElement meetingBox;
	@FindBy (xpath = SchedulerConstant.TIME_LINE_HOURS_LIST) 
    WebElement timelineHoursList;
	@FindBy (xpath = SchedulerConstant.TIME_LINE) 
    WebElement timeline;
	@FindBy (xpath = SchedulerConstant.FROM_HOUR) 
    WebElement fromHour;
	@FindBy (xpath = SchedulerConstant.TO_HOUR) 
    WebElement toHour;
	
	public SchedulerPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public SchedulerPage setOrganizerTextField(String organizer){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(organizerTextField));
		organizerTextField.clear();
		organizerTextField.sendKeys(organizer);
		LogManager.info("Organizer: <"+ organizer +"> was entered");
		return this;
	}
	
	public SchedulerPage setSubjectTextField(String subject){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(subjectTextField));
		subjectTextField.clear();
		subjectTextField.sendKeys(subject);
		LogManager.info("Subject: <"+ subject +"> was entered");
		return this;
	}
	
	public SchedulerPage setAttendeesTextField(String attendee){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(attendeesTextField));
		attendeesTextField.clear();
		attendeesTextField.sendKeys(attendee + ";");
		LogManager.info("Attendee: <"+ attendee +"> was entered");
		return this;
	}
	
	public SchedulerPage setBodyTextArea(String body){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(bodyTextArea));
		bodyTextArea.clear();
		bodyTextArea.sendKeys(body);
		LogManager.info("Body: <"+ body +"> was entered");
		return this;
	}
	
	public CredentialsPage clickCreateButton(){
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(createButton));
		createButton.click();
		LogManager.info("Create Button was clicked");
		return new CredentialsPage(driver);
	}
	
	public boolean isSubjectFieldErrorMessagePresent(){
		String expectedErrorMessage = "Subject is required";
		(new WebDriverWait(driver,60))
			.until(ExpectedConditions.visibilityOf(meetingSubjectErrorMessage));
		String errorMessage = meetingSubjectErrorMessage.getText();
		LogManager.info("Error Message: <"+ errorMessage +"> was displayed");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	public boolean isOrganizerFieldErrorMessagePresent(){
		String expectedErrorMessage = "Organizer is required";
		(new WebDriverWait(driver,60))
			.until(ExpectedConditions.visibilityOf(organizerSubjectErrorMessage));
		String errorMessage = organizerSubjectErrorMessage.getText();
		LogManager.info("Error Message: <"+ errorMessage +"> was displayed");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	public String getSuccessfulMessage(){
		(new WebDriverWait(driver,60))
			.until(ExpectedConditions.visibilityOf(confirmationMessage));
		return confirmationMessage.getText();
	}
	
	public SchedulerPage clickOnMeetingBox(String subject){
		getMeetingBoxBySubject(subject).click();
		(new WebDriverWait(driver,20))
			.until(ExpectedConditions.visibilityOf(updateButton));
		LogManager.info("Meeting <" + subject + "> was clicked");
		return this;
	}
	
	public CredentialsPage clickRemoveButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(removeButton));
		removeButton.click();
		LogManager.info("Remove Button was clicked");
		return new CredentialsPage(driver);
	}
	public CredentialsPage clickUpdateButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(updateButton));
		updateButton.click();
		LogManager.info("Update Button was clicked");
		return new CredentialsPage(driver);
	}
	private WebElement getAttendee(String attendee){
		(new WebDriverWait(driver,60))
			.until(ExpectedConditions.visibilityOf(attendeesList));
		WebElement list = driver.findElement(By.xpath(SchedulerConstant.ATTENDEES_LIST));
		List<WebElement> attendees = list.findElements(By.xpath(SchedulerConstant.ATTENDEE_TEXT));
		for(WebElement element : attendees){
			if(element.getText().equals(attendee)){
				LogManager.info("Attendee <" + attendee + "> was found");
				return element;
			}
		}
		LogManager.info("Attendee <" + attendee + "> was not found");
		return null;
	}
	
	private WebElement getMeetingBoxBySubject(String subject){
		moveTimeline();
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(roomTimeline));
		WebElement time = driver.findElement(By.xpath(SchedulerConstant.ROOM_TIMELINE));
		List<WebElement> boxes = time.findElements(By.xpath(SchedulerConstant.MEETING_BOX));
		for(WebElement element: boxes){
			if(element.getText().equals(subject)){
				LogManager.info("Subject <" + subject + "> was found");
				return element;
			}
		}
		LogManager.info("Subject <" + subject + "> was not found");
		return null;
	}
	
	public boolean existSubjectOnTimeline(String subject){
		return getMeetingBoxBySubject(subject) != null ? true : false;
	}
	
	public boolean existAttendee(String attendee){
		return getAttendee(attendee) != null;
	}
	
	@SuppressWarnings("deprecation")
	private void moveTimeline(){
		int pixels = -60;
		int hour = (new Date()).getHours();
		if(hour <= 7 || hour >= 19){
			if(hour >= 19){
				pixels = -2000;
			}	
			else if(hour <= 7){
				pixels = 2000;
			}	
			WebElement elementToMove = getElementToDragAndDrop();
			Actions act = new Actions(driver);
			act.clickAndHold(elementToMove);
			act.moveToElement(roomTimeline);
			act.moveByOffset(pixels, 5);
			act.release();
			act.build().perform();
			LogManager.info("Timeline moved <"+ pixels + 
					"> pixels, hour <" + hour + ">");
		}
	}
	
	public boolean compareMeetingData(String organizer, String subject, String attendee){
		String actualOrganizer = organizerTextField.getAttribute("value");
		String actualSubject = subjectTextField.getAttribute("value");
		attendee = attendee.replace("\"", "");
		
		boolean organizerComparison = actualOrganizer.equals(organizer);
		boolean subjectComparision = actualSubject.equals(subject);
		boolean isAttendeePresent = existAttendee(attendee);

		return organizerComparison && subjectComparision && isAttendeePresent;
	}
	
	public SchedulerPage dragTimeLineBoxRightEnd(String subject, int endHour){
		WebElement hourElement = getHourFromTimeline(endHour);
		WebElement conferenceRoom = getMeetingBoxBySubject(subject);
		WebElement rightEnd =(new WebDriverWait(driver, 20))
				.until(ExpectedConditions.visibilityOf(conferenceRoom
				.findElement(By.xpath(SchedulerConstant.MEETING_BOX_RIGHT_END))));
		dragAndDrop(rightEnd, hourElement);
		LogManager.info("Drag and drop meeting <" + subject + 
				"> to hour <" + endHour + ">");
		return this;
	}
	
	public SchedulerPage dragTimeLineBoxLeftEnd(String subject, int startHour) {
		WebElement hourElement = getHourFromTimeline(startHour);
		WebElement conferenceRoom = getMeetingBoxBySubject(subject);
		WebElement leftEnd =(new WebDriverWait(driver, 20))
				.until(ExpectedConditions.visibilityOf(conferenceRoom
				.findElement(By.xpath(SchedulerConstant.MEETING_BOX_LEFT_END))));
		dragAndDrop(leftEnd, hourElement);
		LogManager.info("Drag and drop meeting <" + subject + 
				"> to hour <" + startHour + ">");
		return this;
	}
	
	private void dragAndDrop(WebElement fromElement, WebElement toElement){
		int pixelsTo = toElement.getLocation().x;
		int pixelsFrom = fromElement.getLocation().x;
		int pixelsToMove;
		pixelsToMove = (int)((pixelsFrom - pixelsTo)/2);
		Actions builder = new Actions(driver); 
		Action dragAndDrop = builder.clickAndHold(fromElement)
				.moveByOffset(pixelsToMove, fromElement.getLocation().y)
				.release(fromElement)
				.build();
		dragAndDrop.perform();
		LogManager.info("Drag and Drop <" + pixelsToMove + "> pixels");
	}
	
	public WebElement getHourFromTimeline(int expectedHour){
        (new WebDriverWait(driver,60))
        	.until(ExpectedConditions.visibilityOf(timelineHoursList));
        moveTimelineToSpecificHour(expectedHour);
        List<WebElement> hours = timelineHoursList
        		.findElements(SchedulerConstant.DIV_ELEMENT);
        for (WebElement hour : hours) {
              String actualHour = hour.getText();
              if(actualHour.contains(expectedHour + ":00")){
                    LogManager.info("Hour <" + expectedHour + "> was found on Timeline");
                    return hour;
              }
        }
        LogManager.info("Hour <" + expectedHour + "> wasn't found on Timeline");
        return null;
	}

	private WebElement getElementToDragAndDrop(){
		(new WebDriverWait(driver,60))
    	.until(ExpectedConditions.visibilityOf(timelineHoursList));
	    List<WebElement> hours = timelineHoursList
	    		.findElements(SchedulerConstant.DIV_ELEMENT);
	    for (WebElement hour : hours) {
	          String actualHour = hour.getText();
	          if(actualHour.contains("12:00")){
	        	  LogManager.info("Element to drag and drop was retrived");
	              return hour;
	          }
	    }
	    LogManager.info("Element to drag and drop was not retrived");
	    return null;
	}
	
	private void moveTimelineToSpecificHour(int hour){
		if(hour <= 7 || hour >=19 ){
			int xDirection = -60;
			if(hour >= 19){
				xDirection = -2000;
			}
			else if(hour <= 7){
				xDirection = 2000;
			}
			WebElement elementToMove = getElementToDragAndDrop();
			Actions act = new Actions(driver);
			act.clickAndHold(elementToMove);
			act.moveToElement(roomTimeline);
			act.moveByOffset(xDirection, 0);
			act.release();
			act.build().perform();
			LogManager.info("Timeline moved <"+ xDirection + 
					"> pixels, hour <" + hour + ">");
		}
	}
	
	public SchedulerPage setSpecificTimeinTimeline(int hour){
		moveTimelineToSpecificHour(hour);
		WebElement hourInTimeLine = getHourFromTimeline(hour);
		Actions actions = new Actions(driver);
		actions.moveToElement(roomTimeline, hourInTimeLine.getLocation().x, 0);
		actions.doubleClick();
		actions.build().perform();
		LogManager.info("Meeting start Time was set to <" + hour+ ">");
		return this;
	}
	
	public boolean compareMeetingTime(int startHour, int endHour){
		String actualFromHour = fromHour.getAttribute("value");
		String actualToHour = toHour.getAttribute("value");

		boolean fromHourComparison = actualFromHour.contains(endHour + ":00:00.000");
		boolean toHourComparision = actualToHour.contains(startHour + ":00:00.000");;
		return fromHourComparison && toHourComparision;
	}
}
