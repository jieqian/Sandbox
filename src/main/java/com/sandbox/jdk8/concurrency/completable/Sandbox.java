package com.sandbox.jdk8.concurrency.completable;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class Sandbox {
    public static void main(String[] args) {
        MyCompletableFuture myCompletableFuture = new MyCompletableFuture();
        CompletableFuture<String> completableFuture;
        CompletableFuture<String> future;
        String result = "";
        try {
            completableFuture = myCompletableFuture.calculateAsync();
            result = completableFuture.get();
            System.out.println("calculateAsync => " + result);

            try {
                completableFuture = myCompletableFuture.calculateAsyncWithCancellation();
                result = completableFuture.get();
                System.out.println("calculateAsyncWithCancellation => " + result);
            }catch (CancellationException e) {
                e.printStackTrace();
            }

            future = CompletableFuture.supplyAsync(() -> "Hello");
            result = future.get();
            System.out.println("CompletableFuture With Encapsulated Computation Logic => " + result);

            completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
            future = completableFuture.thenApply(s -> s + " World");
            future.thenAccept(s -> System.out.println("Computation returned: " + s)).
                    thenRun(() -> System.out.println("Computation finished."));
            result = future.get();
            System.out.println("Processing Results of Asynchronous Computations => " + result);

            completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
            result = completableFuture.get();
            System.out.println("Combining Futures: thenCompose => " + result);

            completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                    .thenCombine(CompletableFuture.supplyAsync(
                            () -> " World"), (s1, s2) -> s1 + s2);
            result = completableFuture.get();
            System.out.println("Combining Futures: thenCombine => " + result);

            CompletableFuture<Void> emptyFuture = CompletableFuture.supplyAsync(() -> "Hello")
                    .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                            (s1, s2) -> System.out.println("Combining Futures: thenAcceptBoth => " + s1 + s2));

            result = myCompletableFuture.runningMultipleFuturesInParallel();
            System.out.println("Running Multiple Futures in Parallel => " + result);

            completableFuture = myCompletableFuture.handleException();
            result = completableFuture.get();
            System.out.println("Handle exception: handle => " + result);

            completableFuture = myCompletableFuture.handleExceptionally();
            result = completableFuture.get();
            System.out.println("Handle exception: exceptionally => " + result);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            myCompletableFuture.stop();
        }
    }
}
