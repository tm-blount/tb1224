package org.yankovic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yankovic.model.RentAgreementRecord;
import org.yankovic.service.RentAgreementService;

@RestController
public class RentalAgreementController {
    @Autowired
    private RentAgreementService rentAgreementService;

    // TODO actually prob intercept here first
    @GetMapping("/rental/displayAgreement/{toolId}")
    public void displayAgreement(
            @PathVariable("toolId") int toolId,
            @RequestParam("discount") int discount,
            @RequestParam("numDaysToRent") int numDaysToRent
    ) {
        RentAgreementRecord record = rentAgreementService.createRentalAgreementForTool(
                discount,
                numDaysToRent,
                toolId
        );

        System.out.println(record);
    }
}
