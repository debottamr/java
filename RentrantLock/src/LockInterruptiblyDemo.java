
import java.util.concurrent.locks.ReentrantLock;
public class LockInterruptiblyDemo implements Task{
	final ReentrantLock reentrantLock = new ReentrantLock();
	@Override
	public void performTask() {
	     try {
		   reentrantLock.lockInterruptibly();
		   //if it is not able to acquire lock because of other threads interrupts,
		   //it will throw InterruptedException and control will go to catch block.
		   try {
				System.out.println(Thread.currentThread().getName() +": Lock acquired.");
				System.out.println("Work on progress...");
			        Thread.sleep(2000);	
		   } finally {
				System.out.println(Thread.currentThread().getName() +": Lock released.");
				reentrantLock.unlock();
		   }
	     } catch (InterruptedException e) {
		   e.printStackTrace();
	     }
	}
} 