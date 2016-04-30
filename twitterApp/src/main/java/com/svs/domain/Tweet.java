package com.svs.domain;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweet")
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tweet_id")
	private Long tweet_id;

	@Column(name = "content")
	private String content;

	// @ManyToOne(cascade=CascadeType.ALL)
	// @JoinTable(name="User",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="tweet_id"))

	@Column(name = "username")
	private String username;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	public Tweet() {
		// TODO Auto-generated constructor stub
	}

	public Tweet(String content) {
		this.setContent(content);
	}

	public Tweet(String content, String username) {
		this.content = content;
		this.username = username;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTweet_id() {
		return tweet_id;
	}

	public void setTweet_id(Long tweet_id) {
		this.tweet_id = tweet_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return tweet_id;
	}

	public void setId(Long tweet_id) {
		this.tweet_id = tweet_id;
	}
	
	public static String getHeader(){
		Field[] fields = Tweet.class.getDeclaredFields();
		String header = "";
		for (Field f : fields){
			header = header + f.getName();
			header = header + "\t";
		}
		header = header + "\n";
		return header;
	}
	
	public String toString(){
		String ret = this.getId() + "\t" + this.getContent() + "\t" + this.getUsername() + "\t" + this.getTimestamp() + "\n";
		return ret;
		
	}

}
