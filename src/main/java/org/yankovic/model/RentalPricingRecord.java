package org.yankovic.model;

import java.math.RoundingMode;
import java.text.NumberFormat;

public record RentalPricingRecord(
        double totalPrice,
        double preDiscountCharge,
        double discountPercent,
        double dailyRentalPrice,
        int chargeableDays
) {
    private static final NumberFormat numberFormatter = NumberFormat.getInstance();

    // Ensure all the doubles return with
    // typical money precision (2 places)

    @Override
    public double totalPrice() {
        return Double.parseDouble(formatMoney(totalPrice));
    }

    @Override
    public double preDiscountCharge() {
        return Double.parseDouble(formatMoney(preDiscountCharge));
    }

    @Override
    public double dailyRentalPrice() {
        return Double.parseDouble(formatMoney(dailyRentalPrice));
    }

    private String formatMoney(double money) {
        numberFormatter.setMaximumFractionDigits(2);
        numberFormatter.setMinimumFractionDigits(2);
        numberFormatter.setRoundingMode(RoundingMode.HALF_UP);

        return numberFormatter.format(money);
    }
}
