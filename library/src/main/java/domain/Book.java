package domain;

import javax.persistence.*;

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

}
