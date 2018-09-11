package phaser;

import java.util.concurrent.Phaser;

class FileReaderThread implements Runnable {
    private String threadName;
    private String fileName;
    private Phaser ph;

    FileReaderThread(String threadName, String fileName, Phaser ph){
        this.threadName = threadName;
        this.fileName = fileName;
        this.ph = ph;
        ph.register();
        new Thread(this).start();
    }
    @Override
    public void run() {
        System.out.println("This is phase " + ph.getPhase());
        System.out.println("Reading file " + fileName + " thread " + threadName 
           + "parsing and storing to DB ");
        // Using await and advance so that all thread wait here
        ph.arriveAndAwaitAdvance();
        try {
            // Just for creating some delay, not 
            // actually required 
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Deregistering");
        ph.arriveAndDeregister();
    }
}
