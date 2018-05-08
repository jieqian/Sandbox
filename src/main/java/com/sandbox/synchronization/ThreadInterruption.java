package com.sandbox.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qianjie on 5/8/18.
 */
public class ThreadInterruption {

    private static class Worker extends Thread{
        Worker(){

        }

        @Override
        public void run()
        {
            try
            {
                while (!Thread.currentThread().isInterrupted())
                {
                    System.out.println("worker thread is processing ..........");
                    Thread.sleep(1000);


                }
            }
            catch (InterruptedException e)
            {
                System.out.println("Worker thread is interrupted!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Worker();
        worker.start();
        System.out.println("main thread sleeping 10s");
        Thread.sleep(10000);
        worker.interrupt();
//        worker.join();

    }
}
