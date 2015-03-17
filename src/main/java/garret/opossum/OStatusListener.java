package garret.opossum;

import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class OStatusListener implements StatusListener{

    public void onStatus(Status status) {
        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        HashtagEntity[] statusTags = status.getHashtagEntities();
        for (int i=0;i<statusTags.length;i++){
        	String text = statusTags[i].getText();
        	if (OpossumApplication.TAGS.containsKey(text)){
        		boolean success = EventLogic.addMember(status.getUser().getScreenName(), OpossumApplication.TAGS.get(text), status.getId());
        		if (success){
        			System.out.println("Added "+status.getUser().getScreenName() + " to "+OpossumApplication.TAGS.get(text));
        			break;
        		}
        	}else if (text.startsWith("join")){
    			System.out.println("Potentially Adding Event");
    			OpossumUser oUser = UserLogic.getUser(status.getUser().getScreenName());
    			if (oUser != null){
    				String eName = text.substring(4);
    				EventLogic.addEvent(oUser.getScreenName(), eName );
    				System.out.println("Added event "+eName+".");
    			}
    		}
        }
    }

    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
    }

    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
    }

    public void onScrubGeo(long userId, long upToStatusId) {
        //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
    }

    public void onStallWarning(StallWarning warning) {
        //System.out.println("Got stall warning:" + warning);
    }

    public void onException(Exception ex) {
        ex.printStackTrace();
    }
};
