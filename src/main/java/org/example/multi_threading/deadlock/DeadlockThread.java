package org.example.multi_threading.deadlock;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeadlockThread implements Runnable {
    private final Object obj1;
    private final Object obj2;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " acquiring lock on " + obj1);

        synchronized (obj1) {
            System.out.println(name + " acquired lock on " + obj1);
            work();

            System.out.println(name + " acquiring lock on " + obj2);
            synchronized (obj2) {
                System.out.println(name + " acquired lock on " + obj2);
                work();
            }

            System.out.println(name + " released lock on " + obj2);
        }

        System.out.println(name + " released lock on " + obj1);
        System.out.println(name + " finished execution.");
    }

    private void work() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object obj1 = "obj1";
        Object obj2 = "obj2";
        Object obj3 = "obj3";

        Thread t1 = new Thread(new DeadlockThread(obj1, obj2), "t1");
        Thread t2 = new Thread(new DeadlockThread(obj2, obj3), "t2");
        Thread t3 = new Thread(new DeadlockThread(obj3, obj1), "t3");

        t1.start();
        Thread.sleep(1000);

        t2.start();
        Thread.sleep(1000);

        t3.start();
    }

}
