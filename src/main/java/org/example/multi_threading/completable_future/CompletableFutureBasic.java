package org.example.multi_threading.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureBasic {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        System.out.println("Manually complete");
        completableFuture.complete(computeSomething());
        System.out.println("Get the result: " + completableFuture.get());
    }

    public static String computeSomething() throws InterruptedException {
        System.out.println("Computing ... ");
        Thread.sleep(3000);
        return "Future's Result";
    }

}
