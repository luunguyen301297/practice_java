package org.example.multi_threading.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final int GET_TIME_OUT = 5;
    public static final int NUM_OF_TASK = 10;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<Integer> callable;
        Future<Integer> future;

        for (int i = 1; i <= NUM_OF_TASK; i++) {
            callable = new CallableWorker(i);
            future = executor.submit(callable);
            list.add(future);
        }

        int sum = 0;
        for (Future<Integer> f : list) {
            // notice the output delay in console because Future.get() waits for task to get completed
            int result = f.get(GET_TIME_OUT, TimeUnit.SECONDS);
            sum += result;

            System.out.println("Result: " + result);
            System.out.println("Is completed : " + f.isDone());
            System.out.println("Is canceled : " + f.isCancelled());
            System.out.println("---");
        }

        executor.shutdownNow();

        while (true) {
            if (executor.awaitTermination(GET_TIME_OUT * NUM_OF_TASK * 1000, TimeUnit.SECONDS)) break;
        }

        System.out.println("Finished all threads: ");
        System.out.println("Sum all = " + sum);
    }

}
