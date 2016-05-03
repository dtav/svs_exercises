package com.svs.jdbcTw.domain;

public class Tweet {
	
	public long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String content;
	public String username;
	public long timestamp;
	
	public Tweet() {
		// TODO Auto-generated constructor stub
	}
	
	public Tweet(String content, String username){
		this.content=content;
		this.username=username;
		this.timestamp=System.currentTimeMillis();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
