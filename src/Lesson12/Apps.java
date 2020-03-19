package Lesson12;
  
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Topic: Semaphores
 * Semaphore is an object that maintains a count(number of available permits of the semaphore)
 * 
 * the release method of the semaphore class increments the count of available permits.
 * the acquire method of the semaphore class decrements the count of available permits.
 * 
 * NOTE:
 * -acquire actually waits if there's no semaphore e.g(0) until a semaphore is release
 * -acquire and release of a semaphore with one permit is basically a lock
 * -The semaphore class has a fairness parameter e.g(new semaphore(number of permits,  boolean fairness) 
 * which facilitates the rule that which ever thread called acquire first, will be the first to get a 
 * permit when the permit becomes available. So if you don't have true there, that is not guaranteed.
 * usually you would want the fairness to be true since you don't want a thread to be left at the background 
 * while others are serviced or have to wait for so long while those waiting for a shorter time acquires a permit.
 * Although there are performance benefits to having it false!
 * 
 * USES:semaphores are actually used to control access to some resource
 */
public class Apps {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    
    public static void main(String[] args) throws InterruptedException{
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        for(int i=0; i<200; i++){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }
        executor.shutdown();
    }
}
