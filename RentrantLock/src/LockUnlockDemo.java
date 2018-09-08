import java.util.concurrent.locks.ReentrantLock;

public class LockUnlockDemo implements Task {
	final ReentrantLock reentrantLock = new ReentrantLock();	
	@Override
	public void performTask() {
		assert !reentrantLock.isHeldByCurrentThread();
		assert reentrantLock.getHoldCount() == 0;

	    reentrantLock.lock();

	    try { 
	    	 System.out.println(Thread.currentThread().getName() + ": Lock acquired.");
	    	 System.out.println("Processing...");
	    	 Thread.sleep(2000);
	    } catch (InterruptedException e) {
	         e.printStackTrace();
	    } finally {
	    	 System.out.println(Thread.currentThread().getName() + ": Lock released.");
		 reentrantLock.unlock();
            }
	}
} 