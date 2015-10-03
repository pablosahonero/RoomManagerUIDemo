package org.roommanager.framework.pages.admin.conferenceroom;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ResourceAssociationsConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class ResourceAssociationsPage {
	WebDriver driver;

	WebElement associate;

	@FindBy(css = ResourceAssociationsConstant.CANCEL_BUTTON)
	private WebElement cancelButton;
	@FindBy(css = ResourceAssociationsConstant.SAVE_BUTTON)
	private WebElement saveButton;
	@FindBy(xpath = ResourceAssociationsConstant.LIST_RESOURCES_AVAILABLE)
	private WebElement resourceAvailableList;
	@FindBy(xpath = ResourceAssociationsConstant.LIST_RESOURCE_ASSOCIATED)
	private WebElement resourceAssociatedList;
	@FindBy(xpath = ResourceAssociationsConstant.DIV_ELEMENT)
	private WebElement divElement;
	@FindBy(xpath = ResourceAssociationsConstant.NAME_RESOURCE)
	private WebElement nameResource;
	@FindBy(xpath = ResourceAssociationsConstant.ASSOCIATE_BUTTON)
	private WebElement associateButton;
	@FindBy(css = ResourceAssociationsConstant.DESASSOCIATE_RESOURCE)
	private WebElement desassociateButton;
	@FindBy(xpath = ResourceAssociationsConstant.QUANTITY_ASSOCIATE_TEXT_FIELD)
	private WebElement quantityAssociateTextField;

	public ResourceAssociationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * clickCancelButton: It clicks on the Cancel Button on the Resource
	 * Associations Page.
	 * 
	 * @return ConferenceRoomPage
	 */
	public ConferenceRoomPage clickCancelButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(cancelButton));
		cancelButton.click();
		LogManager.info("Cancel button was clicked");
		return new ConferenceRoomPage(driver);
	}

	/**
	 * clickSignInButton: It clicks on the Save Button on the Resource
	 * Associations Page.
	 * 
	 * @return ConferenceRoomPage
	 */
	public ConferenceRoomPage clickSaveButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Save button was clicked");
		return new ConferenceRoomPage(driver);
	}

	/**
	 * clickOnAddResourceButton: It clicks on the Add(+) on an specific resource
	 * Button on the Resource Associations Page.
	 * 
	 * @return ResourceAssociationsPage
	 */
	public ResourceAssociationsPage clickOnAddResourceButton(String resourceName) {
		WebElement resourceAssociationButton = getResourceByName(resourceName);
		resourceAssociationButton.click();
		LogManager.info("The Resource: <" + resourceName
				+ "> was associated to the room");
		return this;
	}

	/**
	 * getResourceByName: It retrieves a specified Resource in the Available
	 * Table.
	 * 
	 * @param resourceName: It represents the Resource's Name
	 * @return WebElement
	 */
	private WebElement getResourceByName(String resourceName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(resourceAvailableList));
		List<WebElement> resourcesTable = resourceAvailableList.findElements(By
				.xpath(ResourceAssociationsConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(
					By.xpath(ResourceAssociationsConstant.NAME_RESOURCE))
					.getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName
						+ "> was retrieved from Resources Table");
				int position = resourcesTable.indexOf(resource) + 1;

				String associationButtonLocator = ResourceAssociationsConstant.LIST_RESOURCES_AVAILABLE
						+ "/"
						+ ResourceAssociationsConstant.DIV_ELEMENT
						+ "["
						+ position
						+ "]/"
						+ ResourceAssociationsConstant.ASSOCIATE_BUTTON;
				LogManager.info("Resource: <" + resourceName
						+ "> in the Available table was found");
				return driver.findElement(By.xpath(associationButtonLocator));
			}
		}
		LogManager.info("Resource: <" + resourceName
				+ "> in the Available table wasn't found");
		return null;
	}

	/**
	 * clickOnDesassociatedResourceButton: It clicks on the Dissociate(-) on an
	 * specific resource Button on the Resource Associations Page.
	 * 
	 * @return ResourceAssociationsPage
	 */
	public ResourceAssociationsPage clickOnDesassociatedResourceButton(
			String resourceName) {
		WebElement resourceDesassociationButton = getResourceAssociatedByName(resourceName);
		resourceDesassociationButton.click();
		LogManager.info("Resource: <" + resourceName
				+ "> was dissasociate from the room");
		return this;
	}

	/**
	 * getResourceAssociatedByName: It retrieves a specified Resource in the
	 * Associated Table.
	 * 
	 * @param resourceName: It represents the Resource's Name
	 * @return WebElement
	 */
	private WebElement getResourceAssociatedByName(String resourceName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(resourceAssociatedList));
		List<WebElement> resourcesTable = resourceAssociatedList
				.findElements(By
						.xpath(ResourceAssociationsConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(
					By.xpath(ResourceAssociationsConstant.NAME_RESOURCE))
					.getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName
						+ "> was retrieved from Resources Table");
				int position = resourcesTable.indexOf(resource) + 1;

				String desassociationButtonLocator = ResourceAssociationsConstant.LIST_RESOURCE_ASSOCIATED
						+ "/"
						+ ResourceAssociationsConstant.DIV_ELEMENT
						+ "["
						+ position
						+ "]/"
						+ ResourceAssociationsConstant.DESASSOCIATE_RESOURCE;
				LogManager.info("Resource: <" + resourceName
						+ "> in the Associated table was found");
				return driver
						.findElement(By.xpath(desassociationButtonLocator));
			}
		}
		LogManager.info("Resource: <" + resourceName
				+ "> in the Associated table wasn't found");
		return null;
	}

	/**
	 * getResourceAssociatedQuantityByName: It returns the name of the resource
	 * for set the quantity.
	 * 
	 * @param String resourceName
	 * @return String
	 */
	private WebElement getResourceAssociatedQuantity(String resourceName,
			String quantity) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(resourceAssociatedList));
		List<WebElement> resourcesTable = resourceAssociatedList
				.findElements(By
						.xpath(ResourceAssociationsConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(
					By.xpath(ResourceAssociationsConstant.NAME_RESOURCE))
					.getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName
						+ "> was retrieved from Resources Table");
				int position = resourcesTable.indexOf(resource) + 1;

				String desassociationButtonLocator = ResourceAssociationsConstant.LIST_RESOURCE_ASSOCIATED
						+ "/"
						+ ResourceAssociationsConstant.DIV_ELEMENT
						+ "["
						+ position
						+ "]/"
						+ ResourceAssociationsConstant.QUANTITY_ASSOCIATE_TEXT_FIELD;
				LogManager.info("Resource: <" + resourceName
						+ "> in the Associated table was found");
				return driver
						.findElement(By.xpath(desassociationButtonLocator));
			}
		}
		LogManager.info("Resource: <" + resourceName
				+ "> in the Associated table wasn't found");
		return null;
	}

	public ResourceAssociationsPage setQuantityOfResource(String resourceName,
			String quantity) {
		WebElement quantityResource = getResourceAssociatedQuantity(
				resourceName, quantity);
		quantityResource.clear();
		quantityResource.sendKeys(quantity);
		LogManager.info("Resource: <" + resourceName
				+ "> was dissasociate from the room");
		return this;
	}

	/**
	 * getResourceAssociatedByNameInTable: It returns the name of the resource
	 * 
	 * @param resourceName: It represents the Resource's Name
	 * @return String
	 */
	public String getResourceAssociatedByNameInTable(String resourceName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(resourceAssociatedList));
		List<WebElement> resourcesTable = resourceAssociatedList
				.findElements(By
						.xpath(ResourceAssociationsConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(
					By.xpath(ResourceAssociationsConstant.NAME_RESOURCE))
					.getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName
						+ "> was retrieved from Resources Table");
				return resourceName;
			}
		}
		LogManager.info("Resource: <" + resourceName + "> wasn't found");
		return null;
	}

	/**
	 * isResourceAssociatedOnTheRoom: It verifies if the Resource does not exist
	 * on associated in the Room.
	 * 
	 * @param resourceName: It represents the Resource's Name
	 * @return boolean
	 */
	public boolean isResourceAssociatedOnTheRoom(String resourceName) {
		return getResourceAssociatedByName(resourceName) == null ? false : true;
	}

	/**
	 * isQuantityResourceDisplayed: It verifies if the quantity of the Resource
	 * is displayed on associated in the Room.
	 * 
	 * @param String resourceName
	 * @param String quantity
	 * @return boolean
	 */
	public boolean isQuantityResourceDisplayed(String resourceName,
			String quantity) {
		WebElement quantityResource = getResourceAssociatedQuantity(
				resourceName, quantity);
		LogManager.info("Resource: <" + resourceName + "> has <" + quantity
				+ "> of the resource");
		return (quantityResource.getAttribute("value")).equals(quantity);
	}

	/**
	 * isResourceAvailableOnTheRoom: It verifies if the Resource is Available in
	 * the Room.
	 * 
	 * @param resourceName: It represents the Resource's Name
	 * @return boolean
	 */
	public boolean isResourceAvailableOnTheRoom(String resourceName) {
		return getResourceByName(resourceName) == null ? false : true;
	}
}
