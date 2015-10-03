package org.roommanager.framework.pages.admin.emailserver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.emailserver.RemoveEmailServerConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class RemoveEmailServerPage {
	WebDriver driver;
	public static final String yesButton = RemoveEmailServerConstant.YES_BUTTON;
	public static final String noButton = RemoveEmailServerConstant.NO_BUTTON;
	
	@FindBy (xpath = yesButton) WebElement yes_Button;
	@FindBy (id = noButton) WebElement no_Button;
	
	public RemoveEmailServerPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public EmailServerPage clickOnYesButton(){
		(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(yes_Button));
        yes_Button.click();
        LogManager.info("Yes Button was clicked");
        return new EmailServerPage(driver);
	}
}
