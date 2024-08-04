package org.example.design_pattern.null_object;

public class NullTax implements Tax {

    private String country = "UNKNOWN_COUNTRY";

    public NullTax(String country) {
        if (country != null && !country.isEmpty()) {
            this.country = country;
        }
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public double apply(double amount) {
        return amount;
    }

}
