/*
Exercise: YearlyCalendar
Using the countDays() method from the DayCounter application, create an application that 
stores every date in a given year from January 1 to December 31 as Strings in an array and 
prints it out.
 */
package yearlyCounter;

import java.util.Scanner;
import dayCounter.DayCounter;

public class YearlyCounter {

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int godina = s.nextInt();
		s.close();

		int totalDays = 0;
		for (int i = 1; i <= 12; i++) {
			totalDays += DayCounter.countDays(i, godina);
		}
		System.out.println(totalDays);

		String[] siteDenovi = new String[totalDays];

		// format dd/mm/yyyy

		int index = 0;
		for (int mesec = 1; mesec <= 12; mesec++) {
			int daysInMonth = DayCounter.countDays(mesec, godina);
			for (int den = 1; den <= daysInMonth; den++) {
				String entry = den + "/" + mesec + "/" + godina;
				System.out.println(entry);
				siteDenovi[index] = entry;
				index++;
			}
		}
	}

}
