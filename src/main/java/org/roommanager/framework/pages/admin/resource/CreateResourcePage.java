package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.CreateResourceConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class CreateResourcePage {
	private WebDriver driver;
	
	@FindBy(xpath = CreateResourceConstant.RESOURCE_NAME_FIELD)
	private WebElement nameTextField;
	@FindBy(xpath = CreateResourceConstant.RESOURCE_DISPLAY_NAME_FIELD)
	private WebElement displayNameTextField;
	@FindBy(xpath = CreateResourceConstant.RESOURCE_DESCRIPTION_AREA)
	private WebElement descriptionTextArea;
	@FindBy(xpath = CreateResourceConstant.SAVE_BUTTON)
	private WebElement saveButton;
	@FindBy (xpath = CreateResourceConstant.CANCEL_BUTTON)
	private WebElement cancelButton;
	@FindBy(xpath = CreateResourceConstant.REPEATED_NAME_ERROR_MESSAGE)
	private WebElement repeatedNameErrorMessage;
	@FindBy(xpath = CreateResourceConstant.EMPTY_DISPLAY_NAME_ERROR_MESSAGE)
	private WebElement emptydisplayNameErrorMessage;
	@FindBy(xpath = CreateResourceConstant.EMPTY_NAME_ERROR_MESSAGE)
	private WebElement emptyNameErrorMessage;

	public CreateResourcePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CreateResourcePage enterResourceName(String resourceName) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(nameTextField));
		nameTextField.clear();
		nameTextField.sendKeys(resourceName);
		LogManager.info("Name: <" + resourceName + "> was entered");
		return this;
	}

	public CreateResourcePage enterResourceDisplayName(
			String resourceDisplayName) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(displayNameTextField));
		displayNameTextField.clear();
		displayNameTextField.sendKeys(resourceDisplayName);
		LogManager.info("Display Name: <" + resourceDisplayName+ "> was entered");
		return this;
	}

	public CreateResourcePage enterResourceDescription(
			String resourceDescription) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(descriptionTextArea));
		descriptionTextArea.clear();
		descriptionTextArea.sendKeys(resourceDescription);
		LogManager.info("Description: <" + resourceDescription+ "> was entered");
		return this;
	}

	public ResourcePage clickSaveResourceButton() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(CreateResourceConstant.SAVE_BUTTON)));
		LogManager.info("Save button was clicked");
		return new ResourcePage(driver);
	}
	/**
	 * clickSaveButtonInvalidData: It performs a click on save button
	 * and returns to the same page.
	 * @return CreateResourcePage
	 */
	public CreateResourcePage clickSaveButtonInvalidData() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Save button was clicked");
		return this;
	}
	
	public ResourcePage clickCancelResourceButton() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(cancelButton));
		cancelButton.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(CreateResourceConstant.SAVE_BUTTON)));
		LogManager.info("Cancel button was clicked");
		return new ResourcePage(driver);
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
	public boolean isEmptyDisplayNameMessagePresent(){
		String expectedErrorMessage = "Display name must not be empty";
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(emptydisplayNameErrorMessage));
		String errorMessage = emptydisplayNameErrorMessage.getText();
		LogManager.info("Error Message: <" + errorMessage + "> was found");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	/**
	 * isEmptyNameFieldErrorMessagePresent: It returns true if the error message
	 * is present above the DisplayName text field.
	 * @return boolean
	 */
	public boolean isEmptyNameErrorMessagePresent(){
		String expectedErrorMessage = "Name must not be empty";
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(emptyNameErrorMessage));
		String errorMessage = emptyNameErrorMessage.getText();
		LogManager.info("Error Message: <" + errorMessage + "> was found");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	/**
	 * isCreateResourcePagePresent It returns true if the CreateResource Page
	 * is displayed.
	 * @return boolean
	 */
	public boolean isCreateResourcePagePresent(){
		boolean isPresent = true;
		
		boolean nameTextFieldPresent = (new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(nameTextField)).isDisplayed();
		LogManager.info("Name TextField is Present");
		
		boolean displayNameTextFieldPresent = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.visibilityOf(displayNameTextField)).isDisplayed();
		LogManager.info("Display Name TextField is Present");
		
		boolean descriptionTextFieldPresent = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.visibilityOf(descriptionTextArea)).isDisplayed();
		LogManager.info("Description TextArea is Present");
		
		return nameTextFieldPresent == isPresent && displayNameTextFieldPresent == isPresent
				&& descriptionTextFieldPresent == isPresent ? true : false;
	}
}
