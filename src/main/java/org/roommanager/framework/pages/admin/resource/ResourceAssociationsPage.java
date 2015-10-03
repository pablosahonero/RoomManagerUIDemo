package org.roommanager.framework.pages.admin.resource;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.ResourceAssociationsConstant;
import org.roommanager.framework.utilities.common.LogManager;
/**
 * This class represents to the Resource Associations Page of Resources
 * feature
 * @author Jimmy Maldonado
 *
 */
public class ResourceAssociationsPage extends PageFactory{
	private WebDriver driver;
	
	@FindBy(xpath = ResourceAssociationsConstant.RESOURCE_ASSOCIATION_TABLE)
	private WebElement resourceAssociationsTable;
	
	/**
	 * Constructor to initialize the page with PageFactory
	 * @param driver
	 */
	public ResourceAssociationsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * getResourceAssociation returns a row that contains the resource
	 * association with the room
	 * @param roomName represents the room associated to a resource
	 * @return The resource association with a room 
	 */
	private WebElement getResourceAssociation(String roomName){
		(new WebDriverWait(driver,60))
			.until(ExpectedConditions.visibilityOf(resourceAssociationsTable));
		List<WebElement> associations = resourceAssociationsTable
				.findElements(By.xpath("div"));
		for(WebElement element : associations){
			String elementName = element
					.findElement(By.xpath(ResourceAssociationsConstant.ROOM_NAME))
					.getText();
			if(elementName.equals(roomName)){
				LogManager.info("Conference Room <" + roomName + "> was found");
				return element;
			}
		}
		LogManager.info("Conference Room <" + roomName + "> was not found");
		return null;
	}
	
	/**
	 * getResourceQuantity return the resource's quantity associated to the 
	 * conference room
	 * @param roomName represents the room associated to a resource
	 * @return It returns the resource's quantity 
	 */
	public String getResourceQuantity(String roomName){
		(new WebDriverWait(driver,60))
			.until(ExpectedConditions.visibilityOf(resourceAssociationsTable));
		WebElement association = getResourceAssociation(roomName);
		String quantity = association
				.findElement(By.xpath(ResourceAssociationsConstant.QUANTITY))
				.getText();
		LogManager.info("Quantity <" + quantity + "> was found");
		return quantity;
	}
	
	/**
	 * isResourceQuantityEquals compares the expected quantity against 
	 * the retrieved quantity
	 * @param quantity represents the expected resource's quantity
	 * @param roomName represents the room that is associated to a resource
	 * @return It returns true if the quantity is equals to the retrieved 
	 * quantity
	 */
	public boolean isResourceQuantityEquals(String quantity, String roomName){
		String actual = getResourceQuantity(roomName);
		String expected = "x " + quantity;
		return expected.equals(actual);
	}
	
	/**
	 * verifyAssociatedRoomExist get the room associated if it exist 
	 * @param roomName represents the room that is associated to a resource
	 * @return It returns a String that it is the room Name.
	 * 
	 */
	public String getRoomNameAssociated(String roomName){
		
		WebElement association = getResourceAssociation(roomName);
		String roomAssociated = association
				.findElement(By.xpath(ResourceAssociationsConstant.ROOM_NAME))
				.getText();
		return roomAssociated;
	}
}
