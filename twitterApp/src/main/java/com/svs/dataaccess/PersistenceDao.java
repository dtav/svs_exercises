package com.svs.dataaccess;

import java.util.Date;
import java.util.List;

import com.svs.domain.Tweet;
import com.svs.domain.User;

public interface PersistenceDao {

	public void saveTweet(String content, String username);
	
	public String retreiveTweet();

	public List<Tweet> getTweetList();

	public List<Tweet> getTweetListDate(Date d);
	
	public List<Tweet> getTweetByUser(User u);

	public Tweet getTweetByID(Long id);
	
	
}
