package org.tb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
        if (ValidationUtils.rentalAgreementIsValid(discount, numDaysToRent)) {
            RentAgreementRecord record = rentAgreementService.createRentalAgreementForTool(
                    discount,
                    numDaysToRent,
                    toolId,
                    checkoutDate
            );

            System.out.println(record);

            return record.toString();
        }

        return null;
    }
}
