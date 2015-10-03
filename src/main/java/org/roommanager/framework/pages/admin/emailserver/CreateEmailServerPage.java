package org.roommanager.framework.pages.admin.emailserver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.emailserver.CreateEmailServerConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class CreateEmailServerPage {

	WebDriver driver;
	public static final String hostnameTextBox = CreateEmailServerConstant.HOSTNAME_TEXTBOX;
	public static final String usernameTextBox = CreateEmailServerConstant.USERNAME_TEXTBOX;
	public static final String passwordTextBox = CreateEmailServerConstant.PASSWORD_TEXTBOX;
	public static final String saveButton = CreateEmailServerConstant.SAVE_BUTTON;
	
	@FindBy (xpath = hostnameTextBox)WebElement hostname_TextBox;
	@FindBy (xpath = usernameTextBox) WebElement username_TextBox;
	@FindBy (xpath = passwordTextBox) WebElement password_TextBox;
	@FindBy (xpath = saveButton) WebElement save_Button;
	
	public CreateEmailServerPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setHostname(String hostname){
		(new WebDriverWait(driver,5)).until(ExpectedConditions.visibilityOf(hostname_TextBox));
		hostname_TextBox.clear();
		hostname_TextBox.sendKeys(hostname);
		LogManager.info("Hostname: <" + hostname + "> was entered");
	}
	
	public void setUsername(String username){
		(new WebDriverWait(driver,5)).until(ExpectedConditions.visibilityOf(username_TextBox));
		username_TextBox.clear();
		username_TextBox.sendKeys(username);
		LogManager.info("Username: <" + username + "> was entered");
	}
	
	public void setPassword(String password){
		(new WebDriverWait(driver,5)).until(ExpectedConditions.visibilityOf(password_TextBox));
		password_TextBox.clear();
		password_TextBox.sendKeys(password);
		LogManager.info("Password: <" + password + "> was entered");
	}
	
	public EmailServerPage clickSaveButton(){
		(new WebDriverWait(driver,5)).until(ExpectedConditions.visibilityOf(save_Button));
		save_Button.click();
		LogManager.info("Save Button was clicked");
		return new EmailServerPage(driver);
	}
}
