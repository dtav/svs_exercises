package com.svs.domain;


public class User {
//	
//	@Id
//	@GeneratedValue
	
	private Long user_id;
	
//	@Column(name = "nick")
	private String nick;
//	
//	@Column(name = "email")
//	@Type(type = "com.svs.domain.EmailAddressUserType")
	private EmailAddress emailAddr;
	
//	@OneToMany(mappedBy="user")
		
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String nick, EmailAddress emailAddr){
		this.setNick(nick);
		this.setEmailAddr(emailAddr);
	}

	public EmailAddress getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(EmailAddress emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	

}
