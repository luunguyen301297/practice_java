package org.example.multi_threading.data_race;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutualExclusion {
    // ReentrantLock
    static final Lock LOCK = new ReentrantLock();
    static int COUNTER = 0;
    static void increaseCounter(int i) {
        LOCK.lock();
        LOCK.lock();
        ++ COUNTER;
        LOCK.unlock();
        LOCK.unlock();
    }

    // Atomic variable
    static final AtomicInteger COUNTER2 = new AtomicInteger(0);
    static void increaseCounter2(int i) {
        COUNTER2.incrementAndGet();
    }
}