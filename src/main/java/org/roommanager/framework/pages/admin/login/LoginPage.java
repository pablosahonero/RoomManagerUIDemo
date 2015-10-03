package org.roommanager.framework.pages.admin.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.roommanager.framework.models.admin.login.LoginConstant;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class LoginPage {

	WebDriver  driver;
	
	@FindBy (xpath = LoginConstant.LOGIN_BUTTON) 
	private WebElement login_Button;
	@FindBy (css = LoginConstant.USERNAME_TEXT_FIELD) 
	private WebElement username_TextField;
	@FindBy (css = LoginConstant.PASSWORD_TEXT_FIELD) 
	private WebElement password_TextField;

	public LoginPage(WebDriver driver){
		this.driver = driver;
		driver.get(PropertiesReader.getLoginUrlAdminModule());
		driver.navigate().refresh();
		PageFactory.initElements(driver, this);
	}

	/**
	 * Author Jimmy Vargas
	 * */
	public LoginPage setCredentials(){
		(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(username_TextField));
		String login = PropertiesReader.getLogin();
		String password = PropertiesReader.getPasswordLogin();

		username_TextField.clear();
		username_TextField.sendKeys(login);

		password_TextField.clear();
		password_TextField.sendKeys(password);
		return this;

	}

	public HomePage clickSignInButton() {
		(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(login_Button));
		login_Button.click();
		LogManager.info("SigIn Button was clicked");
		return new HomePage(driver);
		
	}
	public LoginPage setUserName(String username){
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(username_TextField));
		username_TextField.clear();
		username_TextField.sendKeys(username);
		LogManager.info("Username: <"+ username +">" + "was entered");
		return this;
	}
	
	public LoginPage setPassword(String password){
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(password_TextField));
		password_TextField.clear();
		password_TextField.sendKeys(password);
		LogManager.info("Password: <"+ password +">" + "was entered");
		return this;
	}
}
