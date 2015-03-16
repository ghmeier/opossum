package garret.opossum;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

public class EventLogic {

	public static OpossumEvent getEvent(String name,String eventName){
		
		Firebase ref = new Firebase(OpossumApplication.FIREBASE_HOST).child("events").child(eventName);
		DataSnapshot snap = FirebaseHelper.readData(ref);
		
		if (snap.getValue() == null){
			return null;
		}
		
		OpossumEvent oEvent = new OpossumEvent((Map<String,Object>) snap.getValue());
		
		return oEvent;
	}
	
	public static OpossumEvent addEvent(String name, String eventName){
		
		OpossumEvent oEvent = OpossumEvent.getNewEvent(eventName,name);
		Firebase userRef = new Firebase(OpossumApplication.FIREBASE_HOST).child("users").child(name).child("event_ids");
		Firebase eventRef = new Firebase(OpossumApplication.FIREBASE_HOST).child("events").child(eventName);
		
		Map<String,String> data = new HashMap<String,String>();
		data.put(eventName, name);
		FirebaseHelper.writeData(userRef, data);
		FirebaseHelper.writeData(eventRef, oEvent.toHash());
		
		return oEvent;
	}
}
