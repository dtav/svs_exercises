package service;

import dataaccess.BookDao;
import domain.Book;

public class LibraryService {

	private BookDao bookDao;

	public LibraryService(BookDao bookDao) {
		this.bookDao = bookDao;
	}


	public void registerBook(Book b){
		this.bookDao.registerBook(b);
	}

	public void listBooks(){
		this.bookDao.listBooks();
	}
	
	public void updateRegistration(){
		this.bookDao.updateRegistration();
	}
	
	public void unregisterBook(Book b){
		this.bookDao.unregisterBook(b);
	}

}
