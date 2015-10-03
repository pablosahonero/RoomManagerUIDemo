package org.roommanager.framework.pages.tablet.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.home.HomeConstant;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.SettingsPage;
import org.roommanager.framework.utilities.common.LogManager;

public class HomePage extends PageFactory {
	
	private WebDriver driver;
	
	@FindBy (xpath = HomeConstant.GOTOSETTINGS_BUTTON)
	private WebElement gotosettingsButton;
	@FindBy(xpath = HomeConstant.NOW_BUTTON)
	private WebElement nowButton;
	@FindBy(xpath = HomeConstant.NEXT_BUTTON)
	private WebElement nextButton;
	@FindBy(xpath = HomeConstant.SCHEDULER_BUTTON)
	private WebElement schedulerButton;
	@FindBy(xpath = HomeConstant.SEARCH_BUTTON)
	private WebElement searchButton;	

	@FindBy(xpath = HomeConstant.NAME_MEETING_LABEL_IN_NOW_BUTTON)
	private WebElement nameMeetingLabelInNowButton;
	@FindBy(xpath = HomeConstant.ORGANIZER_LABEL_IN_NOW_BUTTON)
	private WebElement organizerMeetingLabelInNowButton;
	@FindBy(xpath = HomeConstant.DURATION_TIME_LABEL_IN_NOW_BUTTON)
	private WebElement durationTimeLabelInNowButton;
	
