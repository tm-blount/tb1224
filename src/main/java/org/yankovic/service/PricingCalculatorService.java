package org.yankovic.service;

import org.springframework.stereotype.Service;
import org.yankovic.db.entities.Tool;
import org.yankovic.db.entities.ToolType;
import org.yankovic.model.RentalPricingRecord;

@Service("pricingCalculatorService")
public class PricingCalculatorService {
    // TODO add real logic
    public RentalPricingRecord getPricingForRental(Tool tool, int discount, int numDaysToRent) {
        ToolType toolType = tool.getToolType();
        double total = toolType.getDailyCharge() * numDaysToRent;

        return new RentalPricingRecord(
                total,
                // TODO fix me
                3,
                discount,
                toolType.getDailyCharge(),
                // TODO fix me
                numDaysToRent
        );
    }
}
