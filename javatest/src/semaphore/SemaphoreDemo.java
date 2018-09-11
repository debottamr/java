package semaphore;

public class SemaphoreDemo {
	 public static void main(String[] args) {
	  SharedCounter counter = new SharedCounter();
	  // Creating three threads
	  Thread t1 = new Thread(counter, "Thread-A");
	  Thread t2 = new Thread(counter, "Thread-B");
	  Thread t3 = new Thread(counter, "Thread-C");
	  t1.start();
	  t2.start();
	  t3.start();
	 }
	}

	class SharedCounter  implements Runnable{
	    private int c = 0;

	    // incrementing the value
	    public void increment() {
	        try {
	         // used sleep for context switching
	            Thread.sleep(10);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        c++;
	    }
	    // decrementing the value
	    public void decrement() {    
	        c--;
	    }

	    public int getValue() {
	        return c;
	    }
	    
	    @Override
	    public void run() { 
	        this.increment();
	        System.out.println("Value for Thread After increment- " + Thread.currentThread().getName()
	            + " " + this.getValue());
	        this.decrement();
	        System.out.println("Value for Thread at last " + Thread.currentThread().getName() 
	            + " " + this.getValue());
	        
	    }
	}
