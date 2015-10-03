package org.roommanager.framework.pages.admin.resource;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.ResourceConstant;
import org.roommanager.framework.pages.admin.common.LeftMenu;
import org.roommanager.framework.utilities.common.LogManager;

public class ResourcePage extends LeftMenu {
	private WebDriver driver;

	@FindBy(xpath = ResourceConstant.ADD_RESOURCE_BUTTON)
	private WebElement addResource_Button;
	@FindBy(id = ResourceConstant.REMOVE_RESOURCE_BUTTON)
	private WebElement removeResource_Button;
	@FindBy(xpath = ResourceConstant.NEXT_PAGE_BUTTON)
	private WebElement nextPage_Button;
	@FindBy(xpath = ResourceConstant.NEXT_PAGE_INPUT)
	private WebElement nextPage_Input;
	@FindBy(xpath = ResourceConstant.RESOURCES_TABLE_NUMBER_OF_PAGES)
	private WebElement resource_TableNumberOfPages;
	@FindBy(xpath = ResourceConstant.RESOURCES_LIST)
	private WebElement resource_List;
	@FindBy(xpath = ResourceConstant.RESOURCE_TABLE_ITEM)
	private WebElement resource_ListItem;
	@FindBy(xpath = ResourceConstant.DIV_ELEMENT)
	private WebElement div_Element;
	@FindBy(xpath = ResourceConstant.PAGINATION_TEXT_FIELD)
	private WebElement paginationTextField;
	@FindBy(xpath = ResourceConstant.FILTER_TEXT_FIELD)
	private WebElement filterTextField;
    @FindBy(xpath = ResourceConstant.GRID_RESOURCE)
    private WebElement resourceGrid;
    
	/** propertyName Name of the Property to be searched*/
	private String propertyName = "Name";
	
	/** propertyDisplayName Display Name of the Property to be searched*/
	private String propertyDisplayName = "DisplayName";

