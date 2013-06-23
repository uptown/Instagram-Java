package com.sola.instagram.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.sola.instagram.exception.InstagramException;

public class Tag extends InstagramModel {

	int mediaCount;
	String name;

	public Tag(JSONObject obj, String accessToken) throws JSONException {
		super(obj, accessToken);
		setName(obj.getString("name"));
		setMediaCount(obj.getInt("media_count"));
	}

	protected void setMediaCount(int mediaCount) {
		this.mediaCount = mediaCount;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getMediaCount() {
		return mediaCount;
	}

	public String getName() {
		return name;
	}

}
