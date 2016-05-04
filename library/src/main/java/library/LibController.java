package library;

import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import domain.Book;
import domain.Magazine;
import service.LibraryService;

public class LibController {
	LibraryService ls;
	
	public LibController(LibraryService ls) {
		this.ls = ls;
	}
	
	public void exec(){
		Scanner s = new Scanner(System.in);
		while(true){
			printMenu();
			String choice = s.nextLine();
			int choiceInt = NumberUtils.toInt(choice, 5);
			switch (choiceInt) {
			case 1:
				ls.listPublications();				
				break;
			case 2:
				System.out.println("Input book name");
				String nm = s.nextLine();
				System.out.println("Input book isbn");
				String isbn = s.nextLine();
				Book b = new Book(isbn, nm);
				ls.registerBook(b);
				break;
			case 3:
				System.out.println("Input magazine name");
				String mname = s.nextLine();
				System.out.println("Input magazine issn");
				String issn = s.nextLine();
				Magazine m = new Magazine(issn, mname);
				ls.registerMagazine(m);
				break;
			case 4:
				ls.listPublications();
				System.out.println("Input name of book");
				String bname = s.nextLine();
				System.out.println("Input isbn of book");
				String bisbn = s.nextLine();
				Book remBook = new Book(bisbn, bname);
				ls.unregisterBook(remBook);
				break;
			case 5:
				ls.listPublications();
				System.out.println("Input name of magazine");
				String magname = s.nextLine();
				System.out.println("Input issn of magazine");
				String magissn = s.nextLine();
				Magazine remMag = new Magazine(magissn, magname);
				ls.unregisterMagazine(remMag);
				break;
			case 6:
				System.out.println("Choose book id to change: ");
				ls.listBooks();
				String inpStr = s.nextLine();
				long id = NumberUtils.toLong(inpStr, 0);
				System.out.println("Choose new title: ");
				String newTitle = s.nextLine();
				ls.updateRegistration(id, newTitle);
				break;
				
			case 7:
				System.out.println("Exiting, bye");
				System.exit(0);
			default:
				break;
			}
		}
	}
	
	
	public void printMenu(){
		StringBuilder sb = new StringBuilder();
		sb.append("1. List publications\n");
		sb.append("2. Add book\n");
		sb.append("3. Add magazine\n");
		sb.append("4. Unregister book\n");
		sb.append("5. Unregister magazine\n");
		sb.append("6. Update book\n");
		sb.append("7. exit\n");
		sb.append("Your choice: ");
		System.out.println(sb.toString());
		
	}

}
