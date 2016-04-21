package library;

import java.util.logging.Level;

import service.LibraryService;
import dataaccess.*;
import domain.Book;

public class Library {

	public static void main(String[] args) {
		
		//disabling log info for a while
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
		BookDao jdbcb = new JDBCBookDao();
		
		LibraryService ls = new LibraryService(jdbcb);
		ls.listBooks();
		
		//generating books
//		for (int i = 1; i<=10; i++){
//			String isbn = "isbn_" + i;
//			String title = "title_" +i ;
//			Book b = new Book(isbn, title);
//			ls.registerBook(b);
//			System.out.println("registered: " + i);
//		}
		
//		ls.listBooks();
		
		BookDao hibdao = new HibernateBookDao();
		
		
		LibraryService ls2 = new LibraryService(hibdao);
		Book b2 = new Book("hibISBN7", "hibTITLE7");
		ls2.registerBook(b2);
		
		ls.listBooks();
		
		
	}
	
}
