package com.librarySpring.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue
	private long id;
	private Timestamp enddate;
	private Timestamp starttade;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Publication publication;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Member member;
	
	public Loan(){
		
	}
	
	public Loan(Timestamp enddate, Timestamp startdate, Publication pub, Member member){
		this.enddate = enddate;
		this.starttade = startdate;
		this.publication = pub;
		this.member = member;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public Timestamp getStarttade() {
		return starttade;
	}

	public void setStarttade(Timestamp starttade) {
		this.starttade = starttade;
	}

	public Publication getPub() {
		return publication;
	}

	public void setPub(Publication pub) {
		this.publication = pub;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	

	
}
