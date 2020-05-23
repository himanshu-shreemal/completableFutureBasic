package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class BlockMainThread {

	public static void main(String[] args) {
		testAsyncRunnableBlockMainThread();
		System.out.println("Main thread Completed");
	}

	public static void testAsyncRunnableBlockMainThread() {
		try {
			CompletableFuture.runAsync(() -> {
				System.out.println("I'll run in a separate thread than the main thread.");
				try {
					TimeUnit.SECONDS.sleep(2);
					Stream.iterate(11, n -> ++n).limit(2).forEach(System.out::println);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * this .get() method will wait for the execution to complete once the execution
				 * is done main thread will further continue
				 */

			}).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
