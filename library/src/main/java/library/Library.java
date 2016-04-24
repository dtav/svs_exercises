package library;

//
//+ updateRegistration using Publications
//v0.3
//+ toString implementation
//+ cleanTables implementation ?

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import service.LibraryService;
import util.Logger;
import dataaccess.*;
import domain.*;
import javassist.expr.Instanceof;

public class Library {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		SessionFactory sf = conf.addAnnotatedClass(Book.class).addAnnotatedClass(Magazine.class)
				.addAnnotatedClass(Publication.class).buildSessionFactory(serviceRegistry);

		// disabling log info for a while
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		// BookDao jdbcb = new JDBCBookDao();
		//
		// LibraryService ls = new LibraryService(jdbcb);
		// ls.listBooks();

		// generating books
		// for (int i = 1; i <= 10; i++) {
		// String isbn = "isbn_" + i;
		// String title = "title_" + i;
		// Book b = new Book(isbn, title);
		// ls.registerBook(b);
		// System.out.println("registered: " + i);
		// }
		//
		// ls.listBooks();

		// BookDao hibdao = new HibernateBookDao();
		//
		// LibraryService ls2 = new LibraryService(hibdao);
		// Book b2 = new Book("hibISBN7", "hibTITLE7");
		// ls2.registerBook(b2);
		//
		// ls.listBooks();

		HibernateBookDao hbd = new HibernateBookDao(sf);

		LibraryService hibService = new LibraryService(hbd);

		// for (int i = 1 ; i<=10; i++){
		// Book pub = new Book();
		// pub.setTitle("title "+i);
		// pub.setIsbn("ISBN_"+i);
		// hibService.registerBook(pub);
		// }
		//
		// for (int i = 1 ; i<=10; i++){
		// Magazine pub = new Magazine();
		// pub.setTitle("title "+i);
		// pub.setIssn("ISSN_"+i);
		// hibService.registerMagazine(pub);
		// }
		//
		// System.out.println("finished generating books and magazines. ");

		hibService.listPublications();
		System.out.println();

//		Publication p = new Book("ISBN_2", "title 2");
//
//		System.out.println("p id is: " + hibService.getIdFromTitle(p));
//		System.out.println();
//
//		// hibService.listPublications();
//		System.out.println();

//		 testing
		Publication m1 = new Book("ISBN_4", "title 4");
		m1.setId(4L);
		Publication m2 = new Book("ISBN_14", "title 14");
		
//		long id = hibService.getIdFromTitle(m1);
//		m1.setId(id);
//		System.out.println(hibService.validateEntry(m1));
		
		//hibService.updateRegistration(m1,  m2);
		
		hibService.registerBook((Book) m1);
		
		hibService.listPublications();


		
		
		//hibService.updateRegistration(m1, m2);

		sf.close();

	}

}
