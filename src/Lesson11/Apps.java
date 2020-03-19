package Lesson11;
  
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Topic: Deadlock 
 * How to solve it using the try-lock of the re-entrant lock class
 * 
 * solution:
 * The first way to handle a deadlock is to always lock your locks in the same order.
 * 
 * another way to do this is to use a method that acquires the lock in any order without
 * causing a deadlock
 */
public class Apps {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    
    public static void main(String[] args) throws InterruptedException{
        
        final Runner runner = new Runner();
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start(); 
        t2.start();
        
        t1.join();
        t2.join();
        
        runner.finnished();
    }
}
