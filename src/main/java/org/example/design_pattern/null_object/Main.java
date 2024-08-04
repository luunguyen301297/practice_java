package org.example.design_pattern.null_object;

@SuppressWarnings("SpellCheckingInspection")
public class Main {

    /**
        Null không phải là một Object, nó là một giá trị, các thao tác check null so sánh một Object với một value -> mất đi tính OOP
        Thay vì sử dụng giá trị null, ta trả về một Null Object thể hiện hành vi mặc định của đối tượng
    **/
    public static void main(String[] args) {
        final double price = 1000;
        applyCountryTaxToPrice(price, "Switzerland");
        applyCountryTaxToPrice(price, "Germany");
        applyCountryTaxToPrice(price, "Vietnam");
        applyCountryTaxToPrice(price, "Thailand");
        applyCountryTaxToPrice(price, null);
    }

    public static void applyCountryTaxToPrice(double price, String country) {
        Tax tax = TaxFactory.getTaxByCountry(country);
        System.out.println(tax.getCountry() + ": " + tax.apply(price));
    }

}
