package org.example.multi_threading.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final int GET_TIME_OUT = 5;
    public static final int NUM_OF_TASK = 10;

    static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        List<Future<Integer>> list = new ArrayList<>();
        Callable<Integer> callable;
        Future<Integer> future;

        for (int i = 1; i <= NUM_OF_TASK; i++) {
            callable = new CallableWorker(i);
            future = executor.submit(callable);
            list.add(future);
        }

        int sum = 0;
        for (Future<Integer> f : list) {
            int result = f.get(GET_TIME_OUT, TimeUnit.SECONDS);
            sum += result;
        }
        executor.shutdownNow();

        while (true) {
            if (executor.awaitTermination(GET_TIME_OUT * NUM_OF_TASK * 1000, TimeUnit.SECONDS)) break;
        }
        System.out.println("Sum all = " + sum);
    }

}
