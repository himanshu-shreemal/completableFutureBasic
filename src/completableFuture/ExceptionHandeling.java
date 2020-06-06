package completableFuture;

import java.util.concurrent.CompletableFuture;

//There is a significant difference between exceptionally and handle 

public class ExceptionHandeling {

	// Change the value to see the difference from negative to any positive
	long profit = -900;

	public static void main(String[] args) {
		new ExceptionHandeling().testExceptionally();
		new ExceptionHandeling().testHandle();
	}

	private void testExceptionally() {

		// Exceptionally method will only execute when there is an exception in thread
		CompletableFuture.supplyAsync(() -> {
			if (profit < 0) {
				throw new IllegalArgumentException("Please don't say that it really hurts");
			} else
				return profit;
		}).exceptionally(ex -> {
			System.out.println(ex.getMessage());
			return 0L;
		}).thenAccept(result -> {
			System.out.println("Profit that can be broadcast is : " + result);
		});

	}

	// No Matter you get the exception or not but handle method will execute
	private void testHandle() {
		CompletableFuture.supplyAsync(() -> {
			if (profit < 0) {
				throw new IllegalArgumentException("Seems like you have loss");
			} else
				return profit;
		}).handle((res, ex) -> {
			if (ex != null) {
				System.out.println(" Handled : Oops! We have an exception - " + ex.getMessage());
				return "Unknown!";
			}
			System.out.println("Handle will always execute " + res);
			return res;
		}).thenAccept(result -> {
			System.out.println("Profit that can be broadcast from Handle is : " + result);
		});

	}
}
