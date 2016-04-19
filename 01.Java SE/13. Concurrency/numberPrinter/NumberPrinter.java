package numberPrinter;
/*
 Create an application that prints out the numbers starting from one (1) up until a specified number.
 The print process should be interrupted if it fails to complete in a given time interval.
Specify the number and the time interval as arguments to the application
*/

public class NumberPrinter {

	public static void main(String[] args) throws InterruptedException {
		int input = Integer.parseInt(args[0]);
		int interval = Integer.parseInt(args[1]);
		PrintNum pn = new PrintNum(input);
		Thread t = new Thread(pn);

		t.start();
		t.join(interval);
		if (t.isAlive()) {
			t.interrupt();
		}
	}

	public static class PrintNum implements Runnable {
		int endNumber;

		PrintNum(int endNumber) {
			this.endNumber = endNumber;
		}

		public void run() {
			int i = 1;
			while (true) {
				System.out.println(i++);
				if (Thread.interrupted()) {
					System.out.println("interrupted");
					return;
				} else if (i == endNumber) {
					return;
				}

			}
		}

	}
}
