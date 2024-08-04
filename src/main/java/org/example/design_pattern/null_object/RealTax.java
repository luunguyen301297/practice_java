package org.example.design_pattern.null_object;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RealTax implements Tax {

    private String country;
    private double vat;

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public double apply(double amount) {
        return amount * vat;
    }

}
