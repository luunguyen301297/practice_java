package org.example.multi_threading.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureWithCallback {
    public static final int NUMBER = 5;

    static class MathUtil {
        public static int times(int number, int times) {
            return number * times;
        }

        public static int squared(int number) {
            return number * number;
        }

        public static boolean isEven(int number) {
            return number % 2 == 0;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("times");
            return MathUtil.times(NUMBER, 2);
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<Boolean> greetingFuture = future
                .thenApply(n -> {
                    System.out.println("squared");
                    return MathUtil.squared(n);
                })
                .thenApply(n -> {
                    System.out.println("isEvent");
                    return MathUtil.isEven(n);
                });

        System.out.println("Result : " + greetingFuture.get());
    }

}
