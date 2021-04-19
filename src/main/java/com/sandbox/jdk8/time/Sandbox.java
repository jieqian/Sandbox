package com.sandbox.jdk8.time;

import java.time.Duration;
import java.time.Instant;

public class Sandbox {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());

        long init = System.currentTimeMillis();
        Thread.sleep(1);
        long finish = System.currentTimeMillis();
        System.out.println(finish - init);
    }
}
