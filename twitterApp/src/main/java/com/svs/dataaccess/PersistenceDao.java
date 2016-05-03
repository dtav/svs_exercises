package com.svs.dataaccess;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.svs.domain.Member;
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

	public Tweet getTweetById(long id);

	public void editTweet(Tweet t);

	public Member getMemberByUsername(String username);

	public List<Member> getMemberList();

	public Timestamp hasBeenPostedOn(Tweet t);

	public void removeMember(Member m);

	public void saveMember(Member m);

	public void removeAllTweetsByMember(Member m);
	
	
}
