package garret.opossum;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.User;

public class OpossumStatus {
	
	private String tweeter;
	private List<String> hashes;
	
	public OpossumStatus(String tweeter, List<String> hashes){
		this.hashes = hashes;
		this.tweeter = tweeter;
	}
	
	@JsonProperty
	public String getTweeter(){
		return this.tweeter;
	}
	
	@JsonProperty
	public void setTweeter(String tweeter){
		this.tweeter= tweeter;
	}
	
	@JsonProperty
	public List<String> getHashes(){
		return this.hashes;
	}
	
	@JsonProperty
	public void setHashes(List<String> hashes){
		this.hashes = hashes;
	}
	
	public static List<OpossumStatus> getOStatusFromStatus(List<Status> tweets){
		ArrayList<OpossumStatus> statuses = new ArrayList<OpossumStatus>();
		for (Status s:tweets){
			HashtagEntity[] hashes = s.getHashtagEntities();
			List<String> fullHash = new ArrayList<String>();
			for (HashtagEntity e: hashes){
				fullHash.add(e.getText());
			}
			User user = s.getUser();
			statuses.add(new OpossumStatus(user.getName(),fullHash));
		}
		return statuses;
	}
}
