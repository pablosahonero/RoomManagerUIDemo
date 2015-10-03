package org.roommanager.framework.pages.admin.emailserver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.emailserver.EmailServerConstant;
import org.roommanager.framework.pages.admin.common.LeftMenu;
import org.roommanager.framework.utilities.common.LogManager;

public class EmailServerPage extends LeftMenu {

	WebDriver driver;
	public static final String addButton = EmailServerConstant.ADD_BUTTON;
	public static final String removeButton = EmailServerConstant.REMOVE_BUTTON;
	public static final String serverButton = EmailServerConstant.SERVER_BUTTON;
	
	@FindBy (xpath = addButton) WebElement add_Button;
	@FindBy (xpath = removeButton) WebElement remove_Button;
	@FindBy (xpath = serverButton) WebElement server_Button;
	
	public EmailServerPage  (WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public CreateEmailServerPage clickAddButton(){
		(new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(add_Button));
		add_Button.click();
		LogManager.info("Add Button was clicked");
		return new CreateEmailServerPage(driver);
	}
	
	public RemoveEmailServerPage clickRemoveButton(){
		(new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(remove_Button));
		remove_Button.click();
		LogManager.info("Remove Button was clicked");
		return new RemoveEmailServerPage(driver);
	}
	
	public String getEmailServer(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(server_Button));
	    return driver.findElement(By.xpath(serverButton)).getText();
	}
	
	public boolean existsEmailServerRegistered(){
		try{
			(new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(add_Button));
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
