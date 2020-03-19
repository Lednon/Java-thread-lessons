package Lesson12;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    private static Connection instance = new Connection();
    private int connections = 0;
    
    private Semaphore semaphore = new Semaphore(10);
    
    private Connection(){
        
    }
    
    public static Connection getInstance(){
        return instance;
    }
    
    public void connect(){
        try {
            semaphore.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doConnect();
        }finally{
            semaphore.release();
        }
    }
    
    public void doConnect(){
        
        synchronized(this){
            connections++;
            System.out.println("Current connections: "+connections);
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        synchronized(this){
            connections--;
        }
    }
}
