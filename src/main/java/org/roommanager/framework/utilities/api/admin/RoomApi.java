package org.roommanager.framework.utilities.api.admin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class RoomApi {

	/**
	 * getRoomEmailByName: It retrieves the Email address from the specified Room
	 * @param roomName: It represents the Room's Name
	 * @return String
	 */
	public static String getRoomEmailByName(String roomName){
		String url = PropertiesReader.getRoomManagerApi() + "rooms";
		String propertyName = "displayName";
		
		String roomId = ApiManager
			.getObjectPropertyByGivenPropertyValue("emailAddress", propertyName,
					roomName, url);
		return roomId;
	}
	
	/**
	 * getRoomEmailByName: It retrieves the Id from the specified Room
	 * @param roomName: It represents the Room's Name
	 * @return String
	 */
	public static String getRoomIdByName(String roomName){
		String url = PropertiesReader.getRoomManagerApi() + "rooms";
		String propertyName = "displayName";		
		String roomId = ApiManager
			.getObjectPropertyByGivenPropertyValue("_id", propertyName,
					roomName, url);
		return roomId;
	}
	
	/**
	 * disableRoom: It enables the specified Room
	 * @param roomName: It represents the Room's Name
	 */
	public static void disableRoom(String roomName){
		setRoomStatus(roomName, "false");
	}
	
	/**
	 * enableRoom: It enables the specified Room
	 * @param roomName: It represents the Room's Name
	 */
	public static void enableRoom(String roomName){
		setRoomStatus(roomName, "true");
	}
	
	/**
	 * setRoomStatus: It sets the specified Room to the given status
	 * @param roomName: It represents the Room's Name
	 * @param status: It represents Room's status
	 */
	private static void setRoomStatus(String roomName, String status){
		String requestBody = "{\"enabled\": [status],"
				             + " \"customDisplayName\": \"[displayName]\"}";
		requestBody = requestBody.replace("[displayName]", roomName)
					  .replace("[status]", status);
		String url = PropertiesReader.getRoomManagerApi() + "rooms";
		String roomId = getRoomIdByName(roomName);
		url = url + "/"+ roomId;
		String token = "jwt " + LoginApi.getToken();
		ApiManager.putHttpMethod(url, requestBody,token);
	}
	/**
	 * setRoomCapacity: It sets the specified Room to the given capacity
	 * @param roomName: It represents the Room's Name
	 * @param capacity: It represents Room's capacity
	 */
	public static void setRoomCapacity(String roomName, String capacity){
		String requestBody = "{\"capacity\": [capacity],"
				             + " \"customDisplayName\": \"[displayName]\"}";
		requestBody = requestBody.replace("[displayName]", roomName)
					  .replace("[capacity]", capacity);
		String url = PropertiesReader.getRoomManagerApi() + "rooms";
		String roomId = getRoomIdByName(roomName);
		url = url + "/"+ roomId;
		String token = "jwt " + LoginApi.getToken();
		ApiManager.putHttpMethod(url, requestBody,token);
	}
	
	/**
	 * createOutOfOrder: It created an OutOfOrder in the specified Room 
	 * @param roomName: It represents the Room's Name
	 * @param startTime: It start Time for out of order
	 * @param endTime: It end Time for out of order
	 */	
	public static void createOutOfOrder(String startTime, String endTime,String roomName){

		String outOfOrderBody = "{\"from\": \"[startTime]\","
								+ "\"to\": \"[endTime]\","
								+ "\"roomId\": \"[roomName]\","
								+ "\"title\": \"[subject]\"}";
		
		String url = PropertiesReader.getRoomManagerApi() 
				     + "services/[serviceId]/rooms/[roomId]/out-of-orders?active=false&email=true";
		String roomId = RoomApi.getRoomIdByName(roomName);
		String serviceId = EmailServerApi.getEmailServiceId();
		String title = "Temporarily Out of Order";
		url = url.replace("[roomId]", roomId)
				 .replace("[serviceId]", serviceId);
		outOfOrderBody = outOfOrderBody.replace("[startTime]", startTime)
									   .replace("[endTime]", endTime)
									   .replace("[roomName]", roomId)
									   .replace("[subject]",title );

		String token = "jwt " +LoginApi.getToken();
		ApiManager.postHttpMethod(url,outOfOrderBody,token);
	}
	
	/**
	 * deleteAllOutOfOrders: It deletes all Out Of Orders from the specified Room
	 * @param roomName: It represents the Room's Name
	 */
	public static void deleteAllOutOfOrders(String roomName){
		String url = PropertiesReader.getRoomManagerApi() 
				     + "services/[serviceId]/rooms/[roomId]/out-of-orders";
		String roomId = RoomApi.getRoomIdByName(roomName);
		String serviceId = EmailServerApi.getEmailServiceId();
		String propertyName = "_id";
		url = url.replace("[roomId]", roomId)
			  .replace("[serviceId]", serviceId);
		String response = ApiManager.getHttpMethod(url);
		Object outOfOrdersAsJson = ApiManager.jsonRequest(response);
		String token = "jwt " + LoginApi.getToken();

		if (outOfOrdersAsJson instanceof JSONArray) {
            JSONArray outOfOrders =(JSONArray)outOfOrdersAsJson;
            for (Object outOfOrder : outOfOrders) {
                JSONObject outOfOrderAsJson =(JSONObject)outOfOrder;
                String outOfOrderId = outOfOrderAsJson.get(propertyName).toString();
                String outOfOrderUrl = url + "/" + outOfOrderId;
                ApiManager.deleteHttpMethod(outOfOrderUrl,token);
            }
        }else if (outOfOrdersAsJson instanceof JSONObject) {
            JSONObject outOfOrderAsJson =(JSONObject)outOfOrdersAsJson;
            String outOfOrderId = outOfOrderAsJson.get(propertyName).toString();
            String outOfOrderUrl = url + "/" + outOfOrderId;
            ApiManager.deleteHttpMethod(outOfOrderUrl,token);
        }
	}
	
	/**
	 * associateResourceToRoom: It associates a Resource to the specified Room
	 * @param roomName: It represents the Room's Name
	 * @param resourceName: It represents the Resource's name
	 */
	public static void associateResourceToRoom(String roomName, String resourceName, 
											   String quantity){
		String requestBody = "{\"associations\":[{\"resourceId\":\"[resourceId]\","
				+ "\"name\":\"[resourceName]\",\"customName\":\"[resourceCustomName]\","
				+ "\"fontIcon\":\"[resourceIcon]\",\"quantity\":\"[quantity]\"}]}";
		String url = PropertiesReader.getRoomManagerApi() 
				     + "rooms/[roomId]/resources?type=bulk";
		String roomId = getRoomIdByName(roomName);
		url = url.replace("[roomId]", roomId);
		
		JSONObject resource = ResourceApi.getResourceByName(resourceName);
		String resourceId = resource.get("_id").toString();
		String nameResource = resource.get("name").toString();
		String resourceCustomName = resource.get("customName").toString();
		String resourceIcon = resource.get("fontIcon").toString();
		String token = "jwt " + LoginApi.getToken();

		requestBody = requestBody.replace("[resourceId]", resourceId)
					  .replace("[resourceName]", nameResource)
					  .replace("[resourceCustomName]", resourceCustomName)
					  .replace("[resourceIcon]", resourceIcon)
					  .replace("[quantity]", quantity);
		
		ApiManager.postHttpMethod(url, requestBody,token);
	}
	
	/**
	 * dissociateAllResourceFromRoom: It dissociates all Resources from the
	 * specified Room 
	 * @param roomName: It represents the Room's Name
	 */
	public static void dissociateAllResourceFromRoom(String roomName){
		String requestBody = "{\"associations\":[]}";
		String url = PropertiesReader.getRoomManagerApi() 
				     + "rooms/[roomId]/resources?type=bulk";
		String roomId = getRoomIdByName(roomName);
		url = url.replace("[roomId]", roomId);
		String token = "jwt " + LoginApi.getToken();

		ApiManager.putHttpMethod(url, requestBody,token);
	}
	
}
