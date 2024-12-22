package org.yankovic.model;

import org.yankovic.db.entities.Tool;

import java.time.LocalDate;

public record RentAgreementRecord(
        Tool toolToRent,
        RentalPricingRecord pricingInfo,
        double totalPrice,
        double preDiscountCharge,
        double dailyRentalPrice,
        int rentalDayCount,
        int discountPercent,
        int chargeableDays,
        LocalDate checkOutDate,
        LocalDate dueDate
) {
    // Default agreement format
    @Override
    public String toString() {
        return "Tool code: " + toolToRent.getToolCode() + "\n" +
                "Tool type: " + toolToRent.getToolType().getToolName() + "\n" +
                "Tool brand: " + toolToRent.getToolBrand() + "\n" +
                "Rental days: " + rentalDayCount + "\n" +
                "Check out date: " + checkOutDate + "\n" +
                "Due date: " + dueDate + "\n" +
                "Daily rental charge: " + dailyRentalPrice + "\n" +
                "Charge days: " + chargeableDays + "\n" +
                "Pre-discount charge: " + preDiscountCharge + "\n" +
                "Discount percent: " + discountPercent + "\n" +
                "Discount amount: " + pricingInfo.discountAmount() + "\n" +
                "Final charge: " + totalPrice;
    }
}
