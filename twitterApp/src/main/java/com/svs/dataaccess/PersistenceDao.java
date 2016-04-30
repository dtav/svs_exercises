package com.svs.dataaccess;

import java.util.List;

import com.svs.domain.Tweet;

public interface PersistenceDao {

	List<Tweet> getTweetList();

	void saveTweet(Tweet t);

	List<Tweet> getTweetListByUserName(String username);

	String getUsernameById(long id);
	
	
}
