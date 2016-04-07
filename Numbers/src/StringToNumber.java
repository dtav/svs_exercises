
public class StringToNumber {
	String[] input;
	int output;

	public StringToNumber(String[] input) {
		this.input = input;
	}

	public String[] getInput() {
		return input;
	}

	public void setInput(String[] input) {
		this.input = input;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public void convert() {

		String concatenation = new String("");
		
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
		
	}

}
