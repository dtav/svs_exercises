package com.librarySpring.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Membership {
	@Id
	@GeneratedValue
	private long id;

	private Timestamp startdate;

	private Timestamp enddate;

	private String type;

	@OneToOne(cascade=CascadeType.ALL)
	private Member member;
	
public Membership() {
	// TODO Auto-generated constructor stub
}

	public Membership(Timestamp startdate, Timestamp enddate, String type, Member member) {
		this.startdate = startdate;
		this.enddate = enddate;
		this.type = type;
		this.member = member;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String toString() {
		String ret = "MEMBERSHIP: " + id + "   " + startdate + " <-> " + enddate + "   [" + type + "]   "
				+ member.getName();
		return ret;
	}

}
