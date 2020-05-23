package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class RunAsync {
	
	static ExecutorService ex = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) {
		testAsyncThenRunnable();
	}

	private static void testAsyncThenRunnable() {
		
		/* The runAsync method does not return anything */
		CompletableFuture.runAsync(() -> {
			System.out.println("I'll run in a separate thread");
			try {
				TimeUnit.SECONDS.sleep(2);
				Stream.iterate(0, i -> i + 1).limit(2).forEach(System.out::println);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, ex)
				/* then run another task on same thread without taking input from 
				 * previous chain */
		.thenRun(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				Stream.iterate(11, n -> n + 1).limit(2).forEach(System.out::println);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		/*This will again create a new thread (if we pass thread then from treadpool else from
		 * common Fork.Join pool and execute the task on that thread*/
		/*.thenRunAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				Stream.iterate(11, n -> n + 1).limit(2).forEach(System.out::println);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});*/
		
		ex.shutdown();
	}

}
