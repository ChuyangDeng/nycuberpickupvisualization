package tweets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class FetchTweets {
	
	public static void main(String[] args) throws Exception {
		
		/* Setting up Twitter API key and secret */
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true)
	      .setOAuthConsumerKey("9fdXE6dMx6El3ej1woSqGMEjW")
	      .setOAuthConsumerSecret("IsXQXh7oZ6lUz5oLXzXUK2puP1HLL8oJbOUpSkMV6DWNJ9CFT0")
	      .setOAuthAccessToken("807983880577880064-hoVeUhaB1g2eITV5p0dFV4VBMO7UmnZ")
	      .setOAuthAccessTokenSecret("0XkutH8DkW1qkqrH91ctpZaz8pVQuo5e3mLeX9plAD05P");
	    
	    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
	    
	    /* This Query object will search tweets with hashtag #uber */
	    Query query = new Query("#uber");
	    
	    /* Fetch 1000 tweets in total */
	    int numberOfTweets = 1000;
	    
	    /* Store all fetched tweets in an ArrayList<Status> */
	    /* Status is a class defined in twitter4j */
	    ArrayList<Status> tweets = new ArrayList<Status>();
	    
	    /* For every 10 tweets gathered, print the number of tweets fetched so far */
	    while (tweets.size () < numberOfTweets) {
	        if (numberOfTweets - tweets.size() > 10) {
	            query.setCount(10);
	        }
	        else {
	        	query.setCount(numberOfTweets - tweets.size());
	        }
	        try {
	        	
	        	/* Use a Twitter object to search tweets hashtag #uber */
	        	/* And add all results into the ArrayList<Status> */
	        	QueryResult result = twitter.search(query);
	        	tweets.addAll(result.getTweets());
	        	System.out.println("Gathered " + tweets.size() + " tweets"+"\n");
	        } catch (TwitterException te) {
	        	System.out.println("Couldn't connect to Twitter API");
	        }; 
	    }
	    
	    /* Write the fetched tweets into tweet.json file */
	    try {
	    	PrintWriter write = new PrintWriter("tweet.json", "UTF-8");
	    	for (int i = 0; i < tweets.size(); i++) {
		    	Status t = (Status) tweets.get(i);
		
		    	String user = t.getUser().getScreenName();
		    	String msg = t.getText();
		    	write.println("{ USER: " + user + " TWEETS: " + '"' + msg + '"' + "}\n");
		//      System.out. println(i + " USER: " + user + " wrote: " + msg + "\n");
		     }
	    } catch (IOException e) {
	    	System.out.println("Failed to write to text file...");
	    }
     }
 }