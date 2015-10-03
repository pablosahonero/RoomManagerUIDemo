package org.roommanager.framework.pages.tablet.scheduler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.scheduler.CredentialsConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class CredentialsPage {
	@FindBy (xpath = CredentialsConstant.USERNAME_TEXT_FIELD) 
	private WebElement usernameTextField;
	@FindBy (xpath = CredentialsConstant.PASSWORD_TEXT_FIELD) 
	private WebElement passwordTextField;
	@FindBy (xpath = CredentialsConstant.OK_BUTTON) 
	private WebElement okButton; 
	@FindBy (xpath = CredentialsConstant.CREDENTIALS_ERROR_MESSAGE) 
	private WebElement credentialsErrorMessage; 
	private By okButtonLocator = CredentialsConstant.OK_BUTTON_LOCATOR;
	private WebDriver driver;
	
	public CredentialsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public CredentialsPage enterUsername(String username){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(usernameTextField));
		usernameTextField.clear();
		usernameTextField.sendKeys(username);
		LogManager.info("Username: <" + username + "> was entered");
		return this;
	}
	
	public CredentialsPage enterPassword(String password){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(passwordTextField));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		LogManager.info("Password: <" + password + "> was entered");
		return this;
	}
	
	public SchedulerPage clickOkButton(){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(okButton));
		okButton.click();
		LogManager.info("OK Button was clicked");
		new WebDriverWait(driver, 60)
			.until(ExpectedConditions.invisibilityOfElementLocated(okButtonLocator));
		return new SchedulerPage(driver);
	}
	
	public boolean isCredentialsErrorMessagePresent(){
		String expectedErrorMessage = "Wrong username or password";
		(new WebDriverWait(driver,60))
		.until(ExpectedConditions.visibilityOf(credentialsErrorMessage));
		String errorMessage = credentialsErrorMessage.getText();
		LogManager.info("Error Message: <"+ errorMessage +"> was displayed");
		return errorMessage.equals(expectedErrorMessage);
	}
}
