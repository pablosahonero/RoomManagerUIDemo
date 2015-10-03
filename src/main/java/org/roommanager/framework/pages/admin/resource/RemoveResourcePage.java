package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.CreateResourceConstant;
import org.roommanager.framework.models.admin.resource.RemoveResourceConstant;
import org.roommanager.framework.utilities.common.LogManager;

/**
 * This class represents to the Remove Resource Page
 * feature
 * @author Rodrigo Zarate
 *
 */
public class RemoveResourcePage {
	private WebDriver driver;
	
	@FindBy(xpath = RemoveResourceConstant.RESOURCE_NAME_FIELD)
	private WebElement nameTextField;
	@FindBy(css = RemoveResourceConstant.REMOVE_BUTTON)
	private WebElement removeButton;
	
	
	
	public RemoveResourcePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	

	/**
	 * clickRemoveResourceButton: It performs a click on remove button
	 * and returns to the Resource page.
	 * @return ResourcePage
	 */
	public ResourcePage clickRemoveResourceButton() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(removeButton));
		removeButton.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(CreateResourceConstant.SAVE_BUTTON)));
		LogManager.info("Save button was clicked");
		return new ResourcePage(driver);
	}
	
	
	
	/**
	 * verifyIfRemoveResourcePageIsDisplayed It verify if the RemoveResource Page
	 * was displayed
	 * @return RemoveResourcePage
	 */
	public RemoveResourcePage verifyIfRemoveResourcePageIsDisplayed(String resourceName){
		(new WebDriverWait(driver, 90))
		.until(ExpectedConditions.visibilityOf(nameTextField));
		
		if(resourceName == nameTextField.getText()){
			LogManager.info("RemoveResource Page was displayed");
			return this;
		}
		return null;
	}
			
	
}
