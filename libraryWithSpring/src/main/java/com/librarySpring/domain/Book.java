package com.librarySpring.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DiscriminatorValue("book")
@Table(name = "book")
@PrimaryKeyJoinColumn(name = "id")
public class Book extends Publication {
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	@Column(name = "isbn")
	private String isbn;
	
	
	public Book(String isbn, String title){
		super(title);
		this.isbn = isbn;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString(){
		String ret = "BOOK:     "  + this.getId() + "     " + this.getTitle() + "     " + this.getIsbn();
		return ret;
		
	}

}
