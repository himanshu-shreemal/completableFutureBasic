package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestSimpleCF {

	static CompletableFuture<String> cf = new CompletableFuture();

	public static void main(String[] args) {
		try {
			System.out.println(testSimpleCF());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static String testSimpleCF() throws InterruptedException, ExecutionException {
		try {
			// We asked to thread to sleep for 1 seconds
			TimeUnit.SECONDS.sleep(1);

			// We have returned static result
			cf.complete("Jai");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return cf.get();
	}
}
