package org.roommanager.framework.utilities.api.admin;

import org.json.simple.JSONObject;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class LocationApi {
	
	/** LOCATION_BODY: Body of the location*/
	private static final String LOCATION_BODY = 
			"{ \"name\": \"[name]\", \"customName\": \"[displayName]\",\"description\": \"[description]\", \"parentId\":\"\", \"roomIds\": []}";
	
	/** LOCATION_ASSOCIATION_BODY: Location association request body*/
	private static final String LOCATION_ASSOCIATION_BODY = "{\"name\":\"[locationName]\",\"customName\":\"[locationDisplayName]\",\"description\":\"[locationDescription]\",\"parentId\":\"\",\"roomIds\":[\"[roomId]\"]}";
	
	/**
	 * deleteLocationByName: It delete a location by name
	 * @param name: It represents the Location's Name
	 */
	public static void deleteLocationByName(String name){
		String id = getLocationIdByName(name);
		String url = PropertiesReader.getRoomManagerApi() + "locations/" + id;
		String token = "jwt " + LoginApi.getToken();
		ApiManager.deleteHttpMethod(url,token);
	}

	/**
	 * createLocation: It create a location with  the location_body
	 * @param name: It represents the Location's Name
	 * @param displayName: It represents the Location's displayName
	 * @param description: It represents the Location's description
	 */
	public static void createLocation(String name, String displayName, String description){
		String url = PropertiesReader.getRoomManagerApi() + "locations";
		String locationBody = LOCATION_BODY;
		locationBody = locationBody.replace("[name]", name)
			.replace("[displayName]", displayName)
			.replace("[description]", description);
		String token = "jwt " + LoginApi.getToken();

		ApiManager.postHttpMethod(url, locationBody,token);
	}	
	
	/**
	 * getLocationIdByName: It get the location's Id by name
	 * @param locationName: It represents the Location's Name
	 * @return String
	 */
	private static String getLocationIdByName(String locationName) {
		String url = PropertiesReader.getRoomManagerApi() + "locations";
		String propertyName = "name";
		String locationId = ApiManager.getObjectPropertyByGivenPropertyValue("_id", propertyName, locationName, url);
		return locationId;
    }
	
	/**
	 * getLocationByName: It get the location's name
	 * @param locationName: It represents the Location's Name
	 * @return JsonObject
	 */
	public static JSONObject getLocationByName(String locationName) {
		String url = PropertiesReader.getRoomManagerApi() + "locations";
		String propertyName = "name";
		String locationId = ApiManager.getObjectPropertyByGivenPropertyValue("_id", propertyName, locationName, url);
		url = url + "/" + locationId;
		JSONObject location = (JSONObject)ApiManager.jsonRequest(ApiManager.getHttpMethod(url));
		return location;
    }

	public static void associateLocation(String locationName, String roomName){
		String url = PropertiesReader.getRoomManagerApi() + "locations";
		JSONObject location = getLocationByName(locationName);
		String locationId = location.get("_id").toString();
		String locationDisplayName = location.get("customName").toString();
		String locationDescription = location.get("description").toString();
		String roomId = RoomApi.getRoomIdByName(roomName);
		url = url + "/" + locationId;
		String locationAssociationBody = LOCATION_ASSOCIATION_BODY
										 .replace("[locationName]", locationName)
										 .replace("[locationDisplayName]", locationDisplayName)
										 .replace("[locationDescription]", locationDescription)
										 .replace("[roomId]", roomId);
		String token = "jwt " + LoginApi.getToken();

		ApiManager.putHttpMethod(url, locationAssociationBody,token);
	}
}
