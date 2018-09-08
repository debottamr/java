
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
public class TryLockDemo implements Task {
	final ReentrantLock reentrantLock = new ReentrantLock();
	@Override
	public void performTask() {
		try {
			boolean flag = reentrantLock.tryLock(100, TimeUnit.MILLISECONDS);
			if (flag) {
			    try {
					System.out.println(Thread.currentThread().getName() +": Lock acquired.");
					System.out.println("Performing task...");
			    } finally {
					System.out.println(Thread.currentThread().getName() +": Lock released.");
					reentrantLock.unlock();
			    }
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
} 