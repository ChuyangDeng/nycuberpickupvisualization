package tweets;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class FetchTwitterTest {

	@Test
	public void FetchTest() {
		FetchTweets ft = new FetchTweets();
		
		assertNotEquals(null, ft);
	}
	
	@Test
	public void ConfigurationTest() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		assertNotEquals(null, cb);
	}
	
	@Test
	public void TwitterTest() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		assertNotEquals(null, twitter);
	}
	
	@Test
	public void QueryTest() {
		Query query = new Query("#uber");
		
		assertNotEquals(null, query);
	}
	
	@Test
	public void ArrayListTest() {
		ArrayList<Status> tweets = new ArrayList<Status>();
		
		assertNotEquals(null, tweets);
	}

}