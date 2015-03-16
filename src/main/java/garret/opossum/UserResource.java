package garret.opossum;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import twitter4j.TwitterException;
import twitter4j.User;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@GET
	@Path("{name}")
	public OpossumResponse getUser(@PathParam("name")String name){
		
		OpossumUser user = UserLogic.getUser(name);
		
		if (user == null){
			return OpossumResponse.getError("User does not exist.");
		}
		
		return OpossumResponse.getOk(user);
	}
	
	@POST
	@Path("{name}")
	public OpossumResponse addUser(@PathParam("name")String name){
		
		OpossumUser oUser = UserLogic.getUser(name);
		
		if (oUser != null){
			return OpossumResponse.getOk(oUser);
		}
		
		oUser = UserLogic.addUser(name);
		
		if (oUser != null){	
			return OpossumResponse.getOk(oUser);
		}
		
		return OpossumResponse.getError("Couldn't add "+name+" to Firebase.");
		
	}
	
	@GET
	@Path("{name}/events")
	public OpossumResponse getEvents(@PathParam("name")String name){
		Firebase ref = new Firebase(OpossumApplication.FIREBASE_HOST).child("users").child(name).child("eventIds");
		DataSnapshot snap = FirebaseHelper.readData(ref);
		
		if (snap.getValue() == null){
			return OpossumResponse.getOk("No events.");
		}
		
		return OpossumResponse.getOk(snap.getValue());
	}
	
	@GET
	@Path("{name}/events/{e_name}")
	public OpossumResponse getEvent(@PathParam("name")String name,@PathParam("e_name")String eventName){
		
		OpossumUser user = UserLogic.getUser(name);
		
		if (user == null){
			return OpossumResponse.getError("User @"+name+" does not exist.");
		}

		OpossumEvent event = EventLogic.getEvent(name, eventName);
		
		if (event == null){
			return OpossumResponse.getError(name +" does not have the event +"+eventName+".");
		}
		
		return OpossumResponse.getOk(event);
	}
	
	@POST
	@Path("{name}/events/{e_name}")
	public OpossumResponse addEvent(@PathParam("name")String name,@PathParam("e_name")String eventName){

		OpossumEvent event = EventLogic.getEvent(name, eventName);
		
		if (event != null){
			return OpossumResponse.getError("Event with name "+eventName+" already exists.");
		}
		
		event = EventLogic.addEvent(name, eventName);
		
		return OpossumResponse.getOk(event);
	}
}
