package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.ResourceInfoConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class ResourceInfoPage {
	private WebDriver driver;
	@FindBy(xpath = ResourceInfoConstant.NAME_TEXT_FIELD)
	private WebElement nameTextField;
	@FindBy(xpath = ResourceInfoConstant.DISPLAY_NAME_TEXT_FIELD)
	private WebElement displayNameTextField;
	@FindBy(xpath = ResourceInfoConstant.DESCRIPTION_TEX_AREA)
	private WebElement descriptionTextArea;
	@FindBy(xpath = ResourceInfoConstant.SAVE_BUTTON)
	private WebElement saveButton;
	@FindBy(xpath = ResourceInfoConstant.REPEATED_NAME_ERROR_MESSAGE)
	private WebElement repeatedNameErrorMessage;
	@FindBy(xpath = ResourceInfoConstant.EMPTY_DISPLAY_NAME_ERROR_MESSAGE)
	private WebElement emptyDisplayNameErrorMessage;
	@FindBy(xpath = ResourceInfoConstant.EMPTY_NAME_ERROR_MESSAGE)
	private WebElement emptyNameErrorMessage;
	@FindBy(xpath = ResourceInfoConstant.RESOURCE_DISPLAY_NAME_LABEL)
	private WebElement displayNameLabel;
	@FindBy(xpath = ResourceInfoConstant.RESOURCE_ASSOCIATION_LINK)
	private WebElement resourceAssociationLink;

	public ResourceInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ResourceInfoPage enterResourceName(String resourceName) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(nameTextField));
		nameTextField.clear();
		nameTextField.sendKeys(resourceName);
		LogManager.info("Resource Name: <" + resourceName + "> was entered");
		return this;
	}
	
	public ResourcePage clickSaveButton() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath(ResourceInfoConstant.SAVE_BUTTON)));
		LogManager.info("Save button was clicked");
		return new ResourcePage(driver);
	}
	
	public ResourceInfoPage clickSaveButtonInvalidData() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Save button was clicked");
		return this;
	}
	
	public ResourceInfoPage enterDisplayName(String displayName){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(displayNameTextField));
		displayNameTextField.clear();
		displayNameTextField.sendKeys(displayName);
		LogManager.info("Display Name: <" + displayName + "> was entered");
		return this;
	}
	
	/**
	 * isNameFieldErrorMessagePresent: It returns true if the error message
	 * is present above the name text field.
	 * @return boolean
	 */
	public boolean isRepeatedNameErrorMessagePresent(){
		String expectedErrorMessage = "A resource with the same name already exists, "
				+ "please choose another name";
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(repeatedNameErrorMessage));
		String errorMessage = repeatedNameErrorMessage.getText();
		LogManager.info("Error Message: <" + errorMessage + "> was found");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	/**
	 * isDisplayNameFieldErrorMessagePresent: It returns true if the error message
	 * is present above the DisplayName text field.
	 * @return boolean
	 */
	public boolean isEmptyDisplayNameErrorMessagePresent(){
		String expectedErrorMessage = "Display name must not be empty";
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(emptyDisplayNameErrorMessage));
		String errorMessage = emptyDisplayNameErrorMessage.getText();
		LogManager.info("Error Message: <" + errorMessage + "> was found");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	/**
	 * isEmptyNameFieldErrorMessagePresent: It returns true if the error message
	 * is present above the DisplayName text field.
	 * @return boolean
	 */
	public boolean isEmptyNameFieldErrorMessagePresent(){
		String expectedErrorMessage = "Name must not be empty";
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(emptyNameErrorMessage));
		String errorMessage = emptyNameErrorMessage.getText();
		LogManager.info("Error Message: <" + errorMessage + "> was found");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	/**
	 * The getResourceDisplayName method returns the resource's display name
	 * @return String 
	 */
	public String getResourceDisplayName(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(displayNameLabel));
		String displayName = displayNameLabel.getText();
		LogManager.info("Display Name: <" + displayName + "> was found");
		return displayName;
	}

	/**
	 * The clickResourceAssociationLink method clicks on Resource Associations
	 * link.
	 * @return ResourceAssociationsPage 
	 */
	public ResourceAssociationsPage clickResourceAssociationLink(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(resourceAssociationLink));
		resourceAssociationLink.click();
		LogManager.info("Resouce Associations link was clicked");
		return new ResourceAssociationsPage(driver);
	}
}
