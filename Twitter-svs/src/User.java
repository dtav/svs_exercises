//dtav
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

public class User {

	public static void main(String[] args) throws InterruptedException, IOException {
		Server twServer = new Server("tweetDB.txt");
		
		twServer.listMessages();
		System.out.println("*********");
		
		twServer.addNewTweetString("Tweet 4");
		Thread.sleep(1000);
		twServer.addNewTweetString("Tweet 5");
		
		Date d1 = new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong(0, 10000000L)));
		Tweet t1 = new Tweet("Random gen", d1); 
		twServer.addNewTweet(t1);
		
		Date d2 = new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong(0, 10000000L)));
		Tweet t2 = new Tweet("Random gen", d2); 
		twServer.addNewTweet(t2);
		
		Date d3 = new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong(0, 10000000L)));
		Tweet t3 = new Tweet("Random gen", d3);
		twServer.addNewTweet(t3);
		
		Thread.sleep(1500);
		twServer.addNewTweetString("Tweet 6");
		
		twServer.listMessages();
		
		
		twServer.addNewTweetString("Tweet AFTER");
		twServer.listMessages();
		
		twServer.closeApplication();
		
		twServer.listMessages();
		
	}
}
