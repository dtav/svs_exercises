package com.svs.jdbcTw.service;

import java.util.List;

import com.svs.jdbcTw.dataaccess.PersistanceDao;
import com.svs.jdbcTw.domain.Tweet;

public class TwitterService {

	PersistanceDao pd;

	public TwitterService(PersistanceDao pd) {
		this.pd = pd;
	}
	
	public void saveTweet(Tweet t){
		pd.saveTweet(t);
	}
	
	public void listTweets(){
		pd.listTweets();
	}
	
	public List<Tweet> getTweetList(){
		return pd.getTweetList();
	}

}
