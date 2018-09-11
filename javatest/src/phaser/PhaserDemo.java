package phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo {

    public static void main(String[] args) {
        Phaser ph = new Phaser(1);
        // Threads for first phase
        new FileReaderThread("thread-1", "file-1", ph);
        new FileReaderThread("thread-2", "file-2", ph);
        new FileReaderThread("thread-3", "file-3", ph);
        int curPhase;
        curPhase = ph.getPhase();
        // For main thread
        ph.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " completed");
        
        // This will be the second phase where 
        // threads are deregistered from the phaser
        curPhase = ph.getPhase();
        ph.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " completed");
        
        // Threads for third phase
        new QueryThread("thread-1", 40, ph);
        new QueryThread("thread-2", 40, ph);
        curPhase = ph.getPhase();
        ph.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " completed");
        // deregistering the main thread
        ph.arriveAndDeregister();
    }
}