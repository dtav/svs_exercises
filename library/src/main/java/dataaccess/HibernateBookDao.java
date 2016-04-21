package dataaccess;


import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import domain.Book;

public class HibernateBookDao implements BookDao {
	SessionFactory sf;
	
	private void SetSessionFactory(){
		Configuration conf = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		SessionFactory sf = conf.addAnnotatedClass(Book.class).buildSessionFactory(serviceRegistry);
		this.sf = sf;
	}
	
	public HibernateBookDao(){
		this.SetSessionFactory();
	}

	public void registerBook(Book b) {
		Session s = sf.openSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Book book = new Book();
			book.setIsbn(b.getIsbn());
			book.setTitle(b.getTitle());
			s.save(book);
			tx.commit();
			
		} catch (RuntimeException e){
			if (tx != null) { tx.rollback(); }
			throw e;
		} finally {
			s.close();
		}
		
		
	}

	public void listBooks() {
		// TODO Auto-generated method stub
		
	}

	public void updateRegistration() {
		// TODO Auto-generated method stub
		
	}

	public void unregisterBook(Book b) {
		// TODO Auto-generated method stub
		
	}

}
