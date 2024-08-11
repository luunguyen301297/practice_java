package org.example.multi_threading.callable;

import lombok.AllArgsConstructor;

import java.util.concurrent.*;

@AllArgsConstructor
public class CallableWorker implements Callable<Integer> {

    private final int num;

    @Override
    public Integer call() {
        return num * num;
    }

}
