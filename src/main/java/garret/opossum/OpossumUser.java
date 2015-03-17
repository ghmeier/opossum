package garret.opossum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import twitter4j.User;

public class OpossumUser {
	
	private List<String> eventIds;
	private String screenName;
	private String name;
	private Long id;
	private String picture;
	private Long followers;
	
	public OpossumUser(String name,String screenName, long id, String picture, long followers, List<String> eventIds){
		this.setEventIds(eventIds);
		this.setName(name);
		this.setScreenName(screenName);
		this.setId(id);
		this.setPicture(picture);
		this.setFollowers(followers);
	}
	
	public OpossumUser(User user){
		this.setEventIds(new ArrayList<String>());
		this.setName(user.getName());
		this.setScreenName(user.getScreenName());
		this.setId(user.getId());
		this.setPicture(user.getProfileImageURL());
		this.setFollowers(user.getFollowersCount());
	}

	@SuppressWarnings("unchecked")
	public OpossumUser(Map<String,Object> user) {
		Map<String,String> rawIds = (Map<String,String>)user.get("event_ids");
		List<String> ids = new ArrayList<String>();
		if (rawIds != null && rawIds.keySet() != null){
			for (String s:rawIds.keySet()){
				ids.add(s);
			}
		}
		this.setEventIds(ids);
		this.setName((String) user.get("name"));
		this.setScreenName((String) user.get("screenName"));
		this.setId((Long)user.get("id"));
		this.setPicture((String) user.get("picture"));
		this.setFollowers((Long) user.get("followers"));		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> toHashMap(){
		Map<String,Object> user = new HashMap<String,Object>();
		user.put("name", this.getName());
		user.put("screenName",this.getScreenName());
		user.put("id",this.getId());
		user.put("picture",this.getPicture());
		user.put("followers", this.getFollowers());
		user.put("eventIds",new HashMap<String,String>());
		for (int i=0;i<this.getEventIds().size();i++){
			((Map<String,String>)user.get("eventIds")).put(String.valueOf(i), this.getEventIds().get(i));
		}
		return user;
	}

	@JsonProperty
	public List<String> getEventIds() {
		return eventIds;
	}

	@JsonProperty
	public void setEventIds(List<String> eventIds) {
		this.eventIds = eventIds;
	}

	@JsonProperty
	public String getScreenName() {
		return screenName;
	}

	@JsonProperty
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty
	public String getPicture() {
		return picture;
	}

	@JsonProperty
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@JsonProperty
	public Long getFollowers() {
		return followers;
	}

	@JsonProperty
	public void setFollowers(long followers) {
		this.followers = followers;
	}
}
