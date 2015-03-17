package garret.opossum;

import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.conf.ConfigurationBuilder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class OpossumApplication extends Application<OpossumConfiguration> {

	private static TwitterFactory tf;
	private static Twitter twitter;
	private static String AUTH_ACCESS = "345762730-S1TlUCZCkLvDnqIcWYmL1g7xUPLpLoqYZGt35bFV";
	private static String AUTH_SECRET = "vlhBgSHCqDPErqODY7edtDiusVPEKkPtYzvZh1l3jmmqG";
	public static String FIREBASE_HOST = "https://opossum.firebaseio.com";
	public static Map<String,String> TAGS;
	
    public static void main(String[] args) throws Exception {
        new OpossumApplication().run(args);
    }

	@Override
    public void initialize(Bootstrap<OpossumConfiguration> bootstrap) {

		tf = new TwitterFactory(getConfBuilder().build());
		twitter = tf.getInstance();
		
		TAGS = TagLogic.getTagMap();
		
    }

    @Override
    public void run(OpossumConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(new SearchResource());
        environment.jersey().register(new UserResource());
        
        Authorization auth = AuthorizationFactory.getInstance(getConfBuilder().build());
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance(auth);
        StatusListener listener = new OStatusListener();
        twitterStream.addListener(listener);
        String[] tags = {"#opossum"};
        FilterQuery fq = new FilterQuery(0,null,tags);
        twitterStream.filter(fq);
    }
    
    public static Twitter getTwitter(){
    	return twitter;
    }
    
    private static ConfigurationBuilder getConfBuilder(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("zApYQGdOToJYpDr4gVzoWDYAo")
		  .setOAuthConsumerSecret("RQ49hXQFdBRTuhEXUhGQdSxHUjH54qt6gjAreG5SfpJNWZFvpo")
		  .setOAuthAccessToken(AUTH_ACCESS)
		  .setOAuthAccessTokenSecret(AUTH_SECRET);
		return cb;
    }
}
