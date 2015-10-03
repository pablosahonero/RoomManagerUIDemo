package org.roommanager.framework.utilities.api.admin;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class EmailServerApi {
	
	private static final String  EMAILSERVER_BODY = "{\"username\": \"[username]\",\"password\": \"[password]\", \"hostname\": \"[hostname]\"}";
	private static String servicesFilter = "?type=exchange";

	/*modified by Jimmy*/
	public static String getEmailServiceId(){
		String url = PropertiesReader.getRoomManagerApi() + "services";
		String propertyId = "_id";
		//String serviceResponse = ApiManager.getHttpMethod(url);
		String token = "jwt " + LoginApi.getToken();
		String serviceResponse = ApiManager.getHttpMethod(url,token);
		JSONArray services = (JSONArray)ApiManager.jsonRequest(serviceResponse);
		try{
			JSONObject emailService =(JSONObject)services.get(0);
			return emailService.get(propertyId).toString();
		}
		catch (Exception e){
			return null;
		}
	}
	
	public static void removeEmailServer(String name){
		deleteEmailServerByName(name);
	}
	
	public static void createEmailServer(String username, String password, String hostname){
		
		String services= EMAILSERVER_BODY;
		String url = PropertiesReader.getRoomManagerApi() + "services" + servicesFilter;
		String token = "jwt " + LoginApi.getToken();

		services= services.replace("[username]", username)
				.replace("[password]", password)
				.replace("[hostname]", hostname);
		
		ApiManager.postHttpMethod(url, services,token);
	}
	
	private static void deleteEmailServerByName(String name) {
		String url = PropertiesReader.getRoomManagerApi() + "/services";
		String token = "jwt " + LoginApi.getToken();

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
			request.addHeader("Authorization",token);
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);
                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        if(obj.get("name").toString().equals(name)){
                        	System.out.print("name:"+name);
                        	url = url + "/" +obj.get("_id").toString();
                        	ApiManager.deleteHttpMethod(url,token);
                        }
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    if(obj.get("name").toString().equals(name)){
                    	ApiManager.deleteHttpMethod(url,token);
                    }
                }

            } catch (Exception e) {
            	LogManager.error(e.getMessage());
            }

        } catch (Exception e) {
        	LogManager.error(e.getMessage());
        }
    }
}
