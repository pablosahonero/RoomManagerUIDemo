package org.roommanager.framework.utilities.api;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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


public class ApiManager {
	
	private static final String CODIFICATION_UTF_8 = "UTF-8";
	private static final String CONTENT_TYPE_HEADER = "content-type";
	private static final String APPLICATION_CONTENT_TYPE = "application/json";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	
	public static String json;
	
	public static Object jsonRequest(String json){
		Object resultObject = null; 
		try {
             JSONParser parser = new JSONParser();
             resultObject = parser.parse(json);
		 }
		 catch(Exception ex){
			LogManager.info("Error Message");
		 }
		 return resultObject;

	}
	
	public static String getHttpMethod(String url){
	
		String json = null;
		String codification = CODIFICATION_UTF_8;
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(url);
            request.addHeader(contentTypeHeader, contentType);
            HttpResponse result = httpClient.execute(request);
            json = EntityUtils.toString(result.getEntity(), codification);
        } catch (IOException ex) {
        	LogManager.info(ex.getMessage());
        }
		return json;
	}

	/*Author: Jimmy Vargas*/
	public static String getHttpMethod(String url,String token){

		String json = null;
		String codification = CODIFICATION_UTF_8;
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(url);
			request.addHeader(contentTypeHeader, contentType);
			request.addHeader("Authorization",token);

			HttpResponse result = httpClient.execute(request);
			json = EntityUtils.toString(result.getEntity(), codification);
		} catch (IOException ex) {
			LogManager.info(ex.getMessage());
		}
		return json;
	}
	
	public static void deleteHttpMethod(String url){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;
		String authorizationValue = PropertiesReader.getBasicAuthentication();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
			HttpDelete request = new HttpDelete(url);
			request.addHeader(contentTypeHeader, contentType);
			request.addHeader(authorizationHeader, authorizationValue);
            
            CloseableHttpResponse response = httpClient.execute(request);
            try {
            	LogManager.info("The Status of the DELETE Request to Room Manager API is: " + response.getStatusLine());
                EntityUtils.consume(response.getEntity());
            } finally {
                response.close();
            }
        } catch (IOException ex) {
        	LogManager.info(ex.getMessage());
        }
	}

	/**
	 * Method that receives the token
	 * @Author: Jimmy Vargas
	 * */
	public static void deleteHttpMethod(String url,String token){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;


		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
			HttpDelete request = new HttpDelete(url);
			request.addHeader(contentTypeHeader, contentType);
			request.addHeader(authorizationHeader, token);

			CloseableHttpResponse response = httpClient.execute(request);
			try {
				LogManager.info("The Status of the DELETE Request to Room Manager API is: " + response.getStatusLine());
				EntityUtils.consume(response.getEntity());
			} finally {
				response.close();
			}
		} catch (IOException ex) {
			LogManager.info(ex.getMessage());
		}
	}

	public static void postHttpMethod(String url, String body){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;
		String authorizationValue = PropertiesReader.getBasicAuthentication();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader(contentTypeHeader, contentType);
            request.addHeader(authorizationHeader, authorizationValue);
            request.setEntity(params);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
            	LogManager.info("The Status of the POST Request to Room Manager API is: " + response.getStatusLine());
                EntityUtils.consume(response.getEntity());
            } finally {
                response.close();
            }
        } catch (IOException ex) {
        	LogManager.info(ex.getMessage());
        }
	}

	/*
	* Receiving the token as parameter
	* */
	public static void postHttpMethod(String url, String body,String token){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;
		//String authorizationValue = PropertiesReader.getBasicAuthentication();

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(url);
			StringEntity params = new StringEntity(body);
			request.addHeader(contentTypeHeader, contentType);
			request.addHeader(authorizationHeader, token);
			request.setEntity(params);
			CloseableHttpResponse response = httpClient.execute(request);
			try {
				LogManager.info("The Status of the POST Request to Room Manager API is: " + response.getStatusLine());
				EntityUtils.consume(response.getEntity());
			} finally {
				response.close();
			}
		} catch (IOException ex) {
			LogManager.info(ex.getMessage());
		}
	}
	
	public static void putHttpMethod(String url, String body){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;
		String authorizationValue = PropertiesReader.getBasicAuthentication();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPut request = new HttpPut(url);
            StringEntity params = new StringEntity(body);
            request.addHeader(contentTypeHeader, contentType);
            request.addHeader(authorizationHeader, authorizationValue);
            request.setEntity(params);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
            	LogManager.info("The Status of the PUT Request to Room Manager API is: " + response.getStatusLine());
                EntityUtils.consume(response.getEntity());
            } finally {
                response.close();
            }
        } catch (IOException ex) {
        	LogManager.info(ex.getMessage());
        }
	}

	public static void putHttpMethod(String url, String body,String token){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;


		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPut request = new HttpPut(url);
			StringEntity params = new StringEntity(body);
			request.addHeader(contentTypeHeader, contentType);
			request.addHeader(authorizationHeader, token);
			request.setEntity(params);
			CloseableHttpResponse response = httpClient.execute(request);
			try {
				LogManager.info("The Status of the PUT Request to Room Manager API is: " + response.getStatusLine());
				EntityUtils.consume(response.getEntity());
			} finally {
				response.close();
			}
		} catch (IOException ex) {
			LogManager.info(ex.getMessage());
		}
	}
	
	public static String getObjectPropertyByGivenPropertyValue(String propertyToBeSearched, String property, String propertyValue, String url) {
		String responseProperty = null;
		String propertyName = property;
		
		String json = getHttpMethod(url);
		Object resourcesAsJson = jsonRequest(json);
		
		if (resourcesAsJson instanceof JSONArray) {
            JSONArray array=(JSONArray)resourcesAsJson;
            for (Object object : array) {
                JSONObject obj =(JSONObject)object;
                if(obj.get(propertyName).toString().equals(propertyValue)){
                	return obj.get(propertyToBeSearched).toString();
                }
            }
        }else if (resourcesAsJson instanceof JSONObject) {
            JSONObject obj =(JSONObject)resourcesAsJson;
            if(obj.get(propertyName).toString().equals(propertyValue)){
            	return obj.get(propertyToBeSearched).toString();
            }
        }
		return responseProperty;
    }
	
	
}

