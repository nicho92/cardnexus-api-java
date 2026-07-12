package org.api.cardnexus.tools;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConstants;
import org.api.cardnexus.listener.URLCallInfo;
import org.api.cardnexus.listener.URLCallListener;
import org.api.cardnexus.model.PaginateResult;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class RestClient implements Closeable {

    private final CloseableHttpClient httpClient;
    private final Gson gson;
    private final Map<String, String> defaultHeaders;
	protected Logger logger = LogManager.getLogger(this.getClass());
	private URLCallListener listener;
	 
    /**
     * Constructeur sans authentification.
     */
    public RestClient() {
        this(null);
    }

    /**
     * Constructeur avec authentification.
     *
     * @param email  email account
     * @param token Manapool integration token
     */
    public RestClient(String token) {
        this.httpClient = HttpClients.createDefault();
        this.gson = new Gson();
        this.defaultHeaders = new HashMap<>();
        
        defaultHeaders.put("Authorization", "Bearer " +token);
        defaultHeaders.put("Content-Type", "application/json");
        defaultHeaders.put("Accept", "application/json");
    }

    // -------------------- Méthodes principales --------------------
    
    public <T> PaginateResult<T> getPaginated(String url,Map<String,String> headers, Class<T> clazz) throws IOException {
		var type = TypeToken.getParameterized(PaginateResult.class,clazz).getType();
		return get(url, headers, type);
    }
    
    public <T> PaginateResult<T> postPaginated(String url, Object body,Map<String,String> headers, Class<T> clazz) throws IOException {
 		var type = TypeToken.getParameterized(PaginateResult.class,clazz).getType();
 		return post(url, body, headers, type);
     }
    
    public <T> T get(String url, Map<String, String> headers,Type responseType) throws IOException {
        
        var request = new HttpGet(NexusConstants.API_BASE_URL + url);
        applyHeaders(request, headers);
        return executeRequest(request, responseType);
        }
    
    public <T> T get(String url, Map<String, String> headers, Class<T> responseType) throws IOException {
        var request = new HttpGet(NexusConstants.API_BASE_URL+url);
        applyHeaders(request, headers);
        return executeRequest(request, responseType);
    }
    
    public <T> T post(String url, Object body, Map<String, String> headers, Class<T> responseType) throws IOException {
        var request = new HttpPost(NexusConstants.API_BASE_URL+url);
        applyHeaders(request, headers);
        addJsonBody(request, body);
        return executeRequest(request, responseType);
    }
    
    public <T> T post(String url, Object body, Map<String, String> headers,Type responseType) throws IOException {
        var request = new HttpPost(NexusConstants.API_BASE_URL+url);
        applyHeaders(request, headers);
        addJsonBody(request, body);
        return executeRequest(request, responseType);
    }

    

    public <T> T put(String url, Object body, Map<String, String> headers, Class<T> responseType) throws IOException {
        var request = new HttpPut(NexusConstants.API_BASE_URL+url);
        applyHeaders(request, headers);
        addJsonBody(request, body);
        return executeRequest(request, responseType);
    }

    public <T> T delete(String url, Object body, Map<String, String> headers, Class<T> responseType) throws IOException {
        var request = new HttpDeleteWithBody(NexusConstants.API_BASE_URL+url);
        applyHeaders(request, headers);
        if (body != null) {
            addJsonBody(request, body);
        }
        return executeRequest(request, responseType);
    }

    // -------------------- Méthodes internes --------------------

    private void applyHeaders(HttpRequestBase request, Map<String, String> headers) {
        defaultHeaders.forEach(request::addHeader);
        if (headers != null) {
            headers.forEach(request::setHeader);
        }
    }

    private void addJsonBody(HttpEntityEnclosingRequestBase request, Object body) {
        if (body != null) {
            var json = gson.toJson(body);
            logger.debug("body={}", json);
            request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        }
    }

    
    public <T> List<T> toList(JsonArray array, Class<T> clazz)
    {
	var listType = TypeToken.getParameterized​(List.class, clazz).getType();
	return gson.fromJson(array,listType);
    }
    
    public <T> T fromJson(String json, Type responseType)
    {
    	return gson.fromJson(json, responseType);
    }
    
    public <T> T fromJson(String json, Class<T> responseType)
    {
    	return gson.fromJson(json, responseType);
    }
    
    
    public void setCallListener(URLCallListener listener2) {
		this.listener=listener2;
		
	}
    
    @SuppressWarnings("unchecked")
    private <T> T executeRequest(HttpRequestBase request, Type  responseType) throws IOException {
    	
    	var callInfo = new URLCallInfo();
    	
        try (var response = httpClient.execute(request)) {
        	var statusCode = response.getStatusLine().getStatusCode();
            var jsonResponse = response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : null;
       
            logger.info("{} : {},", request,statusCode );
    		callInfo.setEnd(Instant.now());
    		callInfo.setUrl(request.getURI().toASCIIString());
    		callInfo.setRequest(request);
    		callInfo.setResponse(response);
            
            
            if (statusCode >= 200 && statusCode < 300) 
            {
                if (responseType == String.class) {
                    return (T) jsonResponse;
                }
                if (jsonResponse != null && !jsonResponse.isEmpty()) {
                    try {
                    	
                    	  logger.debug("result: {},", jsonResponse );
                    	
                        return fromJson(jsonResponse, responseType);
                    } catch (JsonSyntaxException e) {
                        throw new IOException("Erreur de parsing JSON : " + e.getMessage(), e);
                    }
                }
                return null;
            } else {
                throw new IOException(statusCode + " : " + jsonResponse);
            }
        }
        finally {
        	
        	if(listener!=null)
        		listener.notify(callInfo);
		}
        
        
        
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    private static class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

        public HttpDeleteWithBody(final String uri) {
            setURI(java.net.URI.create(uri));
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

}