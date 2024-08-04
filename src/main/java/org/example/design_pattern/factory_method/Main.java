package org.example.design_pattern.factory_method;

@SuppressWarnings("SpellCheckingInspection")
public class Main {

    /**
        Cung cấp một method để tạo Object mà không cần phải xác định Class cụ thể của object được tạo ra
        Thay vì gọi constructor trực tiếp, client sẽ gọi phương thức của factory
        Cho phép các Class con thay đổi cách tạo đối tượng mà không thay đổi code của Class cha
    **/
    public static void main(String[] args) {
        Bank bank = BankFactory.getBank(BankFactory.BankType.MB_BANK);
        System.err.println(bank.getName());
    }

}
