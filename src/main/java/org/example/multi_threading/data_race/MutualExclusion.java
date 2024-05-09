package org.example.multi_threading.data_race;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class MutualExclusion {

    // ReentrantLock
    static final Lock LOCK = new ReentrantLock();
    static int COUNTER = 0;

    static void increaseCounter() {
        LOCK.lock();
        try {
            ++COUNTER;
        } finally {
            LOCK.unlock();
        }
    }

    // Atomic variable
    static final AtomicInteger COUNTER2 = new AtomicInteger(0);

    static void increaseCounter2() {
        COUNTER2.incrementAndGet();
    }

    // main
    public static void main(String... args) throws Exception {
        final Runnable increaseCounterFunc = () -> IntStream
                .range(0, 100)
                .forEach(i -> MutualExclusion.increaseCounter());

        final Thread first = new Thread(increaseCounterFunc);
        final Thread second = new Thread(increaseCounterFunc);

        first.start();
        second.start();

        first.join();
        second.join();

        System.err.println(MutualExclusion.COUNTER);
    }

}
