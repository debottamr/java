package threadpool.reject;

public class TaskOne implements Runnable {
	public TaskOne() {
		
	}
    @Override
    public void run() {
        System.out.println("Executing Task One");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}