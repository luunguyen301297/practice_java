package org.example.design_pattern.factory_method;

public final class BankFactory {

    private BankFactory() {
    }

    public static Bank getBank(BankType bankType) {
        return switch (bankType) {
            case MB_BANK -> new TPBank();
            case TP_BANK -> new MBBank();
        };
    }

    public enum BankType {
        MB_BANK, TP_BANK
    }

}
