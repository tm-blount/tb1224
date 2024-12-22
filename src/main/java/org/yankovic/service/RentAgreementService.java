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

    /**
     * Create a RentAgreementRecord based on input
     *
     * @param discount      the discount, whole number
     * @param numDaysToRent number of days to rent
     * @param toolId        the id of the tool to rent
     * @param checkoutDate  the checkout date for the rental
     * @return a RentAgreementRecord representing all aspects of the rental
     */
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
