package org.roommanager.framework.utilities.api.tablet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class MeetingApi {
	
	private static final String  MEETING_BODY = "{\"organizer\": \"[organizer]\",\"title\": \"[subject]\", \"start\": \"[startTime]\",\"end\": \"[endTime]\","
			+ " \"location\": \"[roomName]\",\"roomEmail\": \"[roomEmail]\", \"resources\": [\"[roomEmail]\"], \"attendees\": [[attendees]]}";

	public static void createMeeting(String organizer, String subject, String startTime, 
			String endTime, String roomName, String attendees){
			String meeting= MEETING_BODY;
			String url = PropertiesReader.getRoomManagerApi() + "services/[serviceId]/rooms/[roomId]/meetings";
			String serviceId = EmailServerApi.getEmailServiceId();
			String roomId = RoomApi.getRoomIdByName(roomName);
		
			url = url.replace("[serviceId]", serviceId).replace("[roomId]", roomId);
			
			String roomEmail = RoomApi.getRoomEmailByName(roomName);
			meeting= meeting.replace("[organizer]", organizer)
				.replace("[subject]", subject)
				.replace("[startTime]", startTime)
				.replace("[endTime]", endTime)
				.replace("[roomName]", roomName)
				.replace("[roomEmail]", roomEmail)
				.replace("[attendees]", attendees);
			ApiManager.postHttpMethod(url, meeting);
		}
		
		public static void deleteMeetingBySubjectName(String roomName, String meetingSubject){
			String url = PropertiesReader.getRoomManagerApi() + "services/[serviceId]/rooms/[roomId]/meetings";
			String serviceId = EmailServerApi.getEmailServiceId();
			String roomId = RoomApi.getRoomIdByName(roomName);
			
			url = url.replace("[serviceId]", serviceId).replace("[roomId]", roomId);
			String propertyName = "title";
			
			String meetingId = ApiManager.getObjectPropertyByGivenPropertyValue("_id", propertyName, meetingSubject, url);
			url = url + "/" + meetingId;
			ApiManager.deleteHttpMethod(url);
		}
		
		public static void deleteAllRoomMeetings(String roomName){
			String url = PropertiesReader.getRoomManagerApi() 
					     + "services/[serviceId]/rooms/[roomId]/meetings";
			String serviceId = EmailServerApi.getEmailServiceId();
			String roomId = RoomApi.getRoomIdByName(roomName);
			String propertyName = "_id";
			url = url.replace("[serviceId]", serviceId)
				  .replace("[roomId]", roomId);
			String response = ApiManager.getHttpMethod(url);
			
			Object meetingsAsJson = ApiManager.jsonRequest(response);
			if (meetingsAsJson instanceof JSONArray) {
	            JSONArray meetings =(JSONArray)meetingsAsJson;
	            for (Object meeting : meetings) {
	                JSONObject meetingAsJson =(JSONObject)meeting;
	                String meetingId = meetingAsJson.get(propertyName).toString();
	                String meetingUrl = url + "/" + meetingId;
	                ApiManager.deleteHttpMethod(meetingUrl);
	            }
	        }else if (meetingsAsJson instanceof JSONObject) {
	            JSONObject meetingAsJson =(JSONObject)meetingsAsJson;
	            String meetingId = meetingAsJson.get(propertyName).toString();
                String meetingUrl = url + "/" + meetingId;
                ApiManager.deleteHttpMethod(meetingUrl);
	        }
		}
}
