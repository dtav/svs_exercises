package domain;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
	
	@Id @GeneratedValue private Long id;
	@Column(name = "isbn") private String isbn;
	@Column(name = "title") private String title;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String isbn, String title){
		this.isbn = isbn;
		this.title = title;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	
	
	

}
