package com.svs.dataaccess;

import java.util.List;

import com.svs.domain.Tweet;

public interface PersistenceDao {

	List<Tweet> getTweetList();

	void saveTweet(Tweet t);

	
	
}
