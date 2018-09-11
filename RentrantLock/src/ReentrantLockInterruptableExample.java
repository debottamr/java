
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockInterruptableExample {
    private static ReentrantLock lock = new ReentrantLock();
    private boolean interruptable;
 
    ReentrantLockInterruptableExample() {
        this(true);
    }
 
    ReentrantLockInterruptableExample(boolean interruptable) {
        this.interruptable = interruptable;
    }
 
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInterruptableExample lockInterruptable = new ReentrantLockInterruptableExample();
        lockInterruptable.lockAndInterrupt();
    }
 
    void lockAndInterrupt() throws InterruptedException {
        Thread firstThread = new Thread(new AcquireLockRunnable(lock, 1, interruptable), "Thread(1)");
        firstThread.start();
        Thread.sleep(2000);
        Thread[] others = new Thread[6];
        for (int i = 2; i < 8; i++) {
            others[i - 2]= new Thread(new AcquireLockRunnable(lock, i,
                    interruptable), "Thread(" + i + ")");
            others[i - 2].start();
        }
        AcquireLockRunnable.print("Interrupt threads");
        for (int i = 0; i < 6; i++) {
            Thread.sleep(500 * i / 2);
            AcquireLockRunnable.print("Interrupt " + others[i].getName());
            others[i].interrupt();
        }
    }
}