	public ResourcePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CreateResourcePage clickAddResourceButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(addResource_Button));
		addResource_Button.click();
		LogManager.info("Add Resource button was clicked");
		return new CreateResourcePage(driver);
	}
	
	public ResourceInfoPage doubleClickOnResourceFromTable(String resourceName) {
		WebElement resource = getResourceFromAllPages(propertyName,resourceName,
				getResourcesTableNumberOfPages())
				.findElement(By.xpath(ResourceConstant.RESOURCE_TABLE_ITEM));
		String resourceItemName = resource.getText();
		Actions action = new Actions(driver);
		action.doubleClick(resource);
		action.perform();
		LogManager.info("Double Click on Resource: <" + resourceItemName+ "> from Resources Table");
		return new ResourceInfoPage(driver);
	}

	public ResourcePage clickOnResourceFromTable(String resourceName) {
		WebElement resource = getResourceFromAllPages(propertyName,resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(ResourceConstant.RESOURCE_TABLE_ITEM))
				.getText();
		resource.click();
		LogManager.info("Click on Resource: <" + resourceItemName+ "> from Resources Table");
		return this;
	}

	public String getResourceNameInTable(String resourceName) {
		WebElement resource = getResourceFromAllPages(propertyName,resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(ResourceConstant.RESOURCE_TABLE_ITEM))
				.getText();
		LogManager.info("Resource Name: <" + resourceItemName+ "> was retrieved");
		return resourceItemName;
	}
	
	/**
	 * Method getResourceDisplayNameInTable Get the Display Name of the resource 
	 * */
	public String getResourceDisplayNameInTable(String resourceDisplayName) {
		WebElement resource = getResourceFromAllPages(propertyDisplayName,resourceDisplayName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(ResourceConstant.DISPLAYNAMERESOURCE_TABLE_ITEM))
				.getText();
		LogManager.info("Resource Name: <" + resourceItemName+ "> was retrieved");
		return resourceItemName;
	}

	public boolean verifyElementDoesNotExist(String resourceName) {
		WebElement resource = getResourceFromAllPages(propertyName,resourceName,
				getResourcesTableNumberOfPages());
		return resource == null ? true : false;
	}

	private WebElement getResourceFromAllPages(String property,String propertyValue,
			int numberOfPages) {
		WebElement resource = null;
		for (int index = 1; index <= numberOfPages; index++) {
			resource = getResourceByAttribute(property,propertyValue);
			if (resource != null) {
				LogManager.info("Resource: <" + propertyValue+ "> was found in page:" + index);
				return resource;
			}
			clickNextPageButton(index + 1, numberOfPages);
			LogManager.info("Searching for resource in page: " + index);
		}
		return resource;
	}

	private void clickNextPageButton(int actualPage, int numberOfPages) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(nextPage_Button));
		nextPage_Button.click();
		String nextPageinput = nextPage_Input
				.getAttribute("value");
		while (Integer.parseInt(nextPageinput) != actualPage
				&& actualPage <= numberOfPages) {
			nextPageinput = nextPage_Input
					.getAttribute("value");
		}
		LogManager.info("The Next Page button was clicked");
	}

	private int getResourcesTableNumberOfPages() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(resource_TableNumberOfPages));
		String pages = resource_TableNumberOfPages.getText().replace("/ ", "");
		LogManager.info("The number of Pages of the Resources Table is: "+ Integer.parseInt(pages));
		return Integer.parseInt(pages);
	}

	private WebElement getResourceByAttribute(String property,String propertyValue) {
		String itemInTable = "";
		if(property == propertyName){
			itemInTable = ResourceConstant.RESOURCE_TABLE_ITEM;
		}
		else if(property == propertyDisplayName){
			itemInTable = ResourceConstant.DISPLAYNAMERESOURCE_TABLE_ITEM;
		}			
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(resource_List));
		List<WebElement> resourcesTable = resource_List
				.findElements(By.xpath(ResourceConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(
					By.xpath(itemInTable)).getText();
			if (resourceItemName.equals(propertyValue)) {
				LogManager.info("Resource: <" + resourceItemName+ "> was retrieved from Resources Table");
				return resource;
			}
		}
		LogManager.info("Resource: <" + propertyValue + "> wasn't found");
		return null;
	}
	
	/**
	 * setPaginationTextField sets a value in the Pagination text field
	 * @param page represents the page's number in the resource table
	 * @return ResorucePage
	 */
	public ResourcePage setPaginationTextField(String page){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(paginationTextField));
		paginationTextField.clear();
		paginationTextField.sendKeys(page);
		LogManager.info("Page: <" + page + "> was entered");
		return this;
	}
	
	/**
	 * getPaginationTextField returns the content of the Pagination text field 
	 * @return Pagination
	 */
	public String getPaginationTextField(){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(paginationTextField));
		String page = paginationTextField.getAttribute("value");
		LogManager.info("Page: <" + page + "> was retrieved");
		return page;
	}
	
	/**
	 * filterResourceByName filters a resource by name
	 * @param resourceName represents the resource's name to be searched
	 * @return ResourcePage
	 */
	public ResourcePage filterResourceByName(String resourceName){
		(new WebDriverWait(driver, 30))
			.until(ExpectedConditions.visibilityOf(filterTextField));
		filterTextField.clear();
		filterTextField.sendKeys(resourceName);
		LogManager.info("Resource name: <" + resourceName + "> was entered");
		return this;
	}
	
	/**
	 * clickRemoveResourceButton click on the remote button a resource by name
	 * @return RemoveResourcePage
	 */
	public RemoveResourcePage clickRemoveResourceButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(removeResource_Button));
		removeResource_Button.click();
		LogManager.info("Remove button was clicked");
		return new RemoveResourcePage(driver);
	}
	
    /**
    * clickResourceLink click on the Resource link a page of resource should
    * be displayed
    * @return ResourcePage
    */
    public boolean isResourcePagePresent(){
    	boolean isPresent = true;     
    	boolean gridResourcesName = 
    			(new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(resourceGrid)).isDisplayed();
        LogManager.info("The grid of resources is Present");
        return gridResourcesName == isPresent ? true : false;
    }
}
