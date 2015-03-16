package garret.opossum;

import java.util.ArrayList;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

public class SearchHelper {
	
	public static List<OpossumStatus> search(String term){
	    Query query = new Query("#"+term);
	    QueryResult result = null;
	    List<OpossumStatus> statuses = new ArrayList<OpossumStatus>();
	    
		try {
			result = OpossumApplication.getTwitter().search(query);	
			statuses = OpossumStatus.getOStatusFromStatus(result.getTweets());
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return statuses;
	}

}
