import java.util.concurrent.locks.ReentrantLock;

public class AcquireLockRunnable implements Runnable {

	int id;
	boolean interruptable;
	ReentrantLock lock;

	public AcquireLockRunnable(ReentrantLock lock, int id) {
		this(lock, id, true);
	}

	public AcquireLockRunnable(ReentrantLock lock, int id, boolean interruptable) {
		this.lock = lock;
		this.id = id;
		this.interruptable = interruptable;
	}

	public void run() {
		print("Try lock");
		try {
			if (interruptable) {
				lock.lockInterruptibly();
			} else {
				lock.lock();
			}
		} catch (InterruptedException e) {
			print("Acquiring lock failed due to " + e);
			return;
		}
		print("Got lock(" + id + ")");
		try {
			try {
				if (id == 1) {
					Thread.sleep(3000);
				} else {
					Thread.sleep(2500);
				}
			} catch (InterruptedException e) {
				print("Sleep interrupted");
			}
		} finally {
			lock.unlock();
			print("Unlocked(" + id + ")");
		}
	}

	static void print(String p) {
		System.out.println(Thread.currentThread().getName() + ": " + p);
	}

}
