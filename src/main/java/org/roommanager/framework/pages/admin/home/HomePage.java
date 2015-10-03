package org.roommanager.framework.pages.admin.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.home.HomeConstant;
import org.roommanager.framework.pages.admin.common.LeftMenu;
import org.roommanager.framework.utilities.common.LogManager;

public class HomePage extends LeftMenu{
	private WebDriver driver;
	
	@FindBy (linkText = HomeConstant.ROOM_MANAGER_TEXT) 
	private WebElement roomManagerTitle_Text;
	
	public HomePage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String getHomePageTitle(){
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(roomManagerTitle_Text));
		LogManager.info("Home Page Link Text: <" + roomManagerTitle_Text.getText() + "> was retrieved");
		return roomManagerTitle_Text.getText();
	}
}
