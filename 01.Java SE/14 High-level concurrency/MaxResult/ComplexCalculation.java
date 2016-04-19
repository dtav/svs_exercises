package MaxResult;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;


public class ComplexCalculation implements Callable<Integer> {

	private Integer orderNumber;
	private CountDownLatch latch;
	
	public ComplexCalculation(Integer orderNumber, CountDownLatch latch) {
		this.orderNumber = orderNumber;
		this.latch = latch;
	
	}
	
    @Override
    public Integer call() throws Exception {
        System.out.println("Complex calculation started...");
        try {
        	Thread.sleep(3000);	
		} catch (InterruptedException e) {
		}
        System.out.println("Complex calculation completed.");
        latch.countDown();

        return new Random().nextInt(10 + 1);
    }

}
