package dataaccess;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Book;
import domain.Magazine;
import domain.Publication;
import util.Logger;

@Repository
public class HibernateBookDao implements PublicationDao {
	SessionFactory sf;

	@Autowired
	public HibernateBookDao(SessionFactory sf) {
		this.sf = sf;
		Logger.log("Initialized session factory in HibernateBookDao:constructor");

	}

	//Id set not feasable in current hibernate connection setup
	//concerns registerBook / registerMagazine
	
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
				System.out.println(p.toString());
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
	}

	//has to validate that object has id.
	public void unregisterPublication(Publication p) {
		Logger.log("Unregistering publication in HibernateBookDao:unregisterPublication");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			long id = getIdFromTitle(p);
			p.setId(id);
			if (validateEntry(p)) {
				s.delete(p);
				Logger.log("Successfull unregistering of publication: " + p.getTitle());
				tx.commit();
			}

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}

	}

	public void updateRegistration(Publication p1, Publication p2) {
		Logger.log(
				"Updating publication registration in HibernateBookDao:updateRegistration(Publication p1, Publication p2)");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			long id = getIdFromTitle(p1);
			p1.setId(id);
			unregisterPublication(p1);
			if (p2 instanceof Book) {
				registerBook((Book) p2);
			} else if (p2 instanceof Magazine) {
				registerMagazine((Magazine) p2);
			} else
				throw new RuntimeException();

			// if (p1.getId() == null) {
			// Logger.log("Object has no id, finding id from title!");
			// long p1Id = getIdFromTitle(p1);
			// p1.setId(p1Id);
			// Logger.log("Object's id set to: " + p1Id);
			// }
			// if (validateEntry(p1)) {
			// s.delete(p1);
			// Logger.log("Successfull unregistering of publication: " +
			// p1.getTitle());
			// p2.setId(p1.getId());
			// Logger.log("Transfering same id to the replacement object: " +
			// p1.getId());
			// s.save(p2);
			// Logger.log("Object replaced successfully");
			// }
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
	
	//redundancy in access
	//validate and getIdfromTitle can be one method, though it's more robust this way
	
	
	//validates objects against persistent storage
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
					return true;
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
	
	// needed to support higher level methods (validate, unregister, update)
	public long getIdFromTitle(Publication p) {
		Logger.log("Retreiving id from given publication title in HibernateBookDao:getIdFromTitle");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();

			Query q = s.createQuery("from Publication");
			List<Publication> results = q.list();
			for (Publication publicationInPersistence : results) {
				// Logger.log("INSIDE");
				// Logger.log("argument id: " + p.getId());
				// Logger.log("persistent id: " +
				// publicationInPersistence.getId());
				if (p.getTitle().equals(publicationInPersistence.getTitle())) {
					Logger.log("\t Retreived id was: " + publicationInPersistence.getId());

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
