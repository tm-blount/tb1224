package org.yankovic.model;

import org.yankovic.db.entities.Tool;

import java.time.LocalDate;

public record RentAgreementRecord(
        Tool toolToRent,
        RentalPricingRecord pricingInfo,
        int rentalDayCount,
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
                "Daily rental charge: " + pricingInfo.dailyRentalPrice() + "\n" +
                "Charge days: " + pricingInfo.chargeableDays() + "\n" +
                "Pre-discount charge: " + pricingInfo.preDiscountCharge() + "\n" +
                "Discount percent: " + pricingInfo.discountPercent() + "\n" +
                "Discount amount: " + pricingInfo.discountAmount() + "\n" +
                "Final charge: " + pricingInfo.totalPrice();
    }
}
