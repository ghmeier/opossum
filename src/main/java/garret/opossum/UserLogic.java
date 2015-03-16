package garret.opossum;

import java.util.HashMap;
import java.util.Map;

import twitter4j.TwitterException;
import twitter4j.User;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

public class UserLogic {
	
	@SuppressWarnings("unchecked")
	public static OpossumUser getUser(String name){
		Map<String,Object> user = null;
		
		Firebase ref = new Firebase(OpossumApplication.FIREBASE_HOST).child("users").child(name);
		DataSnapshot snap = FirebaseHelper.readData(ref);
		
		if (snap.getValue() == null){
			return null;
		}
		
		user = (Map<String,Object>)snap.getValue();
		System.out.println(user);
		
		return new OpossumUser(user);
	}
	
	public static OpossumUser addUser(String name){
		User user = null;
		OpossumUser oUser = null;
		
		try {		
			user = OpossumApplication.getTwitter().showUser(name);
		} catch (TwitterException e) {
			return null;
		}
		
		if (user !=null){
			Firebase ref = new Firebase(OpossumApplication.FIREBASE_HOST).child("users");
			
			Map<String,Object> data = new HashMap<String,Object>();
			oUser = new OpossumUser(user);
			data.put(user.getScreenName(),oUser.toHashMap());
			FirebaseHelper.writeData(ref, data);
		}
		
		return oUser;
	}
}
