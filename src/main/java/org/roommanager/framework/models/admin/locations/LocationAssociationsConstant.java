package org.roommanager.framework.models.admin.locations;

import org.openqa.selenium.By;

public class LocationAssociationsConstant {
	public static final String AVAILABLE_ROOMS_LIST = "//div[2]/div/div[2]/div[1]/modal-container/div[2]/div/div/ng-transclude/div[2]/div[3]/div[1]/div/ul";
	public static final String ASSOCIATED_ROOMS_LIST = "//div[2]/div/div[2]/div[1]/modal-container/div[2]/div/div/ng-transclude/div[2]/div[3]/div[2]/div[2]/ul";
	public static final String ASSOCIATED_ROOMS_TABLE = "//div[2]/div/div[2]/div[1]/modal-container/div[2]/div/div/ng-transclude/div[2]/div[3]/div[2]";
	public static final By LIST_ITEM_ELEMENT = By.xpath("li");
	public static final By ROOM_NAME = By.xpath("div/div[1]");
	public static final By ASSOCIATE_ROOM_BUTTON = By.xpath("div/div[2]/button");
	public static final String SAVE_BUTTON = "//div[2]/div/div[2]/div[1]/modal-container/div[2]/div/div/ng-transclude/div[3]/div/button[2]";
}
