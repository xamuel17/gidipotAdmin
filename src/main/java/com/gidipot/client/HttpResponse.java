package com.gidipot.client;


public class HttpResponse {

	public int statusCode;
	public String responseBody1;
	
	public HttpResponse(int statusCode, String responseBody) {
		super();
		this.statusCode = statusCode;
		this.responseBody1 = responseBody;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseBody1() {
		return responseBody1;
	}

	public void setResponseBody1(String responseBody1) {
		this.responseBody1 = responseBody1;
	}
	
}
