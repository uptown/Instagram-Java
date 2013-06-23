package com.sola.instagram.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;

import com.sola.instagram.exception.InstagramException;

public abstract class APIMethod {
	String methodUri;
	String type;
	String accessToken;

	abstract protected InputStream performRequest() throws Exception;

	public APIMethod() {}

	public RequestResponse call() throws Exception {
		String line = "", chunk;
		BufferedReader rd = new BufferedReader(new InputStreamReader(performRequest()));
		while ((chunk = rd.readLine()) != null) {
			line += chunk;
		}
		return new RequestResponse(line);
	}
	
	public String getType() {
		return type;
	}

	public String getMethodUri() {
		return methodUri;
	}

	public APIMethod setMethodURI(String methodURI) {
		this.methodUri = methodURI;
		return this;
	}
}