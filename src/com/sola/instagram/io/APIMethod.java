package com.sola.instagram.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;

public abstract class APIMethod {
	String methodUri;
	String type;
	String accessToken;
	static String proxyAddress;
	static int proxyPort;
	DefaultHttpClient client;

	abstract protected InputStream performRequest() throws Exception;
	
	public APIMethod() {
		client = new DefaultHttpClient();
		if(APIMethod.proxyAddress != null) {
			System.out.println("using proxy " + APIMethod.proxyAddress + ":" + APIMethod.proxyPort);
			HttpHost proxy = new HttpHost(APIMethod.proxyAddress, APIMethod.proxyPort, "http");
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
	}
	
	public static void setProxy(String proxyAddress, int proxyPort) {
		APIMethod.proxyAddress = proxyAddress;
		APIMethod.proxyPort = proxyPort;
	}	

	public static void removeProxy() {
		APIMethod.proxyAddress = null;
	}	
	
	public RequestResponse call() throws Exception {
		String line = "", chunk;
		BufferedReader rd = new BufferedReader(new InputStreamReader(performRequest()));
		while ((chunk = rd.readLine()) != null) {
			line += chunk;
		}
		return new RequestResponse(line);
	}
	
	public Boolean hasProxy() {
		return proxyAddress != null;
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