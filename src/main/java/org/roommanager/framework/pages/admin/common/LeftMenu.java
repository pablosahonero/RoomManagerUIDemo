package org.roommanager.framework.pages.admin.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.common.LeftMenuConstant;
import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.emailserver.EmailServerPage;
import org.roommanager.framework.pages.admin.impersonation.ImpersonationPage;
import org.roommanager.framework.pages.admin.locations.LocationsPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.LogManager;

public class LeftMenu {
	private WebDriver driver;
	public static final String emailServerLink = LeftMenuConstant.EMAIL_SERVER_LINK;
	public static final String impersonationLink = LeftMenuConstant.IMPERSONATION_LINK;
	public static final String conferenceRoomsLink = LeftMenuConstant.CONFERENCE_ROOMS_LINK;
	public static final String resourcesLink = LeftMenuConstant.RESOURCES_LINK;
	public static final String locationsLink = LeftMenuConstant.LOCATIONS_LINK;
	
	@FindBy (linkText = emailServerLink) WebElement emailServer_Link;
	@FindBy (linkText = impersonationLink) WebElement impersonation_Link;
	@FindBy (linkText = conferenceRoomsLink) WebElement conferenceRooms_Link;
	@FindBy (linkText = resourcesLink) WebElement resources_Link;
	@FindBy (linkText = locationsLink) WebElement locations_Link;
	
	public LeftMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EmailServerPage selectEmailServerLink(){
		selectLink(emailServer_Link, 60);
		LogManager.info("Email Server Page Link was clicked");
		return new EmailServerPage(driver);
	}

	public ImpersonationPage selectImpersonationLink(){
		selectLink(impersonation_Link, 60);
		return new ImpersonationPage(driver);
	}
	public ConferenceRoomPage selectConferenceRoomsLink(){
		selectLink(conferenceRooms_Link, 60);
		return new ConferenceRoomPage(driver);
	}
	public ResourcePage selectResourcesLink(){
		selectLink(resources_Link, 60);
		LogManager.info("Resource Page Link was clicked");
		return new ResourcePage(driver);
	}
	public LocationsPage selectLocationsLink(){
		selectLink(locations_Link, 30);
		return new LocationsPage(driver);
	}
	private void selectLink(WebElement element, long timeOutInseconds){
		(new WebDriverWait(driver,timeOutInseconds))
			.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
}
