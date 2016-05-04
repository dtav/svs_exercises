package service;

import dataaccess.PublicationDao;
import domain.Book;
import domain.Magazine;
import domain.Publication;

public class LibraryService {

	private PublicationDao pubDao;

	public LibraryService(PublicationDao pubDao) {
		this.pubDao = pubDao;
	}

	public void registerBook(Book b) {
		this.pubDao.registerBook(b);
	}

	public void registerMagazine(Magazine m) {
		this.pubDao.registerMagazine(m);
	}

	public void listPublications() {
		this.pubDao.listPublications();
	}

	
	public void unregisterPublication(Publication p) {
		this.pubDao.unregisterPublication(p);
	}

	public boolean validateEntry(Publication p) {
		return this.pubDao.validateEntry(p);
	}

	public long getIdFromTitle(Publication p) {
		return this.pubDao.getIdFromTitle(p);
	}

	public void unregisterBook(Book b) {
		this.pubDao.unregisterBook(b);
	}

	public void unregisterMagazine(Magazine m) {
		this.pubDao.unregisterMagazine(m);
	}

	public void listBooks() {
		this.pubDao.listBooks();
	}

	public void listMagazines() {
		this.pubDao.listMagazines();
	}
	
	public void updateRegistration(Long id, String title){
		this.pubDao.updateRegistration(id, title);
	}
}
