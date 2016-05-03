package com.librarySpring.control;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarySpring.domain.Book;
import com.librarySpring.domain.Magazine;
import com.librarySpring.domain.Publication;
import com.librarySpring.service.LibraryService;

@Component
public class LibraryController {
	private LibraryService libraryService;
	
	
	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	public void exec() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			printMenu();
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("end")) {
				System.out.println("Query ended");
				break;
			}
			int toSwitch = NumberUtils.toInt(choice, 10);
			switch (toSwitch) {
			case 1:
				libraryService.listPublications();
				break;
			case 2:
				System.out.print("Enter book title: ");
				String bookName = sc.nextLine();
				System.out.println("Enter book ISBN: ");
				String bookISBN = sc.nextLine();
				libraryService.registerBook(new Book(bookISBN, bookName));
				break;
			case 3:
				System.out.print("Enter magazine title: ");
				String magazineName = sc.nextLine();
				System.out.println("Enter magazine ISSN: ");
				String magazineISSN = sc.nextLine();
				libraryService.registerMagazine(new Magazine(magazineISSN, magazineName));
				break;
			case 4:
				System.out.print("Unregister\t 1. Book, 2. Magazine\n");
				int unregisterChoice = NumberUtils.toInt(sc.nextLine(),0);
				if (unregisterChoice == 1) {
					libraryService.listBooks();
					System.out.print("Enter book id: ");
					long id = NumberUtils.toLong(sc.nextLine(), 0);
					libraryService.unregisterBook(id);					
				} else if (unregisterChoice == 2) {
					libraryService.listMagazines();
					System.out.print("Enter magazine id: ");
					long id = NumberUtils.toLong(sc.nextLine(), 0);
					libraryService.unregisterMagazine(id);		
				}

				break;
			case 5:
				System.out.print("Update\t 1. Book, 2. Magazine\n");
				Integer updateChoice = Integer.parseInt(sc.nextLine());
				if (updateChoice == 1) {
					System.out.print("Enter book 1 title: ");
					String bookN1 = sc.nextLine();
					System.out.println("Enter book 2 title: ");
					String bookN2 = sc.nextLine();
					Publication p1 = new Book();
					p1.setTitle(bookN1);
					Publication p2 = new Book();
					p1.setTitle(bookN2);
					libraryService.updateRegistration(p1, p2);

				} else if (updateChoice == 2) {
					System.out.print("Enter magazine 1 title: ");
					String magazineN1 = sc.nextLine();
					System.out.println("Enter magazine 2 title: ");
					String magazineN2 = sc.nextLine();
					Publication p1 = new Magazine();
					p1.setTitle(magazineN1);
					Publication p2 = new Magazine();
					p1.setTitle(magazineN2);
					libraryService.updateRegistration(p1, p2);
				} else {
					throw new RuntimeException();
				}
				break;
			case 6:
				List<Book> books = libraryService.getListOfBooks();
				ListIterator<Book> lib = books.listIterator();
				while (lib.hasNext()){
					Book currentBook = lib.next();
					System.out.println(currentBook.toString());
				}
				break;
			case 7:
				List<Magazine> magazines = libraryService.getListOfMagazines();
				ListIterator<Magazine> lim = magazines.listIterator();
				while (lim.hasNext()){
					Magazine currentMagazine = lim.next();
					System.out.println(currentMagazine.toString());
				}
				break;
			default:
				System.out.println("Wrong number entered! ");
				continue;
			}
			
		}

		sc.close();
	}

	public void printMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("######\n");
		sb.append("1. List Publications\n");
		sb.append("2. Register Book\n");
		sb.append("3. Register Magazine\n");
		sb.append("4. Unregister Publication\n");
		sb.append("5. Update Registration\n");
		sb.append("6. List Books\n");
		sb.append("7. List Magazines\n");
		sb.append("type \"end\" to exit\n");
		sb.append("######\n");
		sb.append("Your choice: ");

		System.out.println(sb.toString());
	}

}
