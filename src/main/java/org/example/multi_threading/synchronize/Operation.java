package org.example.multi_threading.synchronize;

public class Operation {

    public static void main(String[] args) {
        final Customer customer = new Customer();

        Thread t1 = new Thread(() -> {
            try {
                customer.withdraw(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            customer.deposit(500);
            customer.deposit(3000);
        });
        t2.start();
    }

}
