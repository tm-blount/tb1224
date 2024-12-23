package org.tb.service;

import org.springframework.stereotype.Service;
import org.tb.db.entities.Tool;
import org.tb.db.entities.ToolType;
import org.tb.model.RentalPricingRecord;
import org.tb.utilities.PricingCalculatorUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;


/*
 * The pricing calculator service that calculates all the required
 * numbers for the ${RentAgreementRecord}.
 */
@Service("pricingCalculatorService")
public class PricingCalculatorService {
    /**
     * Get the RentalPricingRecord for this request.
     *
     * @param tool          the Tool being rented
     * @param discount      the discount, whole number
     * @param numDaysToRent number of days to rent
     * @param checkoutDate  the date checked out
     * @return a RentalPricingRecord representing the financial aspects of the agreement
     */
    public RentalPricingRecord getPricingForRental(Tool tool, int discount, int numDaysToRent, String checkoutDate) {
        // Get checkout date in LocalDate format for ranging further down
        LocalDate formattedCheckoutDate = PricingCalculatorUtils.formatDateString(checkoutDate);

        // Likely to decrease under most circumstances
        int chargeableDays = numDaysToRent;

        ToolType toolType = tool.getToolType();

        // Get endDate for this rental because it makes the loop cleaner
        LocalDate endDate = formattedCheckoutDate.plusDays(numDaysToRent);

        // Iterate through all the days to rent and reduce chargeable
        // days according to business rules
        for (LocalDate startDate = formattedCheckoutDate;
             startDate.isBefore(endDate);
             startDate = startDate.plusDays(1)) {

            // Hypothetically there can never be < 0 chargeable days but because
            // we are manipulating them here, it's better to be careful.
            if (chargeableDays > 0) {
                DayOfWeek dof = startDate.getDayOfWeek();
                // If this tool is free on weekends, reduce the chargeable days by up to two
                if (!toolType.isWeekendCharge() &&
                        (PricingCalculatorUtils.dateIsWeekendDay(dof))) {
                    chargeableDays--;
                }

                if (!toolType.isHolidayCharge()) {
                    if (PricingCalculatorUtils.isLaborDay(startDate)) {
                        chargeableDays--;
                    }

                    // Making an assumption here
                    if (PricingCalculatorUtils.isIndependenceDayOnWeekend(startDate)) {
                        if (!toolType.isWeekendCharge()) {
                            chargeableDays--;
                        }
                    }
                    else if (PricingCalculatorUtils.isIndependenceDay(startDate)) {
                        if (!toolType.isWeekendCharge()) {
                            chargeableDays--;
                        }
                    }
                }
            }
        }

        double preDiscountTotal = toolType.getDailyCharge() * chargeableDays;
        double finalTotal = preDiscountTotal;

        // Don't bother with the math if the discount is 100%...
        if (discount == 100) {
            finalTotal = 0;
        }
        else {
            finalTotal = finalTotal - (((double) discount / 100) * preDiscountTotal);
        }

        return new RentalPricingRecord(
                finalTotal,
                preDiscountTotal,
                discount,
                toolType.getDailyCharge(),
                chargeableDays
        );
    }
}

