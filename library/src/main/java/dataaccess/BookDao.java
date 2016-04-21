package dataaccess;

import domain.Book;

public interface BookDao {

	public void registerBook(Book b);

	public void listBooks();
	
	public void updateRegistration();
	
	public void unregisterBook(Book b);
	
	
}
