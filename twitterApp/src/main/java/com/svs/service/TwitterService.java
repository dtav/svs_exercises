package com.svs.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.svs.dataaccess.PersistenceDao;
import com.svs.domain.Member;
import com.svs.domain.Tweet;
import com.svs.domain.TweetSimple;

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
	
	public void removeTweet(Tweet t){
		this.persistenceDao.removeTweet(t);
	}
	
	public void editTweet(Tweet t){
		this.persistenceDao.editTweet(t);
	}
	
	public List<Tweet> getTweetListByUserName(String username){
		return this.persistenceDao.getTweetListByUserName(username);
	}
	
	public String getTweetUsername(){
		return null;
	}
	
	public String getUsernameById(long id){
		return this.persistenceDao.getUsernameById(id);
	}
	

	
	public List<Tweet> getTweetListLaterThan(Date d){
		return this.persistenceDao.getTweetListLaterThan(d);
	}
	

	public List<TweetSimple> listSimpleTweets(List<Tweet> normalTweets){
		return this.persistenceDao.listSimpleTweets(normalTweets);
	}

	public Tweet getTweetById(long id){
		return this.persistenceDao.getTweetById(id);
	}
	
	public Member getMemberByUsername(String username){
		return this.persistenceDao.getMemberByUsername(username);
	}
	
	public List<Member> getMemberList(){
		return this.persistenceDao.getMemberList();
	}
	
	public Timestamp hasBeenPostedOn(Tweet t){
		return this.persistenceDao.hasBeenPostedOn(t);
	}
	
	public void removeMember(Member m){
		this.persistenceDao.removeMember(m);
	}
	
	public void saveMember(Member m){
		this.persistenceDao.saveMember(m);
	}
	
	public void removeAllTweetsByMember(Member m){
		this.persistenceDao.removeAllTweetsByMember(m);
	}

	
}
