package com.librarySpring.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "magazine")
@DiscriminatorValue("magazine")
@PrimaryKeyJoinColumn(name = "id")
public class Magazine extends Publication {
	
	public Magazine() {
		// TODO Auto-generated constructor stub
	}

	@Column(name = "issn")
	private String issn;

	public Magazine(String issn, String title){
		super(title);
		this.issn = issn;		
	}
	
	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}
	
	@Override
	public String toString(){
		String ret = "MAGAZINE:     " + this.getId() + "     " + this.getTitle() + "     " + this.getIssn();
		return ret;
		
	}

}
