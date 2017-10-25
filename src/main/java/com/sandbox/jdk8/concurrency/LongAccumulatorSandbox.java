package com.sandbox.jdk8.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

/**
 * Created by qianjie on 4/6/17.
 */
public class LongAccumulatorSandbox {
    public static void main(String[] args) throws InterruptedException {
        LongBinaryOperator op = (x, y) -> x + y;
        LongAccumulator longAccumulator = new LongAccumulator(op, 0L);

        AtomicLong atomicLong = new AtomicLong();

        LongAdder longAdder = new LongAdder();

        int range = 10000000;
        CountDownLatch countDownLatch = new CountDownLatch(range);

        ExecutorService executor = Executors.newFixedThreadPool(500);
        long start = System.currentTimeMillis();
        IntStream.range(0, range)
                .forEach(i -> {
                    executor.submit(() -> {
                        longAccumulator.accumulate(1);
//                        atomicLong.incrementAndGet();
//                        longAdder.increment();
                        countDownLatch.countDown();
                    });
                });
        countDownLatch.await();
        System.out.println(longAccumulator.getThenReset());
//        System.out.println(atomicLong.get());
//        System.out.println(longAdder.longValue());

        System.out.println(System.currentTimeMillis() - start);
        try {
            executor.shutdown();
            executor.awaitTermination(100000, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
