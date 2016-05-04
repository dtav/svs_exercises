package com.librarySpring.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name = "publication")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@OnDelete(action = OnDeleteAction.CASCADE)
public class Publication {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@ManyToMany
	@JoinTable(name = "loan",
			joinColumns = @JoinColumn(name = "publication_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
	private Set<Member> members;
	
	@OneToMany(mappedBy = "publication")
	private Set<Loan> loans;
	
	public Publication() {
		// TODO Auto-generated constructor stub
	}
	
	public Publication(String title){
		this.title = title;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	

	

}
