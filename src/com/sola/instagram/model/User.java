
package com.sola.instagram.model;

import com.sola.instagram.io.GetMethod;
import com.sola.instagram.io.UriFactory;
import com.sola.instagram.util.UriConstructor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class User extends InstagramModel {
	protected long id;
	protected String userName;
	protected String fullName;
	protected String profilePictureURI;
	protected String bio;
	protected String website;
	protected int mediaCount = -1;
	protected int followerCount = -1;
	protected int followingCount = -1;

	public User(JSONObject obj, String accessToken) throws JSONException {
		super(obj, accessToken);
		setId(obj.getLong("id"));
		setUserName(obj.getString("username"));
		setFullName(obj.getString("full_name"));
		setProfilePictureURI(obj.getString("profile_picture"));

		setWebsite(obj.optString("website"));
		setBio(obj.optString("bio"));

		if (obj.has("counts")) {
			JSONObject counts = obj.getJSONObject("counts");
			setFollowerCount(counts.getInt("followed_by"));
			setFollowingCount(counts.getInt("follows"));
			setMediaCount(counts.getInt("media"));
		} else {
			setFollowerCount(-1);
			setFollowingCount(-1);
			setMediaCount(-1);
		}
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	protected void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	protected void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProfilePictureURI() {
		return profilePictureURI;
	}

	protected void setProfilePictureURI(String profilePictureURI) {
		this.profilePictureURI = profilePictureURI;
	}

	public String getBio() throws Exception {
		if (bio == null) {
			refreshObject();
		}
		return bio;
	}

	protected void setBio(String bio) throws JSONException {
		this.bio = bio;
	}

	public String getWebsite() throws Exception {
		if (website == null) {
			refreshObject();
		}
		return website;
	}

	protected void setWebsite(String website) {
		this.website = website;
	}

	public int getMediaCount() throws Exception {
		if (this.followingCount == -1)
			refreshObject();
		return mediaCount;
	}

	protected void setMediaCount(int mediaCount) {
		this.mediaCount = mediaCount;
	}

	public int getFollowerCount() throws Exception {
		if (this.followerCount == -1)
			refreshObject();
		return followerCount;
	}

	protected void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public int getFollowingCount() throws Exception {
		if (this.followingCount == -1)
			refreshObject();
		return followingCount;
	}

	protected void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	private void refreshObject() throws Exception {
		UriConstructor uriConstructor = new UriConstructor(getAccessToken());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", getId());
		String uri = uriConstructor.constructUri(
				UriFactory.Users.GET_DATA, map, true);
		JSONObject userObject = (new GetMethod()
				.setMethodURI(uri)
				).call().getJSON();

		if (userObject.has("data")) {
			JSONObject counts = userObject.getJSONObject("data")
					.getJSONObject("counts");
			setFollowerCount(counts.getInt("followed_by"));
			setFollowingCount(counts.getInt("follows"));
			setMediaCount(counts.getInt("media"));
			setWebsite(userObject.optString("website"));
			setBio(userObject.optString("bio"));
		}
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (o.getClass() != this.getClass())
			return false;
		return ((User) o).getId() == getId();
	}
}
