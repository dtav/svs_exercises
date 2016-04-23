package dataaccess;

import java.util.List;

import org.hibernate.*;
import org.hibernate.SessionFactory;

import util.Logger;

import domain.Book;
import domain.Magazine;
import domain.Publication;

import dataaccess.PublicationDao;

public class HibernateBookDao implements PublicationDao {
	SessionFactory sf;

	public HibernateBookDao(SessionFactory sf) {
		this.sf = sf;
		Logger.log("Initialized session factory in HibernateBookDao:constructor");

	}

	public void registerBook(Book b) {
		Logger.log("Registering book in HibernateBookDao:registerBook");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Book book = new Book(b.getIsbn(), b.getTitle());
			book.setIsbn(b.getIsbn());
			book.setTitle(b.getTitle());
			s.save(book);
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}

	}

	public void registerMagazine(Magazine m) {
		Logger.log("Registering magazine in HibernateBookDao:registerMagazine");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Magazine magazine = new Magazine(m.getIssn(), m.getTitle());
			magazine.setIssn(m.getIssn());
			magazine.setTitle(m.getTitle());
			s.save(magazine);
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}

	}

	public void listPublications() {
		Logger.log("Listing publications in HibernateBookDao:listPublications");
		Session s = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Publication");
			List<Publication> results = q.list();

			for (Publication p : results) {
				System.out.println(p.getTitle() + " id: " + p.getId());
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
	}

	public void updateRegistration(Book b1, Book b2) {
		Logger.log("Updating book registration in HibernateBookDao:updateRegistration(Book b1, Book b2)");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(b1);
			s.save(b2);
			tx.commit();

		} catch (RuntimeException re) {
			System.out.println("update registration Book b1, Book b2 runtime exception");
		} finally {
			try {
				s.close();
			} catch (RuntimeException re) {
				re.printStackTrace();
			}
		}
	}

	public void unregisterPublication(Publication p) {
		Logger.log("Unregistering publication in HibernateBookDao:unregisterPublication");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			if (validateEntry(p)){
				s.delete(p);
				Logger.log("Successfull unregistering of publication: " + p.getTitle());
			}
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}

	}

	public void updateRegistration(Magazine m1, Magazine m2) {
		Logger.log("Updating magazine registration in HibernateBookDao:updateRegistration(Magazine m1, Magazine m2)");
		// TODO Auto-generated method stub

	}

	public boolean validateEntry(Publication p) {
		Logger.log("Validating publication in HibernateBookDao:validateEntry");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
						
			Query q = s.createQuery("from Publication");
			List<Publication> results = q.list();
			boolean isPresentInDB = false;
			for (Publication publicationInPersistence : results) {
//				Logger.log("INSIDE");
//				Logger.log("argument id: " + p.getId());
//				Logger.log("persistent id: " + publicationInPersistence.getId());
				if (p.getId() == publicationInPersistence.getId()) {
					isPresentInDB = true;
				} else {
					
				}
				
			}
			return isPresentInDB;

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}

	}

	public long getIdFromTitle(Publication p) {
		Logger.log("Retreiving id from given publication title in HibernateBookDao:getIdFromTitle");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
						
			Query q = s.createQuery("from Publication");
			List<Publication> results = q.list();
			for (Publication publicationInPersistence : results) {
//				Logger.log("INSIDE");
//				Logger.log("argument id: " + p.getId());
//				Logger.log("persistent id: " + publicationInPersistence.getId());
				if (p.getTitle().equals(publicationInPersistence.getTitle())) {
					return publicationInPersistence.getId();
				} else {
					
				}
				
			}
			
			

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}
		return 0;
	}

}
