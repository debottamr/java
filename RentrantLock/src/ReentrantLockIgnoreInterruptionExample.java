
public class ReentrantLockIgnoreInterruptionExample {
	   public static void main(String[] args) throws InterruptedException {
	        ReentrantLockInterruptableExample lockInterruptable = new ReentrantLockInterruptableExample(false);
	        lockInterruptable.lockAndInterrupt();
	    }
}
