package com.librarySpring.dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarySpring.domain.Book;
import com.librarySpring.domain.Loan;
import com.librarySpring.domain.Magazine;
import com.librarySpring.domain.Member;
import com.librarySpring.domain.Membership;
import com.librarySpring.domain.Publication;
import com.librarySpring.util.Logger;

@Component
public class HibernateBookDao implements PublicationDao {
	SessionFactory sf;

	@Autowired
	public HibernateBookDao(SessionFactory sf) {
		this.sf = sf;
		Logger.log("Initialized session factory in HibernateBookDao:constructor");

	}

	// Id set not feasable in current hibernate connection setup
	// concerns registerBook / registerMagazine

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

	public void listBooks() {
		Logger.log("Listing publications in HibernateBookDao:listPublications");
		Session s = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Book");
			List<Book> results = q.list();

			for (Book b : results) {
				System.out.println(b.toString());
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
	}

	public void listMagazines() {
		Logger.log("Listing publications in HibernateBookDao:listPublications");
		Session s = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Magazine");
			List<Magazine> results = q.list();

			for (Magazine m : results) {
				System.out.println(m.toString());
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
	}

	// unregisters publication by id
	public void unregisterPublication(long id) {
		Logger.log("Unregistering publication in HibernateBookDao:unregisterPublication");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Publication p = new Publication();
			p.setId(id);
			s.delete(p);
			Logger.log("Successfull unregistering of publication: " + p.getTitle());
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

	// defunct
	public void updateRegistration(Publication p1, Publication p2) {
		Logger.log(
				"Updating publication registration in HibernateBookDao:updateRegistration(Publication p1, Publication p2)");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			long id = getIdFromTitle(p1);
			p1.setId(id);
			// unregisterPublication(p1);
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

	// redundancy in access
	// validate and getIdfromTitle can be one method, though it's more robust
	// this way

	// validates objects against persistent storage
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
				// Logger.log("INSIDE");
				// Logger.log("argument id: " + p.getId());
				// Logger.log("persistent id: " +
				// publicationInPersistence.getId());
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

	@Override
	public List<Publication> getListOfPublications() {
		Logger.log("Returning List<Publications> in HibernateBookDao:getListOfPublications()");
		Session s = null;
		List<Publication> results = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Publication");
			results = q.list();
			return results;
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		return results;

	}

	public List<Book> getListOfBooks() {
		Logger.log("Returning List<Book> in HibernateBookDao:getListOfBooks()");
		Session s = null;
		List<Book> results = new ArrayList<Book>();
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Book");
			results = q.list();
			return results;
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		return results;

	}

	@Override
	public List<Magazine> getListOfMagazines() {
		Logger.log("Returning List<Magazine> in HibernateBookDao:getListOfMagazines()");
		Session s = null;
		List<Magazine> results = new ArrayList<Magazine>();
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Magazine");
			results = q.list();
			return results;
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		return results;
	}

	// Book gets its title updated only
	// isbn is a unique field and should be implemented as such, soon :)
	@Override
	public void updateBook(Book b) {
		Logger.log("updating Book in HibernateBookDao:updateBook(Book b)");
		Session s = null;
		Transaction tx = null;
		if (b.getId() == null) {
			b.setId(getIdFromISBN(b));
		}
		s = this.sf.openSession();
		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(b);
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

	@Override
	public void updateMagazine(Magazine mag) {
		Logger.log("updating Magazine in HibernateBookDao:updateMagazine(Magazine mag)");
		Session s = null;
		Transaction tx = null;
		if (mag.getId() == null) {
			mag.setId(getIdFromISSN(mag));
		}
		s = this.sf.openSession();
		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(mag);
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

	@Override
	public long getIdFromISBN(Book b) {
		Logger.log("returning Id from ISBN in HibernateBookDao:getIdFromISBN(Book b)");
		Session s = null;
		Long idToReturn = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Book");
			List<Book> lib = new ArrayList<Book>();
			lib = q.list();
			ListIterator<Book> listIterBooks = lib.listIterator();
			while (listIterBooks.hasNext()) {
				Book currentBook = listIterBooks.next();
				if (currentBook.getIsbn().equals(b.getIsbn())) {
					idToReturn = currentBook.getId();
				}
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		return idToReturn;
	}

	@Override
	public long getIdFromISSN(Magazine m) {
		Logger.log("returning Id from ISSN in HibernateBookDao:getIdFromISSN(Magazine m)");
		Session s = null;
		Long idToReturn = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Magazine");
			List<Magazine> lim = new ArrayList<Magazine>();
			lim = q.list();
			ListIterator<Magazine> listIterMagazine = lim.listIterator();
			while (listIterMagazine.hasNext()) {
				Magazine currentMagazine = listIterMagazine.next();
				if (currentMagazine.getIssn().equals(m.getIssn())) {
					idToReturn = currentMagazine.getId();
				}
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		return idToReturn;

	}

	@Override
	public Book getBookByID(long id) {
		Logger.log("returning Book from ID in HibernateBookDao:getBookByID(long id)");
		List<Book> retreivedBooks = getListOfBooks();
		ListIterator<Book> lib = retreivedBooks.listIterator();
		while (lib.hasNext()) {
			Book currentBook = lib.next();
			if (currentBook.getId() == id) {
				Logger.log("Found a match");
				return currentBook;
			}
		}
		Logger.log("Didn't find a match");
		return null;
	}

	@Override
	public Magazine getMagazineByID(long id) {
		Logger.log("returning Magazine from ID in HibernateBookDao:getMagazineByID(long id)");
		List<Magazine> retreivedMagazines = getListOfMagazines();
		ListIterator<Magazine> lim = retreivedMagazines.listIterator();
		while (lim.hasNext()) {
			Magazine currentMagazine = lim.next();
			if (currentMagazine.getId() == id) {
				Logger.log("Found a match");
				return currentMagazine;
			}
		}
		Logger.log("Didn't find a match");
		return null;
	}

	@Override
	public void unregisterBook(long id) {
		Logger.log("Unregistering book in HibernateBookDao:unregisterBook");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Book b = new Book();
			b.setId(id);
			s.delete(b);
			Logger.log("Successfull unregistering of book: " + b.getTitle());
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

	@Override
	public void unregisterMagazine(long id) {
		Logger.log("Unregistering magazine in HibernateBookDao:unregisterMagazine");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Magazine m = new Magazine();
			m.setId(id);
			s.delete(m);
			Logger.log("Successfull unregistering of publication: " + m.getTitle());
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

	@Override
	public void saveMember(Member m) {
		Logger.log("Saving member in HibernateBookDao:saveMember");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(m);
			Logger.log("Successfull saving of member: " + m.getName());
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

	@Override
	public void makeLoan(Loan l) {
		Logger.log("Making a loan in HibernateBookDao:makeLoan");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(l);
			Logger.log("Successfull creation of loan by: " + l.getMember().getName() + " for publication: "
					+ l.getPub().getTitle());
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

	@Override
	public void saveMembership(Membership m) {
		Logger.log("Saving membership with member in HibernateBookDao:saveMembership");
		Session s = sf.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(m);
			Logger.log("Successfull creation of  "+m.getType() +" membership for: " + m.getMember().getName());
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

	@Override
	public void listMembers() {
		Logger.log("Listing Members in HibernateBookDao:listMembers");
		Session s = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Member");
			List<Member> results = q.list();

			for (Member m : results) {
				System.out.println(m.toString());
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void listMemberships() {
		Logger.log("Listing Memberships in HibernateBookDao:listMemberships");
		Session s = null;
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Membership");
			List<Membership> results = q.list();

			for (Membership m : results) {
				System.out.println(m.toString());
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		
	}

	@Override
	public Publication getPublicationByID(long id) {
		Logger.log("returning Publication from ID in HibernateBookDao:getPublicationByID(long id)");
		List<Publication> retreivedPublications = getListOfPublications();
		ListIterator<Publication> lip = retreivedPublications.listIterator();
		while (lip.hasNext()) {
			Publication currentPub = lip.next();
			if (currentPub.getId() == id) {
				Logger.log("Found a match");
				return currentPub;
			}
		}
		Logger.log("Didn't find a match");
		return null;
	}

	
	@Override
	public Member getMemberByID(long id) {
		Logger.log("returning Member from ID in HibernateBookDao:getMemberByID(long id)");
		List<Member> retreivedMembers = getListOfMembers();
		ListIterator<Member> iterateMembers = retreivedMembers.listIterator();
		while (iterateMembers.hasNext()) {
			Member currentMember = iterateMembers.next();
			if (currentMember.getId() == id) {
				Logger.log("Found a match");
				return currentMember;
			}
		}
		Logger.log("Didn't find a match");
		return null;
	}

	@Override
	public List<Member> getListOfMembers() {
		Logger.log("Returning List<Member> in HibernateBookDao:getListOfMembers()");
		Session s = null;
		List<Member> results = new ArrayList<Member>();
		try {
			s = this.sf.openSession();
			Query q = s.createQuery("from Member");
			results = q.list();
			return results;
		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			s.close();
		}
		return results;
		
	}

	@Override
	public long getMemberIdByName(String name) {
		Logger.log("returning Member id from name in HibernateBookDao:getMemberIdByName(String name)");
		List<Member> retreivedMembers = getListOfMembers();
		ListIterator<Member> iterateMembers = retreivedMembers.listIterator();
		while (iterateMembers.hasNext()) {
			Member currentMember = iterateMembers.next();
			if (currentMember.getName().equals(name)) {
				Logger.log("Found a match");
				return currentMember.getId();
			}
		}
		Logger.log("Didn't find a match");
		return 0;
	}

}
