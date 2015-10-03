package org.roommanager.framework.models.admin.locations;
/**
 * This class contains the locators from Locations Info Page
 * @author Qadev02
 *
 */
public class LocationsInfoConstant {
	/** NAME_TEXT_FIELD locator to Name text field*/
    public static final String NAME_TEXT_FIELD="//input[@id='location-add-name']";
    
    /** DISPLAY_NAME_TEXT_FIELD locator to Display name text field*/
    public static final String DISPLAY_NAME_TEXT_FIELD="//input[@id='location-add-display-name']";
    
    /** ADD_PARENT_LOCATION_BUTTON locator to Parent location button*/
    public static final String ADD_PARENT_LOCATION_BUTTON="(//button[@type='button'])[2]";
    
    public static final String EXPAND_BUTTON = "//treeview/div/div/span";
    
    public static final String DESCRIPTION_TEXT_FIELD = "//textarea[@id='location-add-description']";
    
    public static final String CANCEL_BUTTON = "//div[2]/div/div[2]/div[1]/modal-container/div[2]/div/div/ng-transclude/div[3]/div/button[1]";
    
    public static final String SAVE_BUTTON = "//div[2]/div/div[2]/div[1]/modal-container/div[2]/div/div/ng-transclude/div[3]/div/button[2]";
}
