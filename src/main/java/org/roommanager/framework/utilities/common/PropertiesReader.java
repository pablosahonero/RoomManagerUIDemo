package org.roommanager.framework.utilities.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	/**
	 * This method recovered the content from framework.porperties file
	 * @return a Properties object 
	 * */
	private static Properties properties = new Properties();
	private static InputStream propertyFile = null;
	private static Properties getConfiguration(){
			if(propertyFile == null){
				try {
					propertyFile = new FileInputStream("src/main/resources/framework.properties");
					properties.load(propertyFile);
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
						try {
							propertyFile.close();
						} catch (IOException e) {
							     //LogManager.error(e.getMessage());
						}
				}
			}
		return properties;
	}
	
	
	
	/**
     * This method get a URL of Room Manager App - Admin module
     * @return property URL_ROOM_MANAGER_ADMIN
     */
	public static String getLoginUrlAdminModule(){
		return getConfiguration().getProperty("URL_ROOM_MANAGER_ADMIN");
	}
	/**
     * This method get a URL of Room Manager App - Tablet module
     * @return property URL_ROOM_MANAGER_TABLET
     */
	public static String getLoginUrlTabletModule(){
		return getConfiguration().getProperty("URL_ROOM_MANAGER_TABLET");
	}
	/**
     * This method get a PASSWORD of User
     * @return property PASSWORD
     */
	public static String getUsername(){
		return getConfiguration().getProperty("USER");
	}
	/**
     * This method get a PASSWORD of a User
     * @return property PASSWORD
     */
	public static String getPassword(){
		return getConfiguration().getProperty("PASSWORD");
	}
	/**
     * This method get a EXCHANGE_HOSTNAME of email server
     * @return property Exchange Hostname
     */
	public static String getExchangeHostName(){
		return getConfiguration().getProperty("EXCHANGE_HOSTNAME");
	}
	/**
     * This method get a EXCHANGE_USERNAME of email server
     * @return property Exchange Username
     */
	public static String getExchangeUserName(){
		return getConfiguration().getProperty("EXCHANGE_USERNAME");
	}
	/**
     * This method get a EXCHANGE_PASSWORD of email server
     * @return property Exchange Password
     */
	public static String getExchangePassWord(){
		return getConfiguration().getProperty("EXCHANGE_PASSWORD");
	}
	/**
     * This method get a EXCHANGE_DOMAIN of email server
     * @return property Exchange Domain
     */
	public static String getExchangeDomain(){
		return getConfiguration().getProperty("EXCHANGE_DOMAIN");
	}	
	/**
     * This method get a EXCHANGE_SERVER_DESCRIPTION of email server
     * @return property Exchange Server Description
     */
	public static String getExchangeServerDescription(){
		return getConfiguration().getProperty("EXCHANGE_SERVER_DESCRIPTION");
	}
	/**
     * This method get a Browser
     * @return property Browser name
     */
	public static String getBrowser(){
        return getConfiguration().getProperty("BROWSER");
  }
	/**
     * This method get a URL of Room Manager APIe
     * @return property URL_API
     */
	public static String getRoomManagerApi(){
		return getConfiguration().getProperty("URL_API");
	}
	
	/**
     * This method get a URL of Room Manager settings APIe
     * @return property URL_API
     */
	public static String getRoomManagerSettingsApi(){
		return getConfiguration().getProperty("URL_API" + "/service-types");
	}
	
	/**
     * This method get a URL of Room Manager APIe
     * @return property URL_API
     */
	public static String getBasicAuthentication(){
		return getConfiguration().getProperty("basicAuthentication");
	}

	/**
	 * This method get a LOGIN of email server
	 * @return property Login
	 */
	public static String getLogin(){
		return getConfiguration().getProperty("LOGIN");
	}
	/**
	 * This method get a LOGIN_PASSWORD of email server
	 * @return property Login Password
	 */
	public static String getPasswordLogin(){
		return getConfiguration().getProperty("LOGIN_PASSWORD");
	}

	public static String getRoomName(){ return getConfiguration().getProperty("ROOM_NAME");}

	public static String getRegisterURL(){return getConfiguration().getProperty("URL_ROOM_MANAGER_REGISTER");}
}