	@FindBy(xpath = HomeConstant.NAME_MEETING_LABEL_IN_NEXT_BUTTON)
	private WebElement nameMeetingLabelInNextButton;
	@FindBy(xpath = HomeConstant.ORGANIZER_LABEL_IN_NEXT_BUTTON)
	private WebElement organizerMeetingLabelInNextButton;
	@FindBy(xpath = HomeConstant.START_END_TIME_LABEL_IN_NEXT_BUTTON)
	private WebElement startEndTimeLabelInNextButton;
	@FindBy(xpath = HomeConstant.NAME_ROOM_LABEL)
	private WebElement nameRoomLabel;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}

	
	/**
	 * clickSchedulerButton: It click on Scheduler Button.
	 * @return SchedulerPage
	 */
	
	public SchedulerPage clickSchedulerButton(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(schedulerButton));
		    schedulerButton.click();
		    return new SchedulerPage(driver);
	}

	/**
	 * clickSettingsLink: It click on Settings Button.
	 * @return SettingsPage
	 */
	public  SettingsPage clickSettingsButton(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(gotosettingsButton));
		    gotosettingsButton.click();
		    return new SettingsPage(driver);
	}

	/**
	 * clickNowButton: It click on Save Button.
	 * @return SchedulerPage
	 */
	public  SchedulerPage clickNowButton(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(nowButton));
		    nowButton.click();
		    return new SchedulerPage(driver);
	}

	/**
	 * clickEndOfDayButton: It click on End Of Day Link.
	 * @return SchedulerPage
	 */ 
	public  SchedulerPage clickNextButton(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(nextButton));
		    nextButton.click();
		    return new SchedulerPage(driver);
	}
	
	/**
	 * clickSearchButton: It click on Search Button
	 * @return SearchPage
	 */ 
	public  SearchPage clickSearchButton(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(searchButton));
		    searchButton.click();
		    return new SearchPage(driver);
	}

	 /**
	 * getNameMeetingLabelInNowButton: It retrieves the specified Name of a 
	 * meeting In Now Button.
	 * @return String
	 */
	 public String getNameMeetingLabelInNowButton(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
								.visibilityOf(nameMeetingLabelInNowButton));
		 String getNameMeetingL = nameMeetingLabelInNowButton.getText();
		 return getNameMeetingL ;
	 }
	 
	 /**
	 * getOrganizerMeetingLabelInNowButton: It retrieves the specified Organizer
	 * of a Meeting In Now Button.
	 * @return String
	 */
	 public String getOrganizerMeetingLabelInNowButton(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
								.visibilityOf(organizerMeetingLabelInNowButton));
		 String getOrganizer = organizerMeetingLabelInNowButton.getText();
		 return getOrganizer ;
	 }

	 /**
	 * getDurationTimeLabelInNowButton: It retrieves the specified Duration Time
	 * of a Meeting In Now Button.
	 * @return String
	 */	 
	 public String getDurationTimeLabelInNowButton(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
								.visibilityOf(durationTimeLabelInNowButton));
		 String getDuration = durationTimeLabelInNowButton.getText();
		 return getDuration ;
	 }
	 
	 /**
	 * existMeetingInNowButton: Making Verification if Meeting is showed in the
	 * Now Button.
	 * @return boolean
	 */
	 public boolean existMeetingInNowButton(String Subject, String Organizer){
		 
		 boolean existMeetingInNowButton=false;
		 
		 new WebDriverWait(driver,80).until(ExpectedConditions
							.visibilityOf(organizerMeetingLabelInNowButton));
					
		 new WebDriverWait(driver,80).until(ExpectedConditions
							.visibilityOf(nameMeetingLabelInNowButton));

		 if(organizerMeetingLabelInNowButton.isDisplayed()&&
				 nameMeetingLabelInNowButton.isDisplayed()){
			 	
			 String ExpectedSubject= getNameMeetingLabelInNowButton();
			 String ExpectedOrganizer= getOrganizerMeetingLabelInNowButton();
			 
				 if ((ExpectedSubject.equals(Subject))&&(ExpectedOrganizer.equals(Organizer))){
					 existMeetingInNowButton=true;
				 } 
		 }		 
		 LogManager.info("Subject of Meeting: <" + getNameMeetingLabelInNowButton()+ 
				 		"> and  "  + 
				 		"Organizer: <" + getOrganizerMeetingLabelInNowButton()+ ">");
		 return existMeetingInNowButton;
		 }
	 
	 /**
		 * getNameMeetingLabelInNextButton: It retrieves the specified Name of a 
		 * meeting In Next Button
		 * @return String
		 */
		 public String getNameMeetingLabelInNextButton(){
			 new WebDriverWait(driver,60).until(ExpectedConditions
									.visibilityOf(nameMeetingLabelInNextButton));
			 String getTitle = nameMeetingLabelInNextButton.getText();
			 return getTitle ;
		 }
		 
		 /**
		 * getOrganizerMeetingLabelInNextButton: It retrieves the specified Organizer
		 * of a Meeting In Next Button.
		 * @return String
		 */
		 public String getOrganizerMeetingLabelInNextButton(){
			 new WebDriverWait(driver,60).until(ExpectedConditions
									.visibilityOf(organizerMeetingLabelInNextButton));
			 String getTitle = organizerMeetingLabelInNextButton.getText();
			 return getTitle ;
		 }
	 
	 /**
	 * existMeetingInNextButton: Making Verification if Meeting is showed in the
	 * Next Button.
	 * @return boolean
	 */
	 public boolean existMeetingInNextButton(String Subject, String Organizer){
		 
		 boolean existMeetingInNextwButton=false;
		 
		 new WebDriverWait(driver,80).until(ExpectedConditions
							.visibilityOf(organizerMeetingLabelInNextButton));
					
		 new WebDriverWait(driver,80).until(ExpectedConditions
							.visibilityOf(nameMeetingLabelInNextButton));
	
		 if(organizerMeetingLabelInNextButton.isDisplayed()&&
				 nameMeetingLabelInNextButton.isDisplayed()){
			 	
			 String ExpectedSubject= getNameMeetingLabelInNextButton();
			 String ExpectedOrganizer= getOrganizerMeetingLabelInNextButton();
			 
				 if ((ExpectedSubject.equals(Subject))&&(ExpectedOrganizer.equals(Organizer))){
					 existMeetingInNextwButton=true;
				 } 
		 }		 
	 LogManager.info("Subject of Meeting: <" + getNameMeetingLabelInNextButton()+"> - "
			 		+ "Organizer: <"+getOrganizerMeetingLabelInNextButton()+">");
	 return existMeetingInNextwButton;
	 }
 
	 /**
	 * existMeetingInNextButton: Making Verification if Meeting is showed in the
	 * Next Button.
	 * @return boolean
	 */
	 public boolean existMeetingInNextButton(){
		 boolean existMeetingInNextButton=false;
		 new WebDriverWait(driver,80).until(ExpectedConditions
							.visibilityOf(organizerMeetingLabelInNextButton));
					
		 new WebDriverWait(driver,80).until(ExpectedConditions
							.visibilityOf(nameMeetingLabelInNextButton));

			 if(organizerMeetingLabelInNextButton.isDisplayed()&&
					 nameMeetingLabelInNextButton.isDisplayed()){
				 		existMeetingInNextButton=true;
			 }	
		 LogManager.info("Subject of Meeting: <" + getNameMeetingLabelInNextButton()
				 		+ "Organizer" + getOrganizerMeetingLabelInNextButton()+">");
		 return existMeetingInNextButton;
	 }
	 
	 /**
	 * getRoomNameLabel: It retrieves the Name of room Selected.
	 * @return String
	 */
	 public String getRoomNameLabel(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
								.visibilityOf(nameRoomLabel));
		 String getRoomName = nameRoomLabel.getText();
		 return getRoomName ;
	 }
	 
	 /**
	  * getEndOfDayMessage: It Retrieve the EndOfDay Message 
	  * */
	 public String getEndOfDayMessage(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
					.visibilityOf(nextButton));
		String getEndOfDayMessage = nextButton.getText();
		return getEndOfDayMessage ;
	 }
	 
	 /**
	  * getAvailableMessage: It Retrieve the Available Message
	  * */
	 public String getAvailableMessage(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
					.visibilityOf(nowButton));
		String getAvailableMessage = nowButton.getText();
		return getAvailableMessage ;
	 }
}
