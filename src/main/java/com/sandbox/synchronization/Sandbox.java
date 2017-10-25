package com.sandbox.synchronization;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Sandbox {
	
	public static void main(String[] args) throws InterruptedException{
		Counter counter = new Counter();
		CyclicBarrier barrier = new CyclicBarrier(100);
//		CountDownLatch countDownLatch = new CountDownLatch(100);
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		for(int i=0;i<100;i++){
			threadPool.submit(()->{
				try {
					barrier.await();
					counter.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		threadPool.awaitTermination(10000, TimeUnit.MICROSECONDS);
		threadPool.shutdown();
	}
}
