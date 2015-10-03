package org.roommanager.framework.models.tablet.scheduler;

import org.openqa.selenium.By;

public class CredentialsConstant {
	public static final String USERNAME_TEXT_FIELD = "//input[@type='text']";
	public static final String PASSWORD_TEXT_FIELD = "//input[@type='password']";
	public static final String OK_BUTTON = "//div[2]/div/div/div/div[3]/div/div[1]/div[1]/div[2]/div/div/div/div/div/div/div[4]/div/button[2]";
	public static final By OK_BUTTON_LOCATOR = By.xpath(OK_BUTTON);
	public static final String CREDENTIALS_ERROR_MESSAGE = "//div[@class='ng-scope']/div[@class='ng-scope']/div[@class='ng-scope']/div[@class='ng-scope']/div[@class='Modal-holder ng-scope']/div[@class='Modal-box']/div[@class='row']/div[@class='col-xs-12']/div[@class='row']/div[@class='col-xs-4 col-xs-offset-4 ng-scope']/div[@class='ng-scope']/div[@class='row v-space'][2]/div[@class='col-xs-12']/small[@class='Modal-warnings ng-binding']";
}
