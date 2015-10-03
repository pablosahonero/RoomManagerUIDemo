package org.roommanager.framework.pages.admin.impersonation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.impersonation.ImpersonationConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class ImpersonationPage {

	WebDriver driver;
	@FindBy (xpath = ImpersonationConstant.CONFIRMATION_MESSAGE)
	private WebElement confirmationMessage;
	@FindBy (xpath = ImpersonationConstant.IMPERSONATION_CHECKBOX)
	private WebElement impersonationCheckbox;
	@FindBy (xpath = ImpersonationConstant.IMPERSONATION_TITLE)
	private WebElement impersonationTitle;
	@FindBy (xpath = ImpersonationConstant.SAVE_BUTTON)
	private WebElement saveButton;
	
	public ImpersonationPage (WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * clickImpersonationCheckBox: It click and check on
	 * check box.
	 * 
	 * @return ImpersonationPage
	 */
	public ImpersonationPage clickImpersonationCheckBox(){
		(new WebDriverWait(driver,20)).until(ExpectedConditions.textToBePresentInElement(impersonationTitle,"Exchange Server Account"));
		impersonationCheckbox.click();
		LogManager.info("Click on 'Impersonation' checkbox on impersonation page");
		return this;
	}
	
	/**
	 * clickSignInButton: It click on save button.
	 * 
	 * @return ImpersonationPage
	 */
	public ImpersonationPage clickSaveButton(){
		(new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Click on 'Save' button on impersonation page");
		return this;
	}
	
	/**
	 * getConfirmationMessage: It obtains the value of
	 * confirmation message.
	 * 
	 * @return String
	 */
	public String getConfirmationMessage(){
		(new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(confirmationMessage));
		LogManager.info("Retrieve text of confirmation message: Impersonation now is enabled.");
		return driver.findElement(By.xpath(ImpersonationConstant.CONFIRMATION_MESSAGE)).getText();
	}
}
