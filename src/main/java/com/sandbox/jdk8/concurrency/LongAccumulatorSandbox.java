package com.sandbox.jdk8.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

/**
 * Created by qianjie on 4/6/17.
 */
public class LongAccumulatorSandbox {
    public static void main(String[] args) throws InterruptedException {
        LongBinaryOperator op = (x, y) -> x + y;
        LongAccumulator accumulator = new LongAccumulator(op, 0L);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10)
                .forEach(i -> {
                    executor.submit(() -> {
                        accumulator.accumulate(i);
                        countDownLatch.countDown();
                    });
                });
        countDownLatch.await();
        System.out.println(accumulator.getThenReset());

        try {
            executor.shutdown();
            executor.awaitTermination(100000, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
