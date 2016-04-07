
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
