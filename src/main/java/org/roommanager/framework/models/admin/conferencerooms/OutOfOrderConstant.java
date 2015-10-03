package org.roommanager.framework.models.admin.conferencerooms;

public class OutOfOrderConstant {
	/** Locator Type = Xpath**/
	public static final String START_HOUR_OUT_OF_ORDER = "(//input[@type='text'])[4]";
	/** Locator Type = Xpath*/
	public static final String START_MINS_OUT_OF_ORDER = "(//input[@type='text'])[5]";
	/** Locator Type = Xpath*/
	public static final String START_AM_PM_OUT_OF_ORDER = "(//button[@type='button'])[55]";
	/** Locator Type = Xpath*/
	public static final String END_HOUR_OUT_OF_ORDER = "(//input[@type='text'])[7]";
	/** Locator Type = Xpath*/
	public static final String END_MINS_OUT_OF_ORDER = "(//input[@type='text'])[8]";
	/** Locator Type = Xpath*/
	public static final String END_AM_PM_OUT_OF_ORDER = "(//button[@type='button'])[105]";
	/** Locator Type = Xpath*/
	public static final String TITLE_OUT_OF_ORDER = "(//input[@type='text'])[9]";
	/** Locator Type = Xpath*/
	public static final String DESCRIPTIOM_OUT_OF_ORDER = "//textarea";
	/** Locator Type = Xpath*/
	public static final String SAVE_BUTTON_OUT_OF_ORDER = "//div[3]/div[2]/button";
	/** Locator Type = Xpath*/
	public static final  String OUT_OF_ORDER_BUTTON = "//div[2]/div/div/div/label";
	/** Locator Type = Xpath*/
	public static final String ACTION_SEND_EMAIL = "(//input[@type='checkbox'])[9]";
	/** Locator Type = Xpath*/
	public static final String ERROR_MESSAGE_WITHOUT_TITLE = "//div[2]/small";
	/** Locator Type = Xpath*/
	public static final String BOTTOM_ARROW_START_HOUR_FROM_FIELD = "//tr[3]/td/a/span";
	/** Locator Type = Xpath*/
	public static final String BOTTOM_ARROW_START_HOUR_TO_FIELD = "//div[2]/div/div[2]/table/tbody/tr[3]/td/a/span";
	/** Locator Type = Xpath*/
	public static final String TOP_ARROW_START_HOUR_FROM_FIELD = "//a/span";
	/** Locator Type = Xpath*/
	public static final String TOP_ARROW_START_HOUR_TO_FIELD="//div[2]/div/div[2]/table/tbody/tr/td/a/span";
	/** Locator Type = Xpath*/  
	public static final String ERROR_MESSAGE_TO_GREATER_FROM = "//div[3]/small";
	/** Locator Type = Xpath*/
	public static final String ON_OFF_SCHEDULE_BUTTON = "//div[4]/div/div/div[2]/div/div/div/div[2]/div[1]/div[1]/div/label";
	/** Locator Type = Xpath*/
	public static final String SEND_MAIL_CHECKBOX = "//div[4]/div/div/div[2]/div/div/div/div[2]/div[1]/div[3]/form/div[4]/div/label/span";
}
