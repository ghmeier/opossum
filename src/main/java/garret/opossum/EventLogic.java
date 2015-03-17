package garret.opossum;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

public class EventLogic {

	public static OpossumEvent getEvent(String name,String eventName){
		
		Firebase ref = new Firebase(OpossumApplication.FIREBASE_HOST).child("events").child(eventName);
		DataSnapshot snap = FirebaseHelper.readData(ref);
		
		if (snap == null || snap.getValue() == null){
			return null;
		}
		
		@SuppressWarnings("unchecked")
		OpossumEvent oEvent = new OpossumEvent((Map<String,Object>) snap.getValue());
		
		return oEvent;
	}
	
	public static OpossumEvent addEvent(String name, String eventName){
		
		OpossumEvent oEvent = OpossumEvent.getNewEvent(eventName,name);
		Firebase userRef = new Firebase(OpossumApplication.FIREBASE_HOST).child("users").child(name).child("event_ids");
		Firebase eventRef = new Firebase(OpossumApplication.FIREBASE_HOST).child("events").child(eventName);
		Firebase tagRef = new Firebase(OpossumApplication.FIREBASE_HOST).child("tags");
		
		Map<String,String> data = new HashMap<String,String>();
		data.put(eventName, name);
		
		Map<String,String> tagData = new HashMap<String,String>();
		tagData.put(oEvent.getTag(),eventName);
		
		FirebaseHelper.writeData(userRef, data);
		FirebaseHelper.writeData(eventRef, oEvent.toHash());
		FirebaseHelper.writeData(tagRef, tagData);
		
		OpossumApplication.TAGS = TagLogic.getTagMap();
		
		return oEvent;
	}
	
	public static boolean addMember(String name, String eventName,long statusId){
		Firebase eventRef = new Firebase(OpossumApplication.FIREBASE_HOST).child("events").child(eventName).child("members");
		Firebase memberRef = eventRef.child(name);
		
		DataSnapshot snap = FirebaseHelper.readData(memberRef);
		if (snap != null && snap.getValue() != null){
			return false;
		}
		
		Map<String,Long> data = new HashMap<String,Long>();
		data.put(name, statusId);
		FirebaseHelper.writeData(eventRef, data);
		
		return true;
	}
}
