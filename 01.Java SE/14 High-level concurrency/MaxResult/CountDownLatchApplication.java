package MaxResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountDownLatchApplication {

    private static final int NUMBER_OF_CALCULATIONS = 10;

    public static void main(String[] args) throws Exception {
    	ArrayList<Integer> al = new ArrayList<>();
    	Future<Integer> complexCalculationFuture;
    	List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
    	
    	
        final CountDownLatch latch = new CountDownLatch(NUMBER_OF_CALCULATIONS);
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= NUMBER_OF_CALCULATIONS; i++) {
        	futureList.add(complexCalculationFuture = executorService.submit(new ComplexCalculation(i, latch)));
        	      	
        }
        Iterator i = futureList.iterator();
        while (i.hasNext()){
        	Future<Integer> fi = (Future<Integer>) i.next();
        	Integer in = fi.get();
        	al.add(in);
        }

        System.out.println("Waithing for all complex operations to finish...");
        latch.await();
        System.out.println("All complex operations finished.");
        
        Collections.sort(al);
        System.out.println(al.get(al.size()-1));

        executorService.shutdown();
    }
}
