package garret.opossum;

import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

public class TagLogic {
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getTagMap(){
		Firebase ref = new Firebase(OpossumApplication.FIREBASE_HOST).child("tags");
		DataSnapshot snap = FirebaseHelper.readData(ref);
		
		if (snap == null || snap.getValue() == null){
			return null;
		}
		
		return snap.getValue(Map.class);
	}
}
