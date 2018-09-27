package semaphore;

import java.util.Objects;
import java.util.concurrent.Semaphore;

public class NegativeSemaphore {

	public static void main(String[] args) {
		 Semaphore semaphore = new Semaphore(-2);

		    System.out.println(Objects.equals(-2, semaphore.availablePermits()));
		    semaphore.release();
		    System.out.println(Objects.equals(-1, semaphore.availablePermits()));
		    semaphore.release();
		    System.out.println(Objects.equals(0, semaphore.availablePermits()));
		    semaphore.release();
		    System.out.println(Objects.equals(1, semaphore.availablePermits()));
	}
}
