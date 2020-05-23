package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

/*thenRun method does not accept values from previous chained future calculation.
Instead we should you either thenApply or thenAccept*/

public class SupplyAsync {

	static ExecutorService ex = Executors.newFixedThreadPool(5);

	public static void main(String[] args) {
		supplyAsync();
		System.out.println("Main thread compeleted");
	}

	private static void supplyAsync() {
		
		// Supply Async will run on a separate thread
		CompletableFuture.supplyAsync(() -> {
			System.out.println("I'll run on a separate thread");
			try {
				// Thread will sleep for 3 seconds
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Future is done with his work");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// return the task result
			return "Himanshu";

			/*
			 * uncomment below code to see what happen when we do not pass our own
			 * Thread pool, the result will never print as the main thread will close the 
			 * program.
			 */
			  /*}).thenAccept(result -> { System.out.println("Hello" + result); });*/
			
		
			/* This will get executed even after main thread done with work
			 * thenaccept() we use like terminate of work it does not return any thing*/	
		  /*}, ex).thenAccept(result -> { System.out.println("Hello " + result); });*/
		 
			/* Unline thenAccept method let say we want to further process the result,
			 * we can do that via thenApply()*/
		}, ex).thenApply(result -> {
			return "Hello Mr." +result;
		}).thenAccept(result -> System.out.println(result));
			
		/*
		 * Always shutdown the executor once all tasks are done else it will keep
		 * waiting and main thread will never stop
		 */
		ex.shutdown();
	}

}
