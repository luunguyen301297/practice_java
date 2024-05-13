package org.example.multi_threading.synchronize;

public class Customer {

    private int balance = 1000;

    public Customer() {
        System.out.println("Tài khoản của bạn là " + balance);
    }

    public synchronized void withdraw(int amount) throws InterruptedException {
        System.out.println("Đang thực hiện giao dịch rút tiền " + amount + "...");
        while (balance < amount) {
            System.out.println("Không đủ tiền rút!!!");
            wait();
        }
        balance -= amount;
        System.out.println("Rút tiền thành công. Tài khoản hiện tại là " + balance);
    }

    public synchronized void deposit(int amount) {
        System.out.println("Đang thực hiện giao dịch nạp tiền " + amount + "...");
        balance += amount;
        System.out.println("Nạp tiền thành công. Tài khoản hiện tại là " + balance);
        notify();
    }

}
