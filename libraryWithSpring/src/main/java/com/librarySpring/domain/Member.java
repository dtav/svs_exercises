package com.librarySpring.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Member {
	
	@Id
	@GeneratedValue
	private long id;
	private String email;
	private String name;
	
	@OneToOne(mappedBy="member", cascade=CascadeType.ALL)
	private Membership membership;
	
	@OneToMany(mappedBy="member", cascade=CascadeType.ALL)
	private Set<Loan> loans;
	
	@ManyToMany(mappedBy="members", cascade=CascadeType.ALL)
	private Set<Publication> publications;

	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}
	
	public String toString(){
		String ret = "MEMBER: "+id+"   "+email+"   "+name+"   "+membership.getType();
		return ret;
	}
	
	

}
