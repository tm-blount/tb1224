package org.yankovic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yankovic.model.RentAgreementRecord;
import org.yankovic.service.RentAgreementService;
import org.yankovic.utilities.ValidationUtils;

@RestController
public class RentalAgreementController {
    @Autowired
    private RentAgreementService rentAgreementService;

    @GetMapping("/rental/displayAgreement/{toolId}")
    public RentAgreementRecord displayAgreement(
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

            return record;
        }

        return null;
    }
}
