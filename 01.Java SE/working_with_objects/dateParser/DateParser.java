/*
Exercise: DateParser
Create a program that turns a birthday in MM/DD/YYYY format (such as 12/04/2007) into 
three individual strings which it then displays.
 */

package dateParser;

public class DateParser {
	Date date;
	char delimiter;

	public DateParser(Date date) {
		this.date = date;
		this.delimiter = '/';
	}

	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}

	public void parse() {
		String[] splitDate = date.toString().split(String.valueOf(this.delimiter));
		System.out.println("Month: " + splitDate[0] + " Day: " + splitDate[1] + " Year: " + splitDate[2]);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static void main(String[] args) {
		Date some_date = new Date("12/04/2007");
		DateParser dp = new DateParser(some_date);
		dp.setDelimiter('/');
		dp.parse();

		Date some_other_date = new Date("08-25-2008");
		dp.setDate(some_other_date);
		dp.setDelimiter('-');
		dp.parse();

	}
}
