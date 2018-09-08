
public class Worker implements Runnable {
	private Task task;
	public Worker(Task task) {
		this.task = task;
	}
	@Override
	public void run() {
		task.performTask();
	}
} 