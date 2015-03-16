package garret.opossum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpossumEvent {

	private String name;
	private String tag;
	private String owner;
	private List<String> members;
	
	public OpossumEvent(String name, String tag, String owner, List<String> members){
		this.setName(name);
		this.setTag(tag);
		this.setOwner(owner);
		this.setMembers(members);
	}
	
	@SuppressWarnings("unchecked")
	public OpossumEvent(Map<String,Object> eventMap){
		this.setName((String) eventMap.get("name"));
		this.setOwner((String) eventMap.get("owner"));
		this.setTag((String) eventMap.get("tag"));
		this.setMembers((List<String>) eventMap.get("members"));
	}

	public static OpossumEvent getNewEvent(String eventName,String owner){
		String cap = eventName;
		cap = Character.toUpperCase(eventName.charAt(0)) + eventName.substring(1, cap.length());
		String tag = "join"+cap;
		return new OpossumEvent(eventName, tag, owner, new ArrayList<String>());
	}
	
	public Map<String,Object> toHash(){
		Map<String,Object> hash = new HashMap<String,Object>();
		hash.put("name", this.getName());
		hash.put("tag",this.getTag());
		hash.put("owner",this.getOwner());
		hash.put("members",this.getMembers());
		
		return hash;
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
	public String getTag() {
		return tag;
	}

	@JsonProperty
	public void setTag(String tag) {
		this.tag = tag;
	}

	@JsonProperty
	public String getOwner() {
		return owner;
	}

	@JsonProperty
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@JsonProperty
	public List<String> getMembers() {
		return members;
	}

	@JsonProperty
	public void setMembers(List<String> members) {
		this.members = members;
	}
}
