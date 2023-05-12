package sbu.cs.Semaphore;

import java.sql.Date;
import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    Semaphore lock;
    public Operator(String name,Semaphore lock) {
        super(name);
        this.lock=lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            try{
                lock.acquire();
                System.out.println(this.getName()+" started...");
                Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
                sleep(500);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(this.getName()+" done .");
            lock.release();
        }
    }
}
