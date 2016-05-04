package dataaccess;


import domain.Book;
import domain.Magazine;
import domain.Publication;

public interface PublicationDao {

	public void registerBook(Book b);
	
	public void registerMagazine(Magazine m);

	public void listPublications();
	
	public void updateRegistration(Long id, String title);
		
	public void unregisterPublication(Publication p);
	
	public boolean validateEntry(Publication p);
	
	public long getIdFromTitle(Publication p);

	public void unregisterBook(Book b);

	public void unregisterMagazine(Magazine m);

	public void listBooks();

	public void listMagazines();

	
}
