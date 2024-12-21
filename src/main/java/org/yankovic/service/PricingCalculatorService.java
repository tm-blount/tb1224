package org.yankovic.service;

import org.springframework.stereotype.Service;
import org.yankovic.db.entities.Tool;
import org.yankovic.db.entities.ToolType;
import org.yankovic.model.RentalPricingRecord;
import org.yankovic.utilities.PricingCalculatorUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;


/*
 * The pricing calculator service that calculates all the required
 * numbers for the ${RentAgreementRecord}.
 */
@Service("pricingCalculatorService")
public class PricingCalculatorService {
    public RentalPricingRecord getPricingForRental(Tool tool, int discount, int numDaysToRent, String checkoutDate) {
        boolean isLaborDay = PricingCalculatorUtils.isLaborDay(checkoutDate);
        // TODO: implement
        boolean isIndependenceDay = false;

        // Get checkout date in LocalDate format for ranging further down
        LocalDate formattedCheckoutDate = PricingCalculatorUtils.formatDateString(checkoutDate);

        // Likely to decrease under most circumstances
        int chargeableDays = numDaysToRent;

        ToolType toolType = tool.getToolType();

        // Reduce chargeable days when it's a holiday and the tool type
        // is holiday chargeable
        if (!toolType.isHolidayCharge() &&
                (isLaborDay || isIndependenceDay)) {
            chargeableDays--;
        }

        // Get endDate for this rental because it makes the loop cleaner
        LocalDate endDate = formattedCheckoutDate.plusDays(numDaysToRent);

        // Iterate through all the days to rent and reduce chargeable
        // days according to business rules
        for (LocalDate startDate = formattedCheckoutDate;
             startDate.isBefore(endDate);
             startDate = startDate.plusDays(1)) {
            /*
                 Samples:

                   Renting: Tuesday, Wednesday, Thursday
                   3 chargeable days regardless under current specs

                   Renting: Thursday, Friday, Saturday
                   3 chargeable days IF tool type has weekend charge,
                    or if Sat is Jul 4th and there's a holiday charge
                   2 chargeable days IF tool type has weekend charge,
                    but it's Jul 4th and there is no holiday charge

                   Renting: Saturday, Sunday, Monday
                   3 chargeable days IF tool type has weekend charge, and it's not Labor Day
                    or tool type has no holiday charge
                   2 chargeable days IF tool type has weekend charge but not
                    holiday charge, and it's Labor Day
                   1 chargeable day IF tool type has no weekend charge, but has
                    holiday charge, and it's Labor Day
                   0 chargeable days IF tool type has no weekend charge, no
                    holiday charge, and it's Labor Day
             */
            // Hypothetically there can never be < 0 chargeable days but because
            // we are manipulating them here, it's better to be careful.
            if (chargeableDays > 0) {
                // TODO do we want to offload the weekend thing to the utils?
                // If this tool is free on weekends, reduce the chargeable days by up to two
                if (!toolType.isWeekendCharge() &&
                        ((startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) || startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                    chargeableDays--;
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
            // TODO fix me(???!)
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

