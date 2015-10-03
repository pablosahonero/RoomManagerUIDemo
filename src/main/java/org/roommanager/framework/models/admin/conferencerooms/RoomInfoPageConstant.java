package org.roommanager.framework.models.admin.conferencerooms;

public class RoomInfoPageConstant {

	/**
	 * Locator Type = Xpath*/
	public static final String POWER_ON_BUTTON_ROOM = "//div[4]/div/div/div[2]/div/div/div[1]/div[2]/button[1]";
	/**
	 * Locator Type = Xpath*/
	public static final String POWER_OFF_BUTTON_ROOM = "//div[4]/div/div/div[2]/div/div/div[1]/div[2]/button[2]";
	/**
	 * Locator Type = Xpath**/
	public static final String DISPLAYNAME_ROOM_TEXT_FIELD = "(//input[@type='text'])[4]";
	/**
	 * Locator Type = Xpath*/
	public static final String CODE_ROOM_TEXT_FIELD = "(//input[@type='text'])[5]";
	/**
	 * Locator Type = Xpath*/
	public static final String CAPACITY_ROOM_TEXT_FIELD = "(//input[@type='text'])[6]";
	/**
	 * Locator Type = Xpath*/
	public static final String SAVE_BUTTON_ROOM = "//div[3]/div[2]/button";
	/**
	 * Locator Type = Css*/
	public static final String SAVE_CANCEL_ROOM = "button.btn-clear";
	/**
	 * Locator Type = Xpath*/
	public static final String LINK_OUT_OF_ORDER = "(//a[@onclick='checkBreadCrumb(this)'])[3]";
	/**
	 * Locator Type = Xpath*/
	public static final String LINK_RESOURCES_ASSOCIATIONS = "(//a[@onclick='checkBreadCrumb(this)'])[2]";
	/**
	 * Locator Type = Css*/
	public static final String LOCATION_BUTTON= "//div[4]/div/div/div[2]/div/div/div[2]/div/form/div[5]/div[1]/span/button";
	public static final String LOCATION_TYPE_BUTTTON = "//div[4]/div/div/div[2]/div/div/div[2]/div/form/div[5]/div[2]/div/treeview/div/div[1]/span";
	/**
	 * Locatoro Type = Xpath*/
	public static final String DISPLAY_BUTTON = "//treeview/div/div/span";
	/**
	 * Locator Type = Xpath*/
	public static final String NAME_LOCATION = "treeview/div/div[2]/transclude/div[1]";
	/**
	 * Locator Type = Xpath*/
	public static final String DIV_ELEMENT = "div";
	/**
	 * Locator Type = Xpath*/
	public static final String LOCATION_LIST = "//div[4]/div/div/div[2]/div/div/div[2]/div/form/div[5]/div[2]/div/treeview/div[2]";
	public static final String LOCATION_TEXT_FIELD = "//div[4]/div/div/div[2]/div/div/div[2]/div/form/div[5]/div[1]/div";
	public static final String ERROR_MESSAGE_DISPLAY_NAME ="//div[4]/div/div/div[2]/div/div/div[2]/div/form/small";
	/**
	 * Locator Type = Xpath*/
	public static final String ROOM_NAME_TEXT_FIELD = "//div[4]/div/div/div[2]/div/div/div[2]/div/form/div[1]/input";
	
	/** ROOM_NAME_TITLE: It represents the Room Name Title Xpath*/
	public static final String ROOM_NAME_TITLE = "//div[4]/div/div/div[2]/div/div/div[1]/div[1]/h2";
}
