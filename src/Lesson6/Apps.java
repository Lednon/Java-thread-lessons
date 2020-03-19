package Lesson6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
*Topic: Countdown latches(Thread safe class)
*safe threads are threads you could safely access from without worrying about thread sychronisation
*
*A countdown latch lets you count down from a number specified. It lets one or 
*more threads wiat until the latch reaches the count of zero.
*one or more threads can countdown the latch and when finally equal zero, 
one or more threads wiaitng on the latch can now proceed.
*
*/
class processor implements Runnable{
    private CountDownLatch latch;
    
    public processor(CountDownLatch latch){
        this.latch = latch;
    }
    
    @Override
    public void run() {
        System.out.println("Starting........");
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(processor.class.getName()).log(Level.SEVERE, null, ex);
        }
        latch.countDown();
    }
    
}
public class Apps {
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for(int i=0; i<3; i++){
            executor.submit(new processor(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Apps.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("completed");
    }
}
