package Lesson8;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Topic: Wait and Notify
 * thread waits have relinguished the lock key to another thread 
 * which the notifies the first thread to start after the enter button is clicked
 * 
 */
public class Apps {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    
    public static void main(String[] args) throws InterruptedException{
        
        final Processor processor = new Processor();
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start(); 
        t2.start();
        
        t1.join();
        t2.join();
    }
}
