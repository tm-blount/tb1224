package org.yankovic.service;

import org.springframework.stereotype.Service;
import org.yankovic.db.entities.Tool;
import org.yankovic.model.RentalPricingRecord;

@Service("pricingCalculatorService")
public class PricingCalculatorService {
    public RentalPricingRecord getPricingForRental(Tool tool) {
        // TODO fixme
        return new RentalPricingRecord(
                3,
                3,
                3,
                3,
                3
        );
    }
}
