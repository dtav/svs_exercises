package com.svs.dataaccess;

import java.util.Date;
import java.util.List;

import com.svs.domain.Tweet;
import com.svs.domain.TweetSimple;

public interface PersistenceDao {

	public List<Tweet> getTweetList();

	public void saveTweet(Tweet t);

	public List<Tweet> getTweetListByUserName(String username);

	public String getUsernameById(long id);

	public List<Tweet> getTweetListLaterThan(Date d);

	public List<TweetSimple> listSimpleTweets(List<Tweet> normalTweets);

	public void removeTweet(Tweet t);
	
	
}
