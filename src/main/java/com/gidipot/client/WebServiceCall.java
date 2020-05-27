package com.gidipot.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class WebServiceCall {
	
	static HttpGet httpget = null;
	static HttpPost httppost = null;
	static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	public WebServiceCall() {
	}

	public HttpResponse getMethod(String path) throws URISyntaxException, IOException, Exception {
		String url = path;		
		//URI uri = new URI(API_URL + path);
		
		httpget = new HttpGet(url);
		
		return returnContent(httpget);
	}
	
	public HttpResponse postMethod(String path, String body, HashMap<String, String> extraHeaders) throws Exception {

		String url = path;
		
		httppost = new HttpPost(url);
		
		httppost = (HttpPost) computeHeaders(url, httppost, extraHeaders);
		
		//httppost.setEntity(new UrlEncodedFormEntity(body));
		httppost.setEntity(new StringEntity(body));
		
		return returnContent(httppost);
	}
	
	public static HttpResponse returnContent(HttpUriRequest request) throws ClientProtocolException, IOException {	
		//static CloseableHttpResponse response = null;
		
		CloseableHttpResponse response = httpclient.execute(request);
		
		StringBuffer result = new StringBuffer();
		int statusCode = 0;
		try {
			
			statusCode = response.getStatusLine().getStatusCode();
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			response.close();
		}
		catch (Exception e) {
			System.out.println("Request Not Found");
			//e.printStackTrace();
		}
		
		HttpResponse httpResponse = new HttpResponse(statusCode, result.toString());		
		return httpResponse;
    }

	@SuppressWarnings({ "rawtypes", "unchecked", "unused"})
	private HttpUriRequest computeHeaders(String url, HttpUriRequest request, HashMap<String, String> extraHeaders) throws Exception {
				
		if ((extraHeaders != null) && (extraHeaders.size() > 0))
	    {
	      Iterator<?> it = extraHeaders.entrySet().iterator();
	      while (it.hasNext())
	      {
	        Map.Entry<String, String> pair = (Map.Entry)it.next();
	        request.setHeader(pair.getKey(), pair.getValue());
	      }
	    }
		
		return request;
	}

	
}
