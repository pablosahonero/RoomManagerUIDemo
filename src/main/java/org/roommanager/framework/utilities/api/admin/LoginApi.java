package org.roommanager.framework.utilities.api.admin;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jimmy Vargas on 9/9/2015.
 */
public class LoginApi {

    public static String getToken(){
        String url= PropertiesReader.getRoomManagerApi()+"/login";
        String body = "{\"username\":\"admin\",\"password\":\"control123!@#\",\"authentication\":\"local\"}";
        String token =null;
        //json = EntityUtils.toString(result.getEntity(), codification);

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader("Content-Type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            LogManager.info("The Status of the POST Request to Room Manager API is: " + response.getStatusLine());
            //EntityUtils.consume(response.getEntity());
            InputStream is= response.getEntity().getContent();

            JSONObject json = (JSONObject)ApiManager.jsonRequest(IOUtils.toString(is, "UTF-8"));
            token = json.get("token").toString();

            LogManager.info("The token of the POST Request to Room Manager API is: " + token);
        }
        catch (Exception ex) {

            LogManager.info(ex.getMessage());
        }
        return token;

    }
}
