package org.geekulcha.Util;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SearchTweets {

	
	public List<Status> getListTweets()
	{
		ConfigurationBuilder cb=new ConfigurationBuilder();
		cb.setDebugEnabled(true);
        cb.setUseSSL(true);
		cb.setOAuthConsumerKey("PGi5YzUxfUdoxFmT4WPRO1MtH");
		cb.setOAuthConsumerSecret("9aeSRCJtOvQju9mGZldr9Gtn81fw2AIL5Ghqfds0xF7Ca7fI0E");
		cb.setOAuthAccessToken("1426819668-qdr6GWDeFYlowAqPGz4q5wxmUrHgpLNfllCEvzo");
		cb.setOAuthAccessTokenSecret("K2dWGTpDu623glrIUIbtinPG5HJTAGYQ08TfvMRaM");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter=tf.getInstance();
		List<Status> tweets = null;
		Query query=new Query("Geekulcha");
		query.setCount(100);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result!=null)
		{
			tweets=result.getTweets();
		}

		
		return tweets;
	}
}
