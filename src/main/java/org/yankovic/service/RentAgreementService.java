package org.yankovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yankovic.db.ToolRepository;
import org.yankovic.db.entities.Tool;
import org.yankovic.model.RentAgreementRecord;
import org.yankovic.model.RentalPricingRecord;
import org.yankovic.utilities.PricingCalculatorUtils;

@Service("rentAgreementService")
public class RentAgreementService {
    @Autowired
    private PricingCalculatorService pricingCalculatorService;

    @Autowired
    private ToolRepository toolRepository;

    public RentAgreementRecord createRentalAgreementForTool(int discount, int numDaysToRent, int toolId, String checkoutDate) {
        // Grab the tool, if possible
        Tool toRent = toolRepository.findById(toolId);

        if (toRent != null) {
            // Calculate the price
            RentalPricingRecord rentalPricingRecord =
                    pricingCalculatorService.getPricingForRental(toRent, discount, numDaysToRent, checkoutDate);

            // Create the RentAgreement
            return new RentAgreementRecord(
                    toRent,
                    rentalPricingRecord,
                    numDaysToRent,
                    PricingCalculatorUtils.formatDateString(checkoutDate),
                    PricingCalculatorUtils.formatDateString(checkoutDate).plusDays(numDaysToRent)
            );
        }

        return null;
    }
}
