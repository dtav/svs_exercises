package numbers;

public class StringToNumberStatic {

	public static int convert(String[] input) {
		String concatenation = new String("");
		int output;

		for (int i = 0; i <= input.length - 1; i++) {

			switch (input[i]) {
			case "zero":
				concatenation += "0";
				break;
			case "one":
				concatenation += "1";
				break;
			case "two":
				concatenation += "2";
				break;
			case "three":
				concatenation += "3";
				break;
			case "four":
				concatenation += "4";
				break;
			case "five":
				concatenation += "5";
				break;
			case "six":
				concatenation += "6";
				break;
			case "seven":
				concatenation += "7";
				break;
			case "eight":
				concatenation += "8";
				break;
			case "nine":
				concatenation += "9";
				break;
			}
		}
		output = Integer.parseInt(concatenation);
		return output;
		
	}

}
