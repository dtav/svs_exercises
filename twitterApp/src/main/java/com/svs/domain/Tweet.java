package com.svs.domain;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tweet {

	private long id;

	private String content;

	private Member member;

	private Timestamp timestamp;

	public Tweet(String content, Member member) {
		this.content = content;
		this.member = member;
		this.timestamp = new Timestamp(System.currentTimeMillis());

	}

	public Tweet() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public static String getHeader() {
		String ret = "id \t timestamp \t\t member_id \t content \t\t \n";
		return ret;
	}

	public String toString() {
		String ret = " " + this.getId() + "     " + this.getTimestamp() + "     "
				+ this.getMember().getId() + "     " + this.getContent();
		return ret;
	}
	
	

}
