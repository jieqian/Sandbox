package com.sandbox.jdk8.concurrency.completable;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyCompletableFuture {

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public CompletableFuture<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        threadPool.submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public CompletableFuture<String> calculateAsyncWithCancellation() throws InterruptedException {
       CompletableFuture<String> completableFuture = new CompletableFuture<>();
        threadPool.submit(() -> {
            Thread.sleep(500);
            completableFuture.cancel(false);
            return null;
        });
       return completableFuture;
    }

    public String runningMultipleFuturesInParallel(){
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        return combined;
    }

    public CompletableFuture<String> handleException(){
        String name = null;
        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error: the name is null!");
            }
            return "Hello, " + name;
        }).handle((s, e) -> {
            System.out.println("Oops! We have an exception - " + e.getMessage());
            return s != null ? s : "Hello, Stranger!";});

        return completableFuture;
    }

    public CompletableFuture<String> handleExceptionally(){
        Integer age = -1;

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        return completableFuture;
    }

    public void stop(){
        threadPool.shutdownNow();
    }
}
