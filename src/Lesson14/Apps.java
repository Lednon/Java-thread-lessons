package Lesson14;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Topic: Interrupting Threads
 *
 * @author Bassey Oddy
 */
public class Apps {
    
    public static void main(String[] args) throws InterruptedException{
        
        System.out.println("Starting....");
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                
                
                for(int i=0; i<1E8; i++){
                    try {
                        /*
                        if(Thread.currentThread().isInterrupted()){
                        System.out.println("Thread interrupted.!");
                        break;
                        }*/
                        
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        System.out.println("Thread interrupted");
                        break;
                    }
                    
                    Math.sin(random.nextDouble());
                }
            }
        });
        
        t1.start();
        
        Thread.sleep(500);
        t1.interrupt();
        
        t1.join();
        
        System.out.println("Finished....");
    }
}
