package com.svs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.dataaccess.PersistenceDao;
import com.svs.domain.Tweet;
import com.svs.domain.Member;

@Component
public class TwitterService {

	private PersistenceDao persistenceDao;
	
	@Autowired
	public TwitterService(PersistenceDao persistenceDao) {
		this.persistenceDao = persistenceDao;
	}
	
	public List<Tweet> getTweetList(){
		return this.persistenceDao.getTweetList();
	}
	
	public void saveTweet(Tweet t){
		this.persistenceDao.saveTweet(t);
	}
	
}
