package semaphore;

import java.util.concurrent.Semaphore;

public class SemConProdDemo {

    public static void main(String[] args) {
        
        Shared s = new Shared();
        // Producer and Consumer threads
        Thread t1 = new Thread(new SemProducer(s), "Producer");
        Thread t2 = new Thread(new SemConsumer(s), "Consumer");
        t1.start();
        t2.start();    
    }
}

// Shared class used by threads
class Shared{
    int i;
    // 2 semaphores 
    Semaphore sc = new Semaphore(0);
    Semaphore sp = new Semaphore(1);
    
    public void get(){
        try {
            // acquiring consumer semaphore
            sc.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Got - " + i);
        // releasing producer semaphore
        sp.release();
    }
    
    public void put(int i){
        try {
            // acquiring producer semaphore
            sp.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.i = i;
        System.out.println("Putting - " + i);
        // releasing consumer semaphore
        sc.release();
    }
}

// Producer threadpublic class SemConProdDemo {



class SemProducer implements Runnable{

    Shared s;
    SemProducer(Shared s){
        this.s = s;
    }
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            s.put(i);
        }
    }            
}

// Consumer thread
class SemConsumer implements Runnable{
    Shared s;
    SemConsumer(Shared s){
         this.s = s;
    }
    
    @Override
    public void run() {    
        for(int i = 0; i < 5; i++){
           s.get();                
        }
    }
}