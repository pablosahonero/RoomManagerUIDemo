package org.roommanager.framework.pages.admin.conferenceroom;

import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ConferenceRoomConstant;
import org.roommanager.framework.models.admin.conferencerooms.OutOfOrderConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class OutOfOrderPage {

	WebDriver driver;	
	
	@FindBy (xpath = OutOfOrderConstant.START_HOUR_OUT_OF_ORDER) 
	private WebElement startHourOutOfOrder;
	
	@FindBy (xpath = OutOfOrderConstant.START_MINS_OUT_OF_ORDER) 
	private WebElement startMinsOutOfOrder  ;
	
	@FindBy (xpath = OutOfOrderConstant.START_AM_PM_OUT_OF_ORDER) 
	private WebElement startAmPmOutOfOrder;
	
	@FindBy (xpath = OutOfOrderConstant.END_HOUR_OUT_OF_ORDER) 
	private WebElement endHourOutOfOrder;
	
	@FindBy (xpath = OutOfOrderConstant.END_MINS_OUT_OF_ORDER) 
	private WebElement endMinsOutOfOrder;
	
	@FindBy (xpath = OutOfOrderConstant.END_AM_PM_OUT_OF_ORDER) 
	private WebElement endAmPmOutOfOrder;
	
	@FindBy (xpath = OutOfOrderConstant.TITLE_OUT_OF_ORDER) 
	private WebElement titleOutOfOrder;
	
	@FindBy (xpath = OutOfOrderConstant.DESCRIPTIOM_OUT_OF_ORDER) 
	private WebElement descriptionOutOfOrder;
	
	@FindBy (xpath = OutOfOrderConstant.SAVE_BUTTON_OUT_OF_ORDER) 
	private WebElement saveButtonOutOfOrder;
	
	@FindBy (css = ConferenceRoomConstant.TITLE_TABLE_ROOMS) 
	private WebElement titleTableRooms;
	
	@FindBy (xpath= OutOfOrderConstant.ERROR_MESSAGE_WITHOUT_TITLE)
	private WebElement errorMessageWhitOutTitle;
	
	@FindBy (xpath= OutOfOrderConstant.TOP_ARROW_START_HOUR_TO_FIELD)
	private WebElement topArrowStartHourToField;
	
	@FindBy (xpath= OutOfOrderConstant.BOTTOM_ARROW_START_HOUR_TO_FIELD)
	private WebElement bottomArrowStartHourToField;
	
	@FindBy (xpath= OutOfOrderConstant.BOTTOM_ARROW_START_HOUR_FROM_FIELD)
	private WebElement bottomArrowStartHourFromField;
	
	@FindBy (xpath= OutOfOrderConstant.TOP_ARROW_START_HOUR_FROM_FIELD)
	private WebElement topArrowStartHourFromField;
	
	@FindBy (xpath = OutOfOrderConstant.ERROR_MESSAGE_TO_GREATER_FROM)
	private WebElement errorMessageToGreaterFrom;

	@FindBy (xpath = OutOfOrderConstant.ON_OFF_SCHEDULE_BUTTON)
	private WebElement onOffScheduleButton;
	
	@FindBy (xpath = OutOfOrderConstant.SEND_MAIL_CHECKBOX)
	private WebElement sendMailCheckBox;
	
	public OutOfOrderPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * clickSaveButtonOutOfOrder: It click on Save Button.
	 * @return OutOfOrderPage
	 */
	public OutOfOrderPage clickSaveButtonOutOfOrder(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveButtonOutOfOrder));
		saveButtonOutOfOrder.click();
		LogManager.info("Was Clicking Save Button Out Of Order");
		return this;
	}
	
	/**
	 * setDescription: It enters Out Of Order Description 
	 */
	public OutOfOrderPage setDescription(String setDescription){
		
		new WebDriverWait(driver,60).until(ExpectedConditions
								.visibilityOf(descriptionOutOfOrder));
		descriptionOutOfOrder.clear();
		descriptionOutOfOrder.sendKeys(setDescription);
		LogManager.info("OutOfOrderPage - Description value in the room ");	
		return this;
	}
	
	/**
	 * setDescription: It enters title in an Out Of Order. 
	 * @return String
	 */
	public OutOfOrderPage setTitle(String nameTitle){
		
		new WebDriverWait(driver,80).until(ExpectedConditions
				 					 .visibilityOf(titleOutOfOrder));        		            
		titleOutOfOrder.clear();    
		titleOutOfOrder.sendKeys(nameTitle);
		LogManager.info("It enters title in an Out Of Order");
		return this;
	}
	
	/**
	 * existOutOfOrder: Making Verification if Error Message is showed when 
	 * created an OutOfORder without Title
	 * @return boolean
	 */
	public boolean existErrorMessageWhithoutTitle(){
		boolean errorMessageExist=false;
		new WebDriverWait(driver,80).until(ExpectedConditions
									.visibilityOf(descriptionOutOfOrder));
			if(errorMessageWhitOutTitle.isDisplayed()){
				errorMessageExist=true;
			}	
		LogManager.info("Title Subject: <" + getTitleOutOfOrder()+ ">");
		return errorMessageExist;
	}
	
	/**
	 * getTitleOutOfOrder: It retrieves the specified title of a OutOfOrder.
	 * @return String
	 */
	public String getTitleOutOfOrder(){
		new WebDriverWait(driver,60).until(ExpectedConditions
									.visibilityOf(titleOutOfOrder));
		String getTitle = titleOutOfOrder.getAttribute("value");
		return getTitle ;
	}
	
	/**
	 * getStartHourToField: It retrieves the specified Start HOur a OutOfOrder.
	 * @return String
	 */
	public String getStartHourToField(){
		new WebDriverWait(driver,60).until(ExpectedConditions
									.visibilityOf(startHourOutOfOrder));
		String getTitle = startHourOutOfOrder.getAttribute("value");
		return getTitle ;
	}
	
	/**
	 * getStartHourToField: It retrieves the specified Start HOur a OutOfOrder.
	 * @return String
	 */
	public String getEndHourFromField(){
		new WebDriverWait(driver,60).until(ExpectedConditions
									.visibilityOf(endHourOutOfOrder));
		String getTitle = endHourOutOfOrder.getAttribute("value");
		return getTitle ;
	}
	
	/**
	 * intervalTime: Returns a start<Hour> and end<Hour> with an hour intervalod
	 * @return array
	 */
	public static ArrayList<String> intervalTime() {
		ArrayList<String> intervalTime = new ArrayList<String>(); 		 
		Calendar time = Calendar.getInstance();  
		  
		intervalTime.add((time.get(Calendar.HOUR)+1)+"");
		intervalTime.add(time.get(Calendar.MINUTE)+"");
		if ((time.get(Calendar.AM_PM)+"").equals("0")){		
			intervalTime.add("AM");
		}
		else{
			intervalTime.add("PM");}
			intervalTime.add((time.get(Calendar.HOUR)+2)+"");
			intervalTime.add(time.get(Calendar.MINUTE)+"");
			if ((time.get(Calendar.AM_PM)+"").equals("0")){				
				intervalTime.add("AM");
			}
			else{
				intervalTime.add("PM");
			}			
		return intervalTime;
	}
	
	/**
	 *clickTopArrowStartHourToField: It click on Low Arrow Start of Start Hour Button Button */
	public OutOfOrderPage clickTopArrowStartHourToField(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions
									  .visibilityOf(topArrowStartHourToField));
		topArrowStartHourToField.click();
		LogManager.info("Was Clicking Top Arrow Start of Start Hour Button - To Field");
		return this;
	}
	
	/**
	 * clickTopArrowStartHourFromField: It click on Top Arrow Start of Start Hour Button Button
	 */
	public OutOfOrderPage clickTopArrowStartHourFromField(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions
									  .visibilityOf(topArrowStartHourFromField));
		topArrowStartHourFromField.click();
		LogManager.info("Was Clicking Top Arrow Start of Start Hour Button - From Field");
		return this;
	}
	
	/**
	 *clickBottomArrowStartHourToField: It click on Low Arrow Start of Start Hour Button Button */
	public OutOfOrderPage clickBottomArrowStartHourToField(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions
									  .visibilityOf(bottomArrowStartHourToField));
		bottomArrowStartHourToField.click();
		LogManager.info("Was Clicking Bottom Arrow Start of Start Hour Button - To Field");
		return this;
	}
	
	/**
	 * clickBottomArrowStartHourFromField: It click on Top Arrow Start of Start Hour Button Button
	 */
	public OutOfOrderPage clickBottomArrowStartHourFromField(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions
									  .visibilityOf(bottomArrowStartHourFromField));
		bottomArrowStartHourFromField.click();
		LogManager.info("Was Clicking Top Arrow Start of Start Hour Button - From Field");
		return this;
	}
	
	/** existOutOfOrder: Making Verification if Error Message is showed when 
	 * created an OutOfORder and "To" field must be greater than "From" field.
	 * @return boolean
	 */
	public boolean errorMessageToGreaterFrom(){
		boolean errorMessageExist=false;
		new WebDriverWait(driver,80).until(ExpectedConditions
									.visibilityOf(errorMessageToGreaterFrom));
		if(errorMessageToGreaterFrom.isDisplayed()){
			errorMessageExist=true;
		}	
		LogManager.info("Error Message: <" + errorMessageToGreaterFrom.getText()+ ">");
		return errorMessageExist;
	}	
	
	/**
	 * clickCalendarButton: It clicks on the Calendar Button 
	 * 
	 * @return OutOfOrderPage
	 */
	public OutOfOrderPage clickScheduleButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(onOffScheduleButton));
		onOffScheduleButton.click();
		LogManager.info("O/OffSchedule button was clicked");
		return this;
	}
	
	/**
	 * checkSendMailCheckbox: It check in the SendMailCheckbox
	 * @return OutOfOrderPage
	 */
	public OutOfOrderPage checkSendMailCheckbox() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(sendMailCheckBox));
		sendMailCheckBox.click();		
		LogManager.info("Send mail was checked");
		return this;
	}
	
	/**
	 * isOutOfOrderPageIsAvailable: It verifies Out Of Order Page is 
	 * Available
	 * @return boolean
	 */
	public boolean isOutOfOrderPageIsAvailable() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
			.visibilityOf(onOffScheduleButton));
		boolean isOutOfOrderPageAvailable = onOffScheduleButton
											.isDisplayed();
		LogManager.info("Out Of Order Page is Available");
		return isOutOfOrderPageAvailable;
	}
	
	/**
	 * isOutOfOrderPageIsAvailable: It verifies Out Of Order Page is 
	 * Available
	 * @return boolean
	 */
	public boolean isOutOfOrderUpdated(String StartHourActual,
									   String EndHourActual,
									   String StartHourExpected,
									   String EndHourExpeted) {
		boolean isOutOfOrderUpdated;
		
		if ((StartHourActual.equals(StartHourExpected))||(EndHourActual.equals(EndHourExpeted))){			
			isOutOfOrderUpdated = false;
			LogManager.info("Out Of Order Page not updated ");
		}
		else {
			isOutOfOrderUpdated = true;
			LogManager.info("Out Of Order Page updated ");
		}
		
		

		return isOutOfOrderUpdated;
	}
	
	/**
	 * enabledSendMailCheckbox: It verify IF enabled the  SendMail Check box 
	 * @return OutOfOrderPage
	 */
	public boolean enabledSendMailCheckbox() {
		boolean enabledCheckSendMailCheckbox = false;
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
									   .visibilityOf(sendMailCheckBox));
			if (sendMailCheckBox.isEnabled()){
				enabledCheckSendMailCheckbox=true;
			}		
		LogManager.info("Send mail chexbox enabled: "+enabledCheckSendMailCheckbox);
		return enabledCheckSendMailCheckbox;
	}
}
