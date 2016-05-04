package com.librarySpring.control;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarySpring.domain.Book;
import com.librarySpring.domain.Loan;
import com.librarySpring.domain.Magazine;
import com.librarySpring.domain.Member;
import com.librarySpring.domain.Membership;
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
			case 8:
				System.out.println("Enter name: ");
				String name = sc.nextLine();
				System.out.println("Enter email: ");
				String email = sc.nextLine();
				System.out.println("Choose membership type: 1. monthly, 2. yearly, 3. lifetime");
				String choiceMembershipStr = sc.nextLine();
				int memType=NumberUtils.toInt(choiceMembershipStr,1);
				String memTypeStr = "standard";
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				Timestamp startD = new Timestamp(c.getTimeInMillis());
				Timestamp endD;
			
				
				if (memType==1){
					memTypeStr = "monthly";
					c.add(Calendar.MONTH, 1);
				} else if (memType ==2) {
					memTypeStr = "yearly";
					c.add(Calendar.YEAR, 1);
				} else if (memType ==3){
					memTypeStr = "lifetime";
					c.add(Calendar.YEAR, 100);
				} 
				
				endD = new Timestamp(c.getTimeInMillis());
				Member mem = new Member();
				mem.setEmail(email);
				mem.setName(name);
				Membership membership = new Membership(startD, endD, memTypeStr, mem);
				
				libraryService.saveMembership(membership);
				break;
			case 9:
				libraryService.listMemberships();
				break;
			case 10:
				libraryService.listMembers();
				break;
			case 11:
				libraryService.listPublications();
				System.out.println("Input your membership name: ");
				String memName = sc.nextLine();
				System.out.println("Choose id of publication to loan: ");
				String idPub = sc.nextLine();
				long id = NumberUtils.toLong(idPub, 0);
				System.out.println("How many days will you loan the publication [ 1-30 ]");
				String daysStr = sc.nextLine();
				int daysInt = NumberUtils.toInt(daysStr, 7);
				
				Timestamp startOfLoan = new Timestamp(System.currentTimeMillis());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, daysInt);
				Timestamp endOfLoan = new Timestamp(calendar.getTimeInMillis());
				
				long memberId = libraryService.getMemberIdByName(memName);		
				Member m = libraryService.getMemberByID(memberId);			
				
				Publication p = libraryService.getPublicationByID(id);
				
				Loan l = new Loan(endOfLoan, startOfLoan, p, m);
				libraryService.makeLoan(l);
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
		sb.append("8. Register Member\n");
		sb.append("9. List Memberships\n");
		sb.append("10. List Members\n");
		sb.append("11. Loan a publication\n");
		sb.append("type \"end\" to exit\n");
		sb.append("######\n");
		sb.append("Your choice: ");

		System.out.println(sb.toString());
	}

}
