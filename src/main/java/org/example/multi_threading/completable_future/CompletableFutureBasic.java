package org.example.multi_threading.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureBasic {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        System.out.println("Manually complete");
        completableFuture.complete(computeSomething());

        System.out.println("Get the result: " + completableFuture.get());

        //  có thể sử dụng getNow(valueIfAbsent) để lấy kết quả ngay lập tức (không phải chờ tới khi hoàn thành Future)
        //  nếu có result thì method này sẽ return result, nếu không sẽ return value mặc định (valueIfAbsent)
    }

    public static String computeSomething() throws InterruptedException {
        System.out.println("Computing ... ");
        Thread.sleep(3000);
        return "Future's Result";
    }

}
