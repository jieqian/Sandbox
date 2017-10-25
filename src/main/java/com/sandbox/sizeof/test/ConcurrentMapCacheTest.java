package com.sandbox.sizeof.test;

import com.sandbox.sizeof.RamUsageEstimator;
import com.sandbox.sizeof.com.carrotsearch.cache.prototype.Cache;
import com.sandbox.sizeof.com.carrotsearch.cache.prototype.ConcurrentMapCacheManager;
import com.sandbox.sizeof.com.carrotsearch.cache.prototype.MemoryUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class ConcurrentMapCacheTest {
  static long loopCount = 100000;
  
  public static void main(String[] args) throws InterruptedException{
//    int mb = (int) ConcurrentMapCache.ONE_MB;
//    byte[] value = new byte[10];
//    ConcurrentMapCacheManager cm = new ConcurrentMapCacheManager();
//    Cache cache = cm.getCache();
//    cache.put("k1", value);
//    byte[] copy = cache.get("k1",byte[].class);
//    ValueWrapper vw = cache.get("k1");
//    System.out.println(vw.get().getClass());
    
    ConcurrentMapCacheManager cm = new ConcurrentMapCacheManager();
    Cache cache = cm.getCache();
    cache.estimateCapacity(1, MemoryUnit.GB);
    int workerCount = 10;
    CountDownLatch latch = new CountDownLatch(workerCount);
    
    long start = System.currentTimeMillis();
    List<Thread> threadList = new ArrayList<Thread>();
    for(int i=0; i<workerCount; i++){
      Thread t = new Thread(new Worker(latch,cache,loopCount * i));
      t.start();
      latch.countDown();
      threadList.add(t);
    }
    
    for(Thread t : threadList){
      t.join();
    }
    
    long elapse = System.currentTimeMillis() - start;
    System.out.println("elapse "+ elapse + " ms");
    
    long start2 = System.currentTimeMillis();
    System.out.println("cache capacity is " + RamUsageEstimator.humanSizeOf(cache));
    long estimateTime = System.currentTimeMillis() - start2;
    System.out.println("cache capacity estimate time " + estimateTime +" ms");
  }
  
  public static class Worker implements Runnable{
    Cache cache;
    long idx;
    CountDownLatch latch;
    
    public Worker(CountDownLatch latch, Cache cache, long idx){
      this.cache = cache;
      this.idx = idx;
      this.latch = latch;
    }

    @Override
    public void run() {
      try {
        latch.await();
        for(int i=0; i<loopCount; i++){
          cache.put(idx+i, new byte[1024]);
        }
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
    }
    
  }
}
