package org.example.multi_threading.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableWorker implements Callable<Integer> {

    private final int num;

    public CallableWorker(int num) {
        this.num = num;
    }

    public Integer call() throws Exception {
        Thread.sleep(3000);
        int result = num * num;
        System.out.println("Complete " + num);
        return result;
    }

}
