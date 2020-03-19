package Lesson13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Topic: Callable and Future
 *These are two classes that enable you to get return result from your threads 
 * and they also allow your threads to throw exceptions
 *
 */
public class Apps {
    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();
        
        
        //to use the methods of future without returning a result; 
        //u provide a wild card(?) as the type to future then void to the callacble
        //then return a void call method.
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);
                
                if(duration > 2000){
                    throw new IOException("Sleeping for too long");
                }
                
                System.out.println("Starting ...");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Apps.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Finished.");
                
                return duration;
            }
        });
        
        executor.shutdown();
        try {
            System.out.println("Result is: "+future.get());
        } catch (InterruptedException ex) {
            Logger.getLogger(Apps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            
            IOException e = (IOException) ex.getCause();
            System.out.println(e.getMessage());
        }
    }
}
