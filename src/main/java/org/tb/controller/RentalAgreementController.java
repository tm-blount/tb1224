package org.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tb.errorhandling.exceptions.DiscountOutOfRangeException;
import org.tb.errorhandling.exceptions.RentalDaysOutOfRangeException;
import org.tb.errorhandling.exceptions.ToolNotFoundException;
import org.tb.model.RentAgreementRecord;
import org.tb.service.RentAgreementService;
import org.tb.utilities.ValidationUtils;

@RestController
public class RentalAgreementController {
    @Autowired
    private RentAgreementService rentAgreementService;

    /**
     * Generate a rental agreement.
     * <br/>
     * Specs: print to console
     * Added: plain text to the page for more convenient debugging
     * Added: some error-handling/prettifying
     * <br/>
     * To force JSON: remove the produces element as well as the @ResponseBody
     * annotation. Best practice is to also change the return type to RentAgreementRecord,
     * but it should work without doing that.
     *
     * @param toolId        the id of the tool to rent
     * @param discount      the discount, whole number
     * @param numDaysToRent the number of days to rent
     * @param checkoutDate  the date the checkout occurs
     * @return a plain string representation of the rental agreement
     */
    @GetMapping(value = "/rental/displayAgreement/{toolId}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String displayAgreement(
            @PathVariable("toolId") int toolId,
            @RequestParam("discount") int discount,
            @RequestParam("numDaysToRent") int numDaysToRent,
            @RequestParam("checkoutDate") String checkoutDate
    ) {
        boolean valid = false;
        String errorMsg = "";

        // Validate
        try {
            valid = ValidationUtils.rentalAgreementIsValid(discount, numDaysToRent);
        } catch (DiscountOutOfRangeException | RentalDaysOutOfRangeException ex) {
            errorMsg = ex.getMessage();
        }

        // If valid, try to get the rent agreement record generated for this particular
        // request
        if (valid) {
            try {
                RentAgreementRecord record = rentAgreementService.createRentalAgreementForTool(
                        discount,
                        numDaysToRent,
                        toolId,
                        checkoutDate
                );

                System.out.println(record);

                return record.toString();
            } catch (ToolNotFoundException tnfex) {
                return "{\"Error: \"Tool not found\"}";
            }
        }
        else {
            return "{\"Error: \"" + errorMsg + "\"}";
        }
    }
}
