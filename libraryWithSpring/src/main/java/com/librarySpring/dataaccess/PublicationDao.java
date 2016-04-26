package com.librarySpring.dataaccess;

import java.util.List;

import com.librarySpring.domain.Book;
import com.librarySpring.domain.Magazine;
import com.librarySpring.domain.Publication;

public interface PublicationDao {

	public void registerBook(Book b);

	public void registerMagazine(Magazine m);

	public void listPublications();

	public List<Publication> getListOfPublications();

	public List<Book> getListOfBooks();

	public List<Magazine> getListOfMagazines();

	public void updateRegistration(Publication p1, Publication p2);

	public void unregisterPublication(Publication p);

	public boolean validateEntry(Publication p);

	public long getIdFromTitle(Publication p);

	public long getIdFromISBN(Book b);

	public long getIdFromISSN(Magazine m);

	public void updateBook(Book b);

	public Book getBookByID(long id);

	public void updateMagazine(Magazine mag);

	public Magazine getMagazineByID(long id);

}
