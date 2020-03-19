package Lesson10;
 
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Topic: Re-Entrant Lock
 * An alternative to the synchronized keyword
 * 
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
