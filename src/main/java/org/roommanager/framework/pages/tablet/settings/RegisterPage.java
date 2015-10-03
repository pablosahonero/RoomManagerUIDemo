package org.roommanager.framework.pages.tablet.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.setting.ConnectionConstant;
import org.roommanager.framework.pages.tablet.common.TopMenuPage;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

/**
 * Regsiter Page
 * */
public class RegisterPage {


	@FindBy (xpath = ConnectionConstant.URL_TEXT_FIELD)
	private WebElement serverUrlTextField;

	@FindBy(id = "username")
	private WebElement adminNameTextField;

	@FindBy(id = "password")
	private WebElement passwordTextField;

	@FindBy (css = ".btn-primary")
    private WebElement signInButton;

	//TODO:Deprecated?
    @FindBy (xpath = ConnectionConstant.NAVIGATION_LINK)
    private WebElement navigationLink;
    @FindBy (xpath = ConnectionConstant.SUCCESSFUL_MESSAGE)
    private WebElement successfulMessage;
    By message= By.xpath(ConnectionConstant.SUCCESSFUL_MESSAGE);
    By error = By.xpath(ConnectionConstant.ERROR_MESSAGE);
    
	@FindBy(xpath = ConnectionConstant.ROOM_LOAD)
	private WebElement nameRoomLabel;
	
	private WebDriver driver;

	/**
	 * This is the Register Page
	 * @Author:Jimmy Vargas
	 * */
	public RegisterPage(WebDriver driver){
		this.driver = driver;
		this.driver.get(PropertiesReader.getRegisterURL());
		PageFactory.initElements(driver, this);
	}

	public RegisterPage enterServiceUrl(String url){
		(new WebDriverWait(driver, 30))
				.until(ExpectedConditions.visibilityOf(serverUrlTextField));
		serverUrlTextField.clear();
		serverUrlTextField.sendKeys(url);
		LogManager.info("The Url: <" + url + "> was entered in the Url Text Field");
		return this;
	}

	/**
	 * Method that enters the credentials
	 *
	 * @Author: Jimmy Vargas
	 * */
	public RegisterPage enterCredentials(){
		setAdmin(PropertiesReader.getLogin());
		setAdminPassword(PropertiesReader.getPasswordLogin());
		return this;
	}
	/**
	 * Sets the admin name
	 * @Author: Jimmy Vargas
	 * */
	public void setAdmin(String username){
		(new WebDriverWait(driver, 30))
				.until(ExpectedConditions.visibilityOf(adminNameTextField));
		adminNameTextField.clear();
		adminNameTextField.sendKeys(username);
	}

	/**
	 * Sets the admin name
	 * @Author: Jimmy Vargas
	 * */
	public void setAdminPassword(String password){
		(new WebDriverWait(driver, 30))
				.until(ExpectedConditions.visibilityOf(passwordTextField));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
	}

	public StatusPage clickSignInButton(){
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.invisibilityOfElementLocated(error));
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.elementToBeClickable(signInButton));
		signInButton.click();
        LogManager.info("save Button was clicked");
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.invisibilityOfElementLocated(message));
        return new StatusPage(this.driver);

	}
	public Boolean isConnectionNotEstablished(String url){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.visibilityOf(serverUrlTextField));
		Boolean Notconnection=true;
		String actualConnection=serverUrlTextField.getAttribute("value");
		if(url.equals(actualConnection)){
			Notconnection=false;
			LogManager.info("There is a connection on URL: "+ actualConnection);
		}
		else{
			LogManager.info("There isn't a connection on URL: "+ actualConnection);
		}
		return Notconnection;
	}
	

	
	public NavigationPage clickNavigationLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(navigationLink));
		navigationLink.click();
		return new NavigationPage(driver);
	}
	
	public String connectionMessage(){
        (new WebDriverWait(driver, 30))
        .until(ExpectedConditions.invisibilityOfElementLocated(error));
        (new WebDriverWait(driver,30))
        .until(ExpectedConditions.visibilityOf(successfulMessage));
        String successfulConnectMessage = successfulMessage.getText();
        (new WebDriverWait(driver, 60))
        .until(ExpectedConditions.invisibilityOfElementLocated(message));
        LogManager.info("Successfull Message: <"+ successfulConnectMessage +"> was displayed");
        return successfulConnectMessage; 
	}
	
	 public String getRoomName(){
		 new WebDriverWait(driver,60).until(ExpectedConditions
								.visibilityOf(nameRoomLabel));
		 String getRoomName = nameRoomLabel.getText();
		 return getRoomName ;
	 }
	


}
