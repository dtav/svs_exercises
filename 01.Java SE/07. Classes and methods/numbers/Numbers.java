/*
 Exercise: Numbers
Create a class that takes words for the first 10 numbers (“zero” up to “nine”) and converts 
them into a single integer. Use a switch statement for the conversion and command-line 
arguments for the words.
Example:
"java Numbers one five zero“ should print out "150"
 */
package numbers;

public class Numbers {
	
	public static void main(String[] args) {
		
		if (args.length < 1){
			System.err.print("USAGE: java Numbers arg1 arg2 ...");
			return;
		}
		
		// using StringToNumber class
		StringToNumber stn = new StringToNumber(args);
		stn.convert();
		System.out.println("method 1: " + stn.getOutput());		
		
		// using static method from StringToNumberStatic class
		System.out.println("method 2: " + StringToNumberStatic.convert(args));
	}
}
