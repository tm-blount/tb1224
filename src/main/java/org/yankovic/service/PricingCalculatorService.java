package org.yankovic.service;

import org.springframework.stereotype.Service;
import org.yankovic.db.entities.Tool;
import org.yankovic.db.entities.ToolType;
import org.yankovic.model.RentalPricingRecord;
import org.yankovic.utilities.PricingCalculatorUtils;

/**
 * The pricing calculator service that calculates all the required
 * numbers for the ${RentAgreementRecord}.
 */
@Service("pricingCalculatorService")
public class PricingCalculatorService {
    public RentalPricingRecord getPricingForRental(Tool tool, int discount, int numDaysToRent, String checkoutDate) {
        boolean isLaborDay = PricingCalculatorUtils.isLaborDay(checkoutDate);
        boolean isIndependenceDay;

        double preDiscountTotal;
        // TODO temp
        int chargeableDays = numDaysToRent;

        ToolType toolType = tool.getToolType();

        // TODO need to use data range to pick out holidays
        // TODO and weekday/end charges

        preDiscountTotal = toolType.getDailyCharge() * numDaysToRent;
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
