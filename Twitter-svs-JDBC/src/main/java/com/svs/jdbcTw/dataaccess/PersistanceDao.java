package com.svs.jdbcTw.dataaccess;

import java.util.List;

import com.svs.jdbcTw.domain.Tweet;

public interface PersistanceDao {

	void saveTweet(Tweet t);

	void listTweets();

	List<Tweet> getTweetList();

}
