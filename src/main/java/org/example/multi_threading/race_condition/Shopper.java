package org.example.multi_threading.race_condition;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Shopper extends Thread {
    static int MASK_PACK_COUNT = 1;
    static CyclicBarrier BARRIER = new CyclicBarrier(6);

    Shopper(int i) {
        setName(i % 2 == 0 ? "Husband" : "Wife");
    }

    static void addMask(String threadName) {
        if ("husband".equalsIgnoreCase(threadName)) {
            synchronized (Shopper.class) {
                MASK_PACK_COUNT += 1;
                System.out.println("Husband adds 1 pack");
            }
            waitAtBarrier();
            return;
        }
        waitAtBarrier();
        synchronized (Shopper.class) {
            MASK_PACK_COUNT *= 3;
            System.out.println("Wife multiple 3 times");
        }
    }

    static void waitAtBarrier() {
        try {
            BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        addMask(getName());
    }
}