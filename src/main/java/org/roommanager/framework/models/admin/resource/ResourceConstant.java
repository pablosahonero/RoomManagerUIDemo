package org.roommanager.framework.models.admin.resource;

public class ResourceConstant {
	/**Locator Type = xpath*/
	public static final String	ADD_RESOURCE_BUTTON="//div/div/button";
	/**Locator Type = id*/
	public static final String REMOVE_RESOURCE_BUTTON="btnRemove";
	/**Locator Type = xpath*/
	public static final String NEXT_PAGE_BUTTON="//div[@id='resourcesGrid']/div[3]/div/div[2]/div[2]/button[3]";
	/**Locator Type = xpath*/
	public static final String NEXT_PAGE_INPUT="//input[@type='number']";
	/**Locator Type = xpath*/
	public static final String RESOURCES_TABLE_NUMBER_OF_PAGES="//*[@id='resourcesGrid']/div[3]/div/div[2]/div[2]/span";
	/**Locator Type = xpath*/
	public static final String SEARCH_RESOURCE_TEXT_FIELD="//input[@type='text']";
	/**Locator Type = xpath*/
	public static final String RESOURCES_LIST="//div[@id='resourcesGrid']/div[2]/div";
	/**Locator Type = xpath*/
	public static final String RESOURCE_TABLE_ITEM="div[3]/div[2]/div/span";
	/**Locator Type = xpath*/
	public static final String DISPLAYNAMERESOURCE_TABLE_ITEM="div[4]/div[2]/div/span";
	/**Locator Type = xpath*/
	public static final String DIV_ELEMENT="div";
	/**Locator Type = xpath*/
	public static final String GRID_RESOURCE="//div[2]/div/div[2]/div[2]/div";

	/** Locator to the pagination text field*/
	public static final String PAGINATION_TEXT_FIELD = "//div[@id='resourcesGrid']/div[3]/div/div[2]/div[2]/input";
	
	/** Locator to the filter text field*/
	public static final String FILTER_TEXT_FIELD = "//div[2]/div/div[2]/div[1]/div/label/input";
}
