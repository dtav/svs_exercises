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

	public void updateRegistration(Book b1, Book b2){
		this.pubDao.updateRegistration(b1, b2);
	}

	public void updateRegistration(Magazine m1, Magazine m2){
		this.pubDao.updateRegistration(m1, m2);
	}

	public void unregisterPublication(Publication p){
		this.pubDao.unregisterPublication(p);
	}

	public boolean validateEntry(Publication p){
		return this.pubDao.validateEntry(p);
	}
	
	public long getIdFromTitle(Publication p){
		return this.pubDao.getIdFromTitle(p);
	}

}
