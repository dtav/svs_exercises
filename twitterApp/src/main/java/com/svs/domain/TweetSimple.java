package com.svs.domain;


import java.sql.Timestamp;



public class TweetSimple {

	private long id;

	private String content;

	private long member_id;

	private Timestamp timestamp;

	public TweetSimple(String content, long member_id) {
		this.content = content;
		this.member_id = member_id;
		this.timestamp = new Timestamp(System.currentTimeMillis());

	}

	public TweetSimple() {
		// TODO Auto-generated constructor stub
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public static String getHeader() {
		String ret = "id \t timestamp \t\t member_id \t content \t\t \n";
		return ret;
	}

	public String toString() {
		String ret = " " + this.getId() + "     " + this.getTimestamp() + "     "
				+ this.getMember_id() + "     " + this.getContent();
		return ret;
	}
	
	

}
