package org.yankovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yankovic.db.ToolRepository;
import org.yankovic.db.entities.Tool;
import org.yankovic.model.RentAgreementRecord;
import org.yankovic.model.RentalPricingRecord;

@Service("rentAgreementService")
public class RentAgreementService {
    @Autowired
    private PricingCalculatorService pricingCalculatorService;

    @Autowired
    private ToolRepository toolRepository;

    // TODO intercept
    public RentAgreementRecord createRentalAgreementForTool(int discount, int numDaysToRent, int toolId) {
        // Grab the tool, if possible
        Tool toRent = toolRepository.findById(toolId);

        if (toRent != null) {
            // Calculate the price
            RentalPricingRecord rentalPricingRecord =
                    pricingCalculatorService.getPricingForRental(toRent);

            // Create the RentAgreement
            return new RentAgreementRecord(
                    toRent,
                    rentalPricingRecord.totalPrice(),
                    rentalPricingRecord.preDiscountCharge(),
                    3.00,
                    numDaysToRent,
                    discount,
                    0,
                    null,
                    null
            );
        }

        return null;
    }
}
