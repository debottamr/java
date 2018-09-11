import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Incrementer {
    int count = 0;
    Lock lock = new ReentrantLock(); // Non-fair lock.
 
    public void incrementByFirstThread() {
        lock.lock();
        try {
            increment();
        } finally {
            lock.unlock();  // In any case lock must be released.
        }
    }
 
    public void incrementBySecondThread() {
        lock.lock();
        try{
            increment();
        } finally {
            lock.unlock();
        }
    }
 
    private void increment() {
        for(int i=0; i<1_000; i++) {
            count++;
        }
    }
 
    public void finished() {
        System.out.println("Finished with value: " + count);
    }
    public static void main(String[] args) {
        Incrementer incrementer = new Incrementer();
 
        Thread thread1 = new Thread(() -> {
           incrementer.incrementByFirstThread();
        });
 
        Thread thread2 = new Thread(() -> {
            incrementer.incrementBySecondThread();
        });
 
        thread1.start(); thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        incrementer.finished();
    }

}