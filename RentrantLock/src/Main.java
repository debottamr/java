import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		//main1();
		main3();
	}

	private static void main1() {
		final int threadCount = 5;
		final ExecutorService service = Executors.newFixedThreadPool(threadCount);
		final Task task = new LockUnlockDemo();
		for (int i = 0; i < threadCount; i++) {
			service.execute(new Worker(task));
		}
		
		
		service.shutdown();
	}
	private static void main2() {
		final int threadCount = 5;
		final ExecutorService service = Executors.newFixedThreadPool(threadCount);
		final Task task = new TryLockDemo();
		for (int i = 0; i < threadCount; i++) {
			service.execute(new Worker(task));
		}
		
		
		service.shutdown();
	}
	
	private static void main3() {
		final int threadCount = 5;
		final ExecutorService service = Executors.newFixedThreadPool(threadCount);
		final Task task = new LockInterruptiblyDemo();
		for (int i = 0; i < threadCount; i++) {
			service.execute(new Worker(task));
		}
		
		
		service.shutdown();
	}
}