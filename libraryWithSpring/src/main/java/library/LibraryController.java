package library;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.Book;
import domain.Magazine;
import domain.Publication;
import service.LibraryService;

@Component
public class LibraryController {
	LibraryService lservice;
	
	@Autowired
	public LibraryController(LibraryService lservice) {
		this.lservice = lservice;
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
			Integer toSwitch = Integer.parseInt(choice);
			switch (toSwitch) {
			case 1:
				lservice.listPublications();
				break;
			case 2:
				System.out.print("Enter book title: ");
				String bookName = sc.nextLine();
				System.out.println("Enter book ISBN: ");
				String bookISBN = sc.nextLine();
				lservice.registerBook(new Book(bookISBN, bookName));
				break;
			case 3:
				System.out.print("Enter magazine title: ");
				String magazineName = sc.nextLine();
				System.out.println("Enter magazine ISSN: ");
				String magazineISSN = sc.nextLine();
				lservice.registerMagazine(new Magazine(magazineISSN, magazineName));
				break;
			case 4:
				System.out.print("Unregister\t 1. Book, 2. Magazine\n");
				Integer unregisterChoice = Integer.parseInt(sc.nextLine());
				if (unregisterChoice == 1) {
					System.out.print("Enter book title: ");
					String book = sc.nextLine();
					Publication p = new Book();
					p.setTitle(book);
					lservice.unregisterPublication(p);
				} else if (unregisterChoice == 2) {
					System.out.print("Enter magazine title: ");
					String magazine = sc.nextLine();
					Publication p = new Magazine();
					p.setTitle(magazine);
					lservice.unregisterPublication(p);
				} else {
					throw new RuntimeException();
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
					lservice.updateRegistration(p1, p2);

				} else if (updateChoice == 2) {
					System.out.print("Enter magazine 1 title: ");
					String magazineN1 = sc.nextLine();
					System.out.println("Enter magazine 2 title: ");
					String magazineN2 = sc.nextLine();
					Publication p1 = new Magazine();
					p1.setTitle(magazineN1);
					Publication p2 = new Magazine();
					p1.setTitle(magazineN2);
					lservice.updateRegistration(p1, p2);
				} else {
					throw new RuntimeException();
				}
				break;
			default:
				System.out.println("Wrong number entered! ");
				continue;
			}
			sc.close();
		}

	}

	public void printMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("######\n");
		sb.append("1. List Publications\n");
		sb.append("2. Register Book\n");
		sb.append("3. Register Magazine\n");
		sb.append("4. Unregister Publication\n");
		sb.append("5. Update Registration\n");
		sb.append("######\n");
		sb.append("Your choice: ");

		System.out.println(sb.toString());
	}

}
