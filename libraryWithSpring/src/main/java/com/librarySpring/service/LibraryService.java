package com.librarySpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarySpring.dataaccess.PublicationDao;
import com.librarySpring.domain.Book;
import com.librarySpring.domain.Magazine;
import com.librarySpring.domain.Publication;

@Component
public class LibraryService {

	private PublicationDao pubDao;

	@Autowired
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

	public List<Publication> getListOfPublications() {
		return this.pubDao.getListOfPublications();
	}

	public List<Book> getListOfBooks() {
		return this.pubDao.getListOfBooks();
	}

	public List<Magazine> getListOfMagazines() {
		return this.pubDao.getListOfMagazines();
	}

	public Book getBookByID(long id) {
		return this.pubDao.getBookByID(id);
	}
	
	public Magazine getMagazineByID(long id) {
		return this.pubDao.getMagazineByID(id);
	}

	public void updateRegistration(Publication p1, Publication p2) {
		this.pubDao.updateRegistration(p1, p2);
	}

	public void updateBook(Book b) {
		this.pubDao.updateBook(b);
	}
	
	public void updateMagazine(Magazine mag) {
		this.pubDao.updateMagazine(mag);
		
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

	

	

}
