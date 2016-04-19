package quotientAndRemainder;

public class QuotientAndRemainder {
	int x;
	int y;

	public QuotientAndRemainder(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getQuotient(){
		return x/y;
	}
	
	public int getRemainder(){
		return x%y;
	}
	
	public void process(){
		int quotient = this.getQuotient();
		int remainder = this.getRemainder();
		printResult(quotient, remainder);
	}
	
	public void printResult(int quotient, int remainder){
		System.out.println(quotient + "\t" + remainder);
	}
	
	public static void main(String[] args) {
		QuotientAndRemainder calculation = new QuotientAndRemainder(10, 3);
		calculation.process();
		
		QuotientAndRemainder another_calculation = new QuotientAndRemainder(50, 17);
		another_calculation.process();
	}
}
