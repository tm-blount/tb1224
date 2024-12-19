package org.yankovic.model;

import org.yankovic.db.entities.Tool;

import java.time.LocalDateTime;

public record RentAgreementRecord(
        Tool toolToRent,
        double totalPrice,
        double preDiscountCharge,
        double dailyRentalPrice,
        int rentalDayCount,
        int discountPercent,
        int chargeableDays,
        LocalDateTime checkOutDate,
        LocalDateTime dueDate
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
                // TODO I think this also needs to be passed in
                // TODO maybe just pass in the price record? Lol.
                "Discount amount: " + "TODO will be mathified" + "\n" +
                "Final charge: " + totalPrice;
    }
}
