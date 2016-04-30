package com.svs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.dataaccess.PersistenceDao;
import com.svs.domain.Tweet;
import com.svs.domain.User;

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
	
	public void saveTweet(String content, String username){
		this.persistenceDao.saveTweet(content, username);
	}
	
	public List<Tweet> getTweetListDate(Date d){
		
		return this.persistenceDao.getTweetListDate(d);
	}
	
	public List<Tweet> getTweetByUser(User u){
		return this.persistenceDao.getTweetByUser(u);
	}

	public Tweet getTweetByID(Long id) {
		return this.persistenceDao.getTweetByID(id);
		
	}
	
}
