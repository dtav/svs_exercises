package dataaccess;

import domain.Book;
import domain.Magazine;
import domain.Publication;

public interface PublicationDao {

	public void registerBook(Book b);

	public void registerMagazine(Magazine m);

	public void listPublications();

	public void updateRegistration(Publication p1, Publication p2);

	public void unregisterPublication(Publication p);

	public boolean validateEntry(Publication p);

	public long getIdFromTitle(Publication p);

}
