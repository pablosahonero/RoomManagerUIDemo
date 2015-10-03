package org.roommanager.framework.models.admin.resource;
/**
 * The CreateResourceConstant class provides the locators from 
 * Create Resource Tab.
 * 
 * @author Qadev02
 * 
 */
public class CreateResourceConstant {
        
	/** RESOURCE_NAME_FIELD locator to Resource Name text field*/
    public static final String RESOURCE_NAME_FIELD="(//input[@type='text'])[3]";
    
    /** RESOURCE_DISPLAY_NAME_FIELD locator to Resource Display Name text field*/
    public static final String RESOURCE_DISPLAY_NAME_FIELD="(//input[@type='text'])[4]";
    
    /** RESOURCE_DESCRIPTION_AREA locator to Resource Description text area*/
    public static final String RESOURCE_DESCRIPTION_AREA="//textarea";
    
    /** SAVE_BUTTON locator to Save button*/
    public static final String SAVE_BUTTON="//div[3]/div[2]/button";
    
    /** CANCEL_BUTTON locator to Save button*/
    public static final String CANCEL_BUTTON="//div[4]/div/div/div[3]/div[1]/button";
    
    /** NAME_ERROR_MESSAGE locator to error message from name's text field*/
    public static final String REPEATED_NAME_ERROR_MESSAGE = "//div[4]/div/div/div[2]/div/resource-card/div/div/div[2]/div/form/small[2]";
    
    /** DISPLAY_NAME_ERROR_MESSAGE locator to error message from display name text field*/
    public static final String EMPTY_DISPLAY_NAME_ERROR_MESSAGE = "//div[4]/div/div/div[2]/div/resource-card/div/div/div[2]/div/form/small[5]";
    
    /** EMPTY_NAME_ERROR_MESSAGE locator to error message from name's text field*/
    public static final String EMPTY_NAME_ERROR_MESSAGE = "//div[4]/div/div/div[2]/div/resource-card/div/div/div[2]/div/form/small[1]";
    
}
	   