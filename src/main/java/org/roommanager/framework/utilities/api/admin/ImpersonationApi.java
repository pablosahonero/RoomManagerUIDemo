package org.roommanager.framework.utilities.api.admin;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class ImpersonationApi {
	
	/** json: Json of the impersonation*/
    private static String json;
    
    /** IMPERSONATION_BODY: Body of the location*/
    private static final String IMPERSONATION_BODY = "{\"impersonate\": \"[impersonation]\"}";
	
    /**
	 * jsonRequest: It does the request of impersonation
	 * json.
	 * @param json: It represents the Json of impersonation.
	 * @return Object
	 */
	public static Object jsonRequest(String json){
		Object resultObject = null; 
		try {
             JSONParser parser = new JSONParser();
             resultObject = parser.parse(json);
		 }
		 catch(Exception ex){
			 LogManager.error("Error Message"+ex);
		 }
		 return resultObject;
	}
	
	/**
	 * getRequest: It get the request of impersonation
	 * json.
	 * @param urlRequest: It represents the URL of request the impersonation.
	 * @return String Json.
	 */
	public static String getRequest(String urlRequest) {
		String token = "jwt " + LoginApi.getToken();

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(urlRequest);
            request.addHeader("content-type", "application/json");
			request.addHeader("Authorization", token);

            HttpResponse result = httpClient.execute(request);
            json = EntityUtils.toString(result.getEntity(), "UTF-8");
        } 
		catch (IOException ex) {
			LogManager.error("Error Message"+ex);
        }
		return json;
    }
	
	/**
	 * getImpersonation: It get impersonation.
	 * @param name: It represents the Impersonation's Name
	 * @return String id.
	 */
	public static String getImpersonation(String name) {
		String id = null;
		String url = PropertiesReader.getRoomManagerApi() + "services";
		String propertyName = "name";
		String propertyId = "_id";		
		String json = getRequest(url);
		Object ImpersonationJson = jsonRequest(json);
		if (ImpersonationJson instanceof JSONArray) {
            JSONArray array=(JSONArray)ImpersonationJson;
            for (Object object : array) {
                JSONObject obj =(JSONObject)object;
                if(obj.get(propertyName).toString().equals(name)){
                	return obj.get(propertyId).toString();
                }
            }
        }else if (ImpersonationJson instanceof JSONObject) {
            JSONObject obj =(JSONObject)ImpersonationJson;
            if(obj.get(propertyName).toString().equals(name)){
            	return obj.get(propertyId).toString();
            }
        }
		return id;
    }
	
	/**
	 * setImpersonation: It change the status of the impersonation.
	 * @param impersonation: It represents the Impersonation's status.
	 * @param name: It represents the Impersonation's Name.
	 */
	public static void setImpersonation(String impersonation, String name){
		String id = getImpersonation(name);
		String impersonationBody = IMPERSONATION_BODY;
		impersonationBody = impersonationBody.replace("[impersonation]", 
													  impersonation);
		String url = PropertiesReader.getRoomManagerApi() + "services/" + id;
		String token = "jwt " + LoginApi.getToken();

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPut request = new HttpPut(url);
            StringEntity params = new StringEntity(impersonationBody);
            request.addHeader("content-type", "application/json");
			request.addHeader("Authorization", token);
			request.setEntity(params);
            httpClient.execute(request);   
        } 
		catch (Exception ex) {
			LogManager.error("Error Message"+ex);
        }	
	}
}
