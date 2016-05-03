package com.svs.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Member {

	private long id;
	
	@Column(name="username",unique=true)
	private String username;
	
	private String email;
	
	private Set<Tweet> tweets;
	
	

	public Member() {
		
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="member",cascade=CascadeType.ALL)
	@JsonBackReference //solved json recursive loop problem
	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

	
	public int showNumTweets(){
		return getTweets().size();
	}
	
	

}
