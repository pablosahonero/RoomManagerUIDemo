package org.roommanager.framework.models.admin.resource;

public class ResourceInfoConstant {
	/** RESOURCE_NAME_FIELD locator to Resource Name text field*/
    public static final String NAME_TEXT_FIELD="(//input[@type='text'])[3]";
    
    /** RESOURCE_DISPLAY_NAME_FIELD locator to Resource Display Name text field*/
    public static final String DISPLAY_NAME_TEXT_FIELD="(//input[@type='text'])[4]";
    
    /** RESOURCE_DESCRIPTION_AREA locator to Resource Description text area*/
    public static final String DESCRIPTION_TEX_AREA="//textarea";
    
    /** SAVE_BUTTON locator to Save button*/
    public static final String SAVE_BUTTON="//div[3]/div[2]/button";
    
    /** NAME_ERROR_MESSAGE locator to error message from name's text field*/
    public static final String REPEATED_NAME_ERROR_MESSAGE = "//div[4]/div/div/div[2]/div/div/resource-card/div/div/div[2]/div/form/small[2]";
    
    /** DISPLAY_NAME_ERROR_MESSAGE locator to error message from display name text field*/
    public static final String EMPTY_DISPLAY_NAME_ERROR_MESSAGE = "//div[4]/div/div/div[2]/div/div/resource-card/div/div/div[2]/div/form/small[5]";
    
    /** EMPTY_NAME_ERROR_MESSAGE locator to error message from name's text field*/
    public static final String EMPTY_NAME_ERROR_MESSAGE = "//div[4]/div/div/div[2]/div/div/resource-card/div/div/div[2]/div/form/small[1]";
    
    /** RESOURCE_DISPLAY_NAME_LABEL locator to resource's display name*/
    public static final String RESOURCE_DISPLAY_NAME_LABEL = "//div[4]/div/div/div[2]/div/div/div/div/h3";
    
    /** RESOURCE_ASSOCIATION_LINK locator to resource association link*/
    public static final String RESOURCE_ASSOCIATION_LINK = "//div[@id='breadcrumb']/a[2]";
}
