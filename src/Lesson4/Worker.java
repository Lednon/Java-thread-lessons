package Lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker {
    /*Topic: Using Multiple locks and synchronized code blocks in your application
    
    *This class illustrates two process that are ran cunccurrently.
    *
    *when the synchronized key word is used to avoid tampering of the data
    *passed into the two lists, this sysnchronized keyword accesses the intrinsic 
    *lock of the class Worker. This therefore restricts threads from running
    *the two seperate methods cuncurrently. A thread has to wait for the thread
    *(with the intrinsic lock) to finish executing before realising the loack unto te next thread
    
    *by creatig a lock object now, seperate threads can run individual processes
    *cuncurrently without having to wait for one another to release the inrinsic lock.
    
    *NOTE: no two threads can run the same process cuncurrently
    */
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private Random random = new Random();
    
    private Object lock1 = new Object();
    private Object lock2 = new Object();
            
    public void stageOne(){
        synchronized(lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        list1.add(random.nextInt(100));
    }
    
    public synchronized void stageTwo(){
        synchronized(lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        list2.add(random.nextInt(100));
    }
    
    public void process(){
        for(int i=0; i<1000; i++){
            stageOne();
            stageTwo();
        }
    }
    
    public void main(){
        System.out.println("Starting................");
        long startTime = System.currentTimeMillis();
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken: "+(endTime-startTime));
        System.out.println("List1 size: "+list1.size());
        System.out.println("List2 size: "+list2.size());
    }   
}